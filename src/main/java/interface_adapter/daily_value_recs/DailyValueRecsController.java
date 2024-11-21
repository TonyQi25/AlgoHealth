package interface_adapter.daily_value_recs;

import use_case.daily_value_recs.DailyValueRecsInputBoundary;
import use_case.daily_value_recs.DailyValueRecsIntakeData;

public class DailyValueRecsController {

    private final DailyValueRecsInputBoundary dailyValueRecsUseCaseInteractor;

    public DailyValueRecsController(DailyValueRecsInputBoundary dailyValueRecsUseCaseInteractor) {
        this.dailyValueRecsUseCaseInteractor = dailyValueRecsUseCaseInteractor;
    }

    public void execute(double calories, double protein, double carbs, double fat) {
        DailyValueRecsIntakeData dailyValueRecsIntakeData = new DailyValueRecsIntakeData(calories, protein, carbs, fat);
        dailyValueRecsUseCaseInteractor.execute(dailyValueRecsIntakeData);
    }
}
