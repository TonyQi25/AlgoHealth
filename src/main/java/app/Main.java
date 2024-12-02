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
                .addOneDayHistoryView()
                .addHistoryView()
                .addRemoveFoodView()
                .addDailyValueRecsUseCase()
                .selectFromFoodOptionsUseCase()
                .addFoodLoggingUseCase()
                .addLogoutUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
