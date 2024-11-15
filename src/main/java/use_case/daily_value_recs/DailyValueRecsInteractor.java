package use_case.daily_value_recs;

public class DailyValueRecsInteractor implements DailyValueRecsInputBoundary {

    private final DailyValueRecsOutputBoundary dailyValueRecsPresenter;

    // Take state information from main view, get it from controller,
    // update the recommendations view.

    // constants for DVs based on https://www.canada.ca/en/health-canada/services/food-nutrition/
    // healthy-eating/dietary-reference-intakes/tables/reference-values-macronutrients.html
    // and assumed body weight of 80kg and age 19-30 and assumed recommended calories 2000 Kcal.
    // fat intake is just a randomly selected number right now as there is no indication of its value in the link.

    final static double DVcals = 2000;
    final static double DVprot = 80 * 0.66;
    final static double DVcarbs = 100;
    final static double DVfat = 200 ;

    public DailyValueRecsInteractor(DailyValueRecsOutputBoundary dailyValueRecsPresenter) {
        this.dailyValueRecsPresenter = dailyValueRecsPresenter;
    }
    @Override
    public void execute(DailyValueRecsIntakeData dailyValueRecsIntakeData) {
        double percent_cals = (dailyValueRecsIntakeData.getCalories() / DVcals) * 100;
        double percent_prot = (dailyValueRecsIntakeData.getProtein() / DVprot) * 100;
        double percent_carbs = (dailyValueRecsIntakeData.getCarbs() / DVcarbs) * 100;
        double percent_fat = (dailyValueRecsIntakeData.getFat() / DVfat) * 100;
        DailyValueRecsOutputData dailyValueRecsOutputData = new DailyValueRecsOutputData(percent_cals, percent_prot,
        percent_carbs, percent_fat);
        dailyValueRecsPresenter.prepareSuccessView(dailyValueRecsOutputData);


    }
}
