package use_case.signup;

import data.AccountInfo;

public interface SignupDataAccessInterface {

    void createAccount(String username, String password);

    AccountInfo get(String username);

    void put(String username, String password, AccountInfo accountInfo);

    boolean existsByName(String username);

    String getCurrentUsername();

    void setCurrentUsername(String username);

}
