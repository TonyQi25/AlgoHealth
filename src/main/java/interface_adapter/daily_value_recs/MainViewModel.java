package interface_adapter.daily_value_recs;

import interface_adapter.ViewModel;

public class MainViewModel extends ViewModel<MainViewState> {

    public MainViewModel() {
        super("main view");
        setState(new MainViewState());
    }
}
