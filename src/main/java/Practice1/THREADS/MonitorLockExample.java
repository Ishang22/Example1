package Practice1.THREADS;

/**
 * Description:<br>
 * Date: 15/08/25-5:09 pm
 *
 * @author ishangarg
 * @since
 *
 *
 * ✔   Process is an instance of a program that is getting executed.
 *     It has its own resource like memory, thread etc. OS allocate these resources to process when its created.
       ✅ Correct understanding:

=========javac Test.java → Compilation phase====================================

          Converts Java source code → Bytecode (Test.class)

          Bytecode is platform-independent

=========java Test → Execution phase=============================================
         first process is created then jvm instance is assignes to this process
         The JVM (Java Virtual Machine) loads the bytecode.

         The JIT compiler (Just-In-Time) converts bytecode → machine code at runtime (not before) and store it in code segement.

 *    When a Process is created, it start with 1 thread and that initial thread know as 'main thread' and from that
 *    we can create multiple threads to perform task concurrently.
 *
 *    when we do Java test it will convert to Byte to machine code and one new process is created and new jvm instance
 *    is assigned to it
 */
public class MonitorLockExample {

    public synchronized void task1() {
        try {
            System.out.println("inside task1");
            Thread.sleep(10000); // sleep for 10 seconds
        } catch (Exception e) {
            // exception handling here
            e.printStackTrace();
        }
    }

    public void task2() {
        System.out.println("task2, but before synchronized");
        synchronized (this) {
            System.out.println("task2, inside synchronized");
        }
    }

    public void task3() {
        System.out.println("inside task3");
    }

    public static void main(String[] args) {
        MonitorLockExample obj = new MonitorLockExample();

        Thread t1 = new Thread(() -> obj.task1());
        Thread t2 = new Thread(() -> obj.task2());
        Thread t3 = new Thread(() -> obj.task3());

        t1.start();
        t2.start();
        t3.start();
    }
}

/*
Reentrant, ReadWrite, Stamped & - do not depend on synchronized keyword

with synchronized of can block same object if different objects are coming to handle these locks are introduced

Reentrant Lock  – A mutual-exclusion lock that allows the same thread to acquire it multiple times without blocking itself.

ReadWrite Lock  – A lock that allows multiple concurrent readers but only one writer at a time.

Stamped Lock    – A lock that supports optimistic, read, and write modes for better performance under read-heavy workloads.

Semaphore       – A concurrency control that limits the number of threads accessing a resource at the same time.

virtul threads - https://www.youtube.com/watch?v=0NtIcbSsjBc
Normal Java threads (a.k.a. platform threads) are mapped 1:1 to OS threads. They’re expensive, limited in number, and blocking operations waste resources.

Virtual threads are managed by the JVM instead of the OS. They’re much lighter, so you can create millions of them
without running out of memory or hitting OS limits.


#post_12

Java Interview Prep – Threads vs Virtual Threads

Java 21 introduced Virtual Threads (Project Loom), a game-changer for concurrency.
Here’s how they differ 👇


🔹 Case 1 – Platform Threads (Traditional)

for (int i = 0; i < 1000; i++) {
 new Thread(() -> {
 try { Thread.sleep(1000); } catch (Exception e) {}
 }).start();
}

⚠️ 1000 threads = heavy on memory & OS context switching.
📉 Scalability issues in high-load apps (banking APIs, stock trading).


🔹 Case 2 – Virtual Threads (Java 21+)

try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
 for (int i = 0; i < 100000; i++) {
 executor.submit(() -> {
 Thread.sleep(1000);
 return null;
 });
 }
}

✅ 100,000 virtual threads easily created.
✅ Lightweight – scheduled by JVM, not OS.
✅ Perfect for I/O-bound tasks (DB calls, REST APIs).


⚡ Performance Difference

Platform Thread: ~1–2 MB memory per thread.

Virtual Thread: Only a few KB stack + managed by JVM.

Real-world: A payment gateway can handle 100x more concurrent requests with the same hardware.


🔍 When to Use Which?

Platform Threads → Best for CPU-bound tasks (e.g., fraud detection, encryption) or when relying on ThreadLocal for legacy session storage.

Virtual Threads → Best for I/O-bound tasks (e.g., high-traffic APIs, DB queries, microservices).

Hybrid Approach → Many real-world systems combine both — computation with platform threads + I/O with virtual threads.


💡 Interview Tip:
If asked “Should we completely replace threads with virtual threads?” →
👉 Answer: No. Virtual threads excel at I/O concurrency, but CPU-heavy workloads still benefit from platform threads. Choose based on workload type.


 */

