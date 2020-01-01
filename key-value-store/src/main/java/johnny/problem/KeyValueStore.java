package johnny.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * A versioned Key-Value Store
 */
public class KeyValueStore {

    // Define a map to store versioned values for key, use TreeMap to maintain
    // a sorted map based on version number
    Map<String, TreeMap<Integer, String>> map;
    // version number
    int version;

    // Constructor
    public KeyValueStore() {
        map = new HashMap<>();
        version = 0;
    }

    /*
     * Save key value pair
     * @param key
     * @param value
     * @return version, increments each time
     */
    public int put(String key, String value) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }

        map.get(key).put(++version, value);

        System.out.println("PUT" + "(#" + version + ") " + key + " = " + value);
        return version;
    }

    /*
     * Get value by key
     * @param key
     * @return value
     */
    public String get(String key) {
        if (!map.containsKey(key)) {
            System.out.println("GET " + key + " = <NULL>");
            return null;
        }

        TreeMap<Integer, String> treeMap = map.get(key);
        String value = treeMap.lastEntry().getValue();
        System.out.println("GET " + key + " = " + value);
        return value;
    }

    /*
     * Get value by key and version
     * @param key
     * @param version
     * @return value
     */
    public String get(String key, int version) {
        if (!map.containsKey(key)) {
            System.out.println("GET " + key + "(#"+version+") = <NULL>");
            return null;
        }

        TreeMap<Integer, String> treeMap = map.get(key);
        String value;
        if (treeMap.containsKey(version)) {
            value = treeMap.get(version);
        } else {
            // find the largest smaller version
            Map.Entry<Integer, String>  entry = treeMap.lowerEntry(version);
            if (entry == null) {
                System.out.println("GET " + key + "(#"+version+") = <NULL>");
                return null;
            }
            value = entry.getValue();
        }

        System.out.println("GET " + key + "(#"+version+") = " + value);

        return value;
    }
}
