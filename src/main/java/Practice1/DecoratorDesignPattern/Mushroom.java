package Practice1.DecoratorDesignPattern;

/**
 * Description:<br>
 * Date: 24/10/24-5:15 pm
 *
 * @author ishangarg
 * @since
 */
public class Mushroom extends ToppingDecorator {
    BasePizza basePizza;

    public Mushroom(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return this.basePizza.cost() + 20;
    }
}
