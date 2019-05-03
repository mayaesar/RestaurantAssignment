package model;

public class Restaurant {

    //CONSTANTS
    public static final String DEBIT = "debit";
    public static final String CREDIT = "credit";
    public static final String CASH = "cash";

    private double totalDebit;
    private double totalCredit;
    private double totalCash;
    private double totalIngredients;
    private double totalTotal;

    public Restaurant() {
        totalTotal=0;
        totalIngredients=0;
        totalCash=0;
        totalCredit=0;
        totalDebit=0;
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public double getTotalIngredients() {
        return totalIngredients;
    }

    public void setTotalIngredients(double totalIngredients) {
        this.totalIngredients = totalIngredients;
    }

    public double getTotalTotal() {
        return totalTotal;
    }

    public void setTotalTotal(double totalTotal) {
        this.totalTotal = totalTotal;
    }
}
