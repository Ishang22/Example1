package Practice1;

import java.util.Arrays;
import java.util.HashSet;
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
        Integer[] numbers = {1, 2, 3, 2, 4, 5, 1, 6};

        Set<Integer> seen = new HashSet<>();

        Set<Integer> duplicates = Arrays.stream(numbers)
                .filter(n -> !seen.add(n)) // add returns false if already exists
                .collect(Collectors.toSet());

        System.out.println("Duplicate elements: " + duplicates);

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
// artifact - spring-cloud-starter-netflix-eureka-server, groupId - org.springframework.cloud
// at service discovery we have to put annotation - @EnableEurekaServer

// for clients/ or applications that are running if they want to registor they need-- @EnableEurekaClient
// artifact - spring-cloud-starter-netflix-eureka-client, groupId - org.springframework.cloud
//- @EnableEurekaClient
// host and port hardcode prevention

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// how service connect with each other ////////////////////////////////////////////
        /*
          http web client ->
         1) restTemplate - yeah tu configuration me bean dena huga ya @springbootapplication  class ke nich then autowriehuga
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

//        Step	    Actor	            Action
//        1	       Keycloak	         Signs the JWT with its private key
//        2	       Gateway	         Verifies JWT using Keycloak's public key via JWKS
//        3	       API Server	     Trusts the token if verified by Gateway
    }

}
/////////////////////////////////////////////////////////////JAVA 8////////////////////////////////////////////////////////////////////////////////////////////
/*
* In interface by default methods are abstract
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
*==============================================================================================================================================================================
 *      Abstract Class                                       //    interface -
 *   Can have both abstract and concrete methods............//....Only public and abstract methods (before Java 8), default & static methods allowed from Java 8
 *   Can have instance variables...........................//.....Only public static final (constants)
 *   Yes, can have constructors...........................//........❌ No constructors
 *   Can have private, protected, and public methods.....//....Methods are public by default
*==============================================================================================================================================================================
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
// private, final, and static methods cannot be overridden but overloaded
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
//        | ----------- | ---------------------- | -------------------------------- | -------------------------------------- |
//        | Mutability  | Immutable              | Mutable                          | Mutable                                |
//        | Thread-Safe | Yes                    | Yes (synchronized)               | No                                     |
//        | Performance | Slow (new obj)         | Slower than `StringBuilder`      | Fastest                                |
//        | Use Case    | Constants, few changes | Multi-threaded text manipulation | High-performance single-threaded tasks |


//✅ When finalize() is invoked:
//The GC detects that there are no more references to the object.
//
//Before reclaiming the memory, the JVM calls finalize() (if it’s overridden).
//
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
 */