package Practice1;

/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++

/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++

//        Consumer →  Used in forEach.
//        Predicate → Used in filter .
//        Function →  Used in map    .
//        supplier -> for random values

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
//int sum = numbers.stream()
//        .reduce(0, (a, b) -> a + b); // `a + b` is a BinaryOperator
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
/*

java.util.concurrent in this thread pool exector and completure furture present
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


Method	                          Description
submit(Callable<T>)	             Executes a task that returns a result
submit(Runnable)	             Executes a Runnable (no result) and returns Future<?>
submit(Runnable, T result)	     Executes a Runnable, and wraps a constant result in the Future

execute() is fire-and-forget (simple task execution). it only except runnable

submit() is powerful, with 3 variants supporting result tracking via Future.

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


 */
/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++/// //______+++++++++++++++
