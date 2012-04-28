package egov.data.ibatis.repository;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author Yongkwon Park
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AutomaticSqlMapRepositoryTest {
	
	@Resource(name="sqlMapClient") SqlMapClient sqlMapClient;
	@Resource(name="anotherSqlMapClient") SqlMapClient anotherSqlMapClient;
	@Resource(name="testDomainObjectRepository") TestDomainObjectRepository repository; 

	@Test
	public void Repository가_올바르게_생성되었는가() {
		assertThat(repository, is(notNullValue()));
		assertThat(repository.getSqlMapClient(), not(anotherSqlMapClient));
		assertThat(repository.getSqlMapClient(), is(sqlMapClient));
	}

}
