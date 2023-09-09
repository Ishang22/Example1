//Connection pool
//        - there is already implemented class called Connection.java.
//        - which is expensive to initialize. so we want to create pooling for this class.
//        - implement a Pool to store and serve reusable connection objects.
//        - it should a have threshold(lets say 10) of max Connection objects to be created.
//        - if threshold is reached, then make the requester(java container threads) to wait until one of the connection is free.
//        - has to be lazy initialisation


// Deadlock can be prevented by eliminating any of the four necessary conditions,
// which are mutual exclusion, hold and wait, no preemption, and circular wait.

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class Connection {
    Connection() {

    }
}

public class RazorPay2 {
    int poolSize;
    HashMap<Connection, Boolean> m1 = new HashMap<>();

    RazorPay2(int poolSize) {
        this.poolSize = poolSize;
    }

    synchronized Connection getConnectionObject() throws InterruptedException {
        for (Connection m : m1.keySet()) {
            if (m1.get(m) == true) {
                return m;
            }
        }

        if (poolSize == m1.size())
            this.wait();

        Connection c1 = new Connection();
        m1.put(c1, false);

        return c1;
    }

    synchronized void freeConnectionObject(Connection c) {
        if (m1.containsKey(c)) {
            m1.put(c, true);
            notify();
        }
    }

}
