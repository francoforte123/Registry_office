package Registry_office.Service;

import Registry_office.Exceptions.AlreadyRegisteredException;
import Registry_office.Exceptions.GenericException;
import Registry_office.Exceptions.NotFoundException;
import Registry_office.Repository.UserRepository;
import Registry_office.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public List<User> getAllUsers(){
        List<User> userOptional= userRepository.findAllUsers();
        if (userOptional.isEmpty()) ResponseEntity.status(500).build();
        return userOptional;
    }


    public User createdNewUser(User user) throws AlreadyRegisteredException {
       User user1;
       try {
            user1= userRepository.save(user);
       }catch (DataAccessException dataAccessException){
            throw new AlreadyRegisteredException("the user is create");
       }
       return user1;
    }

    public User singleUser(long id) throws NotFoundException {
        Optional<User> findOnDb= userRepository.getById(id);
        if (findOnDb.isEmpty()){
            throw new NotFoundException("user with id: " + id + ", not found");
        }
        return findOnDb.get();
    }


    public void deleteUser(long id) throws NotFoundException, GenericException {
        Optional<User> deleteUserFromDb= userRepository.getById(id);
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


    public User getUserWithEmail(String email) throws NotFoundException {
        Optional<User> getUserEmail= userRepository.findByEmail(email);
        if (!getUserEmail.isPresent()) throw new NotFoundException("user with email: " + email + ", not found");
        return getUserEmail.get();
    }


    public User verifyIfUserExistByEmail(String email) throws NotFoundException{
        Optional<User> getUser= userRepository.findIfExistTheUserByEmail(email);
        if (!getUser.isPresent()) throw new NotFoundException("the user by email not found");
        return getUser.get();
    }


    public List<User> getUsersDeleted(){
        List<User> userList= userRepository.getAllUsersDeteled();
        if (userList.isEmpty()) throw new NotFoundException("the users not found");
        return userList;
    }
}
