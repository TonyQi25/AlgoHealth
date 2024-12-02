package helpers;

import data.Food;

import java.util.List;

public class UseCaseHelpers {

    public static double[] getNutrientsFromFoods(List<Food> foods) {

        final int CALORIE_INDEX = 0;
        final int PROTEIN_INDEX = 1;
        final int CARB_INDEX = 2;
        final int FAT_INDEX = 3;

        double[] nutrients = new double[] {0, 0, 0, 0};

        for (Food food : foods) {
            nutrients[CALORIE_INDEX] += food.getTotalCalories();
            nutrients[PROTEIN_INDEX] += food.getTotalProtein();
            nutrients[CARB_INDEX] += food.getTotalCarb();
            nutrients[FAT_INDEX] += food.getTotalFat();
        }

        return nutrients;
    }

}
