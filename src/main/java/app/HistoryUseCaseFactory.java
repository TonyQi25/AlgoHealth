//package app;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.history.HistoryController;
//import interface_adapter.history.HistoryPresenter;
//import interface_adapter.history.HistoryViewModel;
//import interface_adapter.remove_food.RemoveFoodController;
//import use_case.history.HistoryDataAccessInterface;
//import use_case.history.HistoryInputBoundary;
//import use_case.history.HistoryInteractor;
//import use_case.history.HistoryOutputBoundary;
//import view.HistoryView.HistoryView;
//
//public final class HistoryUseCaseFactory {
//
//    private HistoryUseCaseFactory() {
//
//    }
//
//    public static HistoryView create(ViewManagerModel viewManagerModel,
//                                     HistoryViewModel historyViewModel,
//                                     HistoryDataAccessInterface historyDAO) {
//        final HistoryController historyController = createHistoryUseCase(viewManagerModel, historyViewModel, historyDAO);
//
//        return new HistoryView(historyViewModel, historyController);
//    }
//
//    private static HistoryController createHistoryUseCase(
//            ViewManagerModel viewManagerModel,
//            HistoryViewModel historyViewModel,
//            HistoryDataAccessInterface historyDAO) {
//
//        final HistoryOutputBoundary historyOutputBoundary = new HistoryPresenter(historyViewModel, removeFoodViewModel, viewManagerModel);
//        final HistoryInputBoundary historyInteractor = new HistoryInteractor(historyOutputBoundary, historyDAO);
//
//        return new HistoryController(historyInteractor);
//    }
//}
