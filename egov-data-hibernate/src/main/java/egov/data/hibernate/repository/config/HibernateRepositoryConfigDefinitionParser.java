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

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.repository.config.AbstractRepositoryConfigDefinitionParser;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author Keesun Baik
 */
public class HibernateRepositoryConfigDefinitionParser
		extends AbstractRepositoryConfigDefinitionParser<SimpleHibernateRepositoryConfiguration, HibernateRepositoryConfiguration> {

	private static final Class<?> PAB_POST_PROCESSOR = PersistenceAnnotationBeanPostProcessor.class;
	private static final Class<?> PET_POST_PROCESSOR = PersistenceExceptionTranslationPostProcessor.class;
	private static final String DEFAULT_TRANSACTION_MANAGER_BEAN_NAME = "transactionManager";
	private static final String DEFAULT_SESSION_FACTORY_BEAN_NAME = "sessionFactory";


	@Override
	protected SimpleHibernateRepositoryConfiguration getGlobalRepositoryConfigInformation(Element element) {
		return new SimpleHibernateRepositoryConfiguration(element);
	}

	@Override
	protected void postProcessBeanDefinition(HibernateRepositoryConfiguration ctx, BeanDefinitionBuilder builder, BeanDefinitionRegistry registry, Object beanSource) {
		String transactionManagerRef = StringUtils.hasText(ctx.getTransactionManagerRef()) ? ctx.getTransactionManagerRef()
				: DEFAULT_TRANSACTION_MANAGER_BEAN_NAME;
		builder.addPropertyValue(DEFAULT_TRANSACTION_MANAGER_BEAN_NAME, transactionManagerRef);

		String sessionFactoryRef = StringUtils.hasText(ctx.getSessionFactoryRef()) ? ctx.getSessionFactoryRef()
				: DEFAULT_SESSION_FACTORY_BEAN_NAME;
		builder.addPropertyValue(DEFAULT_SESSION_FACTORY_BEAN_NAME, sessionFactoryRef);
	}

	@Override
	protected void registerBeansForRoot(BeanDefinitionRegistry registry, Object source) {

		super.registerBeansForRoot(registry, source);

		if (!hasBean(PET_POST_PROCESSOR, registry)) {

			AbstractBeanDefinition definition = BeanDefinitionBuilder.rootBeanDefinition(PET_POST_PROCESSOR)
					.getBeanDefinition();

			registerWithSourceAndGeneratedBeanName(registry, definition, source);
		}

		if (!hasBean(PAB_POST_PROCESSOR, registry)) {

			AbstractBeanDefinition definition = BeanDefinitionBuilder.rootBeanDefinition(PAB_POST_PROCESSOR)
					.getBeanDefinition();

			registerWithSourceAndGeneratedBeanName(registry, definition, source);
		}
	}

}