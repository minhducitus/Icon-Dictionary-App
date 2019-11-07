package Data;

import DTO.Dictionary;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextDataHelper {
    private static final String PATH_DICTIONARY = "emotional dictionary.txt";

    public  static Dictionary loadResource(DefaultListModel word) throws IOException {
        try {
            Dictionary dictionary = new Dictionary();
            BufferedReader reader = new BufferedReader(new FileReader(PATH_DICTIONARY));
            String currentLine;
            String[] token = new String[2];
            while ((currentLine = reader.readLine()) != null) {
                token = currentLine.split("\\s+", 2);
                dictionary.add(token[0], token[1]);
            }
            return dictionary;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
