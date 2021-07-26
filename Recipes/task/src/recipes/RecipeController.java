package recipes;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;

@RestController
public class RecipeController {

    private static HashMap<Integer,Recipe> recipes;
    private static int latestID ;

    static {
        recipes = new HashMap<>();
        latestID = 1;
    }

    @PostMapping("/api/recipe/new")
    public String addRecipe(@RequestBody Recipe recipe) {
        //this.recipe = recipe;
        recipes.put(latestID,recipe);

        JsonObject json = new JsonObject();
        json.addProperty("id", latestID++);
        return json.toString();
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        if(recipes.getOrDefault(id,null) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe Not Found");
        }
        return recipes.get(id);
    }

}

