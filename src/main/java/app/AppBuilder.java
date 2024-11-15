package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.DailyValueRecsController;
import interface_adapter.daily_value_recs.DailyValueRecsPresenter;
import interface_adapter.daily_value_recs.MainViewModel;
import use_case.daily_value_recs.DailyValueRecsInputBoundary;
import use_case.daily_value_recs.DailyValueRecsInteractor;
import use_case.daily_value_recs.DailyValueRecsOutputBoundary;
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

    private MainViewModel mainViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }
    public AppBuilder addMainView() {
        mainViewModel = new MainViewModel();
        mainView = new mainView(mainViewModel);
        cardPanel.add(mainView, mainView.getViewName());
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
}
