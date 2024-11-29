package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.display_food_options.DisplayFoodOptionsDataAccessInterface;
import use_case.select_from_food_options.SelectSearchDataAccessInterface;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class FoodDataCentralSearchDAO implements DisplayFoodOptionsDataAccessInterface,
        SelectSearchDataAccessInterface {
    public String apiKey;

    public FoodDataCentralSearchDAO(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Reads API key from an untracked text file.
     * @param textFile the file path. The file itself must contain the API key as first line.
     * @return API key in text file.
     */
    public static String genMyApiKey(String textFile) {
        try {
            Scanner envVar = new Scanner(new File(textFile));
            return envVar.nextLine();
        } catch (FileNotFoundException ev) {
            return "File not found";
        }
    }

    /**
     *
     * @param food The food query we are searching the USDA database with.
     * @return The first result of the USDA database search with <code>food</code> as the query.
     */
    public JSONObject callUsdaSearch(String food) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + food)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray foodsArray = responseBody.getJSONArray("foods");
            // Consider modifying function to use the Strategy Design Pattern.
            JSONObject firstResult = (JSONObject) foodsArray.get(0);
            return firstResult;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    /**
     * Deprecated. Not working.
     * @param fdcId
     * @return
     */
    public JSONObject getFoodByFdcId(Integer fdcId) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/food/" + String.valueOf(fdcId) + "?api_key=" + apiKey)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            // JSONArray foodsArray = responseBody.getJSONArray("foods");
            //JSONObject firstResult = (JSONObject) foodsArray.get(0);
            return responseBody;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    /**
     * NEW VERSION NOV 27, WORKING.
     *
     * @param fdcId A valid fdcID for a food in the USDA FoodDataCentral database.
     * @param nutrientCodeArray An array which contains valid nutrient code specifiers. Refer to USDA FoodDataCentral
     *                          documentation to confirm validity if necessary.
     * @return Returns the response body from the FDC API. The response contains the necessary information for creating
     * a Food entity. Return value designed to be passed to the method "createFood" in class "PopulateUtility".
     */
    public JSONObject getFoodByFdcId(Integer fdcId, Integer[] nutrientCodeArray) {
        String nutrientSpecifier = nutrientStringGen(nutrientCodeArray);
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/food/" + String.valueOf(fdcId) + "?api_key=" + apiKey +
                        "&format=abridged" + nutrientSpecifier)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    /**
     * Helper method for getFoodbyFdcId. Generates string to add to url for filtering results to just nutrients
     * of food.
     * @param nutrientCodes An array of valid FDC nutrient codes.
     * @return A string which is formatted correctly to be used in getFoodByFdcId API call.
     */
    public static String nutrientStringGen(Integer[] nutrientCodes) {
        String accumulator = "";
        for (Integer nutrientCode: nutrientCodes) {
            StringBuilder baseString = new StringBuilder("&nutrients=" + String.valueOf(nutrientCode));
            accumulator += baseString;
        }
        return accumulator;
    }

    /**
     * Returns up to 10 results from FDC Api based on query. All results correspond to foundation category foods.
     * @param food The food query.
     * @return A HashMap where the key, value pairs are description, fdcId.
     */
    public HashMap<String, Integer> first10FoundationFoods(String food) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + food + "&dataType="
                        + "Foundation")
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray foodsArray = responseBody.getJSONArray("foods");
            HashMap<String, Integer> description2fdcId = new HashMap<>();
            int i = 0;
            while (i < 10 && i < foodsArray.length()) {
                String key1 = ((JSONObject) foodsArray.get(i)).getString("description");
                Integer value1 = ((JSONObject) foodsArray.get(i)).getInt("fdcId");
                description2fdcId.put(key1, value1);
                i += 1;
            }
            return description2fdcId;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    public HashMap<String, Integer> first10FoundationFoods2(String food) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&dataType="
                        + "Foundation" + "&format=abridged" + "&query=" + food)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray foodsArray = responseBody.getJSONArray("foods");
            HashMap<String, Integer> description2fdcId = new HashMap<>();
            int i = 0;
            while (i < 10 && i < foodsArray.length()) {
                String key1 = ((JSONObject) foodsArray.get(i)).getString("description");
                Integer value1 = ((JSONObject) foodsArray.get(i)).getInt("fdcId");
                description2fdcId.put(key1, value1);
                i += 1;
            }
            return description2fdcId;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public HashMap<String, Integer> searchBrandedFood(String food, String brand) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&dataType="
                        + "Branded" + "&format=abridged" + "&query=" + food + "&brandOwner=" + brand)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray foodsArray = responseBody.getJSONArray("foods");
            HashMap<String, Integer> description2fdcId = new HashMap<>();
            int i = 0;
            while (i < 100 && i < foodsArray.length()) {
                String key1 = ((JSONObject) foodsArray.get(i)).getString("description");
                Integer value1 = ((JSONObject) foodsArray.get(i)).getInt("fdcId");
                description2fdcId.put(key1, value1);
                i += 1;
            }
            return description2fdcId;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    @Override
    public HashMap<String, Integer> searchComplexFood(String food) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&dataType="
                        + "SR Legacy" + "&query=" + food)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray foodsArray = responseBody.getJSONArray("foods");
            HashMap<String, Integer> description2fdcId = new HashMap<>();
            int i = 0;
            while (i < 100 && i < foodsArray.length()) {
                String key1 = ((JSONObject) foodsArray.get(i)).getString("description");
                Integer value1 = ((JSONObject) foodsArray.get(i)).getInt("fdcId");
                description2fdcId.put(key1, value1);
                i += 1;
            }
            return description2fdcId;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    public static void main(String[] args) {
        // Sanity check.
        FoodDataCentralSearchDAO usdaObj = new FoodDataCentralSearchDAO(genMyApiKey("myFDCApiKey.txt"));
        Integer[] array = {203,204,205,208, 268, 957, 958};
        //JSONObject result = usdaObj.getFoodByFdcId(173410, array);
        JSONObject result = usdaObj.getFoodByFdcId(2514744, array);
        int i = 0;
        String stringResult = nutrientStringGen(array);
        int y = 7;
        /*HashMap<String, Integer> result2 = usdaObj.first10FoundationFoods2("beef");
        int jh = 9;*/
        HashMap<String, Integer> result2 = usdaObj.searchBrandedFood("macaroni and cheese", "kraft");
        int hy6 = 6;

    }
}

