package Registry_office.Service;

import Registry_office.Exceptions.AlreadyRegisteredException;
import Registry_office.Repository.UserRepository;
import Registry_office.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity createdNewUser(User user) throws AlreadyRegisteredException {
        Optional<User> newUser= userRepository.findByEmail(user.getEmail());
        if (newUser.isPresent()) throw new AlreadyRegisteredException("already registered user");
        userRepository.save(user);
        return ResponseEntity.ok(newUser.get());
    }
}
