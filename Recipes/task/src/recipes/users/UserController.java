package recipes.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/api/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {

        userService.register(user.getEmail(),user.getPassword());

        return ResponseEntity.ok("User registered successfully");
    }

}
