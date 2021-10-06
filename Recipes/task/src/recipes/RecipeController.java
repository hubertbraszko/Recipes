package recipes;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
public class RecipeController {

    private final RecipeService recipeService;



    @Autowired
    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<String> addRecipe(@Valid @RequestBody Recipe recipe) {

        Recipe newRecipe = recipeService.save(recipe);

        JsonObject json = new JsonObject();
        json.addProperty("id", newRecipe.getId());
        return ResponseEntity.ok(json.toString());
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {

        Recipe recipe = recipeService.findRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
       // throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe updatedRecipe){
        recipeService.updateRecipe(id,updatedRecipe);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/recipe/search/")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam Optional<String> category, @RequestParam Optional<String> name) {
        if(category.isPresent() && name.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        if(category.isPresent()) {
            return ResponseEntity.ok(recipeService.findByCategory(category.get()));
        } else if(name.isPresent()) {
            return ResponseEntity.ok(recipeService.findByName(name.get()));
        }

        return ResponseEntity.badRequest().build();
    }



}

