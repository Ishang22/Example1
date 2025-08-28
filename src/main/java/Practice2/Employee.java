package Practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Description:<br>
 * Date: 08/04/25-1:42 am
 *
 * @author ishangarg
 * @since
 */
// An immutable class is typically final, has private final fields, no setters, and only getters,
// ensuring its state can't be changed after creation.
final class Address {
    private final String country;

    public Address(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{country='" + country + "'}";
    }
}


final class Employee {
    private final String name;
    private final double salary;
    private final Address address;

    public Employee(String name, double salary, Address address) {
        this.name = name;
        this.salary = salary;
        // Defensive copy to ensure immutability
        this.address = new Address(address.getCountry());
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Address getAddress() {
        // Return a defensive copy to preserve immutability
        return new Address(address.getCountry());
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + ", address=" + address + "}";
    }
}

class Main
{
    public static void main(String args[])
    {
        Employee e1 = new Employee("P1",400,new Address("india"));
        Employee e2 = new Employee("P2",500,new Address("india"));
        Employee e3 = new Employee("P3",600,new Address("india"));
        Employee e4 = new Employee("P4",700,new Address("india"));
        Employee e5 = new Employee("P5",800,new Address("india"));

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        Predicate<Employee> greaterThan40k = employee -> employee.getSalary()>400;
        Predicate<Employee> lessThan80k = employee -> employee.getSalary()<700;

        Predicate<Employee> combination=greaterThan40k.and(lessThan80k);
        //mutable
        List<Employee> resultList =employees.stream().filter(combination).collect(Collectors.toList());
        //unmutable Collections.unmodifiableList()
        List<Employee> resultList1 =employees.stream().filter(combination).toList();

    }
}
/// /####################  https://www.youtube.com/watch?v=VZ3T2gFlEiA   #####################
// can we achive encasuplation without abstraction if yes how
/*
🔒 What is Encapsulation?
Encapsulation is wrapping data (fields) and code (methods) together into a single unit (usually a class), and
restricting access to some of the object's components using access modifiers (private, protected, public).
🧩 What is Abstraction?
Abstraction is hiding implementation details and showing only the  features of an object. It is often
achieved using abstract classes or interfaces.
 */
// can interface have private methods --- yes from java 9 purpose of private methods inside an interface if they cannot be access outide the interface
// if u dnot use the terminal operations in a stream pipeline , will the intermidiate function will executed ? why or why no ?
// ❌ No, the intermediate operations in a stream pipeline will NOT be executed if there is no terminal operation.
//  how many types of class loaders are there
//. Bootstrap ClassLoader.   = Loads: Core Java classes
//  APPLICATION CLASSLOADERS = USER DEFINE CLASSES
//  Platform ClassLoader     = Loads: Java platform modules, excluding java.base.
// what happend if two class loaders load the same class ----->>>>>
// When two different class loaders load the same class (by name) in Java, they actually load two separate and distinct versions of the class.
// Even though the class name and bytecode may be identical, the JVM considers them completely different classes because they are loaded by different class loaders.
// they are treated as different classes by the JVM, which can lead to ClassCastException, type mismatch, and general incompatibility ,,,even if the bytecode is identical.
// exaplain exception hireacty
/*
* java.lang.Object
   └── java.lang.Throwable
          ├── java.lang.Error --- outofmemory/stackoverflow
          └── java.lang.Exception
                 ├── java.lang.RuntimeException (unchecked exceptions)
                 │      ├── NullPointerException
                 │      ├── ArithmeticException
                 │      ├── ArrayIndexOutOfBoundsException
                 │      └── IllegalArgumentException
                 └── Other checked exceptions
                        ├── IOException
                        ├── SQLException
                        ├── ClassNotFoundException
                        └── InterruptedException
* */
// why does java separate checked and unchecked exception and can we create our own custom checked exception
// difference between hashmap and treemap with order of time complexity of retrival and search
//-----hashmap O(1)  and TREEMAP O (logn)
// can we store null as a key in TreeMap ?
//------no because it uses red black tree for sorting and null conot be compared , althorfh hasmp can store one null key
// what is dispatch serveleet
/*.====================================== DispatcherServlet=================================
🚦 Main Responsibilities of DispatcherServlet:
Receives the request from the client (browser).

Uses HandlerMapping to find the matching controller method.

Calls the controller method and gets the response (usually a Java object or view).

Chooses a ViewResolver or HttpMessageConverter based on the response type.

If it's a REST response (@RestController or @ResponseBody), it uses HttpMessageConverter (e.g., Jackson for JSON).

If it's a normal controller returning a view name, it resolves a template (e.g., JSP, Thymeleaf).

Prepares

✅ 1. @Controller
Used to build web applications that return views (HTML pages).
✅ 2. @RestController = @controller + @ResponseBody
A specialized version of @Controller designed for REST APIs.
It returns data (like JSON or XML), not views.
It is a shortcut for:
@Controller + @ResponseBody

########################################################################################################
*/
// difference between request param(/users?id=10) and path param (/users/10)
// can we use @requestparam @pathparam together yes we can use them together
/*
BUT... in practice, REST APIs can maintain session-like behavior, using things like:

Tokens (e.g., JWT tokens sent with every request)

Cookies (servers can set cookies; browsers automatically send them with requests)

Session IDs (stored on client side and sent back every time)

👉 Important point:
Even when using sessions or tokens, the API itself remains stateless if the client provides all needed info (like a session token) with every request.
The server may verify the token or session ID — but it shouldn't have to remember anything between calls.
 */
// what happen if a circuit breaker opens too long ??
// the request keep failing even after the service is up, a proper timout and retry mechanism can move it to half upon and half close state
// how will u configure your application if your app different databases for different enviroenments ?
// use spring profiles define different YML files
// can a finally block oveeride an exception thrown in try block .--yes
// how tranctions are handled in ur application
// by @transaction aanotation
// how can u optimize ur query which is taking long time .*********
//. indexes,avoid select * use proper coloumns, use join effectively , filter with where, and use limit
// monitoring tools i have used in my project
// what is TDD
// what is SDLC



// java memeory management
// 2025-69261


//https://www.youtube.com/watch?v=gQvMnP5v2D8 nagaroor
//https://www.youtube.com/watch?v=Sx8lsgDPy2U oracle
//https://www.youtube.com/watch?v=aAyHgEaEoNY walmart
//✅ mapToObj — used on primitive streams like IntStream
//java
//        Copy
//Edit
//     IntStream.of(1, 2, 3)
//     .mapToObj(i -> "Number " + i)  // changes 1 → "Number 1", etc.
//     .forEach(System.out::println);


//✅ map — used on normal object streams
//        java
//Copy
//        Edit
//Stream.of("a", "bb", "ccc")
//     .map(s -> s.length())     // changes "a" → 1, "bb" → 2, "ccc" → 3
//        .forEach(System.out::println);


//public class FirstNonRepeatingChar {
//    public static void main(String[] args) {
//        String input = "swiss";
//
//         Map<Character, Integer> countMap = new LinkedHashMap<>();
//
//         //Count frequency of each character
//        for (char c : str.toCharArray()) {
//        countMap.put(c, countMap.getOrDefault(c, 0) + 1);
//        }
//
//        // Find the first character with frequency 1
//        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
//        if (entry.getValue() == 1) {
//        return entry.getKey();
//            }
//                    }
//
//                    return null;
//
//
//        System.out.println("First non-repetitive character: " + result);
//    }
//}

// @responsebody.  // @inject // @requestparam


// There is a single LinkedList where each node represents a character , prove its palindrome through code
// what are different types of deployement
// how deployement works in java ? how pipeline code is written .
// Write a java code to find the largest continuous sequence in an array which sums to zero.


// difference between IOC container and application context
//IoC Container	General term — refers to the mechanism in Spring that manages object creation and dependency injection (DI).
//ApplicationContext	A specific implementation of the IoC container that provides additional enterprise-level features.
//like - Annotation-based Configuration and Scanning,Environment Abstraction and  Internationalization (i18n) Support

//HOW TO HANDLE CASCADE FAILURE IN MICROSERVICES
//  timeout to avoid waiting indefinitely
// use circuit breaker to stop requestion failing services
//fallback respond to ensure system working and reslience

//clustered vs non clustered
//Clustered         - sorts and stored in physical order improving read performance
//NON -Clustered    - it created a separate to store pointers to data,useful for multiple indexes and slower


// how deployement works in java? how pipeline code is written?
//  Building the code, packaging it into jar/war file , deploying it to server
// A pipeline automates this process using jenkins with steps like build test , package and deploy for continous delivery

//Different types of deployment
// Shadow deployment means:
// You deploy a new version of your app alongside the current production version.
// But the new version doesn’t affect real users yet!
// Instead, it quietly processes real traffic in the background, and you monitor it to see if it behaves correctly.
//
//Canary Deployment?
//Canary deployment is a gradual rollout strategy where:
//You release a new version of your app to a small subset of users first.
//If it works fine (no errors, good performance), you slowly roll it out to more users.
//If problems are found, you stop or roll back before it affects everyone.
//
//Blue-Green Deployment is a strategy where:
//You have two identical environments:
//🟦 Blue = current live version (old)
//🟩 Green = new version (updated)
//🚦🚦🚦 🚦🚦🚦How It Works (Step-by-Step) 🚦🚦🚦🚦🚦🚦🚦🚦🚦
//Blue (Current Production):
//You have your current working version of the app running. This is what users are using right now.
//
//Deploy to Green (New Version):
//You deploy the new version of the app to the green environment, but it's not live yet.
//
//Switch Traffic:
//Once the green environment is ready and tested, you switch the traffic from the blue environment to the green one. This is typically done by switching load balancer configurations or DNS settings.
//
//        Green Live, Blue Inactive:
//Now, the green environment is live, and users are accessing the new version.
//
//Rollback (Optional):
//If there's any issue with the green environment, you can quickly switch back to the blue environment without downtime. The blue environment is still there, just inactive for now.

///////HOW TO AVOID DEADLOCK IN JAVA ///////////
/*
we should use lock with timeouts
minimize syncrozed blocks
avoid nested locks
detect and handle circular dependecnies
 */

/*
########################################################################################################
############## REST stands for Representational State Transfer #########################################
########################################################################################################
REST stands for Representational State Transfer. that allows communication between different systems over the internet

Stateless: Each request from a client to a server must contain all the information the server needs to fulfill the request. No session state is stored on the server.
Client-Server Architecture: RESTful APIs are based on a client-server model, where the client and server operate independently, allowing scalability.
Cacheable: Responses from the server can be explicitly marked as cacheable or non-cacheable to improve performance.
Uniform Interface: REST APIs follow a set of conventions and constraints, such as consistent URL paths, standardized HTTP methods, and status codes, to ensure smooth communication.


@RequestMapping(value="/pow", method=RequestMethod.GET)
public int pow(@RequestParam(value="base") int base1, @RequestParam(value="ext") int ext1){
    int pow = (int) Math.pow(base1, ext1);
    return pow;
}

@RequestMapping(value="/sqrt/{num}", method=RequestMethod.GET)
public double sqrt(@PathVariable(value="num") int num1){
    double sqrtnum=Math.sqrt(num1);
    return sqrtnum;
}

HEAD, OPTIONS,TRACE,GET, PUT  , and DELETE   are idempotent
++++++++++++++++++++++++++ 111111  ++++++++++++++++++++++++++++++++++++++++++++++++++++
TRACE - TRACE is used to send a request to the server, and the server replies by echoing back
exactly what it received — so you can see if anything was changed along the way.
TRACE is used to get a full copy of the HTTP request that the server received — including:

The request line
All headers
And possibly the body (though bodies are rare in TRACE)
So while headers are included, TRACE is not limited to headers — it returns the entire original request as the response body, for debugging purposes.

++++++++++++++++++++++++++ 2222  ++++++++++++++++++++++++++++++++++++++++++++++++++++
HEAD -  Retrieves the headers of a resource without the response body.

++++++++++++++++++++++++++ 33333  ++++++++++++++++++++++++++++++++++++++++++++++++++++
OPTIONS is an HTTP method used by a client (like a browser or Postman) to ask the server:

“Hey server, what methods (like GET, POST, PUT, etc.) are allowed on this URL?”
####################################################################################################

@JsonIgnore is used to ignore the logical property used in serialization and deserialization. @JsonIgnore can be used at setters, getters or fields.
/*
@Transient // JPA: Not stored in DB
@JsonIgnore // Jackson: Not shown in JSON
private String tempToken;
/*
Docker to package their applications into containers
while Kubernetes automates the deployment and management of these containers at scale

how to create image in docker asked in interview
Write a Dockerfile pointing to jar file
Build the image[Dockerfile] using and give a name to image
Run the image

Horizontal scaling in Kubernetes means increasing or decreasing the number of pod replicas for a workload to handle varying traffic — more pods = more capacity

📈 How It Works
Kubernetes uses the metrics server to monitor resource usage (CPU/memory).
If average CPU > target (e.g., 50%), it adds more pods.
If average CPU < target, it removes pods (but not below min).
Vertical scaling = changing resource limits per pod; horizontal = changing pod count.


✅ List of Cascade Types in JPA
CascadeType.PERSIST
- Propagates the persist (save) operation.
- If you save the parent, the child is also saved.

CascadeType.MERGE
- Propagates the merge (update) operation.
- If you update the parent, the child gets updated too.

CascadeType.REMOVE
- Propagates the remove (delete) operation.
- If you delete the parent, the child is deleted too.

CascadeType.REFRESH
- if u do this we u do any changes in memory after doing cascadeType.refresh it will get the fresh object from db and overwrite ur changes in memroy


CascadeType.DETACH
- Propagates the detach operation.remove the object from memory
- If the parent is detached from the persistence context, the child is too.

CascadeType.ALL
- Includes all the above types.
- Equivalent to: {PERSIST, MERGE, REMOVE, REFRESH, DETACH}

There is a limit of up to 5 read replicas per RDS instance by default in Amazon RDS




✅ 1. Custom Checked Exception Extend Exception

public class CustomCheckedException extends Exception {
    public CustomCheckedException(String message) {
        super(message);
    }
}
Usage:

public void someMethod() throws CustomCheckedException {
    // some logic
    throw new CustomCheckedException("This is a checked exception");
}
🟡 You must declare it using throws in method signature
🟡 Callers must handle it using try-catch or rethrow

✅ 2. Custom Unchecked Exception
Extend RuntimeException

public class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException(String message) {
        super(message);
    }
}
Usage:
public void anotherMethod() {
    // some logic
    throw new CustomUncheckedException("This is an unchecked exception");
}
🔴 You don’t need to declare it in method signature
🔴 Callers can handle, but are not forced to

🔍 How to Choose Which One?
Exception Type	Extend	Must Handle?	Example Use Case
Checked Exception	Exception	✅ Yes	File not found, invalid input
Unchecked Exception	RuntimeException	❌ Optional	Null pointer, illegal state, custom logic

@Entity                     – Marks the class as an entity.
@Table(name = "table_name") – Specifies the table name in the database.
@Id                         – Marks the primary key.
@Transient                  - When you want a field for business logic or computed values but don’t want it persisted.
@GeneratedValue(strategy = GenerationType.IDENTITY) – Defines primary key generation strategy.
@Column(name = "column_name")
*/
/*
*
*
*
❌ Explaining Dependency Injection like a theory question — without real code examples.
* 🚀 How Spring Boot Handles DI
Spring Boot uses the Spring Framework’s Inversion of Control (IoC) container, which automatically injects dependencies into your beans at runtime using annotations.

❌ Not understanding how @Transactional works under the hood
* @Transactional uses AOP internally
**ASPECT(){
* @Before(pointcutexperssion())// @around
* ADVICE(JOINTPOINT){
* }
* }
*
* - uses point cut expression to search for method,which has @TRANSACTIONAL annotation like @transaction
* - it is using around adive
* In code, the @Around advice uses ProceedingJoinPoint which has the proceed() method to invoke the target method.
❌ Lack of clarity on securing APIs using Spring Security — missing out on authentication, authorization, and filters.
* filters vs interceptors -
*  filters call before chossing the servlet example - authorization for common for all servlet ,
*  and interceptors after dispactch servelet is chossen and before chosen endpoint - example anythin gspecific of rest despector
❌ Struggling to answer: “How would you make this microservice production-ready?”
* https://chatgpt.com/share/6864e7cd-9dd4-8008-8e7e-173e8eb66f4b
❌ Skipping key concepts like exception handling, input validation, and global error response design.
*
*2️⃣ System Design (Microservices)
Task: Design a scalable system
This was the most difficult round. I was tested on my High-Level Design knowledge, and I came out unscathed!
Grilled On:
Distributed system tradeoffs Cache strategies
Pro Tip: Study CAP theorem + understanding of design tradeoffs.
*
*
* 2p and 3p
https://www.youtube.com/watch?v=kFj-0E-en4o
*
* redis distributed system -
* https://www.youtube.com/watch?v=uptxcaf2s_4&t=1520s
* https://www.youtube.com/watch?v=MXARp8kXFbA
*
*  Five Rate Limiting Algorithms
* https://www.youtube.com/watch?v=mQCJJqUfn9Y&t=363s - HELLO BYTE
*
* 🔁 Kafka lets consumers read data from any point in time (offset) — that’s streaming.
 📦 RabbitMQ delivers a message and deletes it — classic messaging queue.
* */

/*
docker -
Docker_File (run with all dependencies and all images)
docker build -t ishan_image // built ishan_image image from docker_file instructions
docker run -it ishan_image // run ishan_image image in the container

 */