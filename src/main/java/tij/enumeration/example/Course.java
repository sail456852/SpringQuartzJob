package tij.enumeration.example;

import java.util.EnumSet;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 11/17/18<br/>
 * Time: 10:57 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public enum Course {
   APPETIZER(Food.Appetizer.class),
   MAINCOURSE(Food.MainCourse.class),
   DESSERT(Food.Dessert.class),
   COFFEE(Food.Coffee.class),
   CHINESESNACK(Food.ChineseSnack.class);

   private Food[] values;

   Course(Class<? extends Food> kind){
      values = kind.getEnumConstants();
   }

   public Food randomSelection(){
       return Enums.random(values);
   }
}
