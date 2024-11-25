package use_case.history;

import javax.swing.*;
import java.time.LocalDate;

/**
 * The input data for the Calendar
 */
public class HistoryInputData {

    LocalDate date;

    public HistoryInputData(LocalDate date) {
        this.date = date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
