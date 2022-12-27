package Registry_office.Controller;

import Registry_office.Exceptions.AlreadyRegisteredException;
import Registry_office.Service.UserService;
import Registry_office.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registry_office")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity create(@RequestBody User user) throws AlreadyRegisteredException {
        return userService.createdNewUser(user);
    }


}
