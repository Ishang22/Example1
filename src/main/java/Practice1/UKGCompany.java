package Practice1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
        Integer arr1[] = {17, 12, 31, 42, 25, 12, 17};

        List<Integer> ar2 = Arrays.asList(arr1);

        Set<Integer> set = new HashSet<>();

        List<Integer> duplicateElements = ar2.stream().map(a -> {
                    if (set.contains(a)) {
                        return a;
                    }
                    set.add(a);
                    return null;
                }
        ).filter(Objects::nonNull).toList();

        System.out.println(duplicateElements);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<Integer> duplicateElements1 = ar2.stream()
                .filter(a -> !set.add(a)) // `set.add(a)` returns false if the element is already in the set
                .toList(); // Collect the duplicate elements into a list

        System.out.println("Duplicate Elements: " + duplicateElements1);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      change in one module need to deploy whole project
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////--- SERVICE DISCOVERYYYYYYYY ---////////////////////////////////////////////////////////////////////////////////////////////////////////

// for server discovery these are needed
//artifact - spring-cloud-starter-netflix-eureka-server, groupId - org.springframework.cloud
// at service discovery we have to put annotation - @EnableEurekaServer

// for clients/ or applications that are running if they want to registor they need
// artifact - spring-cloud-starter-netflix-eureka-client, groupId - org.springframework.cloud
//- @EnableEurekaClient
// host and port hardcode prevention

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// how service connect with each other ////////////////////////////////////////////
        /*
                  http web client ->
                1) restTemplate - yeah tu configration me bean dena huga ya @springbootapplication  class ke nich then autowriehuga
                2) feignClient -spring-cloud-starter-openfeign this is @enablefeignclients same as service discovery
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
* */
/// /////////////////////////////////////////////////////////////JWT TOKEN////////////////////////////////////////////////////
/*
✅ Header →      Defines the algorithm & type
✅ Payload →     Contains user data (claims)
✅ Signature →   Ensures integrity & security

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
    }

}
/////////////////////////////////////////////////////////////JAVA 8////////////////////////////////////////////////////////////////////////////////////////////
/*
*
*
* In interface by default methods are abstract
* but in java 8 we can make methods static and default by defination in inshort we can declare and define methods
* streams api lambda expersions/foreach values.foreach(i->System.out.println("ishan"))
* new date time api example - import java.time.*;
* Method reference
*
* */
/////////////////////////////////////////////////////////////JAVA 17////////////////////////////////////////////////////////////////////////////////////////////
/*
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
 /
/////////////////////////////////////////////////////////MICROSERVICES DESIGN PATTERN////////////////////////////////////////////////////////////////////////////////////
/*
*
* 1) Strangler Pattern
* 2) SAGA Pattern
*         -- choreography vs orchestration
* 3)CQRS PATTERN
*         --- command query request seggregation
*
*
*
*
*Abstract Class/interface -
 *   Can have both abstract and concrete methods..../....Only abstract methods (before Java 8), default & static methods allowed from Java 8
 *   Can have instance variables.................../.....Only public static final (constants)
 *   Yes, can have constructors..../....................❌ No constructors
 *   Can have private, protected, and public methods./....Methods are public by default
* */

//  new ArrayList() mutable vs List.of("a") -- immutable

//  The finalize() method is a method within the Object class that is called by the garbage collector before an object is destroyed.
//  Use Cases:
//  Primarily used for releasing system resources (e.g., native libraries) that cannot be managed by the garbage collector.

//difference between sleep and wait
//> sleep
//> does not release the lock
//        wait
//> release lock should be in the synchronized block


// Method overloading is the compile-time polymorphism
//
// private, final, and static methods cannot be overridden but overloaded
//
//this and super cannot be used together in the constructor.
//
//we can overload the main method.
//
//After starting a thread, it can never be started again. If you do so, an IllegalThreadStateException is thrown. In such a case, the thread will run once but for the second time, it will throw an exception.
//
//================================================SQL+==============================================================
//1) DELETE n1 FROM names n1 JOIN names n2 ON n1.name = n2.name WHERE n1.id < n2.id;
//
//2) DELETE n1, n2 FROM table1 n1 JOIN table2 n2 ON n1.column = n2.column  WHERE some_condition;
//
//3)  SELECT c.customer_id, c.customer_name, SUM(o.order_amount) AS total_order_amount
//    FROM orders o
//    JOIN customers c ON o.customer_id = c.customer_id
//    GROUP BY c.customer_id, c.customer_name
//    HAVING total_order_amount > 1000
//    ORDER BY total_order_amount DESC;
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INSERTION-
//  const user = { name: 'John Doe', age: 30, city: 'New York' };
//
//  const result = await collection.insertOne(user);
//
//
//UPDATE -
// const filter = { name: 'John Doe' }; // Find the document by name
//  const update = { $set: { age: 31 } }; // Set the new age
//  const result = await collection.updateOne(filter, update);
//
//
//UPDATE and find
//
//  const filter = { name: 'John Doe' }; // Find by name
//  const update = { $set: { city: 'Los Angeles' } }; // Set a new city
//
//  const result = await collection.findOneAndUpdate(
//    filter,
//    update,
//    { returnDocument: 'after' } // Return the document after the update
//  );
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////