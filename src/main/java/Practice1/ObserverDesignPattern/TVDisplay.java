package Practice1.ObserverDesignPattern;

/**
 * Concrete Observer - TV Display
 * Displays weather data on a television screen
 */
public class TVDisplay implements Observer {
    private String weatherData;
    
    @Override
    public void update(String message) {
        this.weatherData = message;
        display();
    }
    
    private void display() {
        System.out.println("📺 TV Display: " + weatherData);
    }
}

