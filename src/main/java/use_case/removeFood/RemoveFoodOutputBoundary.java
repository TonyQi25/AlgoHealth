package use_case.removeFood;

public interface RemoveFoodOutputBoundary {

    void prepareSuccessView(RemoveFoodOutputData data);

    void prepareFailView(String errorMessage);
}
