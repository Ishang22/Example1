package Practice1;

public class RealExperiences {
}
/*
Caching in Java Microservices with AWS — My Experience

Recently, I was working on optimizing a Java-based microservices architecture hosted on AWS.
Like many of us, I faced the usual challenge — too many database calls, increasing latency, and occasional performance dips under load.

That’s when caching came to the rescue

Here’s how I approached it -

 Step 1: Identify What to Cache
Not everything needs caching — that’s the key.
I focused on data that:
Is read frequently but changes rarely (e.g., configuration, master data).
Causes repetitive DB or API calls across services.

 Step 2: Local Cache for Quick Access
For small, frequently accessed data within a single service, I used Caffeine — a high-performance in-memory cache for Java.
It’s simple, lightweight, and perfect for low-latency reads within one microservice instance.

 Step 3: Distributed Cache with AWS ElastiCache (Redis)
Since microservices are distributed across multiple EC2 or ECS instances, a shared cache layer was a must.
That’s where Amazon ElastiCache for Redis fit perfectly.

Each service connects to Redis using a client like Jedis or Lettuce, making cached data available across instances.
This reduced database hits drastically and kept data consistent between services.

 Step 4: Integrate with Spring Boot
With @Cacheable annotations and Redis CacheManager, caching becomes clean and maintainable.

 Step 5: Monitor & Tune
Using CloudWatch metrics and Redis insights helped me monitor hit ratios, latency, and memory usage — and tune expiry policies for the best performance.

 Result:
 -60–70% faster API responses
 -Reduced DB load and costs
 -Better scalability and user experience

 */

/*

🚀 Microservice Saga Pattern – Real-World Scenarios

In distributed microservices, handling transactions across services is tricky. A single failure can leave data inconsistent. That’s where Saga Patterns help.

Here are 2 common real-world scenarios 👇

🔹 Scenario 1 – Order Service (Choreography Saga)
Problem:
When a user places an order → Payment, Inventory, and Notification services must update.
If payment fails, order should be cancelled, and inventory rolled back.

Solution (Choreography):

Order Service publishes OrderCreatedEvent.

Payment Service listens, processes, and emits success/failure events.

Inventory & Notification react accordingly.

Failure triggers compensating actions (cancel order, restock items).


✅ Decentralized & scalable
⚠️ Hard to trace/debug in large systems


🔹 Scenario 2 – Travel Booking (Orchestrated Saga)
Problem:
Booking a trip involves Flight + Hotel + Payment services.
If hotel booking fails, the flight must also be cancelled.

Solution (Orchestration):

Orchestrator Service coordinates calls: flight → hotel → payment.

On failure, orchestrator triggers compensating actions (cancel flight, refund payment).


✅ Centralized control & visibility
⚠️ Orchestrator becomes a single point of failure

🌐 Saga Pattern in Microservices Complete Guide - https://lnkd.in/gSttUvWZ
✨ Takeaway:

Use Choreography when services are loosely coupled & event-driven.

Use Orchestration when workflows are complex and need central monitoring.

 */




/*

Our Kafka consumer once lagged 50,000 messages behind.
I thought the broker was slow… but the culprit was me 😅

We were using a single consumer thread for multiple partitions.
 CPU idle, lag climbing.
Split the partitions across multiple threads, tuned max.poll.records.

Boom → lag disappeared in minutes.
Lesson → scalability is mostly about parallelism, not more servers.

💬 What’s the biggest Kafka tuning win you’ve had?

 */