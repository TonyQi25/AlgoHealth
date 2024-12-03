package app;

import javax.swing.*;

public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addMainView()
                .addDisplayOptionsView()
                .addDisplayOptionsUseCase()
                .addLoginView()
                .addSignupView()
                .addDailyValueRecsUseCase()
                .selectFromFoodOptionsUseCase()
                .addOneDayHistoryView() // has to be after select
                .addHistoryView() // has to be after add one day
                .addRemoveFoodView() // has to be after add history view
                .addFoodLoggingUseCase()
                .addLogoutUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
