package interface_adapter.remove_food;

/**
 * state for remove food use case
 */
public class RemoveFoodState {

    private String foodName;
    private double weight;
    private String outputMessage;
    private String removeFoodError;
    private String viewingDate;
    private String username;
    private String password;
    private boolean completed;

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setRemoveFoodError(String removeFoodError) {
        this.removeFoodError = removeFoodError;
    }

    public void setViewingDate(String viewingDate) {
        this.viewingDate = viewingDate;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean getCompleted() {
        return completed;
    }


    public double getWeight() {
        return weight;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getRemoveFoodError() {
        return removeFoodError;
    }

    public String getUsername() {
        return username;
    }

    public String getViewingDate() {
        return viewingDate;
    }

    public String getOutputMessage() {
        return outputMessage;
    }
}
