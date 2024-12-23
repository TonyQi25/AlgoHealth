package view.MainView;

import interface_adapter.daily_value_recs.MainViewState;
import interface_adapter.food_logging.LogFoodState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class selectFromListPopup extends JFrame {



    public selectFromListPopup(LogFoodState currentState, String[] foodList) {
        JPanel popUpPanel = new JPanel();
        JComboBox dropdown = new JComboBox(foodList);
        JButton selectAndReturn = new JButton("Select and Return");
        popUpPanel.add(dropdown);
        popUpPanel.add(selectAndReturn);

        JFrame jframe1 = new JFrame();
        jframe1.setContentPane(popUpPanel);
        jframe1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jframe1.setVisible(true);
        jframe1.pack();

        dropdown.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(dropdown)) {
                            JComboBox comboBox = (JComboBox) evt.getSource();
                            String selected = (String) comboBox.getSelectedItem();
                            int i = 0;
                            currentState.setFdcIDofSelection(currentState.getFoodOptionsMap().get(selected));
                        }

                    }
                });
        selectAndReturn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(selectAndReturn)) {
                            System.out.println(currentState.getFdcIDofSelection());
                            jframe1.dispose();

                        }
                    }
                }
        );
    }
    }