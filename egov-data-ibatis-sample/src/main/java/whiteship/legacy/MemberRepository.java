package whiteship.legacy;

import whiteship.domain.Member;

import java.util.List;

/**
 * @author Keesun Baik
 */
public interface MemberRepository {

    void add(Member member);

    void update(Member member);

    Member getById(long id);

    List<Member> getAll();

    void delete(long id);

}
