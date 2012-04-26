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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

import egov.data.ibatis.sample.domain.SpringSprout;
import egov.data.ibatis.sample.repository.SpringSproutRepository;

/**
 * Integration test for transactional behaviour of repository operations.
 * 
 * @author Jong-il Seok
 *
 */
@ContextConfiguration
public class TransactionalRepositoryTests extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	SpringSproutRepository repository;
	
	@Autowired
	DelegatingTransactionManager transactionManager;
	
	@Before
	public void setUp() throws Exception {
		transactionManager.resetCount();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void simpleManipulationgOperation() throws Exception {
		repository.saveAndFlush(new SpringSprout("daclouds"));
		assertThat(transactionManager.getTransactionRequests(), is(1));
	}
	
	@Test
	public void unannotatedFinder() throws Exception {
		
		repository.findByName("daclouds");
		assertThat(transactionManager.getTransactionRequests(), is(0));
	}
	
	@Test
	public void invokeTransactionalFinder() throws Exception {
		
		repository.findOne(1L);
		assertThat(transactionManager.getTransactionRequests(), is(1));
	}
	
	@Test
	public void invokeRedeclareMethod() throws Exception {
		
		repository.findOne(1L);
		assertFalse(transactionManager.getDefinition().isReadOnly());
	}
	
	public static class DelegatingTransactionManager implements PlatformTransactionManager {
		
		private PlatformTransactionManager txManager;
		private int transactionRequests;
		private TransactionDefinition definition;
		
		public DelegatingTransactionManager(PlatformTransactionManager txManager) {
			
			this.txManager = txManager;
		}
		
		@Override
		public void commit(TransactionStatus status) throws TransactionException {
			
			txManager.commit(status);
		}
		
		@Override
		public TransactionStatus getTransaction(TransactionDefinition definition) {
			
			this.transactionRequests++;
			this.definition = definition;
			
			return txManager.getTransaction(definition);
		}
		
		public int getTransactionRequests() throws TransactionException {
			
			return transactionRequests;
		}
		
		public TransactionDefinition getDefinition() {
			
			return definition;
		}
		
		public void resetCount() {
			this.transactionRequests = 0;
			this.definition = null;
		}
		
		public void rollback(TransactionStatus status) throws TransactionException {
			
			txManager.rollback(status);
		}
	}

}