package helpers;

import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;

public class DOBSelectorListener implements ActionListener {

    private final int febDays = 28;

    private final SignupViewModel signupViewModel;

    private final JComboBox<String> yearField;
    private final JComboBox<String> monthField;
    private final JComboBox<String> dayField;

    public DOBSelectorListener(SignupViewModel signupViewModel, JComboBox<String> yearField,
                               JComboBox<String> monthField, JComboBox<String> dayField) {
        this.signupViewModel = signupViewModel;
        this.yearField = yearField;
        this.monthField = monthField;
        this.dayField = dayField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final int selectedYear = LocalDate.now().getYear() - yearField.getSelectedIndex();
        final int selectedMonth = monthField.getSelectedIndex() + 1;
        int selectedDayIndex = dayField.getSelectedIndex();

        int maxDay = LocalDate.of(selectedYear, selectedMonth, 1).lengthOfMonth();
        if (!LocalDate.now().withYear(selectedYear).isLeapYear() && selectedMonth == 2) {
            maxDay = this.febDays;
        }

        final String options = Arrays.toString(IntStream.rangeClosed(1, maxDay).toArray());
        final String[] optionsArray = options.substring(1, options.length() - 1).split(", ");

        dayField.removeAllItems();

        for (String option: optionsArray) {
            dayField.addItem(String.valueOf(option));
        }

        if (selectedDayIndex < maxDay) {
            dayField.setSelectedIndex(selectedDayIndex);
        }
        else {
            selectedDayIndex = 0;
        }

        signupViewModel.getState().setDateOfBirth(
                LocalDate.of(selectedYear, selectedMonth, selectedDayIndex + 1));

        System.out.println(LocalDate.of(selectedYear, selectedMonth, selectedDayIndex + 1));
    }
}
