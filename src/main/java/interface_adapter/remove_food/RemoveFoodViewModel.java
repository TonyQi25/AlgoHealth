package interface_adapter.remove_food;

import interface_adapter.ViewModel;

public class RemoveFoodViewModel extends ViewModel<RemoveFoodState> {

    public RemoveFoodViewModel() {
        super("remove food");
        setState(new RemoveFoodState());
    }
}
