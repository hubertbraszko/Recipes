package recipes.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import recipes.Recipe;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotNull
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not valid", regexp = ".*@.*\\..+")
    private String email;


    @NotNull
    @Size(min = 8, message = "Password should be at least 8 characters long")
    @NotBlank(message = "password cannot be blank")
    private String password;

    @ElementCollection
    private List<Recipe> recipes;

    private String role;

    public void assignRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public boolean hasRecipe(Recipe recipe) {
        return recipes.contains(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
