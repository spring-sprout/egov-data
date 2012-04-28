package egov.data.ibatis.repository;


public interface TestDomainObjectRepository extends SqlMapRepository<TestDomainObject, Long>
												  , TestCustomRepository {

	
	public static class TestDomainObjectRepositoryImpl implements TestCustomRepository {
		public boolean alwaysTrue() { return true; }
	}
	
}
