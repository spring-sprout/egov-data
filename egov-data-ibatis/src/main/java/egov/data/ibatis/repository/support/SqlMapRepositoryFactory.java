/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egov.data.ibatis.repository.support;

import java.io.*;
import java.lang.reflect.*;

import org.springframework.data.repository.core.*;
import org.springframework.data.repository.core.support.*;
import org.springframework.data.repository.query.*;
import org.springframework.orm.ibatis.*;
import org.springframework.util.*;

import com.ibatis.sqlmap.client.*;
import com.ibatis.sqlmap.engine.impl.*;

import egov.data.ibatis.repository.*;
import egov.data.ibatis.repository.annotation.*;
import egov.data.ibatis.repository.query.*;

/**
 * @author Yongkwon Park
 * @author Yunseok Choi
 */
@SuppressWarnings("deprecation")
public class SqlMapRepositoryFactory extends RepositoryFactorySupport {
	
	private final SqlMapClient sqlMapClient;
	private final SqlMapExecutorDelegate sqlMapExecutorDelegate;
	private final SqlMapClientTemplate sqlMapClientTemplate;
	
	public SqlMapRepositoryFactory(SqlMapClient sqlMapClient) {
		Assert.notNull(sqlMapClient, "SqlMapClient must not be null!");
		
		this.sqlMapClient = (SqlMapClientImpl) sqlMapClient;
		this.sqlMapClientTemplate = new SqlMapClientTemplate(sqlMapClient);
		
		if(ExtendedSqlMapClient.class.isAssignableFrom(sqlMapClient.getClass())) {
			this.sqlMapExecutorDelegate = ((ExtendedSqlMapClient) sqlMapClient).getDelegate();
		} else if(hasSqlMapExecutorDelegate(sqlMapClient)) {
			Field field = findSqlMapExecutorDelegate(sqlMapClient);
			field.setAccessible(true);
			
			this.sqlMapExecutorDelegate = (SqlMapExecutorDelegate) ReflectionUtils.getField(field, sqlMapClient); 
		} else {
			throw new IllegalArgumentException("not found SqlMapExecutorDelegate in SqlMapClient.");
		}
	}

	@Override
	public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected Object getTargetRepository(RepositoryMetadata metadata) {
		return new SimpleSqlMapRepository(sqlMapClient);
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return SqlMapRepository.class;
	}
	
	@Override
	protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key) {
		return new SimpleSqlMapQueryLookupStrategy(sqlMapExecutorDelegate, sqlMapClientTemplate);
	}
	
	private boolean hasSqlMapExecutorDelegate(SqlMapClient sqlMapClient) {
		return findSqlMapExecutorDelegate(sqlMapClient) != null;
	}
	
	private Field findSqlMapExecutorDelegate(SqlMapClient sqlMapClient) {
		Class<?> searchType = sqlMapClient.getClass();
		while(!Object.class.equals(searchType) && searchType != null) {
			Field[] fields = searchType.getDeclaredFields();
			for(Field field : fields) {
				if(SqlMapExecutorDelegate.class.isAssignableFrom(field.getType()))
					return field;
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
	
	
	class SimpleSqlMapQueryLookupStrategy implements QueryLookupStrategy {
		
		private final SqlMapExecutorDelegate delegate;
		private final SqlMapClientTemplate template;
		
		public SimpleSqlMapQueryLookupStrategy(SqlMapExecutorDelegate delegate, SqlMapClientTemplate sqlMapClientTemplate) {
			this.delegate = delegate;
			this.template = sqlMapClientTemplate;
		}
		
		@Override
		public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, NamedQueries namedQueries) {
			Class<?> repositoryInterface = metadata.getRepositoryInterface();
			
			QueryMethod queryMethod = null;
			queryMethod = chooseQueryMethodStrategy(method, metadata, repositoryInterface);
			
			return new SqlMapQuery(queryMethod, delegate, template);
		}

		private QueryMethod chooseQueryMethodStrategy(Method method, RepositoryMetadata metadata, Class<?> repositoryInterface) {
			QueryMethod queryMethod;
			if (repositoryInterface.isAnnotationPresent(Namespace.class) || method.isAnnotationPresent(Statement.class)) {
				queryMethod = new AnnotationBasedSqlMapQueryMethod(method, metadata);
			} else {
				queryMethod = new QueryMethod(method, metadata);
			}
			return queryMethod;
		}
		
	}

}
