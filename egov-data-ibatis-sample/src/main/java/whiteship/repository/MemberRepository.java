package whiteship.repository;

import egov.data.ibatis.repository.SqlMapRepository;
import whiteship.domain.Member;

import java.util.List;

/**
 * @author Keesun Baik
 */
public interface MemberRepository extends SqlMapRepository<Member, Long> {

	void add(Member member);

	void update(Member member);

	Member findOne(long id);

	List<Member> findAll();

	void delete(long id);

}
