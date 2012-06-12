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
package egov.data.ibatis.repository.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.data.repository.config.AbstractRepositoryConfigDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import egov.data.ibatis.repository.support.SqlMapRepositoryFactoryBean;

/**
 * @author Yongkwon Park
 */
public class SqlMapRepositoryConfigDefinitionParser extends 
	AbstractRepositoryConfigDefinitionParser<SimpleSqlMapRepositoryConfiguration, SqlMapRepositoryConfiguration> {
	
	@Override
	protected SimpleSqlMapRepositoryConfiguration getGlobalRepositoryConfigInformation(Element element) {
		return new SimpleSqlMapRepositoryConfiguration(element);
	}
	
	@Override
	protected void postProcessBeanDefinition(SqlMapRepositoryConfiguration ctx, BeanDefinitionBuilder builder, BeanDefinitionRegistry registry, Object beanSource) {
		// SqlMapRepositoryFactoryBean에 sqlMapClient 주입
		if(StringUtils.hasText(ctx.getSqlMapClientRef())) {
			builder.addPropertyReference(SqlMapRepositoryFactoryBean.DEFAULT_SQLMAP_CLIENT_NAME, ctx.getSqlMapClientRef());
		} else {
			builder.addPropertyReference(SqlMapRepositoryFactoryBean.DEFAULT_SQLMAP_CLIENT_NAME
									   , SqlMapRepositoryFactoryBean.DEFAULT_SQLMAP_CLIENT_NAME);
		}
		
		/* TODO Transaction 관련 개발 필요
		if(StringUtils.hasText(ctx.getTransactionManagerRef())) {
			builder.addPropertyValue(TxUtils.DEFAULT_TRANSACTION_MANAGER, ctx.getTransactionManagerRef());
		} else {
			builder.addPropertyValue(TxUtils.DEFAULT_TRANSACTION_MANAGER, TxUtils.DEFAULT_TRANSACTION_MANAGER);
		}
		*/		
	}
	
//	@Override
//	protected void registerBeansForRoot(BeanDefinitionRegistry registry, Object source) {
//		super.registerBeansForRoot(registry, source);
//	}

}