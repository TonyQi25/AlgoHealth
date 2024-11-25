package use_case.history;

import data.DayInfo;
import data.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryInteractor implements HistoryInputBoundary{

    private final HistoryOutputBoundary historyOutputBoundary;
    private final HistoryDataAccessInterface historyDataAccessInterface;

    public HistoryInteractor(HistoryOutputBoundary historyOutputBoundary,
                             HistoryDataAccessInterface dataAccessInterface) {

        this.historyOutputBoundary = historyOutputBoundary;
        this.historyDataAccessInterface = dataAccessInterface;
    }

    public void execute(HistoryInputData input) {
        final LocalDate date = input.getDate();

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

        //get the day info from the DAO

        List<String> returning = new ArrayList<>();

        for (Food food : day1.getFoodLog()) {
            String info = "";
            info += food.getDescription() + ":";
            info += food.getTotalCalories() + " calories";
            // blah blah blah

            returning.add(info);
        }

        final HistoryOutputData output = new HistoryOutputData(returning, day1.getDate().toString(), false);
        historyOutputBoundary.prepareSuccessView(output);


    }
}
