package Practice1.THREADS;

public class PrintEvenAndOdd {

    static int counter = 1;

    int limit;

    PrintEvenAndOdd(int limit) {
        this.limit = limit;
    }

    public synchronized void printOddNum() {
        while (counter <= limit) {
            if (counter % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + ": " + counter);
                counter++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void printEvenNum() {
        while (counter <= limit) {
            if (counter % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + counter);
                counter++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintEvenAndOdd printer = new PrintEvenAndOdd(10);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printOddNum();
            }
        });

        t1.setName("Odd");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printEvenNum();
            }
        });

        t2.setName("Even");

        t1.start();
        t2.start();
    }
}
