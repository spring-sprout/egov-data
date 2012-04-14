package egov.data.ibatis.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import egov.data.ibatis.sample.repository.SpringSproutRepository;

/**
 * @author Yongkwon Park
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class SqlMapRepositoryTest {
	
	@Autowired SpringSproutRepository repository; 

	@Test
	public void Repository가_올바르게_생성되었는가() {
		assertNotNull(repository);
		assertNotNull(repository.getSqlMapClient());
	}

}
