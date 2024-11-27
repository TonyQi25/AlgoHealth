package use_case.removeFood;

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

    }
}
