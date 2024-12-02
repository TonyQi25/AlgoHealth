package use_case.removeFood;

import use_case.history.HistoryInputData;

public class RemoveFoodInteractor implements RemoveFoodInputBoundary{

    private final RemoveFoodOutputBoundary outputBoundary;
    private final RemoveFoodDataAccessInterface removeFoodDataAccessInterface;


    public RemoveFoodInteractor(RemoveFoodOutputBoundary removeFoodOutputBoundary,
                                RemoveFoodDataAccessInterface removeFoodDataAccessInterface) {

        this.outputBoundary = removeFoodOutputBoundary;
        this.removeFoodDataAccessInterface = removeFoodDataAccessInterface;

    }

    @Override
    public void execute(RemoveFoodInputData input) {

        System.out.println("removing food");
        if (input.getFoodName().isEmpty()) {
            System.out.println("error No food selected");
            outputBoundary.prepareFailView("No Food Selected");
        }   else {

            removeFoodDataAccessInterface.removeFood(input.getViewingDate(), input.getUsername(), input.getPassword(),
                    String.valueOf(removeFoodDataAccessInterface.foodExists(input.getViewingDate(), input.getUsername(), input.getFoodName())));

            RemoveFoodOutputData output = new RemoveFoodOutputData(input.getFoodName() + " is removed", input.getUsername(), input.getViewingDate());
            outputBoundary.prepareSuccessView(output);
        }
    }

    public void returnToHistory(HistoryInputData input) {
        outputBoundary.prepareHistoryView(input);
    }
}
