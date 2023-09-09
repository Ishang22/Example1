import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//        A Callable needs to implement call() method while a Runnable needs to implement run() method.
//        A Callable can return a value but a Runnable cannot.
//        A Callable can throw checked exception but a Runnable cannot.


// Class
// Implementing the Callable interface
class CallableMessage implements Callable<String> {
    public String call() throws Exception {
        return "Hello World!";
    }
}

public class CallableExample {
//    Executor
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
//        Executors.callable();

        CallableMessage task = new CallableMessage();

        Future<String> message = executor.submit(task);

        System.out.println(message.get().toString());
    }
}