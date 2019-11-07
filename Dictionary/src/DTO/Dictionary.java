package DTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary {
    private HashMap<String, String> hashMap;

    public Dictionary() {
        hashMap = new HashMap<>();
    }

    public void add(String key, String value) {
        hashMap.put(key, value);
    }

    public void delete(String key) {
        hashMap.remove(key);
    }

    public String getValue(String key) {
        return hashMap.get(key);
    }

    public int getNumWords() {
        return hashMap.size();
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    public Set getKeySet() {
        return hashMap.keySet();
    }

    public void showRecordInMap() {
        for (Map.Entry<String, String> entry : this.hashMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
        }
    }
}
