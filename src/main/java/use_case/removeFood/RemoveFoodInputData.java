package use_case.removeFood;

/**
 * input data for remove food use case
 */
public class RemoveFoodInputData {

    private String foodName;
    private double weight;
    private String username;
    private String password;
    private String viewingDate;

    public RemoveFoodInputData(String foodName, double weight, String username, String date, String password) {
        this.foodName = foodName;
        this.weight = weight;
        this.username = username;
        this.viewingDate = date;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
