package use_case.removeFood;

public interface RemoveFoodDataAccessInterface {

    Integer foodExists(String viewingDate, String username, String foodName); // maybe more parameters

    boolean removeFood(String viewingDate, String username, String password, String fdcID);



}
