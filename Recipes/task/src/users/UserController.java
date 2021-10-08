package users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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


    //TODO
    @PostMapping("/api/recipe/new")
    public ResponseEntity<String> registerNewUser(@Valid @RequestParam String email, @Valid @RequestParam String password) {
        return ResponseEntity.noContent().build();
    }
}
