package Practice1.DecoratorDesignPattern;

/**
 * Description:<br>
 * Date: 24/10/24-5:11 pm
 *
 * @author ishangarg
 * @since
 */

public class ExtraCheese extends ToppingDecorator {

    BasePizza basePizza;

    public ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return this.basePizza.cost() + 10;
    }
}
