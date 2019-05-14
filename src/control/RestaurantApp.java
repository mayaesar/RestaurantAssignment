package control;

import model.MenuList;
import model.Restaurant;
import model.Table;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestaurantApp {
    private MenuList menu;
    private Table[] tables;
    private Restaurant restaurant;

    public RestaurantApp() throws FileNotFoundException {
        menu = new MenuList();
        tables = new Table[12];
        restaurant = new Restaurant();
    }

    public void runRestaurant() throws FileNotFoundException {
        boolean closeRestaurant = false;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println();
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
                    caseThree(keyboard);
                    break;

                case 4:
                    closeRestaurant = caseFour();
                    break;

                default:
                    System.out.println("invalid option - please enter a number from 1-4");
            }
        } while (!closeRestaurant);
        closingRestaurant();
    }

    private void caseOne(Scanner keyboard) throws FileNotFoundException {
        int table = -1;
        for (int i = 0; i < tables.length; i++) {
            if (tables[i] == null) {
                table = i;
                break;
            }
        }
        if (table == -1) {
            System.out.println("No tables available");
        } else {
            int items = 0;
            do {
                System.out.println("How many menu items will this table be having?");
                items = keyboard.nextInt();
            } while (!(items > 0));

            tables[table] = new Table(items - 1);
            System.out.print("Table " + table + " has been seated.\n\n");

        }
    }

    private void caseTwo(Scanner keyboard) {
        int orderTable = 0;
        do {
            System.out.println("Which table is ordering? Enter a table from 0 to 11.");
            orderTable = keyboard.nextInt();
        } while (orderTable < 0 || orderTable > 11);
        if (tables[orderTable] == null) {
            System.out.println("There is nobody at that table!");
        } else if (tables[orderTable].hasOrdered()) {
            System.out.println("That table has already ordered!");
        } else {
            menu.getMenu();
            for (int i = 0; i < tables[orderTable].getOrderingNum(); i++) {
                int orderItem;
                System.out.println();
                do {

                    System.out.println("Choose items from the menu between 1 and " + (menu.getSize() - 1));
                    orderItem = keyboard.nextInt();
                    if (orderItem >= 0 && orderItem <= (menu.getSize() - 1)) {
                        tables[orderTable].addToOrder(orderItem);
                    }
                } while (tables[orderTable].getOrderingNum() == tables[orderTable].getSize());
            }
        }
    }

    public void caseThree(Scanner keyboard) {
        int payTable;
        do {
            System.out.println("Which table is paying? Enter a table from 0 to 11.");
            payTable = keyboard.nextInt();
        } while (payTable < 0 || payTable > 11);
        if (tables[payTable].hasOrdered()) {
            System.out.println("That table hasn't ordered yet!");
        } else {
            double owedAmount;
            owedAmount = tables[payTable].getReceipt();
            System.out.println("Table " + payTable + " owes " + owedAmount + "\n");

            String payMethod;
            do {
                System.out.println("How is this table paying? Enter debit, credit, or cash.");
                payMethod = keyboard.next();
            } while (!(payMethod.equals("debit") || payMethod.equals("credit") || payMethod.equals("cash")));
            restaurant.addPay(tables[payTable].setPaymentMethod(payMethod), owedAmount);
            restaurant.setTotalIngredients(tables[payTable].getIngredientCost());
            tables[payTable] = null;

        }

    }

    public boolean caseFour() {
        System.out.println("Closing Restaurant");
        boolean closeRestaurant = true;

        for (int i = 0; i < tables.length; i++) {
            if (!(tables[i] == null)) {
                System.out.printf("Cannot close - Table %d is occupied\n", i);
                closeRestaurant = false;
            }
        }
        return closeRestaurant;
    }

    public void closingRestaurant() {
        restaurant.endNight();
    }
}
