package tij.enumeration.exercise.four;

import tij.enumeration.example.Enums;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 11/17/18<br/>
 * Time: 10:57 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public enum Meal2 {
   APPETIZER(Food.Appetizer.class),
   MAINCOURSE(Food.MainCourse.class),
   DESSERT(Food.Dessert.class),
   COFFEE(Food.Coffee.class),
   CHINESESNACK(Food.ChineseSnack.class);

   private Food [] values;

   private Course(Class<? extends Food> kind){
      values = kind.getEnumConstants();
   }

   public Food randomSelection(){
       return Enums.random(values);
   }

   public interface Food{
      enum Appetizer{
         SALAD, SOUP, SPRING_ROLLS;
      }
      enum MainCourse{
         LASAGNE, BURRITO, PAD_THAI,
         LENTILS, HUMMOUS, VINDALOO;
      }
      enum Dessert{
         TIRAMISU, GELATO, BLACK_FOREST_CAKE,
         FRUIT, CREME_CARAMEL;
      }
      enum Coffee{
         BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
         LATTE, CAPPUCCINO, TEA, HERB_TEA;
      }
      enum ChineseSnack{
         DUMPLING, BUN, EVIL_EGG, RICE_NOODLE
      }

   }

}
