package interface_adapter.history;

import data.DayInfo;
import data.Food;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryPresenter implements HistoryOutputBoundary {
    private HistoryViewModel viewModel;
    private HistoryOutputData outputData;

    public HistoryPresenter(HistoryViewModel viewModel, HistoryOutputData outputData) {
        this.outputData = outputData;
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView() {
        DayInfo dayInfo = outputData.getDayInfo();
        List<Food> foods = dayInfo.getFoodLog();
        List<String> returning = new ArrayList<>();

        for (Food food : foods) {
            String info = "";
            info += food.getDescription() + ":";
            info += food.getTotalCalories() + " calories";
            // blah blah blah

            returning.add(info);
        }

        viewModel.setDate(dayInfo.getDate().toString());
        viewModel.setDayDetails(returning);
        viewModel.setState(new HistoryState());
    }
}
