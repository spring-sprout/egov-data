package whiteship.legacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import whiteship.domain.Member;

import java.util.List;

/**
 * @author Keesun Baik
 */
//@Repository
public class MemberRepositoryIbatis implements MemberRepository {

	@Autowired SqlMapClientTemplate template;

    @Override
    public void add(Member member) {
        template.insert("add", member);
    }

    @Override
    public void update(Member member) {
	    template.update("update", member);
    }

    @Override
    public Member getById(long id) {
        return (Member) template.queryForObject("findOne", id);
    }

    @Override
    public List<Member> getAll() {
        return template.queryForList("findAll");
    }

    @Override
    public void delete(long id) {
        template.delete("delete", id);
    }

}
