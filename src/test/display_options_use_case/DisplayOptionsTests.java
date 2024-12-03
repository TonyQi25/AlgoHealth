package display_options_use_case;
import data_access.InMemoryFoodSelectionDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.daily_value_recs.HealthCanadaDVstrategy;
import use_case.display_food_options.*;

import java.util.HashMap;

import static java.util.Arrays.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DisplayOptionsTests {

    @Test
    void DisplayOptionsSuccessTest() {
        DisplayFoodOptionsDataAccessInterface mockSearchDao1 = new inMemoryFdaSearchSuccessDataAccessObject();
        DisplayFoodOptionsInputData mockInputData1 = new DisplayFoodOptionsInputData("beef");

        DisplayFoodOptionsDataAccessInterface mockSearchDao2 = new inMemoryFdaSearchFailDataAccessObject();
        DisplayFoodOptionsInputData mockInputData2 = new DisplayFoodOptionsInputData("bei32ff");

        InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO = new InMemoryFoodSelectionDAO();
        DisplayFoodOptionsOutputBoundary displayFoodOptionsPresenter = new DisplayFoodOptionsOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayOptionsOutputData displayOptionsOutputData) {
                String[] expectedFoodList = {"Beef shank", "Beef, ribs, fat left on", "Steak, round"};
                sort(expectedFoodList);
                sort(displayOptionsOutputData.getFoodList());
                int i = 0;
                while (i < displayOptionsOutputData.getFoodList().length) {
                    assertEquals(displayOptionsOutputData.getFoodList()[i], expectedFoodList[i]);
                    i += 1;
                }
            }

            @Override
            public void prepareFailView(DisplayOptionsOutputData displayOptionsOutputData) {
                String[] expectedFoodList = {};
                boolean booli = expectedFoodList.equals(displayOptionsOutputData.getFoodList());
                assertEquals(expectedFoodList, displayOptionsOutputData.getFoodList());
            }
        };

        // success interactor
        DisplayFoodOptionsInteractor displayFoodOptionsInteractor1 = new DisplayFoodOptionsInteractor(
                displayFoodOptionsPresenter, mockSearchDao1, inMemoryFoodSelectionDAO);
        displayFoodOptionsInteractor1.execute(mockInputData1);
    }

    @Test
    void DisplayOptionsFailTest() {
        DisplayFoodOptionsDataAccessInterface mockSearchDao2 = new inMemoryFdaSearchFailDataAccessObject();
        DisplayFoodOptionsInputData mockInputData2 = new DisplayFoodOptionsInputData("bei32ff");

        InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO = new InMemoryFoodSelectionDAO();
        DisplayFoodOptionsOutputBoundary displayFoodOptionsPresenter = new DisplayFoodOptionsOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayOptionsOutputData displayOptionsOutputData) {
            }

            @Override
            public void prepareFailView(DisplayOptionsOutputData displayOptionsOutputData) {
                String[] expectedFoodList = {};
                assertEquals(expectedFoodList, displayOptionsOutputData.getFoodList());
            }
        };

        //fail interactor
        DisplayFoodOptionsInteractor displayFoodOptionsInteractor2 = new DisplayFoodOptionsInteractor(
                displayFoodOptionsPresenter, mockSearchDao2, inMemoryFoodSelectionDAO);
        displayFoodOptionsInteractor2.execute(mockInputData2);
    }
}

