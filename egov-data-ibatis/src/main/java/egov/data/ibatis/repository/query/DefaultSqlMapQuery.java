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

import org.springframework.data.repository.query.*;
import org.springframework.orm.ibatis.*;

import com.ibatis.sqlmap.engine.impl.*;
import com.ibatis.sqlmap.engine.mapping.statement.*;

/**
 * {@link AbstractsSqlMapQuery}를 상속한 기본 전략 구현체
 * 
 * @author Yongkwon Park
 *
 */
public class DefaultSqlMapQuery extends AbstractSqlMapQuery {

	public DefaultSqlMapQuery(QueryMethod queryMethod, SqlMapExecutorDelegate delegate, SqlMapClientTemplate sqlMapClientTemplate) {
		super(queryMethod, delegate, sqlMapClientTemplate);
	}
	
	@Override
	public Object execute(Object[] parameters) {
		
		Object parameterObject = parametersParsing(parameters);
		
		if(StatementType.SELECT.equals(statementType)) {
			if(QueryMethod.Type.SINGLE_ENTITY.equals(queryMethod.getType())) {
	            return template.queryForObject(queryMethod.getNamedQueryName(), parameterObject);
	        }
	        else if(QueryMethod.Type.COLLECTION.equals(queryMethod.getType())) {
	            return template.queryForList(queryMethod.getNamedQueryName(), parameterObject);
	        }
		} else if(StatementType.INSERT.equals(statementType)) {
			template.insert(queryMethod.getNamedQueryName(), parameterObject);
			return parameterObject;
		} else if(StatementType.UPDATE.equals(statementType)) {
			return template.update(queryMethod.getNamedQueryName(), parameterObject);
		} else if(StatementType.DELETE.equals(statementType)) {
			return template.delete(queryMethod.getNamedQueryName(), parameterObject);
		}
		
		throw new UnsupportedOperationException();
	}

}
