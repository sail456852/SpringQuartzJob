package msc.collection;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/7<br/>
 * Time: 14:55<br/>
 * To change this template use File | Settings | File Templates.
 */
public class MyLinkedList<E> {


    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

//    public Iterator<E> iterator() {
//        return new Itr();
//    }

//    public void loop(){
//        if(first == null) return;
//        for (int i = 0; i < size ; i++) {
//           Node l = first.next;
//           if(l == null) return;
//           Node n = l.next;
//        }
//    }


    public static void main(String[] args) {
        MyLinkedList ml = new MyLinkedList();
        ml.add("String1");
        ml.add("String2");
        ml.add("String3");
        ml.add("String4");


//        for (Object o : ml) {
//            System.err.println("o = " + o);
//        }
    }
}
