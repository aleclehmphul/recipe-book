package dev.aleclehmphul.recipebook.recipe.dao;

import dev.aleclehmphul.recipebook.recipe.model.Ingredient;
import dev.aleclehmphul.recipebook.recipe.model.Recipe;
import dev.aleclehmphul.recipebook.recipe.model.Task;

import java.util.List;
import java.util.Optional;

public interface RecipeDao {
    List<Recipe> getAllRecipes();
    int insertRecipe(Recipe recipe);
    int deleteRecipe(int recipeId);
    Optional<Recipe> getRecipeById(int recipeId);
    int updateRecipe(int recipeId, Recipe recipe);

    List<Ingredient> getIngredients(int recipeId);
    int[] insertIngredients(int recipeId, List<Ingredient> ingredients);
    int deleteIngredient(int ingredientId);
    int[] updateIngredients(int recipeId, List<Ingredient> ingredients);

    List<Task> getTasks(int recipeId);
    int[] insertTasks(int recipeId, List<Task> directions);
    int deleteTask(int taskId);
    int[] updateTasks(int recipeId, List<Task> directions);
}
