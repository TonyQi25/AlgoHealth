package interface_adapter.display_food_options;

import interface_adapter.ViewModel;
import interface_adapter.daily_value_recs.MainViewState;

public class DisplayOptionsViewModel extends ViewModel<DisplayOptionsViewState> {

    public DisplayOptionsViewModel() {
        super("display options view");
        setState(new DisplayOptionsViewState());

    }
}
