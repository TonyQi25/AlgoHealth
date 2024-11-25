package data_access;

import data.DayInfo;
import use_case.history.HistoryDataAccessInterface;

import java.time.LocalDate;

public class TempHistoryDAO implements HistoryDataAccessInterface {
    @Override
    public boolean nextDayExists(LocalDate date) {
        return false;
    }

    @Override
    public boolean previousDayExists(LocalDate date) {
        return false;
    }

    @Override
    public DayInfo getNextDay(LocalDate date) {
        return null;
    }

    @Override
    public DayInfo getPreviousDay(LocalDate date) {
        return null;
    }
}
