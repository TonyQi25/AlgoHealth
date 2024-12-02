package use_case.food_logging;

public class DataAccessException extends Exception{
    public DataAccessException(String string) {
        super(string);
    }
}
