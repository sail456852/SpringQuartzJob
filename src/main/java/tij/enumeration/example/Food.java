package tij.enumeration.example;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 11/17/18<br/>
 * Time: 10:33 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface Food {
    enum Appetizer implements Food{
        SALAD, SOUP, SPRING_ROLLS;
    }
    enum MainCourse implements Food{
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }
    enum Dessert implements Food{
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }
    enum Coffee implements Food{
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
    class InnerClass implements Food{
        public void infunction() {
            System.out.println("InnerClass.infunction");
        }
    }
}
