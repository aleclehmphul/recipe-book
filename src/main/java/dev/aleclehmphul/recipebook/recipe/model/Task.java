package dev.aleclehmphul.recipebook.recipe.model;

public class Task {

    private Integer id;
    private String description;
    private Integer taskNumber;
    private Integer recipeId;

    public Task() {
        super();
    }

    public Task(Integer id, String description, Integer taskNumber, Integer recipeId) {
        this.id = id;
        this.description = description;
        this.taskNumber = taskNumber;
        this.recipeId = recipeId;
    }

    public Task(String description, Integer taskNumber, Integer recipeId) {
        this.description = description;
        this.taskNumber = taskNumber;
        this.recipeId = recipeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", taskNumber=" + taskNumber +
                ", recipeId=" + recipeId +
                '}';
    }
}
