package egov.data.hibernate.repository.support;

import egov.data.hibernate.sample.domain.User;
import egov.data.hibernate.sample.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Keesun Baik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-context.xml")
public class UserRepositoryTest {

	@Autowired UserRepository userRepository;

	@Test
	public void crud() {
		assertThat(userRepository.findAll().size(), is(0));

		User keesun = new User();
		keesun.setAge(32);
		keesun.setActive(true);
		keesun.setEmailAddress("whiteship2000@gmail.com");
		keesun.setFirstname("Keesun");
		keesun.setLastname("Baik");

		userRepository.save(keesun);

		List<User> users = userRepository.findAll();
		assertThat(users.size(), is(1));
	}


}
