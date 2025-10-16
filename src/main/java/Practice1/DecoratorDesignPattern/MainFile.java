package Practice1.DecoratorDesignPattern;

/**
 * Description:<br>
 * Date: 23/10/24-11:49 pm
 *
 * @author ishangarg
 * @since
 */
public class MainFile {
    public static void main(String[] args) {
        System.out.println(new Mushroom(new ExtraCheese(new FarmHouse())).cost());
    }

}
/*
🧩 Strategy Pattern — When to Use
The Strategy Pattern is used when you have multiple algorithms or behaviors for a specific task,
and you want to choose which one to use at runtime — without changing the calling code.


🎨 Decorator Design Pattern — When to Use
The Decorator Pattern is used when you want to add new behaviors or responsibilities to an object dynamically
 — without modifying its existing code or creating lots of subclasses.

 */