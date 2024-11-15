package app;

import api.callUsdaApi;
import data.DayInfo;
import data.Food;
import view.mainView;

import java.time.LocalDate;
import java.util.ArrayList;

import static api.callUsdaApi.genMyApiKey;
import static api.populateFromUsda.foodFromFirstResultUsda;
import static api.populateFromUsda.foodFromResultUsda;

class Main {
    public static void main(String[] args) {
        // Demo Part 1.
/*        mainView mV = new mainView();
        DayInfo mockDay = new DayInfo(LocalDate.now());
        mV.genMvGUI(mockDay);*/

        //Demo Part 2.
        mainView mV = new mainView();
        DayInfo mockDay = new DayInfo(LocalDate.now());
        ArrayList<Food> foodList = new ArrayList<>();
        String apiKey = genMyApiKey("myFDCApiKey.txt");
        callUsdaApi usdaObj = new callUsdaApi(apiKey);
        String mockFoodInput = "whole milk";
        String mockWeightInput = "240";
        String mockWeightUnitInput = "ml";
        Food result = foodFromFirstResultUsda(mockFoodInput, usdaObj);
        foodList.add(result);
        result.setWeight(Float.valueOf(mockWeightInput));
        if (mockWeightUnitInput.equals(result.getStandardUnit())) {
            ArrayList<Object> aR = new ArrayList<>();
            Double amountPer100 = (Double) result.getCalories().get("amount per 100ml");

            aR.add(String.valueOf((amountPer100 / 100) * result.getWeight()));
            aR.add(result.getCalories().get("unitName"));
            mV.setCalories(aR);

            ArrayList<Object> aR2 = new ArrayList<>();
            Double amountPer1002 = (Double) result.getMacroNutrients()
                    .get("Protein").get("amount per 100ml");

            aR2.add(String.valueOf((amountPer1002 / 100) * result.getWeight()));
            aR2.add(result.getMacroNutrients().get("Protein").get("unitName"));
            mV.setProtein(aR2);

            ArrayList<Object> aR3 = new ArrayList<>();
            Double amountPer1003 = (Double) result.getMacroNutrients().get("Carbohydrate")
                    .get("amount per 100ml");

            aR3.add(String.valueOf((amountPer1003 / 100) * result.getWeight()));
            aR3.add(result.getMacroNutrients().get("Carbohydrate").get("unitName"));
            mV.setCarbs(aR3);

            ArrayList<Object> aR4 = new ArrayList<>();
            Double amountPer1004 = (Double) result.getMacroNutrients().get("Fat")
                    .get("amount per 100ml");

            aR4.add(String.valueOf((amountPer1004 / 100) * result.getWeight()));
            aR4.add(result.getMacroNutrients().get("Fat").get("unitName"));
            mV.setFat(aR4);
        }
        // Mocking the mainView state.
        mockDay.setFoodLog(foodList);
        mV.genMvGUI(mockDay);
    }
}
