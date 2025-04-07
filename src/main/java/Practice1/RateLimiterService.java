package Practice1;

/**
 * Description:<br>
 * Date: 12/01/24-8:15 pm
 *
 * @author ishangarg
 * @since
 */
public class RateLimiterService {
    public static void main(String[] args) {
        int limit = 5;            // 5 requests per minute
        RateLimit rateLimit = new RateLimit(limit);
        new RateLimitHelper("UserA", rateLimit).start();
        new RateLimitHelper("userB", rateLimit).start();
    }
}