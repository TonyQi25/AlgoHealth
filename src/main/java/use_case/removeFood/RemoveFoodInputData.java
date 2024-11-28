package use_case.removeFood;

public class RemoveFoodInputData {

    private String foodName;
    private double weight;
    private String username;
    private String viewingDate;

    public RemoveFoodInputData(String foodName, double weight, String username, String date) {
        this.foodName = foodName;
        this.weight = weight;
        this.username = username;
        this.viewingDate = date;
    }

    public double getWeight() {
        return weight;
    }

    public String getUsername(){
        return username;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getViewingDate() {
        return viewingDate;
    }
}
