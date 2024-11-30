package interface_adapter.delete_food;

import interface_adapter.ViewModel;

public class DeleteFoodViewModel extends ViewModel<DeleteFoodState> {

    public DeleteFoodViewModel() {
        super("delete food");
        setState(new DeleteFoodState());
    }
}
