package interface_adapter.daily_value_recs;

import interface_adapter.ViewManagerModel;
import use_case.daily_value_recs.DailyValueRecsOutputBoundary;
import use_case.daily_value_recs.DailyValueRecsOutputData;

public class DailyValueRecsPresenter implements DailyValueRecsOutputBoundary {

    private final MainViewModel mainViewModel;
    private final ViewManagerModel viewManagerModel;

    public DailyValueRecsPresenter(ViewManagerModel viewManagerModel, MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DailyValueRecsOutputData dailyValueRecsOutputData) {
        MainViewState mainViewState = mainViewModel.getState();
        mainViewState.setPercent_cals(dailyValueRecsOutputData.getPercent_cals());
        mainViewState.setPercent_prot(dailyValueRecsOutputData.getPercent_prot());
        mainViewState.setPercent_carbs(dailyValueRecsOutputData.getPercent_carbs());
        mainViewState.setPercent_fat(dailyValueRecsOutputData.getPercent_fat());
        this.mainViewModel.setState(mainViewState);
        this.mainViewModel.firePropertyChanged();

    }
}
