package interface_adapter.history;

import interface_adapter.ViewModel;

public class HistoryViewModel extends ViewModel<HistoryState> {

    public HistoryViewModel(String viewName) {
        super("history");
        setState(new HistoryState());
    }

}
