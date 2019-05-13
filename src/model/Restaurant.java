package model;

public class Restaurant {

    private double totalIngredients;
    private double totalTotal;

    public Restaurant() {
        totalTotal=0;
        totalIngredients=0;

    }

    public void setTotal(double paying){
        this.totalTotal += paying;
    }

}
