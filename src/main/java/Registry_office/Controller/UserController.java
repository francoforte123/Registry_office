package Registry_office.Controller;

import Registry_office.Exceptions.AlreadyRegisteredException;
import Registry_office.Exceptions.GenericException;
import Registry_office.Exceptions.NotFoundException;
import Registry_office.Service.UserService;
import Registry_office.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registry_office")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/createUser")
    public ResponseEntity create(@RequestBody User user) throws AlreadyRegisteredException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createdNewUser(user));
    }

    @GetMapping("/getSingleUser")
    public ResponseEntity getSingleUser(@RequestParam long id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.singleUser(id));
    }

    @PutMapping("/modify")
    public void deleteOrModify(@RequestParam long id) throws NotFoundException, GenericException{
        userService.deleteUser(id);
    }

    @GetMapping("/getUserWithEmail")
    public ResponseEntity getUserByEmail(@RequestParam String email) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserWithEmail(email));
    }

    @GetMapping("/getUserIfExixstByEmail")
    public ResponseEntity verifyTheUser(@RequestParam String email) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(userService.verifyIfUserExistByEmail(email));
    }

    @GetMapping("/getUsersDeleted")
    public List<User> findTheUsersDeleted(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersDeleted()).getBody();
    }
}
