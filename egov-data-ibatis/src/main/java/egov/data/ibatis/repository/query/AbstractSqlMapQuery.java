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
package egov.data.ibatis.repository.query;

import java.util.Arrays;

import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.mapping.statement.StatementType;

import egov.data.ibatis.repository.annotation.*;

/**
 * iBatis statement를 호출하는 {@link RepositoryQuery} 추상 클래스로
 * 기본 전략을 구현하는 {@link DefaultSqlMapQuery}와 {@link Statement}를 처리하는
 * {@link AnnotationBasedSqlMapQuery}가 있다.
 * 
 * @author Yongkwon Park
 * @author Yunseok Choi
 */
public abstract class AbstractSqlMapQuery implements RepositoryQuery {
	
	protected final QueryMethod queryMethod;
	protected final SqlMapClientTemplate template;
	protected final StatementType statementType;
	
	public AbstractSqlMapQuery(QueryMethod queryMethod, SqlMapExecutorDelegate delegate, SqlMapClientTemplate sqlMapClientTemplate) {
		this.queryMethod = queryMethod;
		this.template = sqlMapClientTemplate;
		
		// iBatis에게 statement(query)의 유형(select, insert, update, delete) 얻기
		MappedStatement statement = delegate.getMappedStatement(queryMethod.getNamedQueryName());
		this.statementType = statement.getStatementType();
	}

	protected Object parametersParsing(Object[] parameters) {
		if(isEmpty(parameters)) {
			return null;
		} else if(isSingelParameters(parameters)) {
			return parameters[0];
		} else {
			throw new IllegalArgumentException("parameter 가 너무 많습니다. [" + Arrays.toString(parameters) + "]");
		}
	}
	
	private boolean isEmpty(Object[] parameters) {
        return parameters == null || parameters.length == 0 ? true : false; 
    }
    
    private boolean isSingelParameters(Object[] parameters) {
        return parameters != null && parameters.length == 1 ? true : false; 
    }
	
	@Override
	public QueryMethod getQueryMethod() {
		return queryMethod;
	}

}
