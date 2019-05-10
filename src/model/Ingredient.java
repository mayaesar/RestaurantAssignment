package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ingredient {
    private String[] ingredientName;
    private double[] price;

    public Ingredient(String[] ingredientName, double[] price) {
        this.ingredientName = ingredientName;
        this.price = price;
    }

    public String[] getIngredientName() {
        return ingredientName;
    }


    public double[] getPrice() {
        return price;
    }
}