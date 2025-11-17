package Practice1.ObserverDesignPattern;

/**
 * Concrete Observer - Phone Display
 * Displays weather data on a mobile phone
 */
public class PhoneDisplay implements Observer {
    private String weatherData;
    
    @Override
    public void update(String message) {
        this.weatherData = message;
        display();
    }
    
    private void display() {
        System.out.println("📱 Phone Display: " + weatherData);
    }
}

