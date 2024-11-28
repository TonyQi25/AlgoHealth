package data_access;

import data.DayInfo;
import use_case.history.HistoryDataAccessInterface;
import use_case.removeFood.RemoveFoodDataAccessInterface;

import java.time.LocalDate;

public class TempHistoryDAO implements HistoryDataAccessInterface, RemoveFoodDataAccessInterface {

    @Override
    public boolean DayExists(String date, String username) {
        return false;
    }

    @Override
    public DayInfo getDay(String date, String username) {
        return null;
    }

    @Override
    public boolean foodExists(String foodName, String username) {
        return false;
    }

    @Override
    public boolean removeFood(String foodName, String username) {
        return false;
    }
}
