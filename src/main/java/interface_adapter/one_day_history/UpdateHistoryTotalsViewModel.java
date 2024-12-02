package interface_adapter.one_day_history;

import interface_adapter.ViewModel;

public class UpdateHistoryTotalsViewModel extends ViewModel<UpdateHistoryTotalsState> {

    public UpdateHistoryTotalsViewModel() {
        super("update history totals");
        setState(new UpdateHistoryTotalsState());
    }
}
