package interface_adapter.login;

import data.DayInfo;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.MainView.mainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final mainView homePage;

    public LoginPresenter(mainView homePage) {
        this.homePage = homePage;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        List<DayInfo> days = loginOutputData.getDays();
        int latestDayIndex = days.size() - 1;
        homePage.genMvGUI(days.get(latestDayIndex));
    }

    @Override
    public void prepareFailView(String error) {
        JDialog failedLoginFrame = new JDialog();

        JPanel failedLoginLabelPanel = new JPanel();
        JLabel failedLoginLabel = new JLabel(error);
        failedLoginLabelPanel.add(failedLoginLabel);

        JPanel failedLoginButtonPanel = new JPanel();
        JButton failedLoginButton = new JButton("OK");
        failedLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                failedLoginFrame.dispose();
            }
        });
        failedLoginButtonPanel.add(failedLoginButton);

        JPanel failedLoginPanel = new JPanel();
        failedLoginPanel.setLayout(new BoxLayout(failedLoginPanel, BoxLayout.Y_AXIS));
        failedLoginPanel.add(failedLoginLabelPanel);
        failedLoginPanel.add(failedLoginButtonPanel);

        failedLoginFrame.setModal(true);
        failedLoginFrame.setContentPane(failedLoginPanel);
        failedLoginFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        failedLoginFrame.pack();
        failedLoginFrame.setVisible(true);

    }
}
