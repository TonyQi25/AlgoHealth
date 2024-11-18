package data_access;

import data.AccountInfo;
import data.Food;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.food_logging.LogFoodDataAccessInterface;
import use_case.food_logging.DataAccessException;


import java.io.IOException;
import java.util.HashMap;

public class FoodLoggingStorageDAO implements LogFoodDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final int CREDENTIAL_ERROR = 401;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";

    @Override
    public JSONObject saveFood(AccountInfo user, Food foodIntake) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        final JSONObject FoodInfo = new JSONObject();
        FoodInfo.put("description", foodIntake.getDescription());
        FoodInfo.put("standard unit", foodIntake.getStandardUnit());
        FoodInfo.put("food amount", foodIntake.getWeight());
        FoodInfo.put("Calories Info", foodIntake.getCalories());
        FoodInfo.put("Micronutrients Info", foodIntake.getMicroNutrients());
        FoodInfo.put("Macronutrients Info", foodIntake.getMacroNutrients());
        FoodInfo.put("Total Calories", foodIntake.getTotalCalories());
        FoodInfo.put("Total Protein", foodIntake.getTotalProtein());
        FoodInfo.put("Total Carbohydrates", foodIntake.getTotalCarb());
        FoodInfo.put("Total Fat", foodIntake.getTotalFat());
        requestBody.put("info", FoodInfo);
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                return loadFoodInfo(user);
            }
            else if (responseBody.getInt(STATUS_CODE_LABEL) == CREDENTIAL_ERROR) {
                throw new DataAccessException("message could not be found or password was incorrect");
            }
            else {
                throw new DataAccessException("database error: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    @Override
    public JSONObject loadFoodInfo(AccountInfo user) throws DataAccessException {
        // Make an API call to get the user object.
        final String username = user.getUsername();
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                return userJSONObject;
            }
            else {
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}
