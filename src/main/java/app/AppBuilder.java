package app;

import api.ApiKeyReader;
import api.FoodDataCentralSearchDAO;
import data_access.GradeAccountDAO;
import data_access.InMemoryFoodSelectionDAO;
//import data_access.UserFoodSearchInMemoryDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.DailyValueRecsController;
import interface_adapter.daily_value_recs.DailyValueRecsPresenter;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.delete_food.DeleteFoodController;
import interface_adapter.delete_food.DeleteFoodViewModel;
import interface_adapter.display_food_options.DisplayFoodOptionsController;
import interface_adapter.display_food_options.DisplayFoodOptionsPresenter;
import interface_adapter.display_food_options.DisplayOptionsViewModel;
import interface_adapter.food_logging.LogFoodController;
import interface_adapter.food_logging.LogFoodPresenter;
import interface_adapter.food_logging.LogFoodViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.select_from_food_options.SelectFromFoodOptionsController;
import interface_adapter.select_from_food_options.SelectFromFoodOptionsPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.daily_value_recs.DailyValueRecsInputBoundary;
import use_case.daily_value_recs.DailyValueRecsInteractor;
import use_case.daily_value_recs.DailyValueRecsOutputBoundary;
import use_case.display_food_options.*;
import use_case.food_logging.LogFoodInputBoundary;
import use_case.food_logging.LogFoodInteractor;
import use_case.food_logging.LogFoodOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.select_from_food_options.SelectFromFoodOptionsInputBoundary;
import use_case.select_from_food_options.SelectFromFoodOptionsInteractor;
import use_case.select_from_food_options.SelectFromFoodOptionsOutputBoundary;
import use_case.select_from_food_options.SelectSearchDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.DeleteFoodView.DeleteFoodView;
import view.DisplayOptionsView;
import view.LoginView.LoginView;
import view.SignupView.SignupView;
import view.ViewManager;
import view.MainView.mainView;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private mainView mainView;
    private DisplayOptionsView displayOptionsView;
    private LoginView loginView;
    private SignupView signupView;
    private DeleteFoodView deleteFoodView;

    private MainViewModel mainViewModel;
    private LogFoodViewModel logFoodViewModel;
    private DisplayOptionsViewModel displayOptionsViewModel;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;
    private DeleteFoodViewModel deleteFoodViewModel;

    private InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO;
    private DisplayFoodOptionsDataAccessInterface foodDataCentralSearchDAO;
    private SelectSearchDataAccessInterface foodDataCentralSearchDAO2;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addMainView() {
        mainViewModel = new MainViewModel();
        logFoodViewModel = new LogFoodViewModel();
        mainView = new mainView(mainViewModel, logFoodViewModel);
        cardPanel.add(mainView, mainView.getViewName());
        return this;
    }
    public AppBuilder addDisplayOptionsView() {
        displayOptionsViewModel = new DisplayOptionsViewModel();
        displayOptionsView = new DisplayOptionsView(displayOptionsViewModel);
        cardPanel.add(displayOptionsView, displayOptionsView.getViewName());
        return this;
    }

    public AppBuilder addLoginView() {
        this.loginViewModel = new LoginViewModel();
        this.signupViewModel = new SignupViewModel();

        LoginOutputBoundary loginPresenter = new LoginPresenter(this.loginViewModel, this.mainViewModel,
                this.signupViewModel, this.viewManagerModel);
        LoginInputBoundary loginUseCaseInteractor = new LoginInteractor(loginPresenter);
        LoginController loginController = new LoginController(loginUseCaseInteractor);

        loginView = new LoginView(this.loginViewModel, loginController);
        cardPanel.add(loginView.getViewName(), loginView);
        return this;
    }

    public AppBuilder addSignupView() {

        // is there a better way to add these?
        String[] dietOptions = {"None", "Vegan"};
        String[] restrictionOptions = {"None", "Lactose Intolerance", "Fish", "Eggs"};
        String[] goalOptions = {"Maintain Weight", "Lose Weight", "Gain Weight"};

        SignupOutputBoundary signupPresenter = new SignupPresenter(
                this.signupViewModel, this.mainViewModel, this.loginViewModel, this.viewManagerModel);

        SignupInputBoundary signupUseCaseInteractor = new SignupInteractor(signupPresenter);
        SignupController signupController = new SignupController(signupUseCaseInteractor);

        signupView = new SignupView(this.signupViewModel, signupController,
                dietOptions, restrictionOptions, goalOptions);

        cardPanel.add(signupView.getViewName(), signupView);
        return this;
    }

    public AppBuilder addDeleteFoodView() {
        this.deleteFoodViewModel = new DeleteFoodViewModel();

        // come back here when things are implemented
        DeleteFoodController deleteFoodController = new DeleteFoodController();

        this.deleteFoodView = new DeleteFoodView(this.deleteFoodViewModel, deleteFoodController);

        cardPanel.add(this.deleteFoodView.getViewName(), this.deleteFoodView);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("AlgoHealth");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

    public AppBuilder addDailyValueRecsUseCase() {
        final DailyValueRecsOutputBoundary dailyValueRecsOutputBoundary = new DailyValueRecsPresenter(viewManagerModel,
                this.mainViewModel);
        final DailyValueRecsInputBoundary dailyValueRecsInteractor = new DailyValueRecsInteractor(
                dailyValueRecsOutputBoundary);

        final DailyValueRecsController dailyValueRecsController = new DailyValueRecsController(dailyValueRecsInteractor);
        mainView.setDailyValueRecsController(dailyValueRecsController);
        return this;
    }

    public AppBuilder selectFromFoodOptionsUseCase() {
        this.foodDataCentralSearchDAO2 = new FoodDataCentralSearchDAO(ApiKeyReader
                .genMyApiKey("myFDCApiKey.txt"));
        final SelectFromFoodOptionsOutputBoundary selectFromFoodOptionsPresenter = new SelectFromFoodOptionsPresenter(
                viewManagerModel);
        final SelectFromFoodOptionsInputBoundary selectFromFoodOptionsInteractor = new SelectFromFoodOptionsInteractor(
                inMemoryFoodSelectionDAO,
                foodDataCentralSearchDAO2,
                selectFromFoodOptionsPresenter);

        final SelectFromFoodOptionsController selectFromFoodOptionsController = new SelectFromFoodOptionsController(
                selectFromFoodOptionsInteractor);
        displayOptionsView.setSelectFromFoodOptionsController(selectFromFoodOptionsController);
        return this;
    }

    public AppBuilder addDisplayOptionsUseCase() {

        this.inMemoryFoodSelectionDAO = new InMemoryFoodSelectionDAO();
        this.foodDataCentralSearchDAO = new FoodDataCentralSearchDAO(ApiKeyReader
                .genMyApiKey("myFDCApiKey.txt"));
        final DisplayFoodOptionsOutputBoundary displayFoodOptionsOutputBoundary = new DisplayFoodOptionsPresenter(
                viewManagerModel, this.displayOptionsViewModel);
        final DisplayFoodOptionsInputBoundary displayFoodOptionsInteractor = new DisplayFoodOptionsInteractor(
                displayFoodOptionsOutputBoundary, foodDataCentralSearchDAO, inMemoryFoodSelectionDAO);
        final DisplayFoodOptionsController displayFoodOptionsController = new DisplayFoodOptionsController(
                displayFoodOptionsInteractor);
        mainView.setDisplayFoodOptionsController(displayFoodOptionsController);
        return this;



    }

    public AppBuilder addFoodLoggingUseCase(){
        final LogFoodOutputBoundary logFoodOutputBoundary = new LogFoodPresenter(logFoodViewModel, mainViewModel,
                viewManagerModel);
        final LogFoodInputBoundary logFoodInteractor = new LogFoodInteractor(logFoodOutputBoundary, inMemoryFoodSelectionDAO);
        final LogFoodController logFoodController = new LogFoodController(logFoodInteractor);
        mainView.setLogFoodController(logFoodController);
        return this;
    }

    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutPresenter = new LogoutPresenter(this.loginViewModel, this.viewManagerModel);
        final LogoutDataAccessInterface logoutDataAccessObject = new GradeAccountDAO();
        final LogoutInputBoundary logoutUseCaseInteractor = new LogoutInteractor(
                logoutDataAccessObject, logoutPresenter);
        final LogoutController logoutController = new LogoutController(logoutUseCaseInteractor);
        mainView.setLogoutController(logoutController);
        return this;
    }
}
