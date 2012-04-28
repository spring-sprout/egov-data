package egov.data.ibatis.repository;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author Yongkwon Park
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SqlMapRepositoryTest {
	
	@Resource ApplicationContext applicationContext;
	
	@Resource(name="sqlMapClient") SqlMapClient sqlMapClient;
	@Resource(name="anotherSqlMapClient") SqlMapClient anotherSqlMapClient;
	
	TestAutomaticRepository automaticRepository; 
	TestManualRepository manualRepository;
	TestDomainObjectRepository customRepository;
	
	@Test
	public void AutomaticRepository가_올바르게_생성되었는가() {
		automaticRepository = applicationContext.getBean(TestAutomaticRepository.class);
		assertThat(automaticRepository, is(notNullValue()));
		assertThat(automaticRepository.getSqlMapClient(), not(anotherSqlMapClient));
		assertThat(automaticRepository.getSqlMapClient(), is(sqlMapClient));
	}
	
	@Test
	public void TestManualRepository가_올바르게_생성되었는가() {
		manualRepository = applicationContext.getBean(TestManualRepository.class);
		assertThat(manualRepository, is(notNullValue()));
		assertThat(manualRepository.getSqlMapClient(), not(sqlMapClient));
		assertThat(manualRepository.getSqlMapClient(), is(anotherSqlMapClient));
	}
	
	@Test
	public void CustomRepository가_올바르게_생성되었는가() {
		customRepository = applicationContext.getBean(TestDomainObjectRepository.class);
		assertThat(customRepository, is(notNullValue()));
		assertThat(customRepository.alwaysTrue(), is(true));
	}
	
	@Test
	public void SqlMapRepository는_생성되어서는_안된다() {
		@SuppressWarnings("rawtypes")
		Map<String, SqlMapRepository> repositories =  applicationContext.getBeansOfType(SqlMapRepository.class);
		
		assertThat(repositories.size(), is(3));
	}

}
