package Registry_office.Service;

import Registry_office.Exceptions.AlreadyRegisteredException;
import Registry_office.Exceptions.GenericException;
import Registry_office.Exceptions.NotFoundException;
import Registry_office.Repository.UserRepository;
import Registry_office.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public List<User> getAllUsers(){
        List<User> userOptional= userRepository.findAll();
        if (userOptional.isEmpty()) ResponseEntity.status(500).build();
        return ResponseEntity.ok(userOptional).getBody();
    }


    public ResponseEntity<User> createdNewUser(User user) throws AlreadyRegisteredException {
        Optional<User> newUser= userRepository.findByEmail(user.getEmail());
        if (newUser.isPresent()){
            throw new AlreadyRegisteredException("already registered user");
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> singleUser(long id) throws NotFoundException {
        Optional<User> findOnDb= userRepository.findById(id);
        if (findOnDb.isEmpty()){
            throw new NotFoundException("user with id: " + id + ", not found");
        }
        return ResponseEntity.ok(findOnDb.get());
    }


    public void deleteUser(long id) throws NotFoundException, GenericException {
        Optional<User> deleteUserFromDb= userRepository.findById(id);
        if (deleteUserFromDb.isEmpty()){
            throw new NotFoundException("user with id: " + id + ", not found");
        }
        if (deleteUserFromDb.get().isDeleted()==false){
            deleteUserFromDb.get().setDeleted(true);
        }else {
            throw new GenericException("exists user");
        }
        userRepository.save(deleteUserFromDb.get());
    }


    public ResponseEntity getUserWithEmail(String email) throws NotFoundException {
        Optional<User> getUserEmail= userRepository.findByEmail(email);
        if (!getUserEmail.isPresent()) throw new NotFoundException("user with email: " + email + ", not found");
        return ResponseEntity.ok().body(getUserEmail.get());
    }
}
