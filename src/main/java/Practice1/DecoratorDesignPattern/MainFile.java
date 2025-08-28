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
