package POS;

public class Drinks {

    private String drinkName, alcoholContent;
    private boolean alcoholic;
    private double drinkPrice;

    public Drinks(){
        drinkName = "";
        alcoholic = false;
        alcoholContent = "";
        drinkPrice = 0.00;
    }

    public Drinks(String newDrink, boolean alcohol, String abv, double price){
        drinkName = newDrink;
        alcoholic = alcohol;
        alcoholContent = abv;
        drinkPrice = price;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public boolean getAlcoholic() {
        return alcoholic;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }
}
