package data_access;

import data.AccountInfo;
import data.Food;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.food_logging.LogFoodDataAccessInterface;
import use_case.food_logging.DataAccessException;
import use_case.signup.SignupDataAccessInterface;


import java.io.IOException;
import java.util.HashMap;

public class FoodLoggingStorageDAO implements LogFoodDataAccessInterface, SignupDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final int CREDENTIAL_ERROR = 401;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";

    public void saveUser(String userName, String password) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, userName);
        requestBody.put(PASSWORD, password);
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public JSONObject saveFood(String userName, String password, Food foodIntake) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, userName);
        requestBody.put(PASSWORD, password);
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
                return loadFoodInfo(userName);
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
    public JSONObject loadFoodInfo(String userName) throws DataAccessException {
        // Make an API call to get the user object.
        final String username = userName;
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

    @Override
    public AccountInfo get(String username) {
        return null;
    }

    @Override
    public void put(String username, AccountInfo accountInfo) {

    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }

    @Override
    public void setCurrentUsername(String username) {

    }
}