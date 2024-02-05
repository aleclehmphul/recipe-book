package dev.aleclehmphul.recipebook.recipe.model;

public class Ingredient {

    private Integer id;
    private String name;
    private double amount;
    private String measuringUnit;
    private Integer recipeId;

    public Ingredient() {
        super();
    }

    public Ingredient(Integer id, String name, double amount, String measuringUnit, Integer recipeId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.measuringUnit = measuringUnit;
        this.recipeId = recipeId;
    }

    public Ingredient(String name, double amount, String measuringUnit, Integer recipeId) {
        this.name = name;
        this.amount = amount;
        this.measuringUnit = measuringUnit;
        this.recipeId = recipeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", measuringUnit='" + measuringUnit + '\'' +
                ", recipeId=" + recipeId +
                '}';
    }
}
