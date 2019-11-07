package Business;

import DTO.Dictionary;

import javax.swing.*;
import java.util.ArrayList;

public class WorldHandler {
    public static DefaultListModel getModel(Dictionary dictionary) {
        DefaultListModel defaultListModel = new DefaultListModel();
        ArrayList words = new ArrayList<>(dictionary.getKeySet());
        for (int i = 0; i < words.size(); i++) {
            defaultListModel.addElement(words.get(i));
        }
        return defaultListModel;
    }

    public static void addWordToList(String word, JList jList) {
        if (!((DefaultListModel) jList.getModel()).contains(word)) {
            ((DefaultListModel) jList.getModel()).addElement(word);
        }
    }

    public static void loadWordsToList(DefaultListModel words, JList<String> jList) {
        jList.setModel(words);
    }
}
