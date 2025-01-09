package core.basesyntax;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileWork {
    private final int firstIndex = 0;

    public String[] readFromFile(String fileName) {
        StringBuilder wText = new StringBuilder();
        List<Character> keySymbols = List.of('.', ',', '!', '?');

        try(BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            if(checkFileEmpty(fileName)) {
                return new String[firstIndex];
            }

            String read = file.readLine();
            int lastIndexInWord;

            while (read != null) {
                String[] words = read.split(" ");

                for (String word : words) {
                    word = word.toLowerCase();
                    lastIndexInWord = word.length() - 1;

                    if(keySymbols.contains(word.charAt(lastIndexInWord))) {
                        word = word.substring(firstIndex, lastIndexInWord);
                    }

                    if(word.charAt(firstIndex) == 'w') {
                        wText.append(word).append(" ");
                    }
                }

                read = file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(wText.isEmpty())
            return new String[firstIndex];

        return sortedArray(wText);
    }

    private boolean checkFileEmpty(String fileName) {
        File fw = new File(fileName);
        return fw.length() == 0;
    }

    private String[] sortedArray(StringBuilder array) {
        String[] sortedArray = array.toString().split(" ");
        Arrays.sort(sortedArray);

        return sortedArray;
    }
}
