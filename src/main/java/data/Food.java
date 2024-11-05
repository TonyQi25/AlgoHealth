package data;

import java.util.HashMap;

class Food {
    private final String name;
    private final float weight;
    private final float calories;
    private final HashMap<String, Float> microNuitrients;
    private final HashMap<String, Float> macroNuitrients;

    public Food(String name, float weight, float calories) {
        this.name = name;
        this.weight = weight;
        this.calories = calories;
        this.microNuitrients = new HashMap<>();  // could also just put in the nutrients right from the constructor
        this.macroNuitrients = new HashMap<>();  // depends on how API calls work! Check back after API calls do smth
    }

    public String getName() {
        return this.name;
    }

    public float getWeight() {
        return this.weight;
    }

    public float getCalories() {
        return this.calories;
    }

    public HashMap<String, Float> getMicroNuitrients() {
        return this.microNuitrients;
    }

    public HashMap<String, Float> getMacroNuitrients() {
        return this.macroNuitrients;
    }

    public void addMicro(String key, Float value) {
        this.microNuitrients.put(key, value);
    }

    public void addMacro(String key, Float value) {
        this.macroNuitrients.put(key, value);
    }
}