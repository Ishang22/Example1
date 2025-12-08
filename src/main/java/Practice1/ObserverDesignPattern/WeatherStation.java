package Practice1.ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject - WeatherStation that maintains weather data
 * and notifies all registered observers when data changes
 */
public class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherStation() {
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered: " + observer.getClass().getSimpleName());
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removed: " + observer.getClass().getSimpleName());
    }
    
    @Override
    public void notifyObservers() {
        String weatherData = String.format("Temperature: %.1f°C, Humidity: %.1f%%, Pressure: %.1f hPa", 
                                          temperature, humidity, pressure);
        for (Observer observer : observers) {
            observer.update(weatherData);
        }
    }
    
    // Method to update weather measurements
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
    
    private void measurementsChanged() {
        notifyObservers();
    }
}




