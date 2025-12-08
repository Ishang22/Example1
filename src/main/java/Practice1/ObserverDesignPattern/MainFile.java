package Practice1.ObserverDesignPattern;

/**
 * Observer Design Pattern Demo
 * 
 * The Observer pattern defines a one-to-many dependency between objects
 * so that when one object changes state, all its dependents are notified
 * and updated automatically.
 * 
 * Key Components:
 * 1. Subject (Observable) - Maintains list of observers and notifies them of changes
 * 2. Observer - Interface for objects that should be notified of changes
 * 3. Concrete Subject - Implements Subject and holds the state
 * 4. Concrete Observers - Implement Observer and react to state changes
 * 
 * Benefits:
 * - Loose coupling between subject and observers
 * - Supports broadcast communication
 * - Easy to add new observers without modifying subject
 */
public class MainFile {
    public static void main(String[] args) {
        // Create the subject (weather station)
        WeatherStation weatherStation = new WeatherStation();
        
        // Create observers (different displays)
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        TVDisplay tvDisplay = new TVDisplay();
        WebDisplay webDisplay = new WebDisplay();
        
        System.out.println("=== Registering Observers ===");
        // Register observers with the subject
        weatherStation.registerObserver(phoneDisplay);
        weatherStation.registerObserver(tvDisplay);
        weatherStation.registerObserver(webDisplay);
        
        System.out.println("\n=== First Weather Update ===");
        // Update weather data - all observers will be notified
        weatherStation.setMeasurements(25.5f, 65.0f, 1013.1f);
        
        System.out.println("\n=== Second Weather Update ===");
        weatherStation.setMeasurements(28.0f, 70.0f, 1012.5f);
        
        System.out.println("\n=== Removing TV Display ===");
        // Remove an observer
        weatherStation.removeObserver(tvDisplay);
        
        System.out.println("\n=== Third Weather Update (TV won't receive this) ===");
        weatherStation.setMeasurements(22.0f, 80.0f, 1015.0f);
        
        System.out.println("\n=== Adding TV Display Back ===");
        weatherStation.registerObserver(tvDisplay);
        
        System.out.println("\n=== Fourth Weather Update (All displays active) ===");
        weatherStation.setMeasurements(30.0f, 55.0f, 1010.0f);
    }
}




