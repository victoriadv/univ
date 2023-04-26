package com.beginsecure.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFileRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the text:");
        List<String> inputLines = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            inputLines.add(line);
        }

        TextParser parser = new TextParser();
        DataSorter sortData = new DataSorter();

        String parsedData = parser.parseText(inputLines);
        System.out.println("Processed data:");
        System.out.println(sortData.removeDublicate(parsedData));
    }
}
