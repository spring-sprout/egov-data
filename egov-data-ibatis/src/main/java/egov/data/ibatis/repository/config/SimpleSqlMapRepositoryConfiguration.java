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
package egov.data.ibatis.repository.config;

import org.springframework.data.repository.config.AutomaticRepositoryConfigInformation;
import org.springframework.data.repository.config.ManualRepositoryConfigInformation;
import org.springframework.data.repository.config.RepositoryConfig;
import org.w3c.dom.Element;

/**
 * @author Yongkwon Park
 */
public class SimpleSqlMapRepositoryConfiguration extends RepositoryConfig<SqlMapRepositoryConfiguration, SimpleSqlMapRepositoryConfiguration> {

	private static final String DEFAULT_FACTORY_CLASS = "egov.data.ibatis.repository.support.SqlMapRepositoryFactoryBean";
	private static final String SQLMAP_CLIENT_REF = "sqlmap-client-ref";
	
	public SimpleSqlMapRepositoryConfiguration(Element repositoriesElement) {
		super(repositoriesElement, DEFAULT_FACTORY_CLASS);
	}

	@Override
	protected SqlMapRepositoryConfiguration createSingleRepositoryConfigInformationFor(Element element) {
		return new ManualSqlMapRepositoryConfigInformation(element, this);
	}
	
	@Override
	public SqlMapRepositoryConfiguration getAutoconfigRepositoryInformation(String interfaceName) {
		return new AutomaticSqlMapRepositoryConfigInformation(interfaceName, this);
	}

	/*
	 * iBatis에서 사용할 필요가 없을거 같은데... null or "" 반환시 예외가 발생되기 때문에 우선 임의 값을 설정해 둠.
	 * @see org.springframework.data.repository.config.CommonRepositoryConfigInformation#getNamedQueriesLocation()
	 */
	@Override
	public String getNamedQueriesLocation() {
		return "classpath*:META-INF/ibatis-named-queries.properties";
	}

	public String getSqlMapClientRef() {
		return getSource().getAttribute(SQLMAP_CLIENT_REF);
	}
	
	private static class AutomaticSqlMapRepositoryConfigInformation extends
		AutomaticRepositoryConfigInformation<SimpleSqlMapRepositoryConfiguration> implements SqlMapRepositoryConfiguration {

		public AutomaticSqlMapRepositoryConfigInformation(String interfaceName, SimpleSqlMapRepositoryConfiguration parent) {
			super(interfaceName, parent);
		}

		@Override
		public String getSqlMapClientRef() {
			return getParent().getSqlMapClientRef();
		}
		
	}

	private static class ManualSqlMapRepositoryConfigInformation extends
		ManualRepositoryConfigInformation<SimpleSqlMapRepositoryConfiguration> implements SqlMapRepositoryConfiguration {

		public ManualSqlMapRepositoryConfigInformation(Element element, SimpleSqlMapRepositoryConfiguration parent) {
			super(element, parent);
		}
		
		@Override
		public String getSqlMapClientRef() {
			return getAttribute(SQLMAP_CLIENT_REF);
		}

	}	

}
