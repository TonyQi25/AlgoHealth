package daily_value_use_case;



import org.junit.jupiter.api.Test;
import use_case.daily_value_recs.DailyValueCalculationStrategy;
import use_case.daily_value_recs.DailyValueRecsInputBoundary;
import use_case.daily_value_recs.DailyValueRecsIntakeData;
import use_case.daily_value_recs.DailyValueRecsOutputData;
import use_case.daily_value_recs.HealthCanadaDVstrategy;
import use_case.daily_value_recs.DailyValueRecsOutputBoundary;
import use_case.daily_value_recs.DailyValueRecsInteractor;


import static org.junit.Assert.assertEquals;


public class DailyValueUseCaseTests {

    @Test
    void DailyValueRecsInteractorIntegerIntakeTest() {
        DailyValueCalculationStrategy mockDvStrategy = new HealthCanadaDVstrategy();
        DailyValueRecsIntakeData intakeData = new DailyValueRecsIntakeData(500, 20, 2, 5);
        DailyValueRecsOutputBoundary mockPresenter = new DailyValueRecsOutputBoundary() {
            @Override
            public void prepareSuccessView(DailyValueRecsOutputData dailyValueRecsOutputData) {
                assertEquals(dailyValueRecsOutputData.getPercent_cals(), 25, 0);
                assertEquals(dailyValueRecsOutputData.getPercent_prot(), 37, 1);
                assertEquals(dailyValueRecsOutputData.getPercent_carbs(), 2, 0);
                assertEquals(dailyValueRecsOutputData.getPercent_fat(), 2.5, 0);
            }
        };
        DailyValueRecsInputBoundary DVInteractor = new DailyValueRecsInteractor(mockPresenter, mockDvStrategy);
        DVInteractor.execute(intakeData);
    }
}
