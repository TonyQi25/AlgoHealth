package use_case.removeFood;

import use_case.history.HistoryInputData;
import use_case.history.HistoryInteractor;

public class RemoveFoodInteractor implements RemoveFoodInputBoundary{

    private RemoveFoodOutputBoundary outputBoundary;
    private RemoveFoodDataAccessInterface removeFoodDataAccessInterface;


    public RemoveFoodInteractor(RemoveFoodOutputBoundary removeFoodOutputBoundary,
                                RemoveFoodDataAccessInterface removeFoodDataAccessInterface) {

        this.outputBoundary = removeFoodOutputBoundary;
        this.removeFoodDataAccessInterface = removeFoodDataAccessInterface;

    }

    @Override
    public void execute(RemoveFoodInputData input) {

        // call remove food method
        System.out.println("removing food");
        if (input.getFoodName().isEmpty()) {
            System.out.println("error No food selected");
            outputBoundary.prepareFailView("No Food Selected");
        }   else {
            RemoveFoodOutputData output = new RemoveFoodOutputData(input.getFoodName() + " is removed", input.getUsername(), input.getViewingDate());
            outputBoundary.prepareSuccessView(output);
        }
    }

    public void returnToHistory(HistoryInputData input) {
        outputBoundary.prepareHistoryView(input);
    }
}