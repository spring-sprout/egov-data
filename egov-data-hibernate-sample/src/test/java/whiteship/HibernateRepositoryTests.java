package whiteship;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import whiteship.dao.MemberRepository;
import whiteship.domain.Member;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author Keesun Baik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class HibernateRepositoryTests {

    @Autowired MemberRepository memberRepository;
    @Autowired SessionFactory sessionFactory;

    @Test
    public void save(){
	    Member member = new Member();
	    member.setName("keesun");
	    memberRepository.save(member);
	    assertThat(member.getId(), is(not(0)));
    }
}
