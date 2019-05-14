package model;

import java.io.FileNotFoundException;

public class TestTable {
    private static Table table;
    private static MenuList order;
    private static int size = 2;
    private static double receipt = 56.00003;
    private static PayMethod paymentMethod = PayMethod.DEBIT;
    private static int orderingNum=3;
    private static int index = 5;

    public static void main(String[] args) throws FileNotFoundException {
        table = new Table(orderingNum);


    }

}
