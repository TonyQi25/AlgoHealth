package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class FoodDataCentralSearchDAO {
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
            Scanner envVar = new Scanner(new FileReader(textFile));
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

    public JSONObject getFoodByFdcId(Integer fdcId) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" +
                        String.valueOf(fdcId))
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray foodsArray = responseBody.getJSONArray("foods");
            JSONObject firstResult = (JSONObject) foodsArray.get(0);
            return firstResult;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
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

    public static void main(String[] args) {
    }
}
// Citation: lab-4 code https://github.com/CSC207-2024F-UofT/lab-4
