import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// Class
// Implementing the Runnable interface 
class RunnableImpl implements Runnable {
    public void run() {
        System.out.println("Hello World from a different thread than Main");
    }
}

class RunnableExample {
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        List<Integer> number = Arrays.asList(2, 3, 4, 5);

        System.out.println("=======" + number.stream().reduce(0, (a, b) -> a + b));


        String multilineString = "Baeldung helps \n \n developers \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());

        // assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");


        // Creating and running runnable task using Thread class
        RunnableImpl task = new RunnableImpl();
        Thread thread = new Thread(task);
        thread.start();
        // Creating and running runnable task using Executor Service.
        executor.submit(thread);
        executor.submit(task);
    }
}