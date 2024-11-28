//package app;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.remove_food.RemoveFoodController;
//import interface_adapter.remove_food.RemoveFoodPresenter;
//import interface_adapter.remove_food.RemoveFoodViewModel;
//import use_case.removeFood.RemoveFoodDataAccessInterface;
//import use_case.removeFood.RemoveFoodInputBoundary;
//import use_case.removeFood.RemoveFoodInteractor;
//import use_case.removeFood.RemoveFoodOutputBoundary;
//import view.removeFoodView.RemoveFoodView;
//
//public class RemoveFoodUseCaseFactory {
//
//    private RemoveFoodUseCaseFactory() {
//
//    }
//
//    public static RemoveFoodView create(ViewManagerModel viewManagerModel,
//                                        RemoveFoodViewModel removeFoodViewModel,
//                                        RemoveFoodDataAccessInterface removeFoodDataAccessInterface) {
//        final RemoveFoodController removeFoodController = createRemoveFoodUseCase(viewManagerModel, removeFoodViewModel, removeFoodDataAccessInterface);
//
//        return new RemoveFoodView(removeFoodViewModel, removeFoodController);
//    }
//
//    public static RemoveFoodController createRemoveFoodUseCase(
//            ViewManagerModel viewManagerModel,
//            RemoveFoodViewModel removeFoodViewModel,
//            RemoveFoodDataAccessInterface removeFoodDataAccessInterface) {
//
//        final RemoveFoodOutputBoundary removeFoodOutputBoundary = new RemoveFoodPresenter(removeFoodViewModel, viewManagerModel);
//        final RemoveFoodInputBoundary removeFoodInteractor = new RemoveFoodInteractor(removeFoodOutputBoundary, removeFoodDataAccessInterface);
//
//        return new RemoveFoodController(removeFoodInteractor);
//    }
//}
