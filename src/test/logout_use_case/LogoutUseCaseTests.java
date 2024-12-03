package logout_use_case;

import data_access.GradeAccountDAO;
import org.junit.jupiter.api.Test;
import use_case.logout.*;

import static org.junit.Assert.assertEquals;

public class LogoutUseCaseTests {

    @Test
    void logoutInteractorSuccessTest() {
        // existing account info
        LogoutDataAccessInterface logoutDataAccessObject = new GradeAccountDAO();
        LogoutOutputBoundary logoutPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData logoutOutputData) {
                assertEquals("", logoutOutputData.getUsername());
                assertEquals("", logoutOutputData.getPassword());
            }
        };
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutDataAccessObject, logoutPresenter);
        logoutInteractor.execute();
    }

}
