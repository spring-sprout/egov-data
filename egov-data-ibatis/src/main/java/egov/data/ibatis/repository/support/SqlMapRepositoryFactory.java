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

import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapClient;

import egov.data.ibatis.repository.SimpleSqlMapRepository;

/**
 * @author Yongkwon Park
 */
public class SqlMapRepositoryFactory extends RepositoryFactorySupport {
	
	private final SqlMapClient sqlMapClient;
	
	public SqlMapRepositoryFactory(SqlMapClient sqlMapClient) {
		Assert.notNull(sqlMapClient, "SqlMapClient must not be null!");
		this.sqlMapClient = sqlMapClient;
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

}
