package model;

public class Table {
    private MenuItem[] order;
    private int size;
    private double receipt;
    private String paymentMethod;

    public Table(int numMenuItems){
        this.order = new MenuItem[numMenuItems];
        this.size=0;
        this.receipt = 0;
    }

    public MenuItem[] getOrder(){
        return this.order;
    }

    public void add(int table, int index){
        for(int i = 0; i < order.length; i++){
            receipt += order[table].getPrice(index);
            size++;
        }
    }

    public boolean hasOrdered(){
        boolean order = false;
        if (size == this.order.length){
            order = true;
        }
        return order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getSize() {
        return size;
    }

    public double getReceipt() {
        return receipt;
    }
}
