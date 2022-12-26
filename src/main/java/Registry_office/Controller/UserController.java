package Registry_office.Controller;

import Registry_office.Service.UserService;
import Registry_office.User.User;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/registry_office")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity create(@RequestBody User user) throws AlreadyRegisteredException{
        return userService.createdNewUser(user);
    }


}
