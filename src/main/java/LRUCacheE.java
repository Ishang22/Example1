import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

class PriorityExpiryCache<K, V> {
    private final int capacity;
    private final Map<K, CacheEntry> cache;
    private final PriorityQueue<CacheEntry> expiryQueue;
    private final PriorityQueue<CacheEntry> priorityQueue;

    public PriorityExpiryCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
        this.expiryQueue = new PriorityQueue<>(Comparator.comparingLong(e -> e.expiryTime));
        this.priorityQueue = new PriorityQueue<>((e1, e2) -> Integer.compare(e2.priority, e1.priority));
    }

    // Cache Entry class
    private class CacheEntry {
        K key;
        V value;
        int priority;
        long expiryTime;

        CacheEntry(K key, V value, int priority, long expiryTime) {
            this.key = key;
            this.value = value;
            this.priority = priority;
            this.expiryTime = expiryTime;
        }
    }

    public synchronized void put(K key, V value, int priority, long ttlMillis) {
        long expiryTime = System.currentTimeMillis() + ttlMillis;

        // Remove expired items
        removeExpiredEntries();

        // If the cache is full, evict an item
        if (cache.size() >= capacity) {
            evictItem();
        }

        CacheEntry entry = new CacheEntry(key, value, priority, expiryTime);
        cache.put(key, entry);
        expiryQueue.offer(entry);
        priorityQueue.offer(entry);
    }

    public synchronized V get(K key) {
        removeExpiredEntries();

        CacheEntry entry = cache.get(key);
        if (entry == null || entry.expiryTime < System.currentTimeMillis()) {
            cache.remove(key); // Clean up expired item
            return null;
        }
        return entry.value;
    }

    private void removeExpiredEntries() {
        long now = System.currentTimeMillis();

        while (!expiryQueue.isEmpty() && expiryQueue.peek().expiryTime < now) {
            CacheEntry expired = expiryQueue.poll();
            cache.remove(expired.key);
            priorityQueue.remove(expired);
        }
    }

    private void evictItem() {
        while (!priorityQueue.isEmpty()) {
            CacheEntry lowestPriority = priorityQueue.poll();
            if (cache.containsKey(lowestPriority.key)) {
                cache.remove(lowestPriority.key);
                expiryQueue.remove(lowestPriority);
                break;
            }
        }
    }

    public synchronized int size() {
        removeExpiredEntries();
        return cache.size();
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityExpiryCache<String, String> cache = new PriorityExpiryCache<>(3);

        cache.put("A", "Value A", 1, 5000);
        cache.put("B", "Value B", 2, 3000);
        cache.put("C", "Value C", 3, 10000);

        System.out.println("Initial size: " + cache.size()); // Output: 3

        Thread.sleep(4000);
        System.out.println("After 4 seconds, size: " + cache.size()); // Output: 2

        cache.put("D", "Value D", 5, 5000); // This should evict the lowest-priority non-expired item

        System.out.println("After adding D, size: " + cache.size()); // Output: 3
        System.out.println("Value of A: " + cache.get("A")); // Might be null if A was evicted
    }
}