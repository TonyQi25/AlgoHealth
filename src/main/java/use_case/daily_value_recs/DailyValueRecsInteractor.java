package use_case.daily_value_recs;

import java.util.HashMap;

/**
 * Interactor for the daily value use case.
 */
public class DailyValueRecsInteractor implements DailyValueRecsInputBoundary {

    private static final int HUNDRED_FACTOR = 100;
    private final DailyValueRecsOutputBoundary dailyValueRecsPresenter;
    private final DailyValueCalculationStrategy dvStrategy;

    public DailyValueRecsInteractor(DailyValueRecsOutputBoundary dailyValueRecsPresenter,
                                    DailyValueCalculationStrategy DVConstantsAlgo) {
        this.dailyValueRecsPresenter = dailyValueRecsPresenter;
        this.dvStrategy = DVConstantsAlgo;
    }

    @Override
    public void execute(DailyValueRecsIntakeData dailyValueRecsIntakeData) {
        final HashMap<String, Double> dvs = dvStrategy.DVconstantsAlgo(19, 80, 180,
                "", "");
        final double percentCals = dailyValueRecsIntakeData.getCalories() / dvs.get("caloriesDVconstant")
                * HUNDRED_FACTOR;
        final double percentProt = (dailyValueRecsIntakeData.getProtein() / dvs.get("proteinDVconstant"))
                * HUNDRED_FACTOR;
        final double percentCarbs = (dailyValueRecsIntakeData.getCarbs() / dvs.get("carbsDVconstant"))
                * HUNDRED_FACTOR;
        final double percentFat = (dailyValueRecsIntakeData.getFat() / dvs.get("fatDVconstant"))
                * HUNDRED_FACTOR;
        final DailyValueRecsOutputData dailyValueRecsOutputData = new DailyValueRecsOutputData(percentCals,
                percentProt, percentCarbs, percentFat);
        dailyValueRecsPresenter.prepareSuccessView(dailyValueRecsOutputData);
    }
}
