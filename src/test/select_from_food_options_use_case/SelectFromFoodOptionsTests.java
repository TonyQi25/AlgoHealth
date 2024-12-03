package select_from_food_options_use_case;
import data_access.InMemoryFoodSelectionDAO;
import interface_adapter.select_from_food_options.SelectFromFoodOptionsPresenter;
import org.junit.jupiter.api.Test;
import use_case.display_food_options.InMemoryFoodSelectionDataAccessInterface;
import use_case.select_from_food_options.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SelectFromFoodOptionsTests {

    @Test
    void SelectFromFoodOptionsFoundMatchTest() {
        InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO = new InMemoryFoodSelectionDAO();
        HashMap<String, Integer> foodMap = new HashMap<String, Integer>();
        foodMap.put("Beef, New Zealand, imported, manufacturing beef, cooked, boiled", 174730);
        inMemoryFoodSelectionDAO.setCurrOptionsMap(foodMap);

        SelectSearchDataAccessInterface mockGetByFdcIdDAO = new InMemoryGetByFdcIdDAO();
        SelectFromFoodOptionsInputData mockInputData = new SelectFromFoodOptionsInputData(
                "Beef, New Zealand, imported, manufacturing beef, cooked, boiled", "");
        SelectFromFoodOptionsOutputBoundary selectFromFoodOptionsPresenter = new SelectFromFoodOptionsOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectFromFoodOptionsOutputData selectFromFoodOptionsOutputData) {
                assertEquals(selectFromFoodOptionsOutputData.getFoodSelection(),
                        "Beef, New Zealand, imported, manufacturing beef, cooked, boiled");
            }
        };

        SelectFromFoodOptionsInputBoundary selectFromFoodOptionsInteractor = new SelectFromFoodOptionsInteractor(
                inMemoryFoodSelectionDAO,
                mockGetByFdcIdDAO,
                selectFromFoodOptionsPresenter);
        selectFromFoodOptionsInteractor.execute(mockInputData);

    }
    @Test
    void SelectFromFoodOptionsNoMatchTest() {
        SelectFromFoodOptionsInputData selectFromFoodOptionsInputData = new SelectFromFoodOptionsInputData(
                "hegy62", "No food found.");
        InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO = new InMemoryFoodSelectionDAO();
        SelectSearchDataAccessInterface mockGetByFdcIdDAO = new InMemoryGetByFdcIdDAO();
        SelectFromFoodOptionsOutputBoundary selectFromFoodOptionsOutputBoundary =
                new SelectFromFoodOptionsOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectFromFoodOptionsOutputData selectFromFoodOptionsOutputData) {
                assertEquals(selectFromFoodOptionsOutputData.getFoodSelection(), "hegy62");
            }
        };

        SelectFromFoodOptionsInputBoundary selectFromFoodOptionsInteractor = new SelectFromFoodOptionsInteractor(
                inMemoryFoodSelectionDAO,
                mockGetByFdcIdDAO,
                selectFromFoodOptionsOutputBoundary);
        selectFromFoodOptionsInteractor.execute(selectFromFoodOptionsInputData);

    }
}
