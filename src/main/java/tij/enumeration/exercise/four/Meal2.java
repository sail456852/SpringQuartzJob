package tij.enumeration.exercise.four;

import tij.enumeration.example.Course;
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

   Meal2(Class<? extends Food> kind){
      values = kind.getEnumConstants();
   }

   public Food randomSelection(){
       return Enums.random(values);
   }

   public interface Food{
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


   public static void main(String[] args) {
      Meal2[] meal2s = Meal2.values();
      for (int i = 0; i < 5; i++) {
         for (Meal2 meal2 : meal2s) {
            Food food = meal2.randomSelection();
            System.out.println("food2 = " + food);
         }
      }
   }

}
