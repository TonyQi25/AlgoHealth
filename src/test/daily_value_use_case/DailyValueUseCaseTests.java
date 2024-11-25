//package daily_value_use_case;
//
//import org.junit.jupiter.api.Test;
//import use_case.daily_value_recs.*;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class DailyValueUseCaseTests {
//
//    @Test
//    void DailyValueRecsInteractorIntegerIntakeTest() {
//        DailyValueRecsIntakeData intakeData = new DailyValueRecsIntakeData(500, 20, 2, 5);
//        DailyValueRecsOutputBoundary mockPresenter = new DailyValueRecsOutputBoundary() {
//            @Override
//            public void prepareSuccessView(DailyValueRecsOutputData dailyValueRecsOutputData) {
//                assertEquals(dailyValueRecsOutputData.getPercent_cals(), 25, 0);
//                assertEquals(dailyValueRecsOutputData.getPercent_prot(), 37, 1);
//                assertEquals(dailyValueRecsOutputData.getPercent_carbs(), 2, 0);
//                assertEquals(dailyValueRecsOutputData.getPercent_fat(), 2.5, 0);
//            }
//        };
//        DailyValueRecsInputBoundary DVInteractor = new DailyValueRecsInteractor(mockPresenter);
//        DVInteractor.execute(intakeData);
//    }
//}
