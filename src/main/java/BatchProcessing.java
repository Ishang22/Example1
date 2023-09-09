import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BatchProcessing {

    static class Task implements Callable<Integer> {
        private static Random rand = new Random();
        private final int no;

        Task(int no) {
            this.no = no;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(rand.nextInt(5000));
            System.out.println("Task " + no + " finished");
            if (no == 12) {
                throw new Exception("RRR");
            }
            return no;
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        processBatch(executor, 1);
        processBatch(executor, 2);
        processBatch(executor, 3);
        executor.shutdown();
    }

    private static void processBatch(ExecutorService executor, int batchNo) throws InterruptedException {
        Collection batch = new ArrayList<>();
        batch.add(new Task(batchNo * 10 + 1));
        batch.add(new Task(batchNo * 10 + 2));
        batch.add(new Task(batchNo * 10 + 3));
        List<Future> futures = executor.invokeAll(batch);


        for (Future future : futures) {
            try {
                System.out.println("===futures.get(1)===" + future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("=======" + e);
            }
        }

    }
}
