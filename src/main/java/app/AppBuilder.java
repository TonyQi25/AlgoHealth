package app;

import data_access.UserFoodSearchInMemoryDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.DailyValueRecsController;
import interface_adapter.daily_value_recs.DailyValueRecsPresenter;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.display_food_options.DisplayFoodOptionsController;
import interface_adapter.display_food_options.DisplayFoodOptionsPresenter;
import interface_adapter.display_food_options.DisplayOptionsViewModel;
import interface_adapter.food_logging.LogFoodController;
import interface_adapter.food_logging.LogFoodPresenter;
import interface_adapter.food_logging.LogFoodViewModel;
import use_case.daily_value_recs.DailyValueRecsInputBoundary;
import use_case.daily_value_recs.DailyValueRecsInteractor;
import use_case.daily_value_recs.DailyValueRecsOutputBoundary;
import use_case.display_food_options.DisplayFoodOptionsInputBoundary;
import use_case.display_food_options.DisplayFoodOptionsInteractor;
import use_case.display_food_options.DisplayFoodOptionsOutputBoundary;
import use_case.food_logging.LogFoodInputBoundary;
import use_case.food_logging.LogFoodInteractor;
import use_case.food_logging.LogFoodOutputBoundary;
import view.DisplayOptionsView;
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

    private MainViewModel mainViewModel;
    private LogFoodViewModel logFoodViewModel;
    private DisplayOptionsViewModel displayOptionsViewModel;


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


    public JFrame build() {
        final JFrame application = new JFrame("Something fitting here");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(mainView.getViewName());
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

    public AppBuilder addDisplayOptionsUseCase() {
        final DisplayFoodOptionsOutputBoundary displayFoodOptionsOutputBoundary = new DisplayFoodOptionsPresenter(
                viewManagerModel, this.displayOptionsViewModel);
        final DisplayFoodOptionsInputBoundary displayFoodOptionsInteractor = new DisplayFoodOptionsInteractor(
                displayFoodOptionsOutputBoundary);
        final DisplayFoodOptionsController displayFoodOptionsController = new DisplayFoodOptionsController(
                displayFoodOptionsInteractor);
        mainView.setDisplayFoodOptionsController(displayFoodOptionsController);
        return this;



    }

    public AppBuilder addFoodLoggingUseCase(){
        final LogFoodOutputBoundary logFoodOutputBoundary = new LogFoodPresenter(logFoodViewModel, mainViewModel,
                viewManagerModel);
        final LogFoodInputBoundary logFoodInteractor = new LogFoodInteractor(logFoodOutputBoundary);
        final LogFoodController logFoodController = new LogFoodController(logFoodInteractor);
        mainView.setLogFoodController(logFoodController);
        return this;
    }
}
