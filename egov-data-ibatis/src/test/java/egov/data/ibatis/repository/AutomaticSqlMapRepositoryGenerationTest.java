package egov.data.ibatis.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yongkwon Park
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AutomaticSqlMapRepositoryGenerationTest {
	
	@Autowired TestDomainObjectRepository repository; 

	@Test
	public void Repository가_올바르게_생성되었는가() {
		assertNotNull(repository);
		assertNotNull(repository.getSqlMapClient());
	}

}
