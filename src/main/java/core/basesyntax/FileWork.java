package core.basesyntax;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileWork {
    private final int firstIndex = 0;
    private final StringBuilder wText = new StringBuilder();
    private final List<Character> keySymbols = List.of('.', ',', '!', '?');

    public String[] readFromFile(String fileName) {

        try(BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            if(checkFileEmpty(fileName)) {
                return new String[firstIndex];
            }

            String read = file.readLine();

            while (read != null) {
                String[] words = read.split(" ");

                for (String word : words) {
                    word = checkStringOnLastSymbol(word);
                    checkAndAppendWWord(word);
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

    private void checkAndAppendWWord(String word) {
        if(word.charAt(firstIndex) == 'w') {
            wText.append(word).append(" ");
        }
    }

    private String checkStringOnLastSymbol(String word) {
        word = word.toLowerCase();
        int lastIndexInWord = word.length() - 1;

        if(keySymbols.contains(word.charAt(lastIndexInWord))) {
            return word.substring(firstIndex, lastIndexInWord);
        }

        return word;
    }
}
