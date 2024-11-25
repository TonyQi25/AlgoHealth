package view.Helpers;

import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NumericFilter implements DocumentListener {

    private final SignupViewModel signupViewModel;

    private final String fieldName;
    private final JTextField textField;
    private final int maxLength;

    public NumericFilter(SignupViewModel signupViewModel, String fieldName,
                         JTextField textField, int maxLength) {
        this.signupViewModel = signupViewModel;
        this.fieldName = fieldName;
        this.textField = textField;
        this.maxLength = maxLength;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        String currText = this.textField.getText();
        int textLength = currText.length();
        int addedStartIndex = e.getOffset();
        int addedTextLength = e.getLength();

        if (!this.isNumeric(currText) || textLength > this.maxLength) {
            this.maintainField(textField, addedStartIndex, addedTextLength);
        } else {
            // executes the below if the filter is used in signup
            switch (fieldName) {  // never needs to worry about field being empty, but removeUpdate does
                case "whole height": {
                    int wholeHeight = Integer.parseInt(currText);
                    float decHeight = this.signupViewModel.getState().getHeight() -
                            (int) this.signupViewModel.getState().getHeight();

                    this.signupViewModel.getState().setHeight(wholeHeight + decHeight);

                    System.out.println(this.signupViewModel.getState().getHeight());

                    break;
                }
                case "decimal height": {
                    int wholeHeight = (int) this.signupViewModel.getState().getHeight();
                    float decHeight = this.getDecimal(currText);

                    this.signupViewModel.getState().setHeight(wholeHeight + decHeight);

                    System.out.println(this.signupViewModel.getState().getHeight());

                    break;
                }
                case "whole weight": {
                    int wholeWeight = Integer.parseInt(currText);
                    float decWeight = this.signupViewModel.getState().getWeight() -
                            (int) this.signupViewModel.getState().getWeight();
                    this.signupViewModel.getState().setWeight(wholeWeight + decWeight);

                    System.out.println(this.signupViewModel.getState().getWeight());

                    break;
                }
                case "decimal weight": {
                    int wholeWeight = (int) this.signupViewModel.getState().getWeight();
                    float decWeight = this.getDecimal(currText);

                    this.signupViewModel.getState().setWeight(wholeWeight + decWeight);

                    System.out.println(this.signupViewModel.getState().getWeight());

                    break;
                }
            }
        }

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String currText = this.textField.getText();

        // executes the below if the filter is used in signup
        switch (fieldName) {
            case "whole height": {
                int wholeHeight = 0;
                if (!currText.isEmpty()) {
                    wholeHeight = Integer.parseInt(currText);
                }

                float decHeight = this.signupViewModel.getState().getHeight() -
                        (int) this.signupViewModel.getState().getHeight();

                this.signupViewModel.getState().setHeight(wholeHeight + decHeight);

                System.out.println(this.signupViewModel.getState().getHeight());

                break;
            }
            case "decimal height": {
                int wholeHeight = (int) this.signupViewModel.getState().getHeight();

                float decHeight = 0;
                if (!currText.isEmpty()) {
                    decHeight = this.getDecimal(currText);
                }

                this.signupViewModel.getState().setHeight(wholeHeight + decHeight);

                System.out.println(this.signupViewModel.getState().getHeight());

                break;
            }
            case "whole weight": {
                int wholeWeight = 0;
                if (!currText.isEmpty()) {
                    wholeWeight = Integer.parseInt(currText);
                }

                float decWeight = this.signupViewModel.getState().getWeight() -
                        (int) this.signupViewModel.getState().getWeight();

                this.signupViewModel.getState().setWeight(wholeWeight + decWeight);

                System.out.println(this.signupViewModel.getState().getWeight());

                break;
            }
            case "decimal weight": {
                int wholeWeight = (int) this.signupViewModel.getState().getWeight();

                float decWeight = 0;
                if (!currText.isEmpty()) {
                    decWeight = this.getDecimal(currText);
                }

                this.signupViewModel.getState().setWeight(wholeWeight + decWeight);

                System.out.println(this.signupViewModel.getState().getWeight());

                break;
            }
        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public boolean isNumeric(String numStr) {
        for (int i = 0; i < numStr.length(); i++) {
            if (!Character.isDigit(numStr.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void revertText(JTextField textField, int addedIndex, int addedLength) {
        Runnable doRevertText = new Runnable() {
            @Override
            public void run() {
                String currText = textField.getText();
                String firstHalf = currText.substring(0, addedIndex);
                String secondHalf = "";

                if (addedIndex + addedLength <= currText.length()) {
                    secondHalf = currText.substring(addedIndex + addedLength);
                }

                String revertText = firstHalf + secondHalf;
                textField.setText(revertText);
            }
        };
        SwingUtilities.invokeLater(doRevertText);
    }

    public void keepCaratPosition(JTextField textField, int originalPosition) {
        Runnable doKeepCaratPosition = new Runnable() {
            @Override
            public void run() {
                textField.setCaretPosition(originalPosition);
            }
        };
        SwingUtilities.invokeLater(doKeepCaratPosition);
    }

    public void maintainField(JTextField textField, int originalPosition, int addedLength) {
        this.revertText(textField, originalPosition, addedLength);
        this.keepCaratPosition(textField, originalPosition);
    }

    public float getDecimal(String fullNum) {
        if (fullNum.length() < 2) {
            return Float.parseFloat(fullNum) / 10;
        } else {
            return Float.parseFloat(fullNum) / 100;
        }
    }


}
