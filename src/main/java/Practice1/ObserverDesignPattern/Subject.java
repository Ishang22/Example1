package Practice1.ObserverDesignPattern;

/**
 * Subject interface - defines the contract for subjects (observables)
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}




