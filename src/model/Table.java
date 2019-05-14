package model;

import java.io.FileNotFoundException;

public class Table {
    private MenuList order;
    private int size;
    private double receipt;
    private PayMethod paymentMethod;
    private double ingredientCost;
    private int orderingNum;

    public Table(int numMenuItems) throws FileNotFoundException {
        this.orderingNum = numMenuItems;
        this.order = new MenuList();
        this.ingredientCost =0;
        this.size = 0;
        this.receipt = 0;
    }

    public void addToOrder(int index){
        receipt += order.getItemPrice(index);
        ingredientCost += order.getIngPrice(index);
        size++;
    }

    public boolean hasOrdered(){
        boolean order = false;
        if (size == this.orderingNum-1) {
            order = true;
        }
        return order;
    }

    public PayMethod setPaymentMethod(String paymentMethod) {
        if (paymentMethod.equals("cash")){
            return this.paymentMethod = PayMethod.CASH;
        }
        else if (paymentMethod.equals("debit")) {
            return this.paymentMethod = PayMethod.DEBIT;
        }
        else {
            return this.paymentMethod = PayMethod.CREDIT;
        }
    }

    public int getOrderingNum(){
        return orderingNum;
    }

    public double getIngredientCost(){
       return ingredientCost;
    }

    public int getSize() {
        return size;
    }

    public double getReceipt() {
        return receipt;
    }
}
