package Registry_office.Controller;

import Registry_office.Exceptions.AlreadyRegisteredException;
import Registry_office.Exceptions.GenericException;
import Registry_office.Exceptions.NotFoundException;
import Registry_office.Service.UserService;
import Registry_office.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registry_office")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User user) throws AlreadyRegisteredException {
        return userService.createdNewUser(user);
    }

    @GetMapping("/user/id")
    public ResponseEntity getSingleUser(@RequestParam long id) throws NotFoundException {
        return userService.singleUser(id);
    }

    @PutMapping("/user/id")
    public void deleteOrModify(@RequestParam long id) throws NotFoundException, GenericException{
        userService.deleteUser(id);
    }

    @GetMapping("/user/email")
    public ResponseEntity getUserByEmail(@RequestParam String email) throws NotFoundException {
        return userService.getUserWithEmail(email);
    }
}
