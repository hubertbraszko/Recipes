package recipes;


import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {

    private Recipe recipe;

    @PostMapping("/api/recipe")
    public String addRecipe(@RequestBody Recipe recipe) {
        this.recipe = recipe;
        return String.format("Added recipe: %s", recipe.getName());
    }

    @GetMapping("/api/recipe")
    public Recipe getRecipe(){
        return recipe;
    }

}
