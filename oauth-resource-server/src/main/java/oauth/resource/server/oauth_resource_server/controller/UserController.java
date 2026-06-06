package oauth.resource.server.oauth_resource_server.controller;

import oauth.resource.server.oauth_resource_server.dto.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping()
    public List<User> getUsers(){
        List<User>users = new ArrayList<>();
        users.add(new User("john" , "dao"));
        users.add(new User("jane" , "dao"));
        users.add(new User("jack" , "dao"));
        users.add(new User("will" , "dao"));
        return users;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(@RequestBody User user){
        return "user_created";
    }
}
