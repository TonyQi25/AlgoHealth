package view.TextFieldProperties;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NumericFilter implements DocumentListener {

    private final JTextField textField;
    private final int maxLength;
    private final int[] changeLength = new int[1];     // hard dependency, is this a problem?

    public NumericFilter(JTextField textField, int maxLength) {
        this.textField = textField;
        this.maxLength = maxLength;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
//        String currText = this.textField.getText();
//        int textLength = currText.length();
//        int addedIndex = e.getOffset();
//        char addedChar = currText.charAt(addedIndex);
//        int added
//
//        System.out.println(e.getLength());
//
//        if (!Character.isDigit(addedChar) || textLength > this.maxLength) {
//            this.maintainField(textField, addedIndex);
//        }

        String currText = this.textField.getText();
        int textLength = currText.length();
        int addedStartIndex = e.getOffset();
        int addedTextLength = e.getLength();

        if (!this.isNumeric(currText) || textLength > this.maxLength) {
            this.maintainField(textField, addedStartIndex, addedTextLength);
        }

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

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
}
