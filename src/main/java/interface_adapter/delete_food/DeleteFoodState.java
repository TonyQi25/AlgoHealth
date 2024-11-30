package interface_adapter.delete_food;

import java.util.ArrayList;
import java.util.List;

public class DeleteFoodState {

    private String username = "";
    private String password = "";
    private List<String> foods = new ArrayList<>();
    private List<String> selectedFoods = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }

    public List<String> getSelectedFoods() {
        return selectedFoods;
    }

    public void setSelectedFoods(List<String> selectedFoods) {
        this.selectedFoods = selectedFoods;
    }
}
