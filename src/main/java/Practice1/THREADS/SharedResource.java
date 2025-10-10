package Practice1.THREADS;

/**
 * Description:<br>
 * Date: 15/08/25-5:32 pm
 *
 * @author ishangarg
 * @since
 */

// ------------------------------------------------------------
class ProduceTask implements Runnable {
    private final SharedResource sharedResource;

    ProduceTask(SharedResource resource) {
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println("Producer thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000); // simulate work
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            return;
        }
        sharedResource.addItem();
    }
}
// ------------------------------------------------------------
class ConsumeTask implements Runnable {
    private final SharedResource sharedResource;

    ConsumeTask(SharedResource resource) {
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println("Consumer thread: " + Thread.currentThread().getName());
        sharedResource.consumeItem();
    }
}
// ------------------------------------------------------------
class Main {
    public static void main(String[] args) {
        System.out.println("Main method start");

        SharedResource sharedResource = new SharedResource();

        // producer thread
        Thread producerThread = new Thread(new ProduceTask(sharedResource), "Producer-1");

        // consumer thread
        Thread consumerThread = new Thread(new ConsumeTask(sharedResource), "Consumer-1");

        // thread is in "RUNNABLE" state
        producerThread.start();
        consumerThread.start();

        System.out.println("Main method end");
    }
}

// ------------------------------------------------------------

class SharedResource {

    private boolean itemAvailable = false;

    // synchronized -> put the monitor lock
    public synchronized void addItem() {
        itemAvailable = true;
        System.out.println(
                "Item added by: " + Thread.currentThread().getName()
                        + " and invoking all threads which are waiting"
        );
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("ConsumeItem method invoked by: " + Thread.currentThread().getName());

        // using while loop to avoid "spurious wake-ups"
        while (!itemAvailable) {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting now");
                wait(); // releases the monitor lock and waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("Item Consumed by: " + Thread.currentThread().getName());
        itemAvailable = false;
    }
}


