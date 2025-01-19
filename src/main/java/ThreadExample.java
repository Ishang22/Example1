/**
 * Description:<br>
 * Date: 06/01/25-8:42 pm
 *
 * @author ishangarg
 * @since
 */
// Approach 1: Implementing Runnable
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread running via Runnable: " + Thread.currentThread().getName());
    }
}

// Approach 2: Extending Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running via Thread: " + Thread.currentThread().getName());
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        // Using Runnable
        MyRunnable runnableTask = new MyRunnable();
        Thread thread1 = new Thread(runnableTask);
        thread1.start();

        // Using Thread subclass
        MyThread thread2 = new MyThread();
        thread2.start();

        // Main thread
        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}
