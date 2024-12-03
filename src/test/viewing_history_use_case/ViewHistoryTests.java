package viewing_history_use_case;

import data.Food;
import data_access.GradeAccountDAO;
import interface_adapter.history.HistoryPresenter;
import org.junit.jupiter.api.Test;
import use_case.daily_value_recs.*;
import use_case.history.*;
import use_case.one_day_history.UpdateHistoryTotalsInputData;
import use_case.removeFood.RemoveFoodInputData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class ViewHistoryTests {



    @Test
    void LoadPresentDayInfoTest() {
        // existing account info
        HistoryInputData historyInputData = new HistoryInputData(LocalDate.parse("2024-12-02"), "tonyqi25", "password");
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData output) {
                assertNotEquals(new ArrayList<>(), output.getFoodList());
                assertNotEquals(null, output.getFoodList());
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData response) {

            }

            @Override
            public void prepareMainView() {

            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.execute(historyInputData);

    }

    @Test
    void LoadPreviousDayInfoTest() {
        HistoryInputData historyInputData = new HistoryInputData(LocalDate.parse("2024-12-02").minusDays(1), "tonyqi25", "password");
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData output) {
                assertNotEquals(new ArrayList<>(), output.getFoodList());
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData response) {

            }

            @Override
            public void prepareMainView() {

            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.execute(historyInputData);
    }

    @Test
    void LoadNextDayInfoTest() {
        HistoryInputData historyInputData = new HistoryInputData(LocalDate.parse("2024-12-02").plusDays(1), "tonyqi25", "password");
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData output) {
                assertNotEquals(new ArrayList<>(), output.getFoodList());
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData response) {

            }

            @Override
            public void prepareMainView() {

            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.execute(historyInputData);
    }

    @Test
    void NoPreviousDayTest() {

        HistoryInputData historyInputData = new HistoryInputData(LocalDate.parse("2024-12-02").minusDays(5), "tonyqi25", "password");
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData output) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Day Not Found", errorMessage);
            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData response) {

            }

            @Override
            public void prepareMainView() {

            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.execute(historyInputData);


    }

    @Test
    void NoNextDayTest() {
        HistoryInputData historyInputData = new HistoryInputData(LocalDate.parse("2024-12-02").plusDays(2), "tonyqi25", "password");
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData output) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Day Not Found", errorMessage);
            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData response) {

            }

            @Override
            public void prepareMainView() {

            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.execute(historyInputData);
    }

    @Test
    void prepareRemoveFoodViewTest() {
        RemoveFoodInputData removeFoodInputData = new RemoveFoodInputData("fake name", 30.0, "tonyqi25", "2024-12-02", "password");
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData output) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData response) {
                assertEquals(response.getViewingDate(), "2024-12-02");
                assertEquals(response.getUsername(), "tonyqi25");
                assertEquals(response.getPassword(), "password");
                assertEquals(response.getFoodName(), "fake name");
                assertEquals(response.getWeight(), 30.0, 0);
            }

            @Override
            public void prepareMainView() {
            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.removeHighlightedFood(removeFoodInputData);
    }

    @Test
    void prepareViewOneDayTest() {
        UpdateHistoryTotalsInputData inputData = new UpdateHistoryTotalsInputData("tonyqi25", "2024-12-02", "password");
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {

            @Override
            public void prepareSuccessView(HistoryOutputData data) {

            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData input) {

            }

            @Override
            public void prepareMainView() {

            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.viewOneDay(inputData);
    }

    @Test
    void prapareMainViewTest() {
        HistoryDataAccessInterface historyDataAccessInterface = new GradeAccountDAO();
        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(HistoryOutputData output) {
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void prepareRemoveFoodView(RemoveFoodInputData response) {

            }

            @Override
            public void prepareMainView() {

            }

            @Override
            public void viewOneDay(UpdateHistoryTotalsInputData input) {

            }
        };
        HistoryInteractor historyInteractor = new HistoryInteractor(historyPresenter, historyDataAccessInterface);
        historyInteractor.goBack();
    }
}
