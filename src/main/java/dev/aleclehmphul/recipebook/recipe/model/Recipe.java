package dev.aleclehmphul.recipebook.recipe.model;

import java.time.LocalDateTime;
import java.util.List;

public class Recipe {

    private Integer id;
    private String title;
    private Category category;
    private List<Ingredient> ingredients;
    private List<Task> directions;
    private String note;
    private LocalDateTime createAt;
    private LocalDateTime lastUpdated;

    public Recipe() {
        super();
    }

    public Recipe(Integer id,
                  String title,
                  Category category,
                  List<Ingredient> ingredients,
                  List<Task> directions,
                  String note,
                  LocalDateTime createAt,
                  LocalDateTime lastUpdated) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.directions = directions;
        this.note = note;
        this.createAt = createAt;
        this.lastUpdated = lastUpdated;
    }

    public Recipe(Integer id,
                  String title,
                  Category category,
                  List<Ingredient> ingredients,
                  List<Task> directions,
                  String note) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.directions = directions;
        this.note = note;
    }

    public Recipe(String title,
                  Category category,
                  List<Ingredient> ingredients,
                  List<Task> directions,
                  String note) {
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.directions = directions;
        this.note = note;
    }

    public Recipe(Integer id, String title, String note, LocalDateTime createAt, LocalDateTime lastUpdated) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.createAt = createAt;
        this.lastUpdated = lastUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Task> getDirections() {
        return directions;
    }

    public void setDirections(List<Task> directions) {
        this.directions = directions;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", ingredients=" + ingredients +
                ", directions=" + directions +
                ", note='" + note + '\'' +
                ", createAt=" + createAt +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
