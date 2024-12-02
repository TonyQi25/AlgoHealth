package use_case.history;

import org.json.JSONObject;
import use_case.one_day_history.UpdateHistoryTotalsInputData;
import use_case.removeFood.RemoveFoodInputData;

import java.util.ArrayList;
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

//        HashMap<String, DayInfo> sample = new HashMap<>();
//        DayInfo day1 = new DayInfo(LocalDate.now().minusDays(1));
//        HashMap<String,Object> cal1 = new HashMap<>();
//        HashMap<String,Object> cal2 = new HashMap<>();
//        cal1.put("amount per 100",6);
//        cal2.put("amount per 100",12);
//        day1.addToFoodLog(new Food("apple", 200.0F, cal1));
//        day1.addToFoodLog(new Food("banana", 200.0F, cal2));
//        sample.put(day1.getDate().toString(), day1);
//
//        DayInfo day2 = new DayInfo(LocalDate.now().plusDays(1));
//        HashMap<String,Object> cal3 = new HashMap<>();
//        HashMap<String,Object> cal4 = new HashMap<>();
//        cal3.put("amount per 100",6);
//        cal4.put("amount per 100",12);
//        day2.addToFoodLog(new Food("orange", 200.0F, cal3));
//        day2.addToFoodLog(new Food("watermelon", 200.0F, cal4));
//        sample.put(day2.getDate().toString(), day2);
//
//        DayInfo day3 = new DayInfo(LocalDate.now());
//        HashMap<String,Object> cal5 = new HashMap<>();
//        HashMap<String,Object> cal6 = new HashMap<>();
//        cal3.put("amount per 100",6);
//        cal4.put("amount per 100",12);
//        day3.addToFoodLog(new Food("Pineapple", 200.0F, cal5));
//        day3.addToFoodLog(new Food("Cheery", 200.0F, cal6));
//        sample.put(day3.getDate().toString(), day3);

        System.out.println("calling history usecase for " + input.getUsername() + " " + input.getDate());

        if (historyDataAccessInterface.DayExists(input.getDate().toString(), input.getUsername())) {
            JSONObject idToInfo = historyDataAccessInterface.loadFoodInfo(input.getUsername(), input.getDate().toString());

            List<String> returning = new ArrayList<>();

            for (String id : idToInfo.keySet()) {
                String info = "";

                info += idToInfo.getJSONObject(id).getString("name") + ": ";
                info += idToInfo.getJSONObject(id).getDouble("weight") + "(g/ml)";

                returning.add(info);
            }

            System.out.println("Here is the foodlist recieved" + returning);
            final HistoryOutputData output = new HistoryOutputData(returning, input.getDate().toString(), input.getDate(), false);
            historyOutputBoundary.prepareSuccessView(output);
        }   else {
            System.out.println("DAO RETURNING NO DAY FOUND");
            historyOutputBoundary.prepareFailView("Day Not Found");
        }
    }

    public void removeHighlightedFood (RemoveFoodInputData input) {
        System.out.println("IN HISTORY USECASE password: " + input.getPassword());
        historyOutputBoundary.prepareRemoveFoodView(input);
    }

    public void goBack() {
        historyOutputBoundary.prepareMainView();
    }

    public void viewOneDay(UpdateHistoryTotalsInputData input) {
        historyOutputBoundary.viewOneDay(input);
    }
}
