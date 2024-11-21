package view.HistoryView;

import java.awt.*;
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
        this.historyFrame.setLayout(new BoxLayout(this.historyFrame.getContentPane(), BoxLayout.Y_AXIS));
    }

    public void resetDate() {
        this.viewingDate = LocalDate.now();
    }

    public void loadInfo() {

    }

    public void setHeaderPanel() {
        headerPanel = new JPanel();
        JLabel dateLabel = new JLabel("Date: " + this.viewingDate.toString());
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.add(getPrevDayButton(viewingDate.minusDays(1)));
        headerPanel.add(dateLabel);
        headerPanel.add(getNextDayButton(viewingDate.plusDays(1)));
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        historyPanel.add(headerPanel);

    }

    public JButton getPrevDayButton(LocalDate date) {
        return createPrevDayButton(date);
    }

    private JButton createPrevDayButton(LocalDate date) {
        // calls to controller
        JButton returning = new JButton("Previous Day");
        returning.setAlignmentX(Component.CENTER_ALIGNMENT);
        return returning;
    }

    public JButton getNextDayButton(LocalDate date) {
        return createNextDayButton(date);
    }

    private JButton createNextDayButton(LocalDate date) {
        return new JButton("Next Day");
    }

    public void setDataPanel() {
        dataPanel = new JPanel();

    }

    public JList<String> createDataList() {
        JList<String> list = new JList<String>();



        return list;
    }

    public JFrame build() {
        historyFrame.setContentPane(historyPanel);
        historyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setHeaderPanel();
        return historyFrame;
    }

    public static void main(String[] args) {
    }
}
