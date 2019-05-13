package model;

public class Table {
    private MenuItem[] order;
    private int size;
    private double receipt;
    private PayMethod paymentMethod;

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

    public PayMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod.equals("cash")){
            this.paymentMethod = PayMethod.CASH;
        }
        if (paymentMethod.equals("debit")) {
            this.paymentMethod = PayMethod.DEBIT;
        }
        if (paymentMethod.equals("credit")){
            this.paymentMethod = PayMethod.CREDIT;
        }

    }

    public int getSize() {
        return size;
    }

    public double getReceipt() {
        return receipt;
    }
}
