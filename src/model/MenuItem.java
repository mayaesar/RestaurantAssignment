package model;

public class MenuItem {
    private String itemName;
    private double price;
    private int[] ingredients;

    public MenuItem(String itemName, double price, int[] ingredients) {
        this.itemName = itemName;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getItemName() {
        return itemName;
    }

    public int getIngredients(){

        return ingredients.length;
    }

    public double getPrice() {
        return price;
    }
}
