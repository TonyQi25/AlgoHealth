package use_case.login;

import data.AccountInfo;
import data.Food;
import data_access.GradeAccountDAO;
import helpers.UseCaseHelpers;

import java.util.List;

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

                List<Food> currFoods = account.getDays().get(account.getDays().size() - 1).getFoodLog();
                double[] currNutrients = UseCaseHelpers.getNutrientsFromFoods(currFoods);

                final int CALORIE_INDEX = 0;
                final int PROTEIN_INDEX = 1;
                final int CARB_INDEX = 2;
                final int FAT_INDEX = 3;
                final LoginOutputData loginOutputData = new LoginOutputData(account.getUsername(),
                        account.getPassword(), currNutrients[CALORIE_INDEX],
                        currNutrients[PROTEIN_INDEX], currNutrients[CARB_INDEX],
                        currNutrients[FAT_INDEX]);

                loginPresenter.prepareSuccessView(loginOutputData);
                System.out.println("past presenter");
            }
        }
    }

    @Override
    public void switchToSignup() {
        this.loginPresenter.switchToSignup();
        System.out.println("switch to signup interactor");
    }
}
