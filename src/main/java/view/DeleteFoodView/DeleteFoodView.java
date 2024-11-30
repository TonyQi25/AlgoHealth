package view.DeleteFoodView;

import interface_adapter.delete_food.DeleteFoodPresenter;
import interface_adapter.delete_food.DeleteFoodViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteFoodView extends JPanel implements PropertyChangeListener {

    private final DeleteFoodViewModel deleteFoodViewModel;
    private final DeleteFoodPresenter deleteFoodPresenter;
    private final String viewName = "delete food";

    private final JPanel deleteFoodPanel = new JPanel();

    public DeleteFoodView(DeleteFoodViewModel deleteFoodViewModel, DeleteFoodPresenter deleteFoodPresenter) {
        this.deleteFoodViewModel = deleteFoodViewModel;
        this.deleteFoodPresenter = deleteFoodPresenter;

        this.deleteFoodViewModel.addPropertyChangeListener(this);


    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
