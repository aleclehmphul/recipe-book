package dev.aleclehmphul.recipebook.recipe;

import dev.aleclehmphul.recipebook.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public List<Recipe> listRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("{id}")
    public Recipe getRecipeById(@PathVariable("id") Integer id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping()
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.addNewRecipe(recipe);
    }

    @DeleteMapping("{id}")
    public void deleteRecipe(@PathVariable("id") Integer id) {
        recipeService.deleteRecipe(id);
    }

    @PutMapping("{id}")
    public void updateRecipe(@PathVariable("id") Integer id, Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
    }

}
