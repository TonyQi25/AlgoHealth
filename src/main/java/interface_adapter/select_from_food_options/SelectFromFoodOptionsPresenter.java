package interface_adapter.select_from_food_options;

import interface_adapter.ViewManagerModel;
import use_case.select_from_food_options.SelectFromFoodOptionsOutputBoundary;
import use_case.select_from_food_options.SelectFromFoodOptionsOutputData;

public class SelectFromFoodOptionsPresenter implements SelectFromFoodOptionsOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    public SelectFromFoodOptionsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SelectFromFoodOptionsOutputData selectFromFoodOptionsOutputData) {
        this.viewManagerModel.setState("main view");
        this.viewManagerModel.firePropertyChanged();
    }
}
