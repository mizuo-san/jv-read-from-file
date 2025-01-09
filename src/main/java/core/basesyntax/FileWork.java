package core.basesyntax;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileWork {
    public String[] readFromFile(String fileName) {
        StringBuilder wText = new StringBuilder();
        List<Character> keySymbols = List.of('.', ',', '!', '?');

        try(BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            if(checkFileEmpty(fileName)) {
                return new String[0];
            }

            String read = file.readLine();

            while (read != null) {
                String[] words = read.split(" ");

                for (String word : words) {
                    word = word.toLowerCase();

                    if(keySymbols.contains(word.charAt(word.length() - 1))) {
                        word = word.substring(0, word.length() - 1);
                    }

                    if(word.charAt(0) == 'w') {
                        wText.append(word).append(" ");
                    }
                }

                read = file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(wText.isEmpty())
            return new String[0];

        return sortedArray(wText);
    }

    private boolean checkFileEmpty(String fileName) {
        File fw = new File(fileName);
        return fw.length() == 0;
    }

    private String[] sortedArray(StringBuilder array) {
        String[] words = array.toString().split(" ");
        Arrays.sort(words);

        return words;
    }
}
