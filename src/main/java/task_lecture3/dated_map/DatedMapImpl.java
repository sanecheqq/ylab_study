package task_lecture3.dated_map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DatedMapImpl implements DatedMap {
    private Map<String, String> database = new HashMap<>();
    private Map<String, Date> keyDates = new HashMap<>();

    @Override
    public void put(String key, String value) {
        database.put(key, value);
        keyDates.put(key, new Date(System.currentTimeMillis()));
    }

    @Override
    public String get(String key) {
        return database.get(key);
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        return keyDates.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return database.containsKey(key);
    }

    @Override
    public Set<String> keySet() {
        return database.keySet();
    }

    @Override
    public void remove(String key) {
        database.remove(key);
        keyDates.remove(key);
    }
}
