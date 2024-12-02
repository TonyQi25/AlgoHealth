package interface_adapter.history;

import interface_adapter.ViewModel;

/**
 * view model for history use case
 */
public class HistoryViewModel extends ViewModel<HistoryState> {

    public HistoryViewModel() {
        super("history");
        setState(new HistoryState());
    }
}
