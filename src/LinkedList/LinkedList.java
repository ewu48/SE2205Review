package LinkedList;

import java.util.ArrayList;
import java.util.Stack;

public class LinkedList<E> {
    LinkedList list;
    private Node<E> head, tail;
    private int size;
    private static class Node<E>
    {
        E element;
        private Node<E> next = null;
        public Node(E e, Node<E> n)
        {
            element = e;
            next = n;
        }
        public E getElement()
        {
            return  element;
        }
        public Node<E> getNext()
        {
            return next;
        }
        public void setNext(Node<E> n)
        {
            next = n;
        }
    }
    public LinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }
    public void addFirst(E element)
    {
        head = new Node<>(element, head);
        if(isEmpty())
        {
            tail = head;
        }
        size++;
    }
    public E removeFirst()
    {
        if(isEmpty())
        {
            return null;
        }
        E element = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0)
        {
            tail = null;
        }
        return element;
    }
    public E removeLast()
    {
        if(isEmpty())
        {
            return null;
        }
        E element = tail.getElement();
        Node<E> tmp = getNodeAt(size-2);
        tmp.next = null;
        tail = tmp;
        size--;
        return element;

    }
    public int size()
    {
        return size;
    }
    public boolean isEmpty()
    {
        if(size == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Node<E> getNodeAt(int p)
    {
        Node<E> node = head;
        if (p < size)
        {
            for(int i = 0; i < p; i++)
            {
                node = node.getNext();
            }
        }

        return node;
    }

    public E getValueAt(int p)
    {
        return getNodeAt(p).getElement();
    }

    public void insertAt(int p, E e)
    {
        size++;
        Node<E> prev = getNodeAt(p);
        Node<E> post = prev.next;
        Node<E> new_node = new Node<E>(e,post);
        prev.next = new_node;

    }
    public Node<E> removeAt(int p)
    {
        if(p>1)
        {
            Node<E> node = getNodeAt(p);
            Node<E> prev = getNodeAt(p-1);
            Node<E> next = getNodeAt(p+1);
            prev.next = next;
            size--;
            return node;
        }
        else return null;
    }
        public void printList()
        {
        Stack<E> content = new Stack<>();
        Node<E> node = head;
        while(node != null)
        {
            content.push(node.getElement());
            node = node.getNext();
        }
        int counter = 1;
        while(!content.isEmpty())
        {
            System.out.print("Node:" + (size - counter) + "   Value:");
            System.out.print( content.pop());
            System.out.println();
            counter++;
        }
        }

        public Stack<E> exportAsStack()
        {
            Stack<E> stack = new Stack<>();
            Node<E> node = head;
            while(node != null)
            {
                stack.push(node.getElement());
                node = node.getNext();
            }
            return stack;
        }


}
