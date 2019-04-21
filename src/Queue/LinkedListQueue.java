package Queue;

import LinkedList.LinkedList;

import java.util.Stack;


public class LinkedListQueue<E> extends LinkedList<E> implements Queue<E> {
    @Override
    public E first() {
        return getValueAt(0);
    }

    @Override
    public void enqueue(E n) {
        addFirst(n);
    }

    @Override
    public E dequeue() {
        return removeLast();
    }
    @Override
    public void printList()
    {
        Stack<E> content = exportAsStack();
        int qval = 0;
        System.out.println("     -----Queue size: " + size() + "-----");
        System.out.println("-----This is the top of the Queue-----");
        while (!content.isEmpty())
        {
            System.out.print("Queue Pos: " + qval);
            System.out.print("  ");
            System.out.print("Value: " + content.pop());
            System.out.print("\n");
            qval++;
        }
        System.out.println("-----This is the bottom of the Queue-----");


    }

}
