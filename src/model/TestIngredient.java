package model;

public class TestIngredient {

    private static Ingredient ingredient;

    private static String name = "@_I-123#rR)";
    private static double price = 10.34000000000;


    public static void main(String[] args){

    ingredient = new Ingredient(name, price);

    testGetIngredientName();

    testGetPrice();

    }

    private static void testGetIngredientName(){
        System.out.println("testing getIngredientName method");

        if(ingredient.getIngredientName() == name){
            System.out.println("test passed");
        }else {
            System.out.println("test not passed");
        }
    }


    private static void testGetPrice(){
        System.out.println("testing getPrice method");

        if(ingredient.getPrice() == price){
            System.out.println("test passed");
        }else {
            System.out.println("test not passed");
        }
    }


}
