package DTO;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary {
    private TreeMap<String, String> treeMap;

    public Dictionary() {
        treeMap = new TreeMap<>();
    }

    public void add(String key, String value) {
        treeMap.put(key, value);
    }

    public void delete(String key) {
        treeMap.remove(key);
    }

    public String getValue(String key) {
        return treeMap.get(key);
    }

    public void replace(String key, String value) {
        treeMap.replace(key, value);
    }

    public int getNumWords() {
        return treeMap.size();
    }

    public boolean isEmpty() {
        return treeMap.isEmpty();
    }

    public Set getKeySet() {
        return treeMap.keySet();
    }

    public void showRecordInMap() {
        for (Map.Entry<String, String> entry : this.treeMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
        }
    }
}
