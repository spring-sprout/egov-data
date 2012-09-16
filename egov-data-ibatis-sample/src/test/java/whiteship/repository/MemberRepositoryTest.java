package whiteship.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whiteship.domain.Member;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Keesun Baik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;

	@Test
	public void crud(){
		Member member = new Member();
		String name = "스프링 컨퍼런스에 가고 싶은 백기선";
		member.setName(name);
		memberRepository.insert(member);
		Member savedMember = memberRepository.findOne(member.getId());
		assertThat(savedMember.getName(), is(name));
	}


}
