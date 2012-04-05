package whiteship;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whiteship.dao.MemberRepository;

/**
 * @author Keesun Baik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class HibernateRepositoryTests {

//    @Autowired MemberRepository memberRepository;
    @Autowired SessionFactory sessionFactory;

    @Test
    public void di(){

    }
}
