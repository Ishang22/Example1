import lombok.Synchronized;

public class SingletonClass {
    static SingletonClass instance = null;

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
