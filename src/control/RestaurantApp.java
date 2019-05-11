package control;

import model.Restaurant;
import model.Table;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestaurantApp {
    private CreateMenu createMenu;
    private Table[] tables;
    private Restaurant restaurant;

    public RestaurantApp() throws FileNotFoundException {
        createMenu = new CreateMenu();
        tables = new Table[12];
        restaurant = new Restaurant();
    }

    public void runRestaurant() throws FileNotFoundException {
        boolean closeRestaurant = false;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println("Please choose from the following options:\n"
                    + "1. New Party\n"
                    + "2. Add Order\n"
                    + "3. Accept Payment\n"
                    + "4. End Night");
            int option = keyboard.nextInt();
            switch (option) {
                case 1:
                    caseOne(keyboard);
                    break;

                case 2:
                    caseTwo(keyboard);
                    break;

                case 3:
                    break;

                case 4:
                    break;

                default:
            }
        }while (!closeRestaurant);
    }

    private void caseOne(Scanner keyboard){
        int table = -1;
        for(int i = 0; i < tables.length; i++){
            if(tables[i] == null){
                table = i;
                break;
            }
        }
        if(table == -1){
            System.out.println("No tables available");
        }
        else {
            int items = 0;
            do{
                System.out.println("How many menu items will this table be having?");
                items = keyboard.nextInt();
            }while(!(items > 0));

            tables[table] = new Table(items);
            System.out.printf("Table " + table + " has been seated.\n\n");

        }
    }

    private void caseTwo(Scanner keyboard){
        int orderTable;
        do{
            System.out.println("Which table is ordering? Enter a table from 0 to 11.");
            orderTable = keyboard.nextInt();
        }while(orderTable < 0 || orderTable > 11);
        if(tables[orderTable] == null){
            System.out.println("There is nobody at that table!");
        }
        else if (tables[orderTable].hasOrdered()){
            System.out.println("That table has already ordered!");
        }
        else {

        }
        System.out.println("Choose items from the menu:");
        for(int i = 1; i < createMenu.getSize(); i++){
            System.out.printf("%2d. %-15s",i,createMenu.getMenuItem().getItemName(i));
            if(i%3 == 0){
                System.out.println();
            }
        }

        for(int i = 0; i < tables[orderTable].getSize(); i++){
            int orderedItem;
            do{
                System.out.printf("\nEnter the next item to be ordered. Enter a value 1 to %d\n", createMenu.getSize() - 1);
                orderedItem = keyboard.nextInt();
            }while(orderedItem < 1 || orderedItem > createMenu.getSize());
            tables[orderTable].add(orderTable, orderedItem);
        }
    }

    public void caseThree(Scanner keyboard){
        int payTable;
        do {
            System.out.println("Which table is paying? Enter a table from 0 to 11.");
            payTable = keyboard.nextInt();
        }while(payTable < 0 || payTable > 11);
        if(tables[payTable].hasOrdered()) {
            System.out.println("That table hasn't ordered yet!");
        }
        else {
            double owedAmount = 0;
            for (int i=0; i<tables[payTable].getSize(); i++){
                owedAmount += tables[payTable].getReceipt();
                System.out.println("Table " + payTable + " owes " + owedAmount + "\n");

                String payMethod;
                do{
                    System.out.println("How is this table paying? Enter debit, credit, or cash.");
                    payMethod = keyboard.next();
                }while(!(payMethod.equals("debit") || payMethod.equals("credit") || payMethod.equals("cash")));

            }
        }
    }
}
