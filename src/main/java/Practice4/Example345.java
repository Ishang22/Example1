package Practice4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:<br>
 * Date: 28/07/25-5:05 pm
 *
 * @author ishangarg
 * @since
 */
public class Example345 {
    public static void main(String args[]) throws ExecutionException, InterruptedException {

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


    }



}
