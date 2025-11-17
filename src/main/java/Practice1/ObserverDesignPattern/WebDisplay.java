package Practice1.ObserverDesignPattern;

/**
 * Concrete Observer - Web Display
 * Displays weather data on a website
 */
public class WebDisplay implements Observer {
    private String weatherData;
    
    @Override
    public void update(String message) {
        this.weatherData = message;
        display();
    }
    
    private void display() {
        System.out.println("🌐 Web Display: " + weatherData);
    }
}

