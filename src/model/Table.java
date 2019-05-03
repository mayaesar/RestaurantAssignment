package model;

public class Table {
    private MenuItem[] order;
    private int size;
    private double receipt;
    private String paymentMethod;

    public Table(int numMenuItems){
        this.order = new MenuItem[numMenuItems];
        this.size=0;
    }

    public MenuItem[] getOrder(){
        return this.order;
    }

    public void add(MenuItem adding){

        order[size]=adding;
        receipt += adding.getPrice();
        size++;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
