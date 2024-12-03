package use_case.login;

import java.util.List;

import data.AccountInfo;
import data.Food;
import data_access.GradeAccountDAO;
import helpers.UseCaseHelpers;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {

    private final LoginDataAccessInterface loginDataAccessObject = new GradeAccountDAO();
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginOutputBoundary loginOutputBoundary) {
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        if (!loginDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = loginDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                System.out.println("reached incorrect pw");
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final AccountInfo account = this.loginDataAccessObject.get(username);
                System.out.println("reached interactor");
                loginDataAccessObject.setCurrentUsername(account.getUsername());

                final List<Food> currFoods = account.getDays().get(account.getDays().size() - 1).getFoodLog();
                final double[] currNutrients = UseCaseHelpers.getNutrientsFromFoods(currFoods);

                final int calorieIndex = 0;
                final int proteinIndex = 1;
                final int carbIndex = 2;
                final int fatIndex = 3;
                final LoginOutputData loginOutputData = new LoginOutputData(account.getUsername(),
                        account.getPassword(), currNutrients[calorieIndex],
                        currNutrients[proteinIndex], currNutrients[carbIndex],
                        currNutrients[fatIndex]);

                loginPresenter.prepareSuccessView(loginOutputData);
                System.out.println("past presenter");
            }
        }
    }

    @Override
    public void switchToSignup() {
        this.loginPresenter.switchToSignup();
    }
}
