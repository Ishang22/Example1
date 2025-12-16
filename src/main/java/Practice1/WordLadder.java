package Practice1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description:<br>
 * Date: 18/07/25-9:35 pm
 *
 * @author ishangarg
 * @since
 */
public class WordLadder {
    // ["hot","dot","dog","lot","log","cog"]
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        HashMap<String, Boolean> Vmap = new HashMap<>();

        for (int i = 0; i < wordList.size(); i++) {
            Vmap.put(wordList.get(i), false);
        }

        Queue<String> q = new LinkedList<>();
        int length = 1;
        q.offer(beginWord);
        Vmap.put(beginWord, true);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String w = q.poll();
                if (w.equals(endWord)) {
                    return length;
                }
                wordMatch(w, Vmap, q);
            }

            length++;
        }

        return 0;
    }

    public void wordMatch(String w, HashMap<String, Boolean> Vmap, Queue<String> q) {
        for (int i = 0; i < w.length(); i++) {
            char[] word = w.toCharArray();

            for (int j = 0; j < 26; j++) {
                char c = (char) ('a' + j);
                word[i] = c;
                String s = new String(word);

                if (Vmap.containsKey(s) && Vmap.get(s) == false) {
                    q.offer(s);
                    Vmap.put(s, true);
                }

            }

        }
    }

}
/*

https://medium.com/javarevisited/how-i-optimized-a-spring-boot-application-to-handle-1m-requests-second-0cbb2f2823ed


Scenario Questions
1. “You inherit a legacy Spring Boot service. It’s running fine, but takes 20s to respond under load. How do you approach performance tuning?”
 → Looking for: Profiling-first mindset, understanding of thread pools, database bottlenecks, GC tuning

2. “Users report intermittent 500 errors in production, but logs look clean. How do you debug this?”
 → Looking for: Understanding of logging levels, tracing (e.g., Zipkin, OpenTelemetry), exception handling, concurrent request handling

3. “You’re asked to design a backend service for handling 10M+ user requests per day. You have limited infra budget. What’s your plan?”
 → Looking for: Scalability thinking, caching strategies, async processing, build vs buy decisions

4. “Your CI/CD pipeline suddenly slows down from 5 mins to 40 mins. How do you debug?”
 → Looking for: Ability to identify bottlenecks (dependency downloads, tests, container builds), caching strategies, parallelization and even use of AI tools like Copilot for YAML/shell optimization.

5. “Your microservice consumes messages from Kafka. Suddenly, consumer lag keeps increasing. What steps do you take?”
 → Looking for: Knowledge of consumer group rebalancing, partition assignment, backpressure handling, monitoring with Prometheus/Grafana, scaling strategies.

6. “A payment API you integrate with has a 2% failure rate under peak traffic. How do you ensure reliable customer experience?”
 → Looking for: Retry with exponential backoff, circuit breakers, idempotency handling, fallback strategies, SLAs with third parties.

 BroTip for Java developers in 2026: Java interviews are changing a lot compared to a few years ago. Companies look for engineers who understand how systems behave in real production environments and how AI fits into modern architectures.
When you talk about microservices, they expect you to know how services fail and how to protect them. Things like handling timeouts properly, using retries carefully, adding circuit breakers, and making your APIs idempotent are becoming basic expectations.
There is also a strong focus on distributed system thinking. Interviewers want to see if you understand how to deal with partial failures, maintain consistency across different services, reprocess messages safely, and handle scenarios where data arrives late or out of order.
Spring Boot questions have also become deeper. Instead of just asking what an annotation does, they want to know whether you understand auto-configuration, how proxies are created, what slows down application startup, and how bean lifecycles work.
Database and caching discussions are very practical now. You may be asked about tuning queries, choosing the right indexes, managing connection pool limits, avoiding cache stampedes, and deciding which eviction strategy makes sense in different situations.
If the company uses Kafka or any messaging system, be ready for topics like partitions, consumer lag, backpressure, rebalancing, and how to design pipelines that don’t lose data or process the same event twice.
API reliability is another big area. Rate limiting, throttling, load shedding, proper timeout setups, and handling sudden traffic spikes are all becoming common topics.
On top of all this, AI and LLM integration is entering Java interviews too. Companies want developers who can connect Spring Boot or microservices with LLMs using tools like Spring AI, LangChain4j, or direct OpenAI/Gemini API calls. Building simple RAG pipelines, running local models through Ollama, integrating vector databases, or using LLMs to enhance search, summaries, recommendations, or document processing are becoming valuable skills.
And finally, core Java depth is back in demand. People expect you to understand the difference between virtual and platform threads, how atomic operations work, issues like false sharing, how the JVM optimizes code, classloader-related memory leaks, and how GC pauses affect overall performance.
The overall expectation has shifted. It’s not just about building features anymore. It’s about whether you can design systems that are reliable, scalable, AI-ready, and resilient in real-world conditions.
If you're preparing for interviews now, focusing on these areas makes a huge difference and you will stand out.

 */














































/*
payal ta gandi ha majak
raat ni padh leya kr
scholl phone
10 vje tu pehla
jdo ik vaar fruit laki ayi ron lag payi
mera thodi kaam a
tiffin pack
sari assignement ma bnayi
 */