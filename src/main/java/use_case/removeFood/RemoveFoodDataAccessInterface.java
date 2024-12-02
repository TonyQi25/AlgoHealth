package use_case.removeFood;

/**
 * data access interface for remove food use case
 */
public interface RemoveFoodDataAccessInterface {

    /**
     * check if food exists in the database
     * @param viewingDate for which day
     * @param username for who
     * @param foodName for which food
     * @return the id of the food removed
     */
    Integer FoodExists(String viewingDate, String username, String foodName); // maybe more parameters

    /**
     * remove the food
     * @param viewingDate for which day
     * @param username for who
     * @param password password required for changes
     * @param fdcID the id of the food
     * @return boolean that says removed for not
     */
    boolean removeFood(String viewingDate, String username, String password, String fdcID);



}
