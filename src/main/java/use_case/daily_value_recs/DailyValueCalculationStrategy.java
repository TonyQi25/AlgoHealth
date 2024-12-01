package use_case.daily_value_recs;

import java.util.HashMap;

public interface DailyValueCalculationStrategy {

    /**
     * A general algorithm for producing Daily Value constants necessary for calculating the percentage of
     * Daily Value for the macronutrients calories, carbs, protein, and fat.
     *
     * @param weight weight of a person.
     * @param height height of a person.
     * @param goal goal for a person's health.
     * @param diet particular diet person is on.
     * @return A HashMap with keys caloriesDVconstant, proteinDVconstant, carbsDVconstant, fatDVconstant.
     * Where the value represents what is indicated by the key name.
     */
    HashMap<String, Double> DVconstantsAlgo(Integer age, double weight, double height, String goal, String diet);
}
