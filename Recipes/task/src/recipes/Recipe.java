package recipes;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull
    @NotBlank(message = "category is mandatory")
    private String category;

    @CreationTimestamp
    @JsonFormat(pattern = "dd.MM.YYYY HH:mm:ss.SSSSSS")
    private LocalDateTime date;

    @NotNull
    @NotBlank(message = "description is mandatory")
    private String description;

    @NotNull
    @Size(min = 1, message = "Minimal size should be 1")
    @ElementCollection
    private List<String> ingredients;

    @NotNull
    @Size(min = 1, message = "Minimal size should be 1")
    @ElementCollection
    private List<String> directions;


    public Recipe() {
    }

    public Recipe(String name, String category, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(name, recipe.name) && Objects.equals(description, recipe.description) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(directions, recipe.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ingredients, directions);
    }
}
