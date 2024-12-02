package use_case.removeFood;

import use_case.history.HistoryInputData;

/**
 * use case interactor for remove food
 */
public class RemoveFoodInteractor implements RemoveFoodInputBoundary{

    private final RemoveFoodOutputBoundary outputBoundary;
    private final RemoveFoodDataAccessInterface removeFoodDataAccessInterface;


    public RemoveFoodInteractor(RemoveFoodOutputBoundary removeFoodOutputBoundary,
                                RemoveFoodDataAccessInterface removeFoodDataAccessInterface) {

        this.outputBoundary = removeFoodOutputBoundary;
        this.removeFoodDataAccessInterface = removeFoodDataAccessInterface;

    }

    /**
     * executes the use case
     * @param input input
     */
    @Override
    public void execute(RemoveFoodInputData input) {

        System.out.println("removing food");
        if (input.getFoodName().isEmpty()) {
            System.out.println("error No food selected");
            outputBoundary.prepareFailView("No Food Selected");
        }   else {

            removeFoodDataAccessInterface.removeFood(input.getViewingDate(), input.getUsername(), input.getPassword(),
                    String.valueOf(removeFoodDataAccessInterface.FoodExists(input.getViewingDate(), input.getUsername(), input.getFoodName())));

            RemoveFoodOutputData output = new RemoveFoodOutputData(input.getFoodName() + " is removed", input.getUsername(), input.getViewingDate());
            outputBoundary.prepareSuccessView(output);
        }
    }

    /**
     * returns to history view
     * @param input input
     */
    public void returnToHistory(HistoryInputData input) {
        outputBoundary.prepareHistoryView(input);
    }
}
