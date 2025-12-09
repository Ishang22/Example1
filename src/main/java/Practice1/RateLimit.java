package Practice1;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of Sliding window algorithm with timestamp and counter
 * (example: redis hash)
 */
public class RateLimit {

    int rateLimit;

    Map<String, LinkedList<Request>> userRequestMap = new ConcurrentHashMap<>();

    public RateLimit(int limit) {
        this.rateLimit = limit;
    }

    /**
     * Thread safe block being invoked by multiple threads
     *
     * @param user      username
     * @param timestamp timestamp of request
     * @return request allowed true/false
     */
    public synchronized boolean hit(String user, Instant timestamp) {

        if (!userRequestMap.containsKey(user)) {
            return addNewUser(user);
        } else {

            if (getTotalElpasedRequests(user) < rateLimit) {
                LinkedList<Request> requests = userRequestMap.get(user);
                requests.add(new Request(timestamp, 1));
                userRequestMap.put(user, requests);
                return true;
            } else {

                boolean actionTaken = false;

                for (int i = 0; i < userRequestMap.get(user).size(); i++) {
                    Duration duration = Duration.between(userRequestMap.get(user).get(i).getTimestamp(), timestamp);
                    // check for elapsed time greater than 1 minute (60 seconds)
                    // This can be passed as an argument at runtime to avoid hardcoding
                    if (duration.getSeconds() >= 60) {
                        userRequestMap.get(user).remove(i);
                        actionTaken = true;
                    } else {
                        break;
                    }
                }

                if (actionTaken) {
                    LinkedList<Request> requests = userRequestMap.get(user);
                    requests.add(new Request(timestamp, 1));
                    userRequestMap.put(user, requests);
                    return true;
                }

                return false;
            }

        }
    }

    public boolean addNewUser(String user) {
        LinkedList<Request> requests = new LinkedList<>();
        requests.add(new Request(Instant.now(), 1));
        userRequestMap.put(user, requests);
        System.out.println("New User added !! " + user);
        return true;
    }

    public Integer getTotalElpasedRequests(String user) {
        return userRequestMap.get(user).stream().mapToInt(Request::getCount).sum();
    }

}

/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++

/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++

//        Consumer →  Used in forEach.  [input and no return]
//        Predicate → Used in filter .  [input and return boolean]
//        Function →  Used in map    . [input and output]
//        supplier -> for random values [no input and output]

//Consumer in forEach:
//Yes, a Consumer is typically used in forEach. A Consumer is a functional interface representing an operation that accepts a single input argument and returns no result. It is often used in forEach to perform some action on each element of a collection or stream.
//
//java
//Copy code
//List<String> list = List.of("apple", "banana", "orange");
//list.forEach(s -> System.out.println(s));  // `s -> System.out.println(s)` is a Consumer

//Predicate in filter:
//Correct. A Predicate is a functional interface that represents a condition (a boolean-valued function) applied to an input. It is used in filter to test elements and allow only those that satisfy the condition.
//
//java
//Copy code
//List<Integer> numbers = List.of(1, 2, 3, 4, 5);
//List<Integer> evens = numbers.stream()
//        .filter(n -> n % 2 == 0)  // `n -> n % 2 == 0` is a Predicate
//        .toList();

//Function in map:
//Not "functional," but rather a Function. A Function is a functional interface that takes one argument and returns a result. It is used in map to transform each element of a stream into another form.
//List<String> names = List.of("John", "Jane", "Jake");
//List<Integer> nameLengths = names.stream()
//        .map(name -> name.length())  // `name -> name.length()` is a Function
//        .toList();

//List<Integer> numbers = List.of(1, 2, 3, 4, 5);
// Using reduce with a BinaryOperator
// int sum = numbers
//          .stream()
//          .reduce(0, (a, b) -> a + b); // `a + b` is a BinaryOperator
//        System.out.println("Sum: " + sum);

// List<String> myList = arr.stream().map(i->{return i.name;}).collect(Collectors.toList());

// This function returns a random value.
//        Supplier<Double> randomValue = () -> Math.random();
//
//   Print the random value using get()
//        System.out.println(randomValue.get());
/*

public class FlatMapExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", Arrays.asList("Math", "English")),
            new Student("Bob", Arrays.asList("Science", "Math")),
            new Student("Charlie", Arrays.asList("History", "English"))
        );

        List<String> allSubjects = students.stream()
            .flatMap(student -> student.getSubjects().stream())
            .distinct() // optional: to remove duplicates
            .collect(Collectors.toList());

        System.out.println(allSubjects);
        // Output: [Math, English, Science, History]
    }
}
 */
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
/*
The Thread class is part of the java.lang package.
java.util.concurrent in this threadPoolExecutor and completableFuture present
ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // Core pool size
                4, // Maximum pool size
                60, // Keep-alive time
                TimeUnit.SECONDS, // Keep-alive time unit
                new LinkedBlockingQueue<>(2), // Work queue with a size limit of 2
                Executors.defaultThreadFactory(), // Default thread factory
                new ThreadPoolExecutor.AbortPolicy() // Rejection policy
        );
        executor.submit or executor.execute
execute() is fire-and-forget (simple task execution). it only except runnable

submit() is powerful, with 3 variants supporting result tracking via Future.

Method	                          Description
submit(Callable<T>)	             Executes a task that returns a result
submit(Runnable)	             Executes a Runnable (no result) and returns Future<?>
submit(Runnable, T result)	     Executes a Runnable, and wraps a constant result in the Future

If you need results, exceptions, or control, prefer submit().


ExecutorService executor = Executors.newFixedThreadPool(2);

CompletableFuture<String> future = CompletableFuture
            .supplyAsync(() -> {
                simulateDelay("Fetching user");
                return "User: Ishan";
            })
            .thenApply(user -> {
                simulateDelay("Fetching orders");
                return user + " | Orders";
            })
            .thenApply(data -> {
                simulateDelay("Calculating total");
                return data + " | Total ₹5000";
            });

--------------------------------------------------------------------------------------------------------------------------------------------
CompletableFuture is part of the broader Executor framework.
it uses fork joinpol
It does use Runnable and Callable behind the scenes — just via functional interfaces like Supplier, Function, etc.
--------------------------------------------------------------------------------------------------------------------------------------------





        Thread t1 = new Thread(()->{
            System.out.println("TASK 1");
        });

        Thread t2 = new Thread(()->{
            System.out.println("TASK 2");
        });

        t1.start();
        t2.start();

        ExecutorService executor=    Executors.newFixedThreadPool(2);

        Future<Integer> s1 = executor.submit(()->{return 5;});

        System.out.println(s1.get());






        Thread t1 = new Thread(()->{
            System.out.println("TASK 1");
        });

        Thread t2 = new Thread(()->{
            System.out.println("TASK 2");
        });

        t1.start();
        t2.start();

        ExecutorService executor=Executors.newFixedThreadPool(2);

        Future<Integer> s1 = executor.submit(()->{return 5;});

        System.out.println(s1.get());
 */
/*
🧵 Java Multithreading — Callable & Future Explained Simply

Today I was reading about Callable & Future, and honestly…
this pair is one of the most useful (and underrated) features in Java concurrency.

Most people think Callable is “just another Runnable,”
but it actually fixes two big limitations of Runnable 👇

🟦 1️⃣ Runnable → Cannot return a value
Runnable runs, finishes, and that’s it.
You cannot get anything back from it.

🟩 2️⃣ Callable → Can return a value & throw checked exceptions
Callable is like a more powerful Runnable.

Callable<Integer> task = () -> {
 Thread.sleep(1000);
 return 42;
};

This code returns data. Runnable cannot.

🧠 So where does Future come in?
When you submit a Callable to an ExecutorService:

Future<Integer> result = executor.submit(task);
Java immediately gives you a Future.

A Future is basically a promise:
“I’ll give you the result later”
“You can check if I’m done”
“You can wait for me”
“You can cancel me too”
You get the result like this:

Integer value = result.get(); // waits until result is ready
This is one of the simplest ways in Java to do async work and later collect results.

💡 When should you use Callable + Future?
✔ When tasks need to return values
✔ When tasks might throw exceptions
✔ When tasks run in parallel and you want to gather results
✔ When you're building async workflows

This is the foundation for:
🧵 Thread pools
🔵 CompletableFuture
🤝 Parallel processing

Callable + Future is where “real” multithreading begins.
Drop 👍 & save 📘 for later.
If you’re enjoying these, follow me for more simple Java Multithreading posts.
 */
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
