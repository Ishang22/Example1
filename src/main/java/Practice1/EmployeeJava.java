package Practice1;


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
final class Address1 {
    private final String country;

    public Address1(String country) {
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


final class Employee1 {
    private final String name;
    private final double salary;
    private final Address1 address;

    public Employee1(String name, double salary, Address1 address) {
        this.name = name;
        this.salary = salary;
        // Defensive copy to ensure immutability
        this.address = new Address1(address.getCountry());
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Address1 getAddress() {
        // Return a defensive copy to preserve immutability
        return new Address1(address.getCountry());
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + ", address=" + address + "}";
    }
}

class Main1
{
    public static void main(String args[])
    {
        Employee1 e1 = new Employee1("P1",400,new Address1("india"));
        Employee1 e2 = new Employee1("P2",500,new Address1("india"));
        Employee1 e3 = new Employee1("P3",600,new Address1("india"));
        Employee1 e4 = new Employee1("P4",700,new Address1("india"));
        Employee1 e5 = new Employee1("P5",800,new Address1("india"));

        List<Employee1> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        Predicate<Employee1> greaterThan40k = employee -> employee.getSalary()>400;
        Predicate<Employee1> lessThan80k = employee -> employee.getSalary()<700;

        Predicate<Employee1> combination=greaterThan40k.and(lessThan80k);
        //mutable
        List<Employee1> resultList =employees.stream().filter(combination).collect(Collectors.toList());

        List<Employee1> resultList1 =employees.stream().filter(combination).toList();

    }
}
/// /####################  https://www.youtube.com/watch?v=VZ3T2gFlEiA   #####################
/*

🔒 What is Encapsulation?
Encapsulation is wrapping data (fields) and code (methods) together into a single unit (usually a class), and
restricting access to some of the object's components using access modifiers (private, protected, public).

🧩 What is Abstraction?
Abstraction is hiding implementation details and showing only the  features of an object. It is often
achieved using abstract classes or interfaces.

// can interface have private methods --- yes from java 9 purpose of private methods inside an interface if they cannot be access outide the interface
// if u dnot use the terminal operations in a stream pipeline , will the intermidiate function will executed ? why or why no ?

// exaplain exception hireacty

* java.lang.Object
   └── java.lang.Throwable
          ├── java.lang.Error --- outofmemory/stackoverflow
          └── java.lang.Exception
                 ├── java.lang.RuntimeException (unchecked exceptions)
                 │      ├── NullPointerException
                 │      ├── ArithmeticException
                 │      ├── ArrayIndexOutOfBoundsException
                 │      └── IllegalArgumentException
                 └──-----
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

Calls the controller method and gets the response .

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

Q1 what happen if a circuit breaker opens too long ??
A1 The request keep failing even after the service is up, a proper timeout and retry mechanism can move it to half upon and half close state
Good question 👍 — this is a common confusion when learning about Circuit Breaker pattern.
Let’s break it down simply 👇

🧩 Background: What is a Circuit Breaker?

A Circuit Breaker prevents an application from repeatedly trying to call a failing service.
It has 3 main states:

State	Meaning	Behavior
Closed	    Service is healthy	All requests go through
Open	    Service is failing	Requests are blocked immediately (fail fast)
Half-Open	Testing recovery	A few test requests are allowed to check if service recovered

⚡ Q1: What happens if a circuit breaker stays open too long?

When the circuit breaker is open, it blocks all requests to the service — it assumes the service is still down.

➡️ So if it stays open too long, even after the service has recovered,
✅ the service is actually healthy,
❌ but your app keeps failing requests unnecessarily — because the breaker is still open.

🧠 Why does this happen?

Because the circuit breaker needs a timeout duration to decide when to try again (move to half-open).
If this timeout (also called sleep window or reset timeout) is too long —
it won’t test the service soon enough, so it keeps blocking requests even though the service is fine.

🔄 How to fix it?

By setting a proper timeout and retry mechanism:

Timeout → decides how long the breaker should stay open before trying again.
e.g. “Stay open for 10 seconds, then move to half-open.”

Half-Open → allows a few requests through to check if the service has recovered.

If success → move back to Closed (normal operation).

If failure → go back to Open.

🧾 Simplified Example:

Let’s say:

Service fails → breaker opens for 30 seconds.

But the service comes back healthy in 5 seconds.

If your timeout is too long (30s) →
For the next 25 seconds, requests still fail, even though the service is fine.
→ This is why we need a proper timeout.

✅ In short:

If a circuit breaker stays open too long, your app keeps failing requests even after the service has recovered.
A well-tuned timeout lets it move to half-open sooner, test the service, and recover quickly.



Q2 how will u configure your application if your app different databases for different enviroenments ?
A2 use spring profiles define different YML files

Q3 can a finally block over-ride an exception thrown in try block . --yes
A3 how tranctions are handled in ur application by @transaction aanotation

Q4 how can u optimize ur query which is taking long time .*********
A4. indexes,avoid select * use proper coloumns, use join effectively , filter with where, and use limit


//  HOW TO HANDLE CASCADE FAILURE IN MICROSERVICES
//  - timeout to avoid waiting indefinitely
//  - use circuit breaker to stop requestion failing services
//  - fallback respond to ensure system working and reslience
What problem are we solving?

Cascade failure happens when one service degrades or becomes slow and many callers keep waiting/retrying — that load ripples and can take down the whole system. The techniques below stop the ripple, let healthy parts keep working, and give you safe ways to recover.

Techniques (what they mean & why they help)
1) Timeout — avoid waiting indefinitely

Meaning: give any outbound call a hard upper-bound. If the remote doesn’t reply in time, fail the call rather than hang threads.
Why: prevents thread exhaustion and reduces queuing pressure when a downstream is slow.

Example behavior: set a 2s timeout for an HTTP call — if not returned, cancel/abort and handle failure.

2) Circuit Breaker — stop requesting failing services

Meaning: detect repeated failures and stop sending traffic to the failing service for a while.
Why: avoids wasting resources on calls that will likely fail, reduces load on the failing system, and gives it time to recover.

States:

Closed: normal.

Open: fail-fast (no outbound calls).

Half-Open: test a small number of requests; if they succeed, close the breaker.

3) Fallback responses — graceful degradation

Meaning: when a call fails (timeout or open circuit), return a safe alternative instead of propagating error to caller.
Why: keeps the user experience acceptable and prevents total failure of a feature. Examples: cached data, default value, degraded UI (e.g., “recent activity is unavailable”), or a simplified response.

Trade-offs: fallbacks can return stale or partial data; design them carefully.


=======================         //  clustered vs non clustered //              =======================
//Clustered         - sorts and stored in physical order improving read performance
//NON -Clustered    - it created a separate to store pointers to data,useful for multiple indexes and slower


// Different types of deployment


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

HEAD, OPTIONS, TRACE, GET, PUT  and DELETE   are idempotent
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
Kubernetes automates the deployment and management of these containers at scale


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
Exception Type	Extend	Must Handle?	   Example Use Case
Checked Exception	Exception	        ✅ Yes	File not found, invalid input
Unchecked Exception	RuntimeException	❌ Optional	Null pointer, illegal state, custom logic

@Entity                     – Marks the class as an entity.
@Table(name = "table_name") – Specifies the table name in the database.
@Id                         – Marks the primary key.
@Transient                  - When you want a field for business logic or computed values but don’t want it persisted.
@GeneratedValue(strategy = GenerationType.IDENTITY) – Defines primary key generation strategy.
@Column(name = "column_name")
*
*
*
❌ Not understanding how @Transactional works under the hood
* @Transactional uses AOP internally
**ASPECT(){
* @Before(pointcutexperssion()) // @around
* ADVICE(JOINTPOINT){
* }
* }
*
* uses point cut expression to search for method,which has @TRANSACTIONAL annotation like @transaction
* it is using around adive
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
1. Create a Dockerfile

A Dockerfile defines the environment and dependencies for your application.
Start with a base image (e.g., Ubuntu), then install all required packages.

# Use Ubuntu as the base image
FROM ubuntu:latest

# Set working directory
WORKDIR /app

# Install dependencies (example)
RUN apt-get update && apt-get install -y python3 python3-pip

# Copy application files
COPY . .

# Run your app (example command)
CMD ["python3", "app.py"]

2. Build the Docker Image
Use the docker build command to create an image from the Dockerfile.
docker build -t ishan_image .
✅ This builds an image named ishan_image using the current directory (.) as the build context.

3. Run a Container from the Image
Use the docker run command to start a container interactively from your image.
docker run -it ishan_image
✅ This runs the ishan_image container in interactive mode (-it), giving you access to its shell.

Kubernetes, also known as K8s is an open-source container orchestration system for automating software deployment, scaling, and management.
Originally designed by Google, the project is now maintained by a worldwide community of contributors, and the trademark is held by the
Cloud Native Computing Foundation

component vs repro vs service
https://www.youtube.com/watch?v=VG7ep7MIvjw

 */




///////////////// ////////////// ////////////// ////////////// ////////////// ////////////// ////////////// ////////////// ///////////
//////////////// ////////////// ////////////// ////////////// ////////////// ////////////// ////////////// ////////////// ///////////
/*

IoC → You give up control of object creation and lifecycle management.
Someone else (Spring Framework) does it for you.

DI → How Spring connects objects (by injecting dependencies) “Injecting the dependencies that a class needs, instead of the class creating them.”

ApplicationContext → The real container that performs IoC + DI

 */