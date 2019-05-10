package model;

public class MenuItem {
    private String[] itemName;
    private double[] price;
    private Ingredient[] ingredients;

    public MenuItem(String[] itemName, double[] price, int[][] ingredients) {
        this.itemName = itemName;
        this.price = price;
       // this.ingredients = ingredients;
    }

    public String getItemName(int index) {
        return itemName[index];
    }


    public double getPrice(int index) {
        return price[index];
    }


    public Ingredient getIngredients(int index) {
        return ingredients[index];
    }
}
