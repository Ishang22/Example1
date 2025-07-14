package Practice1;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PerUserRateLimiter {
    private final long capacity;                //     10
    private final long refillIntervalSeconds;   //     2
    private final long refillTokens;            //.    5
    private final Map<String, TokenBucket> userBuckets = new ConcurrentHashMap<>();

    public PerUserRateLimiter(long capacity, long refillIntervalSeconds, long refillTokens) {
        this.capacity = capacity;
        this.refillIntervalSeconds = refillIntervalSeconds;
        this.refillTokens = refillTokens;
    }

    public boolean allowRequest(String userId) {
        if (!userBuckets.containsKey(userId)) {
            userBuckets.put(userId, new TokenBucket(capacity, refillIntervalSeconds, refillTokens));
        }
        TokenBucket bucket = userBuckets.get(userId);
        return bucket.allowRequest();
    }

    // Sample usage
    public static void main(String[] args) throws InterruptedException {
        // capacity: 10 tokens max, refill 5 tokens every 2 seconds
        PerUserRateLimiter limiter = new PerUserRateLimiter(10, 2, 5);

        String[] users = {"alice", "bob"};

        for (int i = 0; i < 15; i++) {
            for (String user : users) {
                boolean allowed = limiter.allowRequest(user);
                System.out.printf("User %-5s | Request %2d | Allowed: %s%n", user, i + 1, allowed);
            }
            Thread.sleep(500); // simulate 500ms between attempts
        }
    }
}

class TokenBucket {
    private final long capacity;
    private final long refillIntervalSeconds;
    private final long refillTokens;
    private double tokens;
    private Instant lastRefillTime;

    public TokenBucket(long capacity, long refillIntervalSeconds, long refillTokens) {
        this.capacity = capacity;                              // 10
        this.refillIntervalSeconds = refillIntervalSeconds;   // 2
        this.refillTokens = refillTokens;                    // 5
        this.tokens = capacity;
        this.lastRefillTime = Instant.now();
    }

    public synchronized boolean allowRequest() {
        refill();

        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }

        return false;
    }

    private void refill() {
        Instant now = Instant.now();
        Duration duration = Duration.between(lastRefillTime, now);
        long secondsPassed = duration.getSeconds();

        if (secondsPassed >= refillIntervalSeconds) {
            long intervals = secondsPassed / refillIntervalSeconds;
            double tokensToAdd = intervals * refillTokens;

            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTime = lastRefillTime.plusSeconds(secondsPassed);
        }
    }
}