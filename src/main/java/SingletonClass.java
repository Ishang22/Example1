//import lombok.Synchronized;

//private constructor
//private variable that is static to acces in static method

public class SingletonClass {
    private static SingletonClass instance = null;

    private SingletonClass() {
    }

    static SingletonClass getInstance() {
        if (instance == null) {
            synchronized (SingletonClass.class) {
                if (instance == null) {
                    instance = new SingletonClass();
                }
            }
        }
        return instance;
    }
}

class SingletonDemo {
    public static void main(String args[]) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                SingletonClass obj = SingletonClass.getInstance();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                SingletonClass obj = SingletonClass.getInstance();
            }
        });

        t1.start();
        t2.start();
    }
}