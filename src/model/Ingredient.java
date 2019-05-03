package model;

public class Ingredient {
    private String ingredientName;
    private double price;

    public Ingredient(String ingredientName, float price) {
        this.ingredientName = ingredientName;
        this.price = price;

        //comment
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public double getPrice() {
        return price;
    }
}


