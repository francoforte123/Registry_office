package Registry_office;

import Registry_office.Repository.UserRepository;
import Registry_office.User.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class UserControllerTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void createTheUser1(){
		User user = new User();
		user.setName("Anna");
		user.setSurname("Neri");
		user.setBirthday(new Date(02 / 02 / 1990));
		user.setEmail("anna.neri@gmail.com");
		user.setPassword("anna.neri");
		user.setGender("female");
		user.setHeight(1.60);
		user.setDeleted(false);

		userRepository.save(user);
		Assertions.assertNotNull(user);
	}

	@Test
	public void createTheUser2(){
		User user = new User();
		user.setName("Marco");
		user.setSurname("Verdi");
		user.setBirthday(new Date(02 / 02 / 1990));
		user.setEmail("marco.verdi@gmail.com");
		user.setPassword("marco.verdi");
		user.setGender("male");
		user.setHeight(1.80);
		user.setDeleted(false);

		userRepository.save(user);
		Assertions.assertNotNull(user);
	}


	@Test
	public void getAllUsers() {
		createTheUser1();
		createTheUser2();

		List<User> userList= userRepository.findAll();
		Assertions.assertNotNull(userList);
	}

	@Test
	public void getSingleUserWithId(){
		createTheUser1();
		createTheUser2();

		long findIdTheUser= 2;

		Optional<User> user= userRepository.findById(findIdTheUser);
		Assertions.assertEquals(user.get().getId(), findIdTheUser);
	}


	@Test
	public void deleteTheUserWithId(){
		createTheUser1();
		createTheUser2();

		long deleteUserWithId= 2;

		Optional<User> deleteUser= userRepository.findById(deleteUserWithId);
		deleteUser.get().setDeleted(true);

		Assertions.assertEquals(userRepository.save(deleteUser.get()).isDeleted(), true);
	}


	@Test
	public void findUserWithEmail(){
		System.out.println("creo l'utente");
		createTheUser1();
		createTheUser2();

		String emailTheUser= "anna.neri@gmail.com";

		Optional<User> getUserWithEmail= userRepository.findByEmail(emailTheUser);
		System.out.println("faccio il confronto");
		Assertions.assertEquals(getUserWithEmail.get().getEmail(), emailTheUser);

		System.out.println(getUserWithEmail);
	}
}
