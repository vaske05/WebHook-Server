package com.elfak.whserver.helpers;

public class UrlHelper {

    public static String replaceStringOccurrence(String source, String oldString, String newString) {
        String desiredString = source;
        while (desiredString.contains(oldString)) {
            String firstPart = desiredString.substring(0, desiredString.indexOf(oldString));
            String secondPart = desiredString.substring(desiredString.indexOf(oldString) + 3);
            desiredString = firstPart + newString + secondPart;
        }
        return desiredString;
    }
}