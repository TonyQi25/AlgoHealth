package view.MainView;


public class ViewFormattingUtility {

/*    *//**
     * Makes a string have length greater than or equal to its original length
     * by appending whitespace.
     * @param baseString the string to be lengthened.
     * @param X the length we want the final string.
     * @return the string of length x with trailing whitespace as needed to make it such a length.
     *//*
    public static String makeStringXLength(String baseString, int X) {
        int originalLength = baseString.length();
        int whitespaceLength = X - originalLength;
        String whitespace = "";
        int i = 0;
        while (i <= whitespaceLength - 1) {
            whitespace += " ";
            i += 1;
        }
        return baseString + whitespace;
    }*/

    /**
     * Truncates a string that represents a double to 2 decimal places.
     * @param number a string that represents a real number in decimal format.
     * @return a string representation of the number with all numbers in decimal places strictly after the
     * second simply dropped. If the number is natural or has 1 decimal place only, the function just returns
     * the number.
     */
    public static String truncateString2Places(String number) {
        StringBuilder num = new StringBuilder(number);
        char[] dst = new char[number.length()];
        num.getChars(0, number.length(), dst, 0);
        Integer i = 0;
        String roundedNum = "";
        while ( i < dst.length && dst[i] != '.') {
            roundedNum += dst[i];
            i += 1;
        }
        if (i.equals(dst.length)) {
            return roundedNum;
        }
        else if (i.equals(dst.length - 2)) {
            // we're at '.' so append that, then append the next, and append a number based on what the next
            // number is.
            roundedNum += dst[i];
            roundedNum += dst[i + 1];
        }
       /* if (Integer.valueOf(dst[i + 2]) >= 5) {
            roundedNum += dst[i + 2]) + 1;
        }*/
        else {
            roundedNum += dst[i];
            roundedNum += dst[i + 1];
            roundedNum += dst[i + 2];
        }
        return roundedNum;

    }

    public static void main(String[] args) {
 /*       String basestring = "Total calories: 0Kcal";
        String newString = makeStringXLength(basestring, ("Total calories: 0Kcal" + "                               ")
                .length());
        boolean bool = basestring.equals(newString);
        int len = newString.length();
        int len2 = ("Total calories: 0Kcal" + "                               ").length();*/
        String num = "2.134";
        String num2 = truncateString2Places(num);
        num = "2.1";
        String num3 = truncateString2Places(num);
        num = "2.12";
        num2 = truncateString2Places("546");

        num2= truncateString2Places("198765437777777777.2754992923");

    }
}
