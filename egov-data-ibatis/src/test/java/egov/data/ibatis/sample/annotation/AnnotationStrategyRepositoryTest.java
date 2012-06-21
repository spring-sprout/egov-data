package egov.data.ibatis.sample.annotation;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import egov.data.ibatis.sample.domain.SpringSprout;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class AnnotationStrategyRepositoryTest {
	
	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired AnnotationStrategyRepository repository;
	
	SpringSprout first, second, third;
	
	@Before
	public void setup() {
		first = new SpringSprout("miracle");
		second = new SpringSprout("daclouds");
		third = new SpringSprout("yunseok");
	}
	
	@Test
	public void testInsertMap() throws Exception {
		int before = count();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "test");
		repository.saveMap(map);
		
		assertThat(count(), is(before + 1));
	}
	
	@Test
	public void testInsert() {
		int before = count();
		
		registerTestSpringSprouts();
		
		assertThat(count(), is(before + 3));
	}
	
	@Test
	public void testIdInjected() throws Exception {
		repository.save(first);
		repository.save(second);
		repository.save(third);
		
		checkIdInjected();
	}
	
	@Test
	public void testFindByName() {
		registerTestSpringSprouts();
		
		SpringSprout springSprout = repository.selectByName(first.getName());
		
		assertThat(springSprout, is(first));
	}
	
	@Test
	public void testFindAll() {
		registerTestSpringSprouts();
		
		List<SpringSprout> springSprouts = repository.selectAll();
		
		assertThat(springSprouts, is(hasItems(first, second, third)));
	}
	
	@Test
	public void testUpdate() {
		registerTestSpringSprouts();
		
		SpringSprout miracle = repository.selectByName(first.getName());
		miracle.setName("update");
		
		int updateCount = repository.newOne(miracle);
		SpringSprout update = repository.selectByName(miracle.getName());
		
		assertThat(updateCount, is(1));
		assertThat(update, is(miracle));
	}
	
	@Test
	public void testDelete() {
		registerTestSpringSprouts();
		
		int before = count();
		
		SpringSprout miracle = repository.selectByName(first.getName());
		int deleteCount = repository.remove(miracle.getId());
		
		assertThat(deleteCount, is(1));
		assertThat(count(), is(before - deleteCount));
	}
	
	
	int count() {
		return jdbcTemplate.queryForInt("SELECT count(ID) FROM SPRINGSPROUT");
	}

	void registerTestSpringSprouts() {
		first = repository.save(first);
		second = repository.save(second);
		third = repository.save(third);
	}
	
	void checkIdInjected() {
		assertThat(first.getId(), is(notNullValue(Long.class)));
		assertThat(second.getId(), is(notNullValue(Long.class)));
		assertThat(third.getId(), is(notNullValue(Long.class)));
	}

}
