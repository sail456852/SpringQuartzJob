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
        SALAD, SOUP, SPRING_ROLLS
    }
    enum MainCourse implements Food{
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO
    }
    enum Dessert implements Food{
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL
    }
    enum Coffee implements Food{
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA
    }
    enum ChineseSnack implements Food{
        DUMPLING, BUN, EVIL_EGG, RICE_NOODLE
    }

}
