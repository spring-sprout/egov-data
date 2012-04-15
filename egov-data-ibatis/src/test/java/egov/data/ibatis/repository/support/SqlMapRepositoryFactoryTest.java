package egov.data.ibatis.repository.support;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;

import egov.data.ibatis.repository.SqlMapRepository;
import egov.data.ibatis.sample.domain.SpringSprout;

@RunWith(MockitoJUnitRunner.class)
public class SqlMapRepositoryFactoryTest {
	
	@Mock DataSource dataSource;
	@Mock SqlMapExecutorDelegate delegate;
	
	@Mock SqlMapClientImpl sqlMapClientImpl;
	
	SqlMapRepositoryFactory factory;
	
	@Before
	public void setup() {
		when(sqlMapClientImpl.getDataSource()).thenReturn(dataSource);
		when(sqlMapClientImpl.getDelegate()).thenReturn(delegate);
		
		this.factory = new SqlMapRepositoryFactory(sqlMapClientImpl);
	}
	
	@Test
	public void Factory가_Repository_객체를_생성했는가() throws Exception {
		assertNotNull(factory.getRepository(TestSpringSproutRepository.class));
	}
	
	@Test
	public void Repository가_SqlMapClient를_전달받았는가() throws Exception {
		TestSpringSproutRepository repository = factory.getRepository(TestSpringSproutRepository.class);
		assertEquals(repository.getSqlMapClient(), sqlMapClientImpl);
	}
	
	@Test
	public void Object_메소드를_호출해본다() {
		TestSpringSproutRepository repository = factory.getRepository(TestSpringSproutRepository.class);

		assertNotNull(repository.hashCode());
		assertNotNull(repository.toString());
		assertTrue(repository.equals(repository));
	}
	

	public interface TestSpringSproutRepository extends SqlMapRepository<SpringSprout, Long> {

	} 
	
}
