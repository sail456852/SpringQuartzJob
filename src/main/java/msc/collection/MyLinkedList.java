package msc.collection;

import java.util.*;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 1/6/19<br/>
 * Time: 6:06 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class MyLinkedList<E> extends AbstractList<E> {

    private Node first;
    private Node last;
    private int size = 0;

    private class Node<E> {
        private E element;
        private Node last;
        private Node next;

        public Node(Node last, E element,  Node next) {
            this.element = element;
            this.last = last;
            this.next = next;
        }
    }

    @Override
    public E get(int index) {
        System.err.println("MyLinkedList.get" + index);
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean add(E e){
        linkLast(e);
        return true;
    }

    private void linkLast(E e) {
        Node<E> l = last; // when first comes in
        Node<E> newNode = new Node<E>(l, e, null);
        if(l == null) first = newNode;
        else  l.next = newNode;
        size++;
    }


    public static void main(String[] args) {
        MyLinkedList<String> ml = new MyLinkedList<>();
        ml.add("nameone");
        ml.add("nametwo");
        ml.add("three");

        for (String s : ml) {
            System.err.println("s = " + s);
        }
    }
}


