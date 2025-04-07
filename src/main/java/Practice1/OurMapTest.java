package Practice1;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:<br>
 * Date: 05/09/24-5:48 pm
 *
 * @author ishangarg
 * @since
 */
public class OurMapTest {
    public static void main(String[] args) {

        OurMap<Integer, String> nameToLen = new OurMap<>();

        nameToLen.put(1, "a");
        nameToLen.put(6, "ishan");
        nameToLen.put(5, "absxd");
        nameToLen.put(3, "abc");
        nameToLen.put(5, "garg");

        nameToLen.remove(3);

        System.out.println(nameToLen.get(5));
        System.out.println(nameToLen.get(1));
        System.out.println(nameToLen.get(6));
        System.out.println(nameToLen.get(3));
        System.out.println(nameToLen.get(7));

    }

}

class OurMap<K, V> {
    private List<MapNode<K, V>> bucket;
    private int capacity;                           // length of the bucket
    private int size;                               // number of elements in the map
    private final int INITIAL_CAPACITY = 5;         // initial length of the bucket array

    public OurMap() {
        bucket = new ArrayList<>();
        capacity = INITIAL_CAPACITY;
        for (int i = 0; i < capacity; i++)
            bucket.add(null);
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % capacity;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;

        MapNode<K, V> newEntry = new MapNode<>(key, value);
        head = bucket.get(bucketIndex);
        newEntry.next = head;
        bucket.set(bucketIndex, newEntry);

    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        MapNode<K, V> prev = null;
        while (head != null) {

            if (head.key.equals(key)) {
                if (prev == null) {
                    bucket.set(bucketIndex, head.next);
                }
                else {
                    prev.next = head.next;
                }

                head.next = null;
                size--;
                break;
            }

            prev = head;
            head = head.next;
        }
    }

    private class MapNode<K, V> {
        K key;
        V value;
        MapNode<K, V> next;

        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}