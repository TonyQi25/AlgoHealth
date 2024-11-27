package use_case.removeFood;

public interface RemoveFoodDataAccessInterface {

    boolean foodExists(String foodName, String username); // maybe more parameters

    boolean removeFood(String foodName, String username);



}
