package com.elfak.whserver.helpers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class UrlHelper {

    public String replaceStringOccurrence(String source, String oldString, String newString) {
        String desiredString = source;
        while (desiredString.contains(oldString)) {
            String firstPart = source.substring(0, source.indexOf(oldString));
            String secondPart = source.substring(source.indexOf(oldString) + 3, source.length());
            desiredString = firstPart + newString + secondPart;
        }
        return desiredString;
    }
}