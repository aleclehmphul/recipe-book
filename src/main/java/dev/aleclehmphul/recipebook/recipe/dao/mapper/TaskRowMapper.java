package dev.aleclehmphul.recipebook.recipe.dao.mapper;

import dev.aleclehmphul.recipebook.recipe.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(
                rs.getInt("id"),
                rs.getString("description"),
                rs.getInt("number"),
                rs.getInt("recipe_id")
        );
    }
}
