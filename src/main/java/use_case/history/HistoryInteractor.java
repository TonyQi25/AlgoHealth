package use_case.history;

import data.DayInfo;
import data.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryInteractor implements HistoryInputBoundary{

    private final HistoryInputData historyInputData;
    private final HistoryOutputBoundary historyOutputBoundary;
    private final HistoryOutputData historyOutputData;
    private final HistoryDataAccessInterface historyDataAccessInterface;

    public HistoryInteractor(HistoryInputData historyInputData, HistoryOutputBoundary historyOutputBoundary,
                             HistoryOutputData outputData, HistoryDataAccessInterface dataAccessInterface) {

        this.historyInputData = historyInputData;
        this.historyOutputBoundary = historyOutputBoundary;
        this.historyOutputData = outputData;
        this.historyDataAccessInterface = dataAccessInterface;
    }

    public void execute() {
        HashMap<String, DayInfo> sample = new HashMap<>();
        DayInfo day1 = new DayInfo(LocalDate.now());
        HashMap<String,Object> cal1 = new HashMap<>();
        HashMap<String,Object> cal2 = new HashMap<>();
        cal1.put("amount per 100",6);
        cal2.put("amount per 100",12);
        day1.addToFoodLog(new Food("apple", 200.0F, cal1));
        day1.addToFoodLog(new Food("banana", 200.0F, cal2));
        sample.put(day1.getDate().toString(), day1);

        System.out.println(day1.getDate().toString());


        historyOutputData.setDayInfo(day1);
        historyOutputBoundary.prepareSuccessView();


    }
}
