package tij.collection;

//: innerclasses/Sequence.java
// Holds a sequence of Objects.

import java.util.LinkedList;
import java.util.List;

interface Selector {
    boolean end();
    Object current();
    void next();
}

public class Sequence {
    private List<Object> items;
    private int next = 0;
//    public Sequence(int size) { items = new Object[size]; }
    public Sequence(){
        items = new LinkedList<Object>();
    }
    public void add(Object x) {
//        if(next < items.size())
//            items[next++] = x;
            items.add(next++, x);
    }
    private class SequenceSelector implements Selector {
        private int i = 0;
        public boolean end() { return i == items.size(); }
        public Object current() { return items.get(i); }
        public void next() { if(i < items.size()) i++; }
    }
    public Selector selector() {
        return new SequenceSelector();
    }
    public static void main(String[] args) {
//        Sequence sequence = new Sequence(10);
        Sequence sequence = new Sequence();
        for(int i = 0; i < 19; i++)
            sequence.add(Integer.toString(i));
        Selector selector = sequence.selector();
        while(!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}

