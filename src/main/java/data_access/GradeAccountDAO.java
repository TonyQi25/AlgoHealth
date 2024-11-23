package data_access;

import api.GradeHelper;
import data.AccountInfo;
import okhttp3.*;
import org.json.JSONObject;
import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.io.IOException;

public class GradeAccountDAO implements SignupDataAccessInterface, LoginDataAccessInterface {

    public void createAccount(String username, String password) {
        GradeHelper.postUser(username, password);
    }

    @Override
    public AccountInfo get(String username) {
        return GradeHelper.getUser(username);
    }

    @Override
    public void put(String username, String password, AccountInfo accountInfo) {
        GradeHelper.setUserInfo(username, password, accountInfo);
    }

    @Override
    public boolean existsByName(String username) {
        return GradeHelper.checkUsernameExists(username);
    }

    @Override
    public void save(AccountInfo account) {

    }

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public void setCurrentUsername(String username) {

    }
}
