import java.util.*;

public class AllOne {

    // Map that stores key to the node
    Map<String, Integer> map;

    // Map that groups keys having same value using double
    // linked list as value
    Map<Integer, Set<String>> valueMap;

    // maxMin linkedlist to keep track of
    // max and min values
    LinkedList<Integer> maxMin;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        map = new HashMap<>();
        valueMap = new HashMap<>();
        maxMin = new LinkedList<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        // If new key, just insert into the map
        // and place it in the appropriate position
        // in value map

        if (!map.containsKey(key)) {
            map.put(key, 1);
            putInValueMap(1, key);
        } else {
            // If already existing
            int val = map.get(key);
            // Remove from value map for old value
            removeFromValueMap(val, key);
            // Increment value
            map.put(key, val + 1);
            // Place it the new value in value map
            putInValueMap(val + 1, key);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        // If this key not found, just return
        if (!map.containsKey(key)) {
            return;
        }

        int val = map.get(key);
        // If value is 1, then remove from map
        if (val == 1) {
            map.remove(key);
            // Remove from value map too
            removeFromValueMap(1, key);
        } else {
            // Remove from old value
            removeFromValueMap(val, key);
            // Decrement value
            map.put(key, val - 1);

            // Insert at new value
            putInValueMap(val - 1, key);
        }

    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        while (!maxMin.isEmpty() && null == valueMap.get(maxMin.getFirst())) {
            maxMin.removeFirst();
        }

        if (maxMin.isEmpty()) {
            return "";
        }
        // minMax.getFirst() always has max value
        return valueMap.get(maxMin.getFirst()).iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        while (!maxMin.isEmpty() && null == valueMap.get(maxMin.getLast())) {
            maxMin.removeLast();
        }

        if (maxMin.isEmpty()) {
            return "";
        }
        // minMax.getFirst() always has min value
        return valueMap.get(maxMin.getLast()).iterator().next();
    }

    private void putInValueMap(int count, String node) {
        // If not seen before, create a new dll
        if (!valueMap.containsKey(count)) {
            valueMap.put(count, new HashSet<String>());
        }
        // Add to dll
        valueMap.get(count).add(node);

        // Update min max
        if (maxMin.isEmpty() || maxMin.getFirst() < count) {
            maxMin.addFirst(count);
        }

        if (!maxMin.isEmpty() && maxMin.getLast() > count) {
            maxMin.addLast(count);
        }
    }

    private void removeFromValueMap(int count, String node) {
        // If not present in value map, just return
        if (!valueMap.containsKey(count)) {
            return;
        }

        // Remove from dll in value map
        valueMap.get(count).remove(node);

        // If no elements present with this value
        if (valueMap.get(count).size() == 0) {
            // Remove from value map all together
            valueMap.remove(count);

            // Update min max
            if (!maxMin.isEmpty() && maxMin.getFirst() == count) {
                maxMin.removeFirst();
            }

            if (!maxMin.isEmpty() && maxMin.getLast() == count) {
                maxMin.removeLast();
            }
        }
    }


    public static void main(String args[]) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.inc("hello");

        allOne.inc("ishan");
        allOne.inc("ishan");
        allOne.inc("ishan");

        allOne.inc("ishan1");

        //   4  -   3 -  1
        allOne.getMaxKey(); // return "hello"
        allOne.getMinKey(); // return "hello"


        allOne.dec("ishan");

        allOne.dec("hello");
        allOne.dec("hello");
        allOne.dec("hello");

        //   2  -   1
        allOne.getMaxKey(); // return "hello"
        allOne.getMinKey(); // return "leet"
    }
}