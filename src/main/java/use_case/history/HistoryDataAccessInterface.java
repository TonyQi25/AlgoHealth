package use_case.history;

import data.DayInfo;

import java.time.LocalDate;

public interface HistoryDataAccessInterface {

    boolean DayExists(String date, String username);

    DayInfo getDay(String date, String username);
}
