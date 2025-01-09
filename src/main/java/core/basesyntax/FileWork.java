package core.basesyntax;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileWork {
    public String[] readFromFile(String fileName) {
        StringBuilder wText = new StringBuilder();
        List<Character> ar = List.of('.', ',', '!', '?');

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            if(checkFileEmpty(fileName)) {
                return new String[0];
            }

            String read = br.readLine();

            while (read != null) {
                String[] words = read.split(" ");
                for (String word : words) {
                    word = word.toLowerCase();

                    if(ar.contains(word.charAt(word.length() - 1))) {
                        word = word.substring(0, word.length() - 1);
                    }

                    if(word.charAt(0) == 'w') {
                        wText.append(word).append(" ");
                    }
                }

                read = br.readLine();
            }
        } catch (IOException e) {}

        if(wText.isEmpty()) {
            return new String[0];
        }

        String[] words = wText.toString().split(" ");
        Arrays.sort(words);
        return words;
    }

    private boolean checkFileEmpty(String fileName) {
        File fw = new File(fileName);
        return fw.length() == 0;
    }
}
