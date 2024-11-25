package use_case.history;

import data.DayInfo;

import java.time.LocalDate;

public interface HistoryDataAccessInterface {

    boolean nextDayExists(LocalDate date);

    boolean previousDayExists(LocalDate date);

    DayInfo getNextDay(LocalDate date);

    DayInfo getPreviousDay(LocalDate date);
}
