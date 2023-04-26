package com.beginsecure.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DataSorter {

    public String removeDublicate(String stringForEdit) {
        List<String> listOfWords = Arrays.asList(stringForEdit.split(" "));
        Map<String, String> unsortedWords = createMap(listOfWords);
        Map<String, String> sortedWords = new TreeMap<>((String s1, String s2) -> {
            if (s1.length() < s2.length()) {
                return -1;
            } else if (s1.length() > s2.length()) {
                return 1;
            } else {
                return s1.compareTo(s2);
            }
        });

        sortedWords.putAll(unsortedWords);
        return sortedWords.values().stream().collect(Collectors.joining(" "));
    }

    private Map<String, String> createMap(List<String> listOfWords) {
        Map<String, String> unsortedWords = new TreeMap<>();
        listOfWords.stream().forEach(e -> {
            e = cutStringLongerThan30(e);
            unsortedWords.put(removeVowels(e), e);
        });
        return unsortedWords;
    }

    private String cutStringLongerThan30(String e) {
        if (e.length() > 30) {
            e = e.substring(0, 29);
        }
        return e;
    }

    public String removeVowels(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        return word.replaceAll("[aeiouAEIOUаоуиеіАОУИЕІ]", "");
    }


}
