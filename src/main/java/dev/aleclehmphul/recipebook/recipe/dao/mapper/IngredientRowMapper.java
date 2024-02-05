package dev.aleclehmphul.recipebook.recipe.dao.mapper;

import dev.aleclehmphul.recipebook.recipe.model.Ingredient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientRowMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("amount"),
                rs.getString("measuring_unit"),
                rs.getInt("recipe_id")
        );
    }
}
