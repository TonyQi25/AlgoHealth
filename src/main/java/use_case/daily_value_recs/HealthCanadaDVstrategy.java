package use_case.daily_value_recs;

import java.util.HashMap;

public class HealthCanadaDVstrategy implements DailyValueCalculationStrategy{

    /**
     *  Constants for DVs based on https://www.canada.ca/en/health-canada/services/food-nutrition/
     *  healthy-eating/dietary-reference-intakes/tables/reference-values-macronutrients.html
     *  and assumed body weight of 80kg and age 19-30 and assumed recommended calories 2000 Kcal.
     *  fat intake is just a randomly selected number right now as there is no indication of its value in the link.
     * @param weight weight of a person.
     * @param height height of a person.
     * @param goal goal for a person's health.
     * @param diet particular diet person is on.
     * @return A HashMap with keys caloriesDVconstant, proteinDVconstant, carbsDVconstant, fatDVconstant.
     * Where the value represents what is indicated by the key name.
     */
    @Override
    public HashMap<String, Double> DVconstantsAlgo(Integer age, double weight, double height, String goal, String diet) {
        HashMap<String, Double> DVconstants = new HashMap<String, Double>();
        DVconstants.put("caloriesDVconstant", 2000.0);
        DVconstants.put("proteinDVconstant", weight * 0.66);
        DVconstants.put("carbsDVconstant", 100.0);
        DVconstants.put("fatDVconstant", 200.0);

        return DVconstants;

    }
}
