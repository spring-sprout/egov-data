package whiteship.repository;

import egov.data.ibatis.repository.SqlMapRepository;
import whiteship.domain.Member;

/**
 * @author Keesun Baik
 */
public interface MemberRepository extends SqlMapRepository<Member, Long> {

}
