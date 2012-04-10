/*
 * Copyright 2011 the original author or authors.
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
package egov.data.hibernate.repository.support;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author Keesun Baik
 */
public class HibernateRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends
		TransactionalRepositoryFactoryBeanSupport<T, S, ID> {

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected RepositoryFactorySupport doCreateRepositoryFactory() {
		return new HibernateRepositoryFactory(sessionFactory);
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(sessionFactory, "SessionFactory must not be null!");
		super.afterPropertiesSet();
	}
}
