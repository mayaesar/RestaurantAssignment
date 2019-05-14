package model;

public class TestMenuItem {
    private static MenuItem menuItem;

    private static String name = "@_I-123#rR)";
    private static double price = 10.34000000000;
    private static int[] ingredient = {111, 5377};

    public static void main(String[] args){

        menuItem = new MenuItem(name, price, ingredient);

        testGetItemName();

        testGetIngredients();

        testGetPrice();

    }

    private static void testGetItemName(){
        System.out.println("testing GetItemName method");

        if(menuItem.getItemName() == name){
            System.out.println("test passed");
        }else {
            System.out.println("test not passed");
        }
    }


    private static void testGetIngredients(){
        System.out.println("testing getIngredients method");

        if(menuItem.getIngredients() == ingredient.length){
            System.out.println("test passed");
        }else {
            System.out.println("test not passed");
        }
    }

    private static void testGetPrice(){
        System.out.println("testing getPrice method");

        if(menuItem.getPrice() == price){
            System.out.println("test passed");
        }else {
            System.out.println("test not passed");
        }
    }

}
