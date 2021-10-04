package recipes;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.time.LocalDateTime;


@RestController
public class RecipeController {

    private final RecipeService recipeService;



    @Autowired
    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @PostMapping("/api/recipe/new")
    public String addRecipe(@Valid @RequestBody Recipe recipe) {
        //System.out.println(LocalDateTime.now());
        recipe.setDate(LocalDateTime.now());
        Recipe newRecipe = recipeService.save(recipe);

        JsonObject json = new JsonObject();
        json.addProperty("id", newRecipe.getId());
        return json.toString();
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {

        return recipeService.findRecipeById(id);
    }

    @DeleteMapping("/api/recipe/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

}

