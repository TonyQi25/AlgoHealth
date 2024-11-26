package data_access;

import data.DayInfo;
import use_case.history.HistoryDataAccessInterface;

import java.time.LocalDate;

public class TempHistoryDAO implements HistoryDataAccessInterface {

    @Override
    public boolean DayExists(LocalDate date, String username) {
        return false;
    }

    @Override
    public DayInfo getDay(LocalDate date, String username) {
        return null;
    }
}
