package use_case.logout;

import data.AccountInfo;

public interface LogoutDataAccessInterface {

    /**
     * Saves the user.
     * @param account the account to save
     */
    void save(AccountInfo account);

    AccountInfo get(String username);

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUsername(String username);
}
