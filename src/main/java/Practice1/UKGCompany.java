package Practice1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
//3142->3214
/**
 * Description:<br>
 * Date: 25/01/25-10:51 am
 *
 * @author ishangarg
 * @since
 *
 *    https://www.rackspace.com/blog/aws-scaling-best-practices-for-black-friday
 * // The latest version of Spring Boot is 3.4.1
 *    The next permutation of 3142 is 3214.
 */

// https://www.youtube.com/watch?v=ZJJHm_bd9Zo kafka
public class UKGCompany {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 40, 50, 50);

        Integer secondLargest = numbers.stream()
                .distinct()                              // remove duplicates
                .sorted(Comparator.reverseOrder())       // sort descending
                .skip(1)                             // skip largest
                .findFirst()                           // get next
                .orElseThrow(() -> new NoSuchElementException("No second largest"));

        System.out.println("Second largest: " + secondLargest);
        /*
        Key Takeaways

Loops are fastest for small/medium collections (no overhead).

Streams (sequential) are more elegant, but slightly slower.

Parallel streams shine with large datasets + CPU-intensive tasks (but can hurt if dataset is small).

Performance difference is often negligible compared to readability & maintainability
👉 So: Loops = better control, Streams = better abstraction.

🔹 Key Insight

Streams were not added to replace loops for performance.

They were added to:
✅ Simplify code (less boilerplate)
✅ Improve readability (focus on what, not how)
✅ Enable functional programming
✅ Make parallel processing trivial
         */

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      change in one module need to deploy whole project[in monolith]
 //       [in micro]
//      easy debugging and maintaince
//      divide large app into small
//      we can use different language
//      different dataBases easily scalability
//.     each module manage independently
//      monolith tight couple if we change one line it can impact too manily functionality and need test of full application
//      overload IDE
//      difficult scaling -> [like sub operation should be easily, ci cd job time is length,one change line impact so many domains,time taking in deploy,we have to scale whole application instead of one]
//      latency can increase if we did not divide microservice correct.
//      transaction difficult in mircoservices
        /*
        If I had to boil it down to the 3 main rules for dividing microservices, they’d be:

        Business capability (Domain-Driven Design) → Split by bounded context, not by technical layers. Example: Order Service, Payment Service, Inventory Service (not “Controller Service” vs “DAO Service”).

        Data ownership → Each microservice owns its own database/schema. No direct DB sharing across services; communicate via APIs or events.
        What “data ownership” means

      Each microservice is the single authority for its data.

       That service’s database (or schema) is private — no other service can read/write it directly.

       Other services must ask via API or events if they need that data.
      Each microservice = its own data + its own rules.
       If another service needs that data → it must ask via API or events, not poke into the DB directly.

        Independent deployability & scalability → A service should be deployable, scalable, and fail independently of others.
        🔹 Synchronous communication (Sync)

         Definition: The caller waits for the callee to respond.

         🔹 Synchronous communication (Sync)

Definition: The caller waits for the callee to respond.

Common tech: HTTP/REST, gRPC.

Flow:

Service A → calls Service B

Service A waits until Service B replies

Only then continues

✅ Pros:

Simple, request–response (easy to understand).

Immediate result (good for user-facing APIs).

Easier debugging.

❌ Cons:

Tight coupling (if B is slow/down, A suffers).

Cascading failures possible.

Harder to scale under high load.

Example:
Checkout service calls Payment service → waits for success/failure → responds to user.

🔹 Asynchronous communication (Async)

Definition: The caller sends a message/event and doesn’t wait.

Common tech: Kafka, RabbitMQ, SQS, Pub/Sub.

Flow:

Service A → publishes event (e.g., “OrderPlaced”)

Service A continues immediately

Service B processes the event later

✅ Pros:

Loose coupling (services don’t block each other).

Better resilience (temporary failures don’t block flow).

Scales well for high throughput.

❌ Cons:

Eventual consistency (results not instant).

Harder debugging/tracing.

More complex error handling (retries, duplicates).

Example:
Order service publishes “OrderPlaced” → Inventory & Shipping services consume asynchronously.

         */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////--- SERVICE DISCOVERYYYYYYYY ---////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
In microservices, services often run on dynamic hosts/ports (e.g., in Kubernetes, ECS, cloud autoscaling). Since instances
come and go, you need a registry that keeps track of where services live.
Service Discovery = how a client finds the actual IP:Port of a service instance.
 */

        /*
        🔹 1. Client-Side Discovery

Flow:

Client asks the service registry (e.g., Eureka, Consul) for available instances of a service.
🔹 Example

Suppose you have these services:

Order Service → needs to call Inventory Service

Inventory Service → runs on multiple pods/instances, addresses change

If you use client-side discovery:

Order Service (the client in this case) calls Eureka/Consul registry:
→ “Hey, give me all live instances of Inventory Service.”

Eureka responds:
→ inventory-service: [10.0.1.12:8080, 10.0.1.13:8080, 10.0.1.14:8080]

Order Service (the client) runs a load balancing algorithm (e.g., round-robin, random, weighted).

Order Service directly calls 10.0.1.13:8080.

So here, Order Service is the client, because it’s the caller.

Client picks one instance (via load balancing logic).

Client calls that instance directly.

Example tools: Netflix Eureka, Ribbon, Consul (client libraries).

✅ Pros:

Simple, client knows exactly which instance it talks to.

Flexible load balancing strategies (client decides).

❌ Cons:

All clients need to implement discovery + load balancing logic.

More coupling between clients and the registry.

🔹 2. Server-Side Discovery

Flow:

Client makes a request to a load balancer / proxy (e.g., Envoy, Nginx, AWS ELB, Istio ingress).

The load balancer queries the service registry.
A service registry is basically a dynamic phonebook of all service instances in your system:
Load balancer forwards request to a healthy service instance.

Example tools: Kubernetes kube-proxy + DNS, Istio/Envoy, AWS ELB/ALB, Nginx, HAProxy.

✅ Pros:

Clients stay simple (they just call one endpoint).

Centralized load balancing → consistent policies.

Easy to change routing without touching clients.

❌ Cons:

Load balancer/proxy = extra hop.

Single point of failure if not HA.
         */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// how service connect with each other ////////////////////////////////////////////
        /*
          http web client ->
         1) restTemplate - yeah tu configuration me bean dena huga ya @springbootapplication  class ke nich then autowriehuga
         2) feignClient -spring-cloud-starter-openfeign this is @enablefeignclients same as service discovery

✅ With RestTemplate: You write the plumbing (URL, params, error handling).
✅ With FeignClient: You just declare the API contract — Spring + Feign handle the plumbing.
  */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////                 --- API GATEWAY ---     //////////////////////////////////////////////////////////
/*

1)  Common Url for all service apne aap identify kre ga konsi service call krni ha
2)  Authentication / Authorization
3)  ratelimiter

name- spring cloud gateway
spring:
    cloud:
        routes:
            id: USER-SERVICE
            uri: lb://USER_SERVICE
            predicates:
            - Path=/users/**,/staffs/*

 */


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////--- Config Server ---///////////////////////////////////////////////////////////////////////////////////
/*
* 1) externalize properties
* 2) can make common for all
* */


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////--- Fault tolerence ---///////////////////////////////////////////////////////////////////////////////////
/*
*
* <artifactId>resilience4j-spring-boot2</artifactId>
*
*
* @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallback")
* @Retry(name="ratingHotelService",fallbackMethod="ratingHotelFallback")
* @RateLimiter(name="",fallback)
*
*          Hystrix is a circuit-breaker library (Netflix) used to make calls to external/remote services resilient.

Primary goals:

Circuit breaking: stop calling a failing dependency for a while (open circuit) to let it recover.

Fallbacks: return a cached/default response when the dependency is down.

Bulkhead/thread isolation: prevent one failing dependency from consuming all threads and bringing down the caller.

Metrics / monitoring: health of downstream calls over time.

Hystrix protects you from cascading failures.

6) Hystrix semantics (quick)

Closed: calls are allowed normally.

Open: too many recent failures → short-circuit calls to fallback immediately.

Half-open: let a few test requests through to probe recovery; if successful → close circuit.

Isolation: Hystrix provides thread or semaphore isolation to keep the caller healthy.
Important caveats & modern advice

Hystrix is in maintenance mode (no active new features). For new projects, prefer:

Resilience4j (lightweight, modular, functional style) or

Spring Cloud Circuit Breaker (adapter to Resilience4j/Resilience4j + Spring Boot integration).

Resilience4j supports circuit breaker, rate limiter, retry, bulkhead — and integrates well with functional endpoints and WebClient.

* */
/// /////////////////////////////////////////////////////////////JWT TOKEN////////////////////////////////////////////////////
/*
✅ Header    →      Defines the algorithm & type
✅ Payload   →      Contains user data (claims)
✅ Signature →      Ensures integrity & security
Header → algorithm + token type (e.g., alg: RS256, typ: JWT).

Payload (claims) → user data. Two types:

Registered claims (standard ones): sub (subject), exp (expiry), iat (issued at).

Custom claims (your app-specific): roles, permissions, tenant, etc.

Signature → cryptographic proof token is issued by trusted authority (e.g., Keycloak).

artifact - spring-boot-starter-security + keycloak

✔ Each microservice should authenticate JWT locally using the public key.
✔ Microservices should not call the API Gateway for JWT validation.
✔ API Gateway can optionally validate JWT, but microservices should not depend on it.
✔ Decentralized JWT validation improves performance, scalability, and security.

🔐 Final Rule: "Trust the JWT, but verify it locally!" 🚀

🔹 Summary: Where Does JWT Validation Happen?
Step	Who Handles It?	Purpose
✅ JWT Creation	Auth Server (e.g., Keycloak, OAuth2)	Generates signed JWT token
✅ JWT Validation	API Gateway	Verifies token before forwarding requests
✅ Authorization	Microservices	Checks user roles & permissions
🚀 Final Answer:
🔹 API Gateway handles authentication (JWT validation).
🔹 Microservices handle authorization (role-based access control).
 */

//        Step	    Actor	            Action
//        1	       Keycloak	         Signs the JWT with its private key
//        2	       Gateway	         Verifies JWT using Keycloak's public key via JWKS
//        3	       API Server	     Trusts the token if verified by Gateway
    }

}
/////////////////////////////////////////////////////////////JAVA 8////////////////////////////////////////////////////////////////////////////////////////////
/*
* In interface by default methods are public abstract
* but in java 8 we can make methods static and default by defination in inshort we can declare and define methods
* streams api lambda expersions/foreach values.foreach(i->System.out.println("ishan"))
* new date time api example - import java.time.*;
* Method reference
* */
/////////////////////////////////////////////////////////////JAVA 17////////////////////////////////////////////////////////////////////////////////////////////
/* ********************************************************************************
* sealed classes and interfaces
*
* sealed interface Animal permits Dog, Cat { }
*
* pattern matching instance of
*
* Object obj = "Hello, Java 17!";
* if (obj instanceof String str) {  // str is automatically cast to String
*    System.out.println(str.toUpperCase());
* }
*
* arrow syntax with switch case
*
* Improved garabage collector(i.e faster and lower memory footprint)
*
* Enables java application to interact with native code and memory outside JVM
*
* public record Person(String name, int age) {} same as immutable class
****************************************************************************************** /
/////////////////////////////////////////////////////////MICROSERVICES DESIGN PATTERN////////////////////////////////////////////////////////////////////////////////////
/*
*
* 1) Strangler Pattern
* 2) SAGA Pattern
*         -- choreography vs orchestration
* 3) CQRS PATTERN
*         --- command query request seggregation
*
* =============================================================================================================================================================================
 *      Abstract Class                                       //    interface
 *   Can have both abstract and concrete methods............ //.... Only public and abstract methods (before Java 8), default & static methods allowed from Java 8
 *   Can have instance variables...........................  //.....Only public static final (constants)
 *   Yes, can have constructors...........................   //.... No constructors
 *   Can have private, protected, and public methods.....    //.... Methods are public by default
 *
❌ No, you cannot directly create an object of an abstract class in Java.
==============================================================================================================================================================================
*/

//  new ArrayList() mutable vs List.of("a") -- immutable

//  The finalize() method is a method within the Object class that is called by the garbage collector before an object is destroyed.
//  Use Cases:
//  Primarily used for releasing system resources (e.g., native libraries) that cannot be managed by the garbage collector.

//difference between sleep and wait
// sleep
//> does not release the lock
// wait
//> release lock should be in the synchronized block
//
//
// Method overloading is the compile-time polymorphism
//
// private, final and static methods cannot be overridden but overloaded
//
// this and super cannot be used together in the constructor.
//
// we can overload the main method.
//
// After starting a thread, it can never be started again. If you do so, an IllegalThreadStateException is thrown. In such a case, the thread will run once but for the second time, it will throw an exception.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// No, you cannot instantiate an abstract class directly

//.        | Feature     | `String`               | `StringBuffer`                   | `StringBuilder`                        |
//        | -----------  | ---------------------- | -------------------------------- | --------------------------------------  |
//        | Mutability  | Immutable              | Mutable                          | Mutable                                 |
//        | Thread-Safe | Yes                    | Yes (synchronized)               | No                                      |
//        | Performance | Slow (new obj)         | Slower than `StringBuilder`      | Fastest                                 |
//        | Use Case    | Constants, few changes | Multi-threaded text manipulation | High-performance single-threaded tasks  |


//✅ When finalize() is invoked:
//The GC detects that there are no more references to the object.
//Before reclaiming the memory, the JVM calls finalize() (if it’s overridden).
//This gives the object a last chance to release resources like closing files or network connections.



//1- Semrush: careers.semrush.com
//2- Chili Piper: www.chilipiper.com/careers
//3- Semaphore: semaphoreci.com/hiring
//4- Toggl: toggl.com/jobs
//5- Siege Media: www.siegemedia.com/careers
//6- GitHub: github.careers
//7- GitLab: about.gitlab.com/jobs
//8- Doist: doist.com/careers
//9- Superside: careers.superside.com
//10- Kinsta: kinsta.com/careers
//11- 10up: 10up.com/careers
//12- Kit (formerly ConvertKit): kit.com/careers
//13- Awesome Motive, Inc.: awesomemotive.com/careers
//14- RevenueCat: https://lnkd.in/gEZjSQRA
//        15- Automattic: https://lnkd.in/gZA4yPgi
//        16- B12: www.b12.io/careers
//17- Float.com: www.float.com/careers
//18- Chameleon: www.chameleon.io/careers
//19- Zapier: zapier.com/jobs
//20- Aha!: www.aha.io/company/careers
//21- Toptal: www.toptal.com/careers
//22- TestGorilla: https://lnkd.in/gwiSVjzK
//        23- Help Scout: https://lnkd.in/gt-m4fE2
//        24- Hubstaff: hubstaff.com/jobs
//25- Uscreen: www.uscreen.tv/careers

/*
These questions are mostly around services that we commonly integrate with Java — like Lambda, S3, API Gateway, EC2, DynamoDB, SQS, SNS, and CloudWatch.

➡️ AWS Lambda

1. What is AWS Lambda and how do you use it in Java?


2. How do you deploy a Java-based Lambda function?


3. What are cold starts in Lambda?


4. How do you pass input and return output from Lambda?


5. How do you handle exceptions and logging in Lambda?

➡️ Amazon S3

6. How do you upload/download files to S3 using Java SDK?


7. What is a presigned URL and how is it generated?


8. How do you secure S3 buckets?


9. What is the difference between S3 Standard and S3 Glacier?


10. How do you trigger a Lambda from an S3 event?

➡️ API Gateway

11. What is API Gateway and how does it work with Lambda?


12. How do you secure APIs in API Gateway (e.g., using API keys, authorizers)?


13. What is the difference between HTTP API and REST API in API Gateway?


14. How do you handle CORS in API Gateway?


15. How to test and deploy an API Gateway endpoint?


➡️Amazon EC2

16. What is EC2 and how is it used in backend deployments?


17. What are security groups in EC2?


18. How do you connect to EC2 from your local machine?


19. Difference between on-demand, reserved, and spot instances


20. How do you host a Spring Boot application on EC2?

➡️Amazon DynamoDB

21. What is DynamoDB and how do you connect to it from a Java application?


22. Difference between partition key and sort key


23. How do you perform CRUD operations using the AWS Java SDK?


24. How does DynamoDB handle scaling?


25. What is a Global Secondary Index (GSI)?

➡️Amazon SQS

26. What is Amazon SQS and how is it used in a Java backend system?


27. Difference between Standard Queue and FIFO Queue


28. How do you send and receive messages using Java SDK?


29. What is message visibility timeout?


30. How do you handle retries and dead-letter queues?

➡️Amazon SNS

31. What is Amazon SNS and how does it differ from SQS?


32. How do you publish a message to a topic using Java?


33. Can SNS trigger Lambda or send SMS/Email notifications?


34. What are the different protocols supported by SNS?


35. How do you subscribe an endpoint to a topic?
 */

/*
Java:
1) Do you know about String Constant pool?
2) Why String is immutable in Java?
3) What is Hashing?
4) Tell me about the unique features of Hashtable. Internal functionality. Is it fast or slow? Reason?
5) What are Daemon threads?
6) How does a thread work? Code and Data
7) Tell me about your current project?
8) What is the difference between Future and Completable Future? Can we use Executor Service with Completable Future?
9) Which HTTP methods are idempotent and which are safe and why? How REST is different from SOAP?
10) Regarding mvn commands. Which mvn command creates our Jar in target and which command sends it to our loval repo? Difference between remote repo and our local maven repo.
 forEach(System.out::println)

 Mutual TLS (mTLS) is a security protocol that enhances the standard TLS (Transport Layer Security) by requiring both the client and server to authenticate each other using digital certificates before establishing a secure connection.
 */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
LAMBDA(stateless)->
     create function then create trigger means source jaha se call kre ge example - api gateway,alexa sns

 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
Yes ✅ you got it — let me polish your understanding a bit so it’s exact and clear.

🔹 How it really works

Spring Boot looks at META-INF config files

(Spring Boot < 3.x) → META-INF/spring.factories

(Spring Boot ≥ 3.x) → META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

These files list all AutoConfiguration classes (like RabbitAutoConfiguration, DataSourceAutoConfiguration, WebMvcAutoConfiguration, etc.).

It loads those AutoConfiguration classes

Each one is just a @Configuration class that defines some beans.

Example: RabbitAutoConfiguration might define ConnectionFactory, RabbitTemplate, etc.

Conditions decide if they should run

They don’t all run blindly — each auto-config class has annotations like:

@ConditionalOnClass(RabbitTemplate.class)
@ConditionalOnMissingBean(ConnectionFactory.class)


Meaning:

If the class RabbitTemplate is on the classpath, AND

You didn’t already define your own ConnectionFactory bean,

→ then Spring Boot will auto-configure those beans for you.

🔹 So in short:

👉 Spring Boot loads the list of possible auto-configurations from META-INF.
👉 Then for each one, it checks the conditions (classpath, properties, existing beans, etc.).
👉 If conditions are true → ✅ beans get created automatically.
 */


/*

🔹 1. Client-Side Service Discovery with API Gateway

Here, the gateway or the client itself talks to the service registry and chooses an instance.

Client
   │
   ▼
API Gateway
   │
   ▼
(Service Registry like Eureka/Consul)
   │
   ▼
Chooses instance (client-side load balancing)
   │
   ▼
Service Instance (e.g., Orders Service Pod)


API Gateway (or the client) is responsible for querying the registry and doing the balancing.

No central LB in between.

Example: Netflix OSS stack (Eureka / Zuul).

🔹 2. Service-Side Service Discovery with API Gateway

Here, the gateway just calls a stable load-balanced endpoint (DNS or LB). The LB or proxy does the discovery.

Client
   │
   ▼
API Gateway
   │
   ▼
Load Balancer / Proxy
   │
   ▼
(Service Registry / Kubernetes DNS / Envoy mesh)
   │
   ▼
Service Instance (e.g., Orders Service Pod)


API Gateway is simpler: it only knows a single URL (LB DNS or K8s Service name).

The LB/proxy handles discovery and load balancing.

Example:

AWS API Gateway → ALB → Service.

Kong/NGINX Gateway → K8s Service DNS → Pod.

Istio Ingress Gateway → Envoy mesh → Pod.

✅ Key Difference:

Client-side discovery → API Gateway (or client) must be aware of service registry.

Service-side discovery → API Gateway just calls LB/proxy; discovery is transparent.



   User (Browser/Mobile)
        ↓
   Route 53 (DNS) [url to ip]
        ↓
   CloudFront (CDN, optional)
        ↓
   API Gateway (optional, if APIs need mgmt features)
        ↓
   Load Balancer (ALB/NLB)
        ↓
   ECS/EKS/EC2/Lambda (your app)


------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------
Of course 👍 — here’s a simple and clear summary of the three HTTP methods:

🧩 1. HEAD

Same as GET, but does not return the body — only headers.

Used to check if a resource exists .

Example:

HEAD /file.pdf
→ Server replies with headers only (no file content)

⚙️ 2. OPTIONS

Used to ask the server what methods are allowed on a URL.

Commonly used by browsers for CORS preflight checks.

Example:

OPTIONS /api/user
→ Allow: GET, POST, PUT, DELETE

🔍 3. TRACE

Used to see what your request looks like when it reaches the server.

The server echoes back your request for debugging.

Usually disabled for security reasons.

Example:

TRACE /test
→ Returns the same request you sent

In Java, Comparable defines a class's natural ordering through its compareTo() method,
modifying the class itself to provide a single, default sorting sequence, while Comparator provides custom sorting
 logic defined in a separate class via its compare()

 2p vs 3p
 https://www.youtube.com/watch?v=kFj-0E-en4o



 Ever wondered how @Async works internally in Spring Boot?

Here’s what happens behind the scenes:

1. Proxy Creation – Spring creates an AOP proxy for beans with @Async methods.

2. Method Intercepted – Proxy intercepts the call before actual method execution.

3. Task Submission – The method is submitted to a TaskExecutor.

4. Thread Execution – Task runs in a separate thread, freeing up the caller.

5. Return Handling – For methods returning Future/CompletableFuture, results are managed asynchronously.

Important Components:

a) TaskExecutor – Executes async tasks (can customize via @EnableAsync).

b)AsyncAnnotationBeanPostProcessor – Creates proxies for @Async methods.

c) AOP Proxy – Routes calls through TaskExecutor.

In short:
@Async = Proxy + TaskExecutor + Thread Management



stock exchange- https://chatgpt.com/c/68207472-bf9c-8008-93c0-c6a9cebdc4e2
 */