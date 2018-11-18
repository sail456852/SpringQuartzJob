package msc.jdk;// Java program to demonstrate working of shuffle()

import java.util.*;

public class Shuffler {
    public static void main(String[] args) {
        ArrayList<String> mylist = new ArrayList<String>();
        mylist.add("code");
        mylist.add("quiz");
        mylist.add("geeksforgeeks");
        mylist.add("quiz");
        mylist.add("practice");
        mylist.add("qa");
        System.out.println("Original List : \n" + mylist);

        Collections.shuffle(mylist);

        System.out.println("\nShuffled List : \n" + mylist);
    }
}
