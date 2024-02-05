package dev.aleclehmphul.recipebook.recipe.dao;

import dev.aleclehmphul.recipebook.recipe.dao.mapper.IngredientRowMapper;
import dev.aleclehmphul.recipebook.recipe.dao.mapper.RecipeRowMapper;
import dev.aleclehmphul.recipebook.recipe.dao.mapper.TaskRowMapper;
import dev.aleclehmphul.recipebook.recipe.model.Ingredient;
import dev.aleclehmphul.recipebook.recipe.model.Recipe;
import dev.aleclehmphul.recipebook.recipe.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeDataAccessService implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes;
        List<Ingredient> recipeIngredients;
        List<Task> recipeDirections;

        String sql = """
                SELECT
                    id, title, note, created_at, last_updated
                FROM
                    recipe
                LIMIT 100;
                """;

        recipes = jdbcTemplate.query(sql, new RecipeRowMapper());

        // Loop to create the ingredients and task lists based off the recipe_id
        for (Recipe recipe : recipes) {
            sql = """
                    SELECT
                        id, name, amount, measuring_unit, recipe_id
                    FROM 
                        ingredient
                    WHERE 
                        recipe_id = %s;
                    """.formatted(recipe.getId().toString());

            recipeIngredients = jdbcTemplate.query(sql, new IngredientRowMapper());

            sql = """
                    SELECT
                        id, description, number, recipe_id
                    FROM 
                        task 
                    WHERE 
                        recipe_id = %s;
                    """.formatted(recipe.getId().toString());

            recipeDirections = jdbcTemplate.query(sql, new TaskRowMapper());

            recipe.setIngredients(recipeIngredients);
            recipe.setDirections(recipeDirections);
        }

        return recipes;
    }

    @Override
    public int insertRecipe(Recipe recipe) {
//        String sql = """
//                SELECT currval('recipe_id_seq')+1 AS recipe_id;
//                """;
//
//        Integer recipeId = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
//            @Override
//            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
//                if (rs.next())
//                    return rs.getInt("recipe_id");
//                return 0;
//            }
//        });

        String sql = """
                INSERT INTO recipe (title, note, created_at)
                    VALUES (?, ?, CURRENT_TIMESTAMP);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int recipeResult = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, recipe.getTitle());
            ps.setString(2, recipe.getNote());
            return ps;
        }, keyHolder);
        long recipeId = (long) keyHolder.getKeys().get("id");

        // int recipeResult = jdbcTemplate.update(sql, recipe.getTitle(), recipe.getNote());

        int[] ingredientsResult = insertIngredients((int) recipeId, recipe.getIngredients());
        int[] tasksResult = insertTasks((int) recipeId, recipe.getDirections());

        return recipeResult;
    }

    @Override
    public int deleteRecipe(int id) {
        String sql = """
                DELETE FROM recipe
                    WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Recipe> getRecipeById(int recipeId) {
        String sql = """
                SELECT
                    id, title, note, created_at, last_updated
                FROM
                    recipe
                WHERE
                    id = ?;
                """;
        Optional<Recipe> optionalRecipe = jdbcTemplate.query(sql, new RecipeRowMapper(), recipeId)
                .stream().findFirst();

        // If present, get ingredient and task lists
        if (optionalRecipe.isPresent()) {
            List<Ingredient> recipeIngredients;
            List<Task> recipeDirections;
            sql = """
                    SELECT
                        id, name, amount, measuring_unit, recipe_id
                    FROM
                        ingredient
                    WHERE
                        recipe_id = %s;
                    """.formatted(recipeId);
            recipeIngredients = jdbcTemplate.query(sql, new IngredientRowMapper());

            sql = """
                    SELECT
                        id, description, number, recipe_id
                    FROM
                        task
                    WHERE
                        recipe_id = %s;
                    """.formatted(recipeId);
            recipeDirections = jdbcTemplate.query(sql, new TaskRowMapper());

            optionalRecipe.get().setIngredients(recipeIngredients);
            optionalRecipe.get().setDirections(recipeDirections);
        }

        return optionalRecipe;
    }

    @Override
    public int updateRecipe(int recipeId, Recipe recipe) {
        String sql = """
                UPDATE 
                    recipe
                SET
                    title = ?,
                    note = ?,
                    last_updated = CURRENT_TIMESTAMP
                WHERE
                    id = ?;
                """;
        int recipeResult = jdbcTemplate.update(sql, recipe.getTitle(), recipe.getNote(), recipeId);

        int[] ingredientsResult = updateIngredients(recipeId, recipe.getIngredients());
        int[] tasksResult = updateTasks(recipeId, recipe.getDirections());

        return recipeResult;
    }

    @Override
    public List<Ingredient> getIngredients(int recipeId) {
        String sql = """
                SELECT
                    id, name, amount, measuring_unit, recipe_id
                FROM
                    ingredient
                WHERE
                    recipe_id = %s;
                """.formatted(recipeId);
        return jdbcTemplate.query(sql, new IngredientRowMapper());
    }

    @Override
    public int[] insertIngredients(int recipeId, List<Ingredient> ingredients) {
        String sql = """
                 INSERT INTO ingredient (name, amount, measuring_unit, recipe_id)
                     VALUES (?, ?, ?, ?);
                 """;
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Ingredient ingredient = ingredients.get(i);
                ps.setString(1, ingredient.getName());
                ps.setDouble(2, ingredient.getAmount());
                ps.setString(3, ingredient.getMeasuringUnit());
                ps.setInt(4, recipeId);
            }

            @Override
            public int getBatchSize() {
                return ingredients.size();
            }
        });
    }

    @Override
    public int deleteIngredient(int ingredientId) {
        String sql = """
                DELETE FROM ingredient
                    WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, ingredientId);
    }

    @Override
    public int[] updateIngredients(int recipeId, List<Ingredient> ingredients) {
        String sql = """
                 UPDATE
                    ingredient
                 SET
                    name = ?,
                    amount = ?,
                    measuring_unit = ?
                 WHERE
                    recipe_id = ?;
                 """;
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Ingredient ingredient = ingredients.get(i);
                ps.setString(1, ingredient.getName());
                ps.setDouble(2, ingredient.getAmount());
                ps.setString(3, ingredient.getMeasuringUnit());
                ps.setInt(4, recipeId);
            }

            @Override
            public int getBatchSize() {
                return ingredients.size();
            }
        });
    }

    @Override
    public List<Task> getTasks(int recipeId) {
        String sql = """
                SELECT
                    id, description, number, recipe_id
                FROM
                    task
                WHERE
                    recipe_id = %s;
                """.formatted(recipeId);
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    @Override
    public int[] insertTasks(int recipeId, List<Task> directions) {
        String sql = """
                 INSERT INTO task (description, number, recipe_id)
                     VALUES (?, ?, ?);
                 """;
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Task task = directions.get(i);
                ps.setString(1, task.getDescription());
                ps.setInt(2, task.getTaskNumber());
                ps.setInt(3, recipeId);
            }

            @Override
            public int getBatchSize() {
                return directions.size();
            }
        });
    }

    @Override
    public int deleteTask(int taskId) {
        String sql = """
                DELETE FROM task
                    WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, taskId);
    }

    @Override
    public int[] updateTasks(int recipeId, List<Task> directions) {
        String sql = """
                 UPDATE
                    task
                 SET
                    description = ?,
                    number = ?
                 WHERE
                    recipe_id = ?;
                 """;
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Task task = directions.get(i);
                ps.setString(1, task.getDescription());
                ps.setInt(2, task.getTaskNumber());
                ps.setInt(3, recipeId);
            }

            @Override
            public int getBatchSize() {
                return directions.size();
            }
        });
    }

}
