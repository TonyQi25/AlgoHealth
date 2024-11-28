package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapFromString {

    public static HashMap<String, Object> hashMapFromString(String hashMapString) {
        String noBracesString = hashMapString.substring(1, hashMapString.length() - 1);
        String[] splitString = noBracesString.split(", ");

        HashMap<String, Object> newHashMap = new HashMap<>();
        for (String keyValuePair : splitString) {
            String[] keyValueArray = keyValuePair.split("=");
            newHashMap.put(keyValueArray[0], keyValueArray[1]);
        }

        return newHashMap;
    }

//    public static HashMap<String, HashMap<String, Object>> nestedHashMapFromString (String nestedHashMapString) {
//        String noBracesString = nestedHashMapString.substring(1, nestedHashMapString.length() - 1);
//
//        List<String> stringValues = new ArrayList<>();
//        StringBuilder currValue = new StringBuilder();
//        boolean copyValue = false;
//        for (int i = 0; i < noBracesString.length(); i++) {
//            if (noBracesString.charAt(i) == '{') {
//                copyValue = true;
//            }
//
//            if (copyValue) {
//                currValue.append(noBracesString.charAt(i));
//            }
//
//            if (noBracesString.charAt(i) == '}') {
//                copyValue = false;
//                stringValues.add(currValue.toString());
//                currValue = new StringBuilder();
//            }
//        }
//
//        for (String value : stringValues) {
//            for (int i = 0; i < noBracesString.length() - value.length(); i++) {
//                if (noBracesString.startsWith(value, i)) {
//                    String noBracesStringStart = noBracesString.substring(0, i);
//
//                    System.out.println(noBracesStringStart);
//
//                    String noBracesStringEnd = "";
//                    if (!(value.length() + i == noBracesString.length())) {
//                        noBracesStringEnd = noBracesString.substring(value.length() + i + 1);
//                    }
//
//                    System.out.println(noBracesStringEnd);
//
//                    noBracesString = noBracesStringStart + noBracesStringEnd;
//                    break;
//                }
//            }
//        }
//
//        noBracesString = noBracesString.replace('=', ' ');
//        String[] stringKeys = noBracesString.split(", ");
//        String[] formattedStringKeys = new String[stringKeys.length];
//        for (int j = 0; j < stringKeys.length; j++) {
//            formattedStringKeys[j] = stringKeys[j].strip();
//        }
//
//        HashMap<String, HashMap<String, Object>> newNestedHashMap = new HashMap<>();
//
//        System.out.println(stringValues.size());
//        System.out.println(stringValues);
//        System.out.println(formattedStringKeys.length);
//        System.out.println(Arrays.toString(formattedStringKeys));
//
//        for (int k = 0; k < formattedStringKeys.length; k++) {
//            HashMap<String, Object> innerHashMap = hashMapFromString(stringValues.get(k));
//            newNestedHashMap.put(formattedStringKeys[k], innerHashMap);
//        }
//
//        return newNestedHashMap;
//    }
}
