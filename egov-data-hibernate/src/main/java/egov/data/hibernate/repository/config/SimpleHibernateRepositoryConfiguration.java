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
package egov.data.hibernate.repository.config;

import org.springframework.data.repository.config.AutomaticRepositoryConfigInformation;
import org.springframework.data.repository.config.ManualRepositoryConfigInformation;
import org.springframework.data.repository.config.RepositoryConfig;
import org.w3c.dom.Element;

/**
 * @author Keesun Baik
 */
public class SimpleHibernateRepositoryConfiguration extends
		RepositoryConfig<HibernateRepositoryConfiguration, SimpleHibernateRepositoryConfiguration> {

	private static final String FACTORY_CLASS = "egov.data.hibernate.repository.support.HibernateRepositoryFactoryBean";
	private static final String SESSION_FACTORY_REF = "session-factory-ref";

	/**
	 * @param repositoriesElement
	 */
	public SimpleHibernateRepositoryConfiguration(Element repositoriesElement) {
		super(repositoriesElement, FACTORY_CLASS);
	}

	@Override
	protected HibernateRepositoryConfiguration createSingleRepositoryConfigInformationFor(Element element) {
		return new ManualHibernateRepositoryConfigInformation(element, this);
	}

	@Override
	public HibernateRepositoryConfiguration getAutoconfigRepositoryInformation(String interfaceName) {
		return new AutomaticHibernateRepositoryConfigInformation(interfaceName, this);
	}

	@Override
	public String getNamedQueriesLocation() {
		return "classpath*:META-INF/hibernate-named-queries.properties";
	}

	public String getSessionFactoryRef() {
		return getSource().getAttribute(SESSION_FACTORY_REF);
	}

	private static class AutomaticHibernateRepositoryConfigInformation extends
			AutomaticRepositoryConfigInformation<SimpleHibernateRepositoryConfiguration> implements HibernateRepositoryConfiguration {

		public AutomaticHibernateRepositoryConfigInformation(String interfaceName, SimpleHibernateRepositoryConfiguration parent) {
			super(interfaceName, parent);
		}

		@Override
		public String getSessionFactoryRef() {
			return getParent().getSessionFactoryRef();
		}
	}

	private static class ManualHibernateRepositoryConfigInformation extends
			ManualRepositoryConfigInformation<SimpleHibernateRepositoryConfiguration> implements HibernateRepositoryConfiguration {

		public ManualHibernateRepositoryConfigInformation(Element element, SimpleHibernateRepositoryConfiguration parent) {
			super(element, parent);
		}

		@Override
		public String getSessionFactoryRef() {
			return getAttribute(SESSION_FACTORY_REF);
		}
	}

}
