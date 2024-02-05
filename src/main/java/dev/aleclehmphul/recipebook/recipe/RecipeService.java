package dev.aleclehmphul.recipebook.recipe;

import dev.aleclehmphul.recipebook.exception.NotFoundException;
import dev.aleclehmphul.recipebook.recipe.dao.RecipeDao;
import dev.aleclehmphul.recipebook.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeDao recipeDao;

    @Autowired
    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public List<Recipe> getRecipes() {
        return recipeDao.getAllRecipes();
    }

    public Recipe getRecipe(Integer id) {
        return recipeDao.getRecipeById(id)
                .orElseThrow(() -> new NotFoundException("Recipe with id " + id + " not found"));
    }

    public void addNewRecipe(Recipe recipe) {
        // TODO: check if recipe exists
        int result = recipeDao.insertRecipe(recipe);
        if (result != 1) {
            throw new IllegalStateException("oops cannot add new recipe");
        }
    }

    public void deleteRecipe(Integer id) {
        Optional<Recipe> recipe = recipeDao.getRecipeById(id);
        if (recipe.isPresent()) {
            int result = recipeDao.deleteRecipe(id);
            if (result != 1) {
                throw new IllegalStateException("oops cannot delete recipe");
            }
        } else {
            throw new NotFoundException("Recipe with id " + id + " not found");
        }
    }

    public void updateRecipe(Integer id, Recipe newRecipe) {
        Optional<Recipe> currentRecipe = recipeDao.getRecipeById(id);
        if (currentRecipe.isPresent()) {
            int result = recipeDao.updateRecipe(id, newRecipe);
            if (result != 1) {
                throw new IllegalStateException("oops cannot update recipe");
            }
        } else {
            throw new NotFoundException("Recipe with id " + id + " not found");
        }
    }
}
