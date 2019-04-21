import Queue.ArrayQueue;
import Queue.LinkedListQueue;

public class Main {
    public static void main(String args[])
    {
        LinkedListQueue<Integer> Q = new LinkedListQueue<Integer>();
        Q.enqueue(2);
        Q.enqueue(3);
        Q.printList();
        System.out.println(Q.dequeue());





    }
}
