package use_case.history;

import data.DayInfo;
import data.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryInteractor {
    private final HistoryInputData historyInputData;
    private final HistoryOutputBoundary historyOutputBoundary;
    private final HistoryDataAccessInterface historyDataAccessInterface;

    public HistoryInteractor(HistoryInputData historyInputData, HistoryOutputBoundary historyOutputBoundary,
                             HistoryDataAccessInterface dataAccessInterface) {

        this.historyInputData = historyInputData;
        this.historyOutputBoundary = historyOutputBoundary;
        this.historyDataAccessInterface = dataAccessInterface;
    }

    public void execute() {
        List<DayInfo> sample = new ArrayList<>();
        DayInfo day1 = new DayInfo(LocalDate.now());
        HashMap<String,Object> cal1 = new HashMap<>();
        HashMap<String,Object> cal2 = new HashMap<>();
        cal1.put("amount per 100",6);
        cal2.put("amount per 100",12);
        day1.addToFoodLog(new Food("apple", 200.0F, cal1));
        day1.addToFoodLog(new Food("banana", 200.0F, cal2));
        sample.add(day1);

        // historyOutputBoundary.execute


    }
}
