package control;

import model.Ingredient;
import model.MenuItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CreateMenu {
    private Ingredient[] ingredients;
    private MenuItem[] menuItems;
    //private MenuItem menuItem;
    private int size;

    public CreateMenu() throws FileNotFoundException {
        loadIngredients();
        loadMenuItems();
    }


    private void loadIngredients() throws FileNotFoundException {
        String[] iName;
        double[] iPrice;

        File ingredientsFile = new File("src//ingredients.csv");
        Scanner ingredientScan = new Scanner(ingredientsFile);
        int ingredientsCount = 0;
        while (ingredientScan.hasNextLine()) {
            ingredientScan.nextLine();
            ingredientsCount++;
        }

        this.ingredients = new Ingredient[ingredientsCount];
        iName = new String[ingredientsCount];
        iPrice = new double[ingredientsCount];

        ingredientScan = new Scanner(ingredientsFile);
        int ingredientNo = 0;

        while(ingredientScan.hasNextLine()){
            String[] inputLine = ingredientScan.nextLine().split(",");
            iName[ingredientNo] = inputLine[0];
            iPrice[ingredientNo] = Double.parseDouble(inputLine[1]);
            this.ingredients[ingredientNo] = new Ingredient(iName[ingredientNo], iPrice[ingredientNo]);
            ingredientNo++;

        }
    }

    private void loadMenuItems() throws FileNotFoundException {
        String[] mName;
        double[] mPrice;



        File menuItemFile = new File("src//menuitems.csv");
        Scanner menuItemScan = new Scanner(menuItemFile);
        this.size = 0;
        while (menuItemScan.hasNextLine()) {
            menuItemScan.nextLine();
            size++;
        }
        mName = new String[size];
        mPrice = new double[size];
        menuItems = new MenuItem[size];

        menuItemScan = new Scanner(menuItemFile);
        int menuNo = 0;
        while(menuItemScan.hasNextLine()) {
            String[] inputLine = menuItemScan.nextLine().split(",");
            mName[menuNo] = inputLine[0];
            mPrice[menuNo] = Double.parseDouble(inputLine[1]);
            int[] ingredientList = new int[inputLine.length - 2];
            for (int i = 2; i < inputLine.length; i++) {
                for (int j = 0; j < ingredients.length; j++) {
                    if (inputLine[i].equals(ingredients[j].getIngredientName())) {
                        ingredientList[i - 2] = j;
                    }

                }
            }
            menuItems[menuNo] = new MenuItem(mName, mPrice, ingredientList);
            menuNo ++;

        }
    }


//    public Ingredient getIngredient() {
//        return ingredient;
//    }
//
//    public MenuItem getMenuItem() {
//        return menuItem;
//    }
//
//    public int getSize() {
//        return size;
//    }
}

