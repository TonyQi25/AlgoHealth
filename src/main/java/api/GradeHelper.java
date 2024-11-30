package api;

import data.AccountBuilder;
import data.AccountInfo;
import data.Food;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class GradeHelper {

    public static void postUser(String username, String password) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");

        JSONObject createBody = new JSONObject();
        createBody.put("username", AccountInfo.USERNAME_PREFIX + username);
        createBody.put("password", password);

        RequestBody createRequestBody = RequestBody.create(createBody.toString(), mediaType);

        Request createRequest = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .post(createRequestBody)
                .build();

        try (Response createResponse = client.newCall(createRequest).execute()) {
            if (createResponse.isSuccessful() && createResponse.body() != null) {
                System.out.println("Response: " + createResponse.body().string());
            } else {
                System.out.println("Request failed. HTTP error code: " + createResponse.code());
            }
        } catch (IOException e) {
            e.printStackTrace();      // placeholder
        }
    }

    public static JSONObject makeJSONAccountInfo(String username, String password, AccountInfo accountInfo){
        JSONObject newAccountInfo = new JSONObject();
        newAccountInfo.put("date of birth", accountInfo.getDateOfBirth().toString());
        newAccountInfo.put("height", accountInfo.getHeight());
        newAccountInfo.put("weight", accountInfo.getWeight());
        newAccountInfo.put("diet", accountInfo.getDiet());
        newAccountInfo.put("goal", accountInfo.getGoal());
        newAccountInfo.put("username", accountInfo.getUsername());
        newAccountInfo.put("password", accountInfo.getPassword());
        newAccountInfo.put("restrictions", accountInfo.getDietaryRestrictions());
        return newAccountInfo;
    }

    public static void addFoodUserInfo(String username, String password, AccountInfo accountInfo, JSONObject foodLog,
                                   String date) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject newAccountInfo = GradeHelper.makeJSONAccountInfo(username, password, accountInfo);
        newAccountInfo.put("foodLog", foodLog);
        JSONObject newAccountBody = new JSONObject();
        newAccountBody.put("username", AccountInfo.USERNAME_PREFIX + username);
        newAccountBody.put("password", password);
        newAccountBody.put("info", newAccountInfo);

        RequestBody accountRequestBody = RequestBody.create(newAccountBody.toString(), mediaType);

        System.out.println(newAccountBody);

        Request accountRequest = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .put(accountRequestBody)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response accountResponse = client.newCall(accountRequest).execute()) {
            if (accountResponse.isSuccessful() && accountResponse.body() != null) {
                System.out.println("Response: " + accountResponse.body().string());
            } else {
                System.out.println("Request failed. HTTP error code: " + accountResponse.code());
            }
        } catch (IOException e) {
            e.printStackTrace();      // placeholder
        }
    }

    public static void setUserInfo(String username, String password, AccountInfo accountInfo) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");


        JSONObject newAccountInfo = GradeHelper.makeJSONAccountInfo(username, password, accountInfo);
        JSONObject newAccountBody = new JSONObject();
        JSONObject emptyFoodLog = new JSONObject();
        newAccountInfo.put("foodLog", emptyFoodLog);
        newAccountBody.put("username", AccountInfo.USERNAME_PREFIX + username);
        newAccountBody.put("password", password);
        newAccountBody.put("info", newAccountInfo);

        RequestBody accountRequestBody = RequestBody.create(newAccountBody.toString(), mediaType);

        System.out.println(newAccountBody);

        Request accountRequest = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .put(accountRequestBody)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response accountResponse = client.newCall(accountRequest).execute()) {
            if (accountResponse.isSuccessful() && accountResponse.body() != null) {
                System.out.println("Response: " + accountResponse.body().string());
            } else {
                System.out.println("Request failed. HTTP error code: " + accountResponse.code());
            }
        } catch (IOException e) {
            e.printStackTrace();      // placeholder
        }
    }

    public static boolean checkUsernameExists(String username) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        String endpoint = "http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=" +
                AccountInfo.USERNAME_PREFIX + username;

        Request checkRequest = new Request.Builder()
                .url(endpoint)
                .get()
                .build();

        System.out.println("runs?");
        System.out.println(endpoint);

        try (Response userExists = client.newCall(checkRequest).execute()) {
            return userExists.isSuccessful() && userExists.body() != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static JSONObject getUserInfo(String username) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        String endpoint = "http://vm003.teach.cs.toronto.edu:20112/user?username=" +
                AccountInfo.USERNAME_PREFIX + username;

        Request userRequest = new Request.Builder()
                .url(endpoint)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response userResponse = client.newCall(userRequest).execute()) {
            if (userResponse.isSuccessful() && userResponse.body() != null) {
                JSONObject userObject = (JSONObject) new JSONObject(userResponse.body().string()).get("user");
                JSONObject userInfo = (JSONObject) userObject.get("info");
                return userInfo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static AccountInfo getUser(String username) {
        JSONObject user = GradeHelper.getUserInfo(username);
        AccountBuilder accountBuilder = new AccountBuilder(user);
        //accountBuilder.addFoodLog(user);
        AccountInfo accountInfo = accountBuilder.getAccountInfo();
        return accountInfo;
    }

    public static JSONObject getJSONFoodLog(String username) {
        JSONObject userInfo = GradeHelper.getUserInfo(username);
        JSONObject JSONFoodLog = userInfo.getJSONObject("foodLog");
        return JSONFoodLog;
    }

    public static JSONObject getSingleDayJSONFoodLog(String username, String date) {
        JSONObject JSONFoodLog = GradeHelper.getJSONFoodLog(username);
        JSONObject JSONDayLog = JSONFoodLog.getJSONObject(date);
        return JSONDayLog;
    }

    public static JSONObject copySingleDayJSONFoodLog(JSONObject SingleFoodLog) {
        JSONObject copyFoodLog = new JSONObject();
        String[] fdcIDs = JSONObject.getNames(SingleFoodLog);
        for (String fdcID: fdcIDs){
            JSONObject foodInfo = SingleFoodLog.getJSONObject(fdcID);
            JSONObject copyFoodInfo = new JSONObject();
            copyFoodInfo.put("name", foodInfo.getString("name"));
            copyFoodInfo.put("weight", foodInfo.getFloat("weight"));
            copyFoodLog.put(fdcID, copyFoodInfo);
        }
        return copyFoodLog;
    }

    public static JSONObject copyEntireFoodLog(JSONObject entireFoodLog){
        JSONObject copyFoodLog = new JSONObject();
        String[] days = JSONObject.getNames(entireFoodLog);
        if (days == null){
            return  copyFoodLog;
        }
        for (String day: days){
            JSONObject copyDayLog = copySingleDayJSONFoodLog(entireFoodLog.getJSONObject(day));
            copyFoodLog.put(day, copyDayLog);
        }
        return copyFoodLog;
    }

}
