package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe save(Recipe newRecipe) {
        return recipeRepository.save(newRecipe);

    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Recipe not found for id = " + id));
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        if(!this.recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Recipe not found for id = " + id);
        }
        updatedRecipe.setId(id);
        updatedRecipe.setDate(LocalDateTime.now());
        this.recipeRepository.save(updatedRecipe);
        return updatedRecipe;
    }

    public void deleteRecipeById(Long id) {
        Recipe recipe = findRecipeById(id);
        recipeRepository.delete(recipe);
    }
}
