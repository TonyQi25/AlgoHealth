package use_case.removeFood;

public class RemoveFoodInputData {

    private String foodName;
    private double weight;

    RemoveFoodInputData(String foodName, double weight) {
        this.foodName = foodName;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public String getFoodName() {
        return foodName;
    }
}
