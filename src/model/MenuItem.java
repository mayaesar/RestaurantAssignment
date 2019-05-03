package model;

public class MenuItem {
    private String itemName;
    private double price;
    private Ingredient[] ingredients;

    public MenuItem(String itemName, double price, Ingredient[] ingredients) {
        this.itemName = itemName;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }
}
