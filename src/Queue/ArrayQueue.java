package Queue;
/*
This is a circular array queue which is of fixed length
 */
public class ArrayQueue<E> implements Queue<E> {
    private E[] list;
    private int maxCapacity = 11, rear = 0, front = 0, currSize = 0;
    public ArrayQueue()
    {
        list = (E[]) new Object[maxCapacity];
    }


    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean isEmpty() {
        if(currSize > 0)
        {
            return true;
        }
        else return false;
    }

    @Override
    public E first() {
        return list[front];
    }

    @Override
    public void enqueue(E n) throws IllegalStateException {
        if(currSize == maxCapacity) throw new IllegalStateException("Queue is full");
        list[rear] = n;
        rear = (rear + 1)%maxCapacity;
        currSize++;
    }

    @Override
    public E dequeue() {
        if(currSize == 0)
        {
            return null;
        }
        E e = list[front];
        front = (front +1)%maxCapacity;
        currSize--;
        return e;
    }
    public void printQueue()
    {
        System.out.println("     -----Queue size: " + size() + "-----");
        System.out.println("-----This is the top of the Queue-----");
        int i = 0;
        for(E e:list)
        {
            System.out.println("Queue Pos:" + i + "  Value: " + e);
            i++;
        }
        System.out.println("-----This is the bottom of the Queue-----");
    }

}
