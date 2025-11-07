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
    }
}
        /*
        Key Takeaways

🚀 Loops vs Streams in Java

🔹 Loops --------------------------->>>>>>>>

Best for small to medium collections — minimal overhead, faster in simple cases.

Offer fine-grained control and flexibility.

🔹 Streams (Sequential) --------------------------->>>>>>>>

Slightly slower than loops due to abstraction overhead (method calls, lambdas) and boxing/unboxing
between primitives and wrapper types. This added flexibility comes at a small performance cost.

Prioritize readability and expressiveness — focus on what to do, not how to do it.

🔹 Parallel Streams  --------------------------->>>>>>>>

Ideal for large datasets and CPU-intensive tasks.

Can degrade performance on small datasets due to thread management overhead.

💡 Key Insight

Streams weren’t designed to replace loops for speed.
They were designed to:
✅ Simplify code (less boilerplate)
✅ Improve readability and maintainability
✅ Encourage a functional programming style
✅ Make parallel processing effortless
*/

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      change in one module need to deploy whole project[in monolith]
//      overload IDE
//      difficult scaling
//      latency can increase if we did not divide microservice correct.
//      monolith tight couple if we change one line it can impact too many functionality and need test of full application
//      [=============in micro==========================]
//      easy debugging and maintenance
//      divide large app into small
//      we can use different language
//      different dataBases easily scalability
//.     each module manage independently
//      transaction difficult in mircoservices
        /*
        If I had to boil it down to the 3 main rules for dividing microservices, they’d be:

=======1 Business capability (Domain-Driven Design) → Split by bounded context, not by technical layers. Example: Order Service, Payment Service, Inventory Service (not “Controller Service” vs “DAO Service”).

=======2 Data ownership → Each microservice owns its own database/schema. No direct DB sharing across services; communicate via APIs or events.
        What “data ownership” means

        Each microservice is the single authority for its data.

       That service’s database (or schema) is private — no other service can read/write it directly.

       Other services must ask via API or events if they need that data.
       Each microservice = its own data + its own rules.
       If another service needs that data → it must ask via API or events, not poke into the DB directly.

=======3 Independent deployability & scalability → A service should be deployable, scalable, and fail independently of others.

🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹 Synchronous communication (Sync)🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
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

Cascading failures possible means If Service B is down, Service A or many other may keep trying and crash or hang — leading to a cascading failure.

Harder to scale under high load.

Example:
Checkout service calls Payment service → waits for success/failure → responds to user.

🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹 Asynchronous communication (Async)🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹

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

🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹 SERVICE DISCOVERY  🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹

In microservices, services often run on dynamic hosts/ports (e.g., in Kubernetes, ECS, cloud autoscaling). Since instances
come and go, you need a registry that keeps track of where services live.
Service Discovery = how a client finds the actual IP:Port of a service instance.

🔹🔹🔹🔹🔹🔹🔹 1. Client-Side Discovery🔹🔹🔹🔹🔹🔹🔹
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

🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹 2. Server-Side Discovery 🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹

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


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// how service connect with each other ////////////////////////////////////////////

          http web client ->
         1) restTemplate - yeah tu configuration me bean dena huga ya @springbootapplication  class ke nich then autowriehuga
         2) feignClient -spring-cloud-starter-openfeign this is @enablefeignclients same as service discovery

✅ With RestTemplate: You write the plumbing (URL, params, error handling).
✅ With FeignClient: You just declare the API contract — Spring + Feign handle the plumbing.

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////                 --- API GATEWAY ---     //////////////////////////////////////////////////////////


1)  Common Url for all service apne aap identify kre ga konsi service call krni ha
2)  Authentication / Authorization
3)  Rate-limiter

name- spring cloud gateway
spring:
    cloud:
        routes:
            id: USER-SERVICE
            uri: lb://USER_SERVICE
            predicates:
            - Path=/users/**,/staffs/*




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
Hystrix is a circuit-breaker library (Netflix) used to make calls to external/remote services resilient.

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
Signature = HMACSHA256(
    base64UrlEncode(header) + "." + base64UrlEncode(payload),
    secret_key
)
Header → algorithm + token type (e.g., alg: RS256, typ: JWT).

The JWT Payload contains claims, i.e., statements about the user and token metadata.
********************  ********************  ********************  ********************  ********************
It usually has three types of claims:

********************  Registered claims (standard fields) – predefined, optional but commonly used:

iss → Issuer (who created the token)

sub → Subject (whom the token refers to)

aud → Audience (who can use the token)

exp → Expiration time

iat → Issued at

nbf → Not before (valid from time)

********************  Public claims – custom claims agreed upon by both parties (e.g., role, email, userId).

********************  Private claims – custom fields used internally between systems.

✅ Example payload:

{
  "sub": "1234567890",
  "name": "Ishan Garg",
  "role": "admin",
  "iat": 1718282351,
  "exp": 1718285951
}
********************  ********************  ********************  ********************  ********************  ********************
artifact - spring-boot-starter-security + keycloak

In this model, the API Gateway is the single point responsible for both authentication and authorization, while the microservices remain lightweight and trust the Gateway for access control.

⚙️ Flow Overview

Keycloak – The Identity Provider

Keycloak authenticates the user (via login, SSO, etc.) and issues a JWT, digitally signed using its private key.

The token contains user identity, roles, and permissions (claims).

API Gateway – Security Enforcement Layer

When a request arrives with a JWT (Authorization: Bearer <token>), the Gateway:

✅ Authenticates the request by verifying the JWT’s signature using Keycloak’s public key (fetched from JWKS endpoint).

✅ Authorizes the request by checking user roles, scopes, or permissions defined in the token against access policies (e.g., “only admins can call /admin/* APIs”).

Only requests that pass both authentication and authorization checks are forwarded to the target microservice.

Microservices – Trusted Resource Servers

Microservices trust the API Gateway and do not re-validate the JWT.

They focus purely on business logic and data processing.

The assumption is that any incoming request from the Gateway has already been authenticated and authorized.

/////////////////////////////////////////////////////////////JAVA 8////////////////////////////////////////////////////////////////////////////////////////////
/*
*
* In interface by default methods are public abstract
* but in java 8 we can make methods static and default by defination in inshort we can declare and define methods
* streams api lambda expersions/foreach values.foreach(i->System.out.println("ishan"))
* new date time api example - import java.time.*;
* Method reference
*
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


/*
 Mutual TLS (mTLS) is a security protocol that enhances the standard TLS (Transport Layer Security) by requiring both the client and server to authenticate each other using digital certificates before establishing a secure connection.


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

LAMBDA(stateless)->
     create function then create trigger means source jaha se call kre ge example - api gateway,alexa,sns

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Yes ✅ you got it — let me polish your understanding a bit so it’s exact and clear.

🔹 How it really works

Spring Boot looks at META-INF config files


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

Kafka Delivery Semantics | At-Least-Once, At-Most-Once & Exactly-Once


https://www.youtube.com/watch?v=V0c0qAP7sWk
Drop vs Truncate vs Delete get() vs load() in JPA
 */