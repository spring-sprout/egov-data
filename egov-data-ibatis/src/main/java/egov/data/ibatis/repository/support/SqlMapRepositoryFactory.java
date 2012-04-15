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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;

import egov.data.ibatis.repository.SimpleSqlMapRepository;
import egov.data.ibatis.repository.query.SqlMapQuery;

/**
 * @author Yongkwon Park
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
		return metadata.getRepositoryInterface();
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
			return new SqlMapQuery(new QueryMethod(method, metadata), delegate, template);
		}
		
	}

}
