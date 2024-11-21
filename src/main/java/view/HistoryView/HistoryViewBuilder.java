package view.HistoryView;

import java.time.LocalDate;
import javax.swing.*;

public class HistoryViewBuilder {

    private final JFrame historyFrame;
    private final JPanel historyPanel;
    private JPanel headerPanel;
    private JPanel dataPanel;
    private LocalDate viewingDate;

    public HistoryViewBuilder(JPanel historyPanel, JFrame historyFrame) {
        this.historyPanel = historyPanel;
        this.historyFrame = historyFrame;
        resetDate();
        this.historyPanel.setLayout(new BoxLayout(this.historyPanel, BoxLayout.Y_AXIS));
    }

    public void resetDate() {
        this.viewingDate = LocalDate.now();
    }

    public void loadInfo() {

    }

    public void setHeaderPanel() {
        JPanel headerPanel = new JPanel();
        JLabel dateLabel = new JLabel("Date ...");

    }
    /*
    public JButton getPrevDayButton(LocalDate date) {
        return createPrevDayButton(date);
    }

    private JButton createPrevDayButton(LocalDate date) {
        // calls to controller
    }

    public JButton getNextDayButton() {
        return createNextDayButton();
    }

    private JButton createNextDayButton() {

    }
    */
    public void setDataPanel() {

    }
}
