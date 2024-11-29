package api;

import data.AccountInfo;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

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

    public static void setUserInfo(String username, String password, AccountInfo accountInfo) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");

        JSONObject newAccountInfo = new JSONObject();
        JSONObject userInfo = new JSONObject();
        userInfo.put("date of birth", accountInfo.getDateOfBirth().toString());
        userInfo.put("height", accountInfo.getHeight());
        userInfo.put("weight", accountInfo.getWeight());
        userInfo.put("diet", accountInfo.getDiet());
        userInfo.put("goal", accountInfo.getGoal());
        userInfo.put("username", accountInfo.getUsername());
        userInfo.put("password", accountInfo.getPassword());
        userInfo.put("restrictions", accountInfo.getDietaryRestrictions());
        newAccountInfo.put("account info", userInfo);
        JSONObject foodLog = new JSONObject();
        newAccountInfo.put("currentDayFoodLog", foodLog);

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

    public static AccountInfo getUser(String username) {
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
                JSONObject user = (JSONObject) userObject.get("info");
                JSONObject jsonAccount = (JSONObject) user.get("account info");
                AccountInfo accountInfo = AccountInfo.fromJSONString(jsonAccount);
                JSONObject foodLog = (JSONObject) user.get("currentDayFoodLog");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
