package use_case.signup;

import data.AccountInfo;

public interface SignupDataAccessInterface {

    AccountInfo get(String username);

    void put(String username, AccountInfo accountInfo);

    boolean existsByName(String username);

    String getCurrentUsername();

    void setCurrentUsername(String username);

}
