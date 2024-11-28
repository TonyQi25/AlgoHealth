package data_access;

import helpers.GradeHelper;
import data.AccountInfo;
import use_case.login.LoginDataAccessInterface;
import use_case.logout.LogoutDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

public class GradeAccountDAO implements SignupDataAccessInterface, LoginDataAccessInterface,
        LogoutDataAccessInterface {

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
        GradeHelper.setUserInfo(account.getUsername(), account.getPassword(), account);
    }

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public void setCurrentUsername(String username) {

    }
}
