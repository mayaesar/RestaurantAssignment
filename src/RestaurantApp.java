import java.util.Scanner;
import java.io.*;
public class RestaurantApp{
  
  //PROPERTIES
  
  //The kitchen of our restaurant needs to track the ingredients that are used to make the various items on the menu.
  //Each ingredient has two features, a name and a cost. The name and the cost are stored in separate arrays, but they
  //have a shared index which associates them to each other. (IE: ingredientName[i] has the cost ingredientCost[i])
  public String[] ingredientName;
  public double[] ingredientCost;

  //For menu items, we have 3 pieces of information to track, the ingredients, the name, and the price
  //Each of these aspects of a menu items has its own array.
  
  //The ingredients of one menu item are tracked by a int[], where each int represents the index of an ingredient of
  //the menuItem. 
  //(IE: If a menu item has ingredients {1,2}, then it contains ingredientName[1] with cost ingredientCost[1]
  //and ingredientName[2] with cost ingredientCost[2])
  
  //Like ingredients, these three features of a menuItem are stored in separate arrays, but have a shared index which
  //associates them to each other. (IE: The menu item with name menuItemName[i] has ingredients menuItemIngredients[i]
  //and price menuItemPrice[i])
  public int[][] menuItemIngredients;
  public String[] menuItemName;
  public double[] menuItemPrice;

  //tables represents the menuItems ordered by each table that is currently still eating.
  //Each table has it's own int[] representing the indices of menuItems that table has ordered, and the restaurant has
  //several of these tables.
  //(IE: If a table has ordered items {1,2}, that means they have ordered menuItemName[1] and menuItemName[2])
  public int[][] tables;
   
  
  //Once a table has paid, we remove their list of items ordered from the tables array and move it to the paidTables
  //array. This frees up the table for new people to come eat while still keeping track of what that table had ordered.
  //On top of tracking the int[] of menu items that tables has ordered, we also have a String representing the payment 
  //method that table used to pay for their order.
  public int[][] paidTables;
  public String[] paidTablePayMethod;
  public int totalPaidTables;
  
  
  
  //Set up and run the application
  public static void main(String[] args) throws FileNotFoundException{
    RestaurantApp app = new RestaurantApp();
    app.runRestaurant();
  }
  
  //Run the restaurant
  public void runRestaurant() throws FileNotFoundException{
    //Restaurant has 12 tables.
    this.tables = new int[12][];
    
    //When a table has paid for their meal, we move them into the "paidTables" array, which tracks all of the
    //meals we've sold for the entire evening. We also track their pay method in the paidTablePayMethod array.
    this.paidTables = new int[200][];
    this.totalPaidTables = 0;
    this.paidTablePayMethod = new String[200];
    
    //Call the loadfiles method, which reads in the ingredients and menu items from ingredients and menu items files
    //into their respective arrays
    this.loadFiles();
    
    //Boolean which tracks if the restaurant has closed.
    boolean closeRestaurant = false;
    
    Scanner keyboard = new Scanner(System.in);
    do{
      //Present menu options
      //New party will allow for an incoming set of customers to be seated at a table
      //Add order defines the menu items that a specified table of customers are ordering
      //Accept payment allows for a specified table to pay, telling them what they owe and asking for their pay method
      //End night ends the application, at which time we will present our total earnings for the night.
      System.out.println("Please choose from the following options:\n"
                           + "1. New Party\n"
                           + "2. Add Order\n"
                           + "3. Accept Payment\n"
                           + "4. End Night");
      int option = keyboard.nextInt();
      switch(option){
        case 1:
          //ADD NEW PARTY
          //Find if a table is available
          int table = -1;
          for(int i = 0; i < tables.length; i++){
            if(tables[i] == null){
              table = i;
              break;
            }
          }
          
          //if no table was found, print a message indicating this, and break back to the option menu.
          if(table == -1){
           System.out.println("No tables available");
           break;
          }
          int items = 0;        
          //Ask how many items will be ordered until a number > 0 has been entered.
          do{
            System.out.println("How many menu items will this table be having?");
            items = keyboard.nextInt();
          }while(!(items > 0));
          
          //create an int[] representing the menu items being ordered, and place it at the available table.
          //the menu items will be filled when the customer places their order.
          tables[table] = new int[items];
          System.out.printf("Table %d has been seated.\n\n", table);
          
          break;
          
          
          
        case 2:
          //ADD ORDER
          //Ask which table is paying until a choice between 0 and 11 has been entered
          int orderTable;
          do{
            System.out.println("Which table is ordering? Enter a table from 0 to 11.");
            orderTable = keyboard.nextInt();
          }while(orderTable < 0 || orderTable > 11);
          
          //if nobody is sitting at that table, break back to the option menu.
          if(tables[orderTable] == null){
           System.out.println("There is nobody at that table!"); 
           break;
          }
          
          //if that table has already ordered, break back to the option menu.
          if(tables[orderTable][0] != 0){
            System.out.println("That table has already ordered!"); 
            break;
          }
          
          //Print out a formatted list of all the items on the menu          
          System.out.println("Choose items from the menu:");
          for(int i = 1; i < menuItemName.length; i++){
           System.out.printf("%2d. %-15s",i,menuItemName[i]);
           //Every third item, we print a new line to make the menu more readable.
           if(i%3 == 0){
            System.out.println(); 
           }
          }
          
          //Ask for each item to be added to the table's list of orders.
          for(int i = 0; i<tables[orderTable].length; i++){
            int orderedItem;
            //Make sure each input menu item corresponds to an item that is actually on the menu.
            do{
              System.out.printf("\nEnter the next item to be ordered. Enter a value 1 to %d\n", menuItemName.length - 1);
              orderedItem = keyboard.nextInt();
            }while(orderedItem < 1 || orderedItem > menuItemName.length);
            //add each individual item to the list of the table's ordered items.
            tables[orderTable][i] = orderedItem;
          }  
          break;
          
          
          
        case 3:
          //ACCEPT PAYMENT
          //Ask which table is paying until a choice between 0 and 11 has been entered
          int payTable;
          do{
            System.out.println("Which table is paying? Enter a table from 0 to 11.");
            payTable = keyboard.nextInt();
          }while(payTable < 0 || payTable > 11);
          
          //if nobody is sitting at that table, break back to the option menu.
          if(tables[payTable] == null){
           System.out.println("There is nobody at that table!"); 
           break;
          }
          
          //if that table has yet to order, break back to the option menu.
          if(tables[payTable][0] == 0){
            System.out.println("That table hasn't ordered yet!"); 
            break;
          }
          
          //Calculate the amount the table owes
          double owedAmount = 0;
          for(int i = 0; i<tables[payTable].length; i++){
            //determine the price of each menuItem that is in the list of items ordered by the table and add it.
            owedAmount += menuItemPrice[tables[payTable][i]];
          }
          //print out the owed amount to 2 decimal places.
          System.out.printf("Table %d owes $%.2f\n",payTable,owedAmount);
          
          //Ask them whether they are paying via debit credit or cash.
          String payMethod;
          do{
            System.out.println("How is this table paying? Enter debit, credit, or cash.");
            payMethod = keyboard.next();
          }while(!(payMethod.equals("debit") || payMethod.equals("credit") || payMethod.equals("cash")));
        
          //move the array of ints representing the tables order into the paidTables array.
          paidTables[totalPaidTables] = tables[payTable];
          //put the payment method this table used at the associated index in the payMethod array.
          paidTablePayMethod[totalPaidTables] = payMethod;
          //increment the total amount of tables that have paid.
          totalPaidTables++;
          //empty the table from the tables array, allowing for new customers to come in.
          tables[payTable] = null;
          break;
        
        
        
        case 4:
          //CLOSE RESTAURANT
          
          //Print a message indicating the close, and modify closeRestaurant so we break out of the loop.
          System.out.println("Closing Restaurant");
          closeRestaurant = true;
          
          //Check to make sure there are no occupied tables, if a table is occupied print a message.
          for(int i = 0; i<tables.length; i++){
            if(!(tables[i] == null)){
              System.out.printf("Cannot close - Table %d is occupied\n",i);
              closeRestaurant = false;
            }
          }
          break;
          
        default:
          //If the user enters a value that isn't 1-4, they have done something wrong.
          System.out.println("invalid option - please enter a number 1 - 4");
      }
      //Repeat until the restaurant is closed.
    }while(!closeRestaurant);
    
    //Compute the amount of money the restaurant took in from cash/debit/credit
    //as well as the amount we spent on ingredients before computing total profit.
    double creditTransactions = 0;
    double debitTransactions = 0;
    double cashTransactions = 0;
    double ingredientTotal = 0;
    
    //iterate the the orders of each paid table.
    for(int i = 0; i<totalPaidTables; i++){
      //iterate through each menu item the table has ordered.
      for(int j = 0; j<paidTables[i].length; j++){
        //identify the menuItem in question
        int menuItem = paidTables[i][j];
        //check how the table paid, and add the cost of the meal to the appropriate sum.
        if(paidTablePayMethod[i].equals("debit")){
          debitTransactions += menuItemPrice[menuItem];
        }else if(paidTablePayMethod[i].equals("credit")){
          creditTransactions += menuItemPrice[menuItem];
        }else if(paidTablePayMethod[i].equals("cash")){
          cashTransactions += menuItemPrice[menuItem];
        }else{
          System.out.println("Invalid payment method accepted - something went wrong!");
        }
        //for each ingredient used to make the item in question, 
        for(int ingr = 0; ingr <menuItemIngredients[menuItem].length; ingr++){
          int ingredientIndex = menuItemIngredients[menuItem][ingr];
          ingredientTotal += ingredientCost[ingredientIndex];
        }
      }
    }
    
    //Print out the amount made from various transactions, amount paid for ingredients, and total profit made.
    System.out.printf("We made %7.2f from credit.\n",creditTransactions);
    System.out.printf("We made %7.2f from debit.\n",debitTransactions);
    System.out.printf("We made %7.2f from cash.\n",cashTransactions);
    System.out.printf("We paid %7.2f on ingredients.\n",ingredientTotal);
    System.out.printf("We made %7.2f total profit.\n",creditTransactions + debitTransactions + cashTransactions - ingredientTotal);
  }
  
  
  //load the list of ingredients from ingredients.csv, load menuitems from menu.csv
  public void loadFiles() throws FileNotFoundException{
    
    //LOAD INGREDIENTS
    //Start by counting the lines in our ingredients file to find out how many ingredients we have.
    File ingredientsFile = new File("/Users/mayaesar/Desktop/school/my classes/2nd semester/java |/assignment4/out/production/assignment4/ingredients.csv");
    Scanner ingredientScan = new Scanner(ingredientsFile);
    int ingredientsCount = 0;
    //For each line in the file, add one to our count of total ingredients
    while(ingredientScan.hasNextLine()){
      ingredientScan.nextLine();
      ingredientsCount++;
    }
    
    //initialize our ingredient arrays to be big enough to hold all of our ingredients
    this.ingredientName = new String[ingredientsCount];
    this.ingredientCost = new double[ingredientsCount];

    
    //Reset our scanner to start from the top of the file again
    ingredientScan = new Scanner(ingredientsFile);
    int ingredientNo = 0;
    //so long as we have more lines in our file
    while(ingredientScan.hasNextLine()){
      //break up incoming lines using split, which breaks up the input into a number of strings around commas (,)
      String[] inputLine = ingredientScan.nextLine().split(",");
      //the first item on each line is the name of the ingredient
      this.ingredientName[ingredientNo] = inputLine[0];
      //the second item on each line is the cost of the ingredient, which we parse into a double
      this.ingredientCost[ingredientNo] = Double.parseDouble(inputLine[1]);
      ingredientNo++;
    }
    
    //LOAD MENU ITEMS
    //Start by counting the lines in our menu items file to find out how many menu items we have.
    File menuFile = new File("/Users/mayaesar/Desktop/school/my classes/2nd semester/java |/assignment4/out/production/assignment4/menuitems.csv");
    Scanner menuItemScan = new Scanner(menuFile);
    int menuItemsCount = 0;
    //For each line in the file, add one to our count of total menu items
    while(menuItemScan.hasNextLine()){
      menuItemScan.nextLine();
      menuItemsCount++;
    }
    
    this.menuItemIngredients = new int[menuItemsCount][];
    this.menuItemName = new String[menuItemsCount];
    this.menuItemPrice = new double[menuItemsCount];

    //Reset our scanner to start from the top of the file again
    menuItemScan = new Scanner(menuFile);
    int menuNo = 0;
    
    //So long as we have more lines in our input file
    while(menuItemScan.hasNextLine()){
      //break up incoming lines using split, which breaks up the input into a number of strings around commas (,)
      String[] inputLine = menuItemScan.nextLine().split(",");
      //the first item on each line is the name of the menu item
      this.menuItemName[menuNo] = inputLine[0];
      //the second item on each line is the price of that menu item
      this.menuItemPrice[menuNo] = Double.parseDouble(inputLine[1]);
      //all subsequent items on the line are the ingredients that are used to make the menu item
      //the number of ingredients for a given menu item is the number of entries on that line - 2
      int[] ingredientList = new int[inputLine.length - 2];
      //loop through each ingredient on the input line to build up our array of ingredients
      for(int i = 2; i<inputLine.length; i++){
        //Search our array of ingredients to find that ingredient, 
        for(int j = 0; j < this.ingredientName.length; j++){
          //once we find the ingredient, associate the index of that ingredient with the menu item in question
          if(inputLine[i].equals(ingredientName[j])){
            ingredientList[i - 2] = j;
          }
        }
      }
      //finally, once our ingredient list has been built, associate that list of ingredients with the
      //corresponding menu item
      this.menuItemIngredients[menuNo] = ingredientList;
      menuNo++;
    }
  }
}