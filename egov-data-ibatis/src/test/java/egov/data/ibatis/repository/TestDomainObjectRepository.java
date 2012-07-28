package egov.data.ibatis.repository;


public interface TestDomainObjectRepository extends SqlMapRepository<TestDomainObject, Long> 
												  , TestCustomRepository {

}
