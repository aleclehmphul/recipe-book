package dev.aleclehmphul.recipebook.recipe.dao.mapper;

import dev.aleclehmphul.recipebook.recipe.model.Category;
import dev.aleclehmphul.recipebook.recipe.model.Ingredient;
import dev.aleclehmphul.recipebook.recipe.model.Recipe;
import dev.aleclehmphul.recipebook.recipe.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class RecipeRowMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Recipe(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("note"),
                LocalDateTime.ofInstant(rs.getTimestamp("created_at").toInstant(), ZoneId.systemDefault()),
                rs.getTimestamp("last_updated") == null ? null :
                        LocalDateTime.ofInstant(rs.getTimestamp("last_updated").toInstant(),
                                ZoneId.systemDefault())
        );
    }
}
