package egov.data.jpa.sample;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egov.data.jpa.repository.QueryMethod;

/**
 * @author Keesun Baik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserRepositoryTest {

	@Autowired 
	UserRepository userRepository;

	@Test
	public void crud() {
		assertThat(userRepository.findAll().size(), is(0));

		User keesun = new User();
		keesun.setAge(32);
		keesun.setActive(true);
		keesun.setEmailAddress("whiteship2000@gmail.com");
		keesun.setFirstname("Keesun");
		keesun.setLastname("Baik");

		// CREATE
		userRepository.save(keesun);

		// READ
		List<User> users = userRepository.findAll();
		assertThat(users.size(), is(1));

		// UPDATE
		keesun.setActive(false);
		userRepository.saveAndFlush(keesun);

		keesun = userRepository.findOne(keesun.getId());
		assertThat(keesun.isActive(), is(false));

		// DELETE
		userRepository.delete(keesun);
		assertThat(userRepository.findOne(keesun.getId()), is(nullValue()));
		assertThat(userRepository.findAll().size(), is(0));
	}
	
	
	
	@Test
	public void queryMethod1() {
	
		assertThat(userRepository.findAll().size(), is(0));
		userRepository.save(new User("헝", "길동" , "a1@google.com" , 16));
		userRepository.save(new User("김", "길동" , "a2@google.com", 24));
		userRepository.save(new User("이", "철수" , "a1@twitter.com", 21));
		userRepository.save(new User("이", "영희" , "a2@twitter.com", 50));
		List<User> users = userRepository.findAll();
		assertThat(users.size(), is(4));
		
		User param = new User();

		param.setFirstname("이");
		param.setLastname(null);
		List<User> users1 = userRepository.findAll("findByFirstnameAndLastname" , param);
		assertThat(users1.size(), is(2));

	
		param.setFirstname(null);
		param.setLastname("영희");
		List<User> users2 = userRepository.findAll("findByFirstnameAndLastname" , param);
		assertThat(users2.size(), is(1));
	

		param.setFirstname(null);
		param.setLastname(null);
		List<User> users3 = userRepository.findAll("findByFirstnameAndLastname" , param);
		assertThat(users3.size(), is(4));
	}

	@Test
	public void queryMethod2() {

		FindByEmailAddressContaining param = new FindByEmailAddressContaining();
		param.setEmailAddress("a1");
		
		List<User> users = userRepository.findAll(param);
		assertThat(users.size(), is(2));
	}

	public class FindByEmailAddressContaining {

		private String emailAddress;

		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
	}
	
	
	@Test
	public void queryMethod3() {
		UserParam param = new UserParam();
		param.setAgeMin(20);
		param.setAgeMax(30);
		
		List<User> users = userRepository.findAll(param);
		assertThat(users.size(), is(2));
	}

	@QueryMethod("findByAgeBetween")
	public class UserParam {

		private int ageMax;
		private int ageMin;
		
		public int getAgeMax() {
			return ageMax;
		}
		public void setAgeMax(int ageMax) {
			this.ageMax = ageMax;
		}
		public int getAgeMin() {
			return ageMin;
		}
		public void setAgeMin(int ageMin) {
			this.ageMin = ageMin;
		}
	}
}
