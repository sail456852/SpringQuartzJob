package msc.collection;

import java.util.ArrayList;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 1/6/19<br/>
 * Time: 7:52 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayList<E> {
   private int DEFAULT_SIZE = 2;

   private Object[] myarr = new Object[DEFAULT_SIZE];
   private int size = DEFAULT_SIZE;

   private int count = 0;
   private int cursor = 0;


   private boolean willOverFLow(){
      return count >= size;
   }

   public E get(int i){
      return (E) myarr[i];
   }

   public int size(){
      return count;
   }

   public void add(E e){
      if(willOverFLow()){
         doubleSize();
      }
      myarr[cursor] = e;
      count ++;
      cursor++;
   }

   private void doubleSize() {
       size = 2 * size;
       Object[] newEmptyArr = new Object[size];
       myarr = copyArr2NewArr(myarr, newEmptyArr);
   }

   private Object[] copyArr2NewArr(Object[] myarr, Object[] newEmptyArr) {
      if(size != DEFAULT_SIZE){
         for (int i = 0; i < size/2 ; i++) {
            newEmptyArr[i] = myarr[i];
         }
         return newEmptyArr;
      }
      return myarr;
   }

   public static void main(String[] args) {
      MyArrayList list = new MyArrayList<String>();
      list.add("ES1");
      list.add("AHVC2");
      list.add("3");
      list.add("4");
      list.add("909");
      list.add("8080");
      for (int i = 0; i < list.size(); i++) {
         System.err.println("list[i] = " + list.get(i));
      }
   }
}
