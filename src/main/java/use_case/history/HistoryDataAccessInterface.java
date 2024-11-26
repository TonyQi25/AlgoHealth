package use_case.history;

import data.DayInfo;

import java.time.LocalDate;

public interface HistoryDataAccessInterface {

    boolean DayExists(LocalDate date, String username);

    DayInfo getDay(LocalDate date, String username);
}
