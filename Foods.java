package POS;

public class Foods {
    private String foodName, foodID;
    private double foodPrice;

    public Foods(){
        foodName = "";
        foodID = "";
        foodPrice = 0.00;
    }

    public Foods(String newFood, String newFoodID, double price){
        foodName = newFood;
        foodID = newFoodID;
        foodPrice = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodID() {
        return foodID;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
}
