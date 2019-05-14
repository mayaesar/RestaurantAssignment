package model;

public class Restaurant {

    private double totalCash;
    private double totalDebit;
    private double totalCredit;
    private double totalIngredients;
    private double totalTotal;

    public Restaurant() {
        totalTotal=0;
        totalIngredients=0;
        totalCash=0;
        totalDebit=0;
        totalCredit=0;
    }

    public void addPay(PayMethod payMethod, double price){
        if(payMethod == PayMethod.CASH){
            totalCash += price;
        }
        if(payMethod == PayMethod.DEBIT){
            totalDebit += price;
        }
        if(payMethod == PayMethod.CREDIT){
            totalCredit += price;
        }
        totalTotal += price;
    }
    public void setTotalIngredients(double ingredientsCost){
        totalIngredients += ingredientsCost;
        totalTotal -= ingredientsCost;
    }

    public void endNight(){
        System.out.println("total cash: " + totalCash +
                "\n total debit: " + totalDebit +
                "\n total credit: " + totalCredit +
                "\n total ingredients: " + totalIngredients +
                "\n total profit: " + totalTotal);
    }
}
