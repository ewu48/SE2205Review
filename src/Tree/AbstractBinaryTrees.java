package Tree;

import LinkedList.LinkedList;
import Queue.LinkedListQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbstractBinaryTrees<E> implements TreeADT<E> {

    public Node<E> root;
    public AbstractBinaryTrees()
    {

    }
    private int size =0;
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            parent = p;
            left = l;
            right = r;
        }
        public Node() {

        }

        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
            if (!(p instanceof Node)) {
                throw new IllegalArgumentException("Not valid position type");
            }
            Node<E> node = (Node<E>) p;
            if (node.getParent() == node) {
                throw new IllegalArgumentException("p is no longer in the tree");
            }
            return node;
        }

    }
    public Node<E> createNode(E e, Node<E> p, Node<E> l, Node<E> r) {
        return new Node<E>(e, p, l, r);
    }

    public void printTree()
    {
        int height = height(root);
        for(int i = 1; i < height; i++)
        {
                        
        }



    }
    public E remove(Position<E> p)throws IllegalArgumentException
    {
        Node<E> node = root.validate(p);
        if(numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft():node.getRight());
        if(child != null)
        {
            child.setParent(node.getParent());
        }
        if(node == root)
        {
            root = child;
        }
        else
        {
            Node<E> parent = root.validate(node.getParent());
            if(parent.getRight() != null)
            {
                parent.setLeft(child);
            }
            else
            {
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(null);
        return temp;

    }


    public void attach(Position<E> p, AbstractBinaryTrees<E> t1, AbstractBinaryTrees<E> t2) throws IllegalArgumentException
    {
        Node<E> node = root.validate(p);
        if(isInternal(p)) throw new IllegalArgumentException("p is not a leaf node");
        size = size + t1.size() + t2.size();
        if(!t1.isEmpty())
        {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if(!t2.isEmpty())
        {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }


    public E set(Position<E> p, E e) throws IllegalArgumentException
    {
        Node<E> node = root.validate(p);
        E tempE = node.getElement();
        node.setElement(e);
        return tempE;
    }

    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(parent(p));
        }
    }

    public int height(Position<E> p) {
        int height = 0;
        if (isExternal(p)) {
            return 0;
        } else {
            for (Position<E> c : children(p)) {
                height = Math.max(height, height(c)) + 1;
            }
        }
        return height;
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = root.validate(p);
        return node.getLeft();
    }


    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = root.validate(p);
        return node.getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException
    {
        if(!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = new Node(e,null,null,null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException
    {
        Node<E> node = root.validate(p);
        if(node.getLeft()!=null) throw new IllegalArgumentException("p already has a left child");
        Node<E> leftChild = new Node(e,node,null,null);
        node.setLeft(leftChild);
        size++;
        return leftChild;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException
    {
        Node<E> node = root.validate(p);
        if(node.getRight()!=null) throw new IllegalArgumentException("p already has a right child");
        Node<E> rightChild = new Node(e,node,null,null);
        node.setRight(rightChild);
        size++;
        return rightChild;
    }


    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        if(isRoot(p))
        {
            return null;
        }
        Position<E> parent = parent(p);
        if(p == left(parent))
        {
            return right(parent);
        }
        else
        {
            return left(parent);
        }
    }


    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = root.validate(p);
        return node.getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> clist = new ArrayList<>(2);
        if(left(p) != null)
        {
            clist.add(left(p));
        }
        if(right(p) != null)
        {
            clist.add(right(p));
        }
        return clist;
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if(left(p) != null)
        {
            count++;
        }
        if(right(p) != null)
        {
            count++;
        }
        return count;
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preOrder();
    }


    //Types of Tree Traversal

    //Pre-Order Traversal - Visit root, children from left to right

     public Iterable<Position<E>> preOrder()
     {
         List<Position<E>> snapshot = new ArrayList<Position<E>>();
         if(!isEmpty()){
             preOrderST(root(),snapshot);
         }

         return snapshot;
     }
     private void preOrderST(Position<E> p, List<Position<E>> snap)
     {
         snap.add(p);
         for(Position<E> c: children(p))
         {
             preOrderST(c,snap);
         }
     }

     //Post-Order Traversal - Visit the children from left to right, then root

    public Iterable<Position<E>> postOrder()
    {
        List<Position<E>> snapshot = new ArrayList<Position<E>>();
        if(!isEmpty()){
            postOrderST(root(),snapshot);
        }
        return snapshot;
    }
    private void postOrderST(Position<E> p, List<Position<E>> snap)
    {
        for(Position<E> c: children(p))
        {
            preOrderST(c,snap);
        }
        snap.add(p);

    }

    //In-Order Traversal - Visit left side, root, right side

    public Iterable<Position<E>> inOrder()
    {
        List<Position<E>> snapshot = new ArrayList<Position<E>>();
        if(!isEmpty()){
            inOrderST(root(),snapshot);
        }
        return snapshot;

    }

    private void inOrderST(Position<E> p, List<Position<E>> snap)
    {
        Iterator<Position<E>> clist = children(p).iterator();
        if(clist.hasNext())
        {
            inOrderST(clist.next(),snap);
        }
        snap.add(p);
        if(clist.hasNext())
        {
            inOrderST(clist.next(),snap);
        }
    }

    //Level-Order Traversal - Root, level by level, left to right

    public Iterable<Position<E>> levelOrder()
    {
        List<Position<E>> snapshot = new ArrayList<Position<E>>();
        Position<E> p;
        if(!isEmpty())
        {
            LinkedListQueue<Position<E>> llQ = new LinkedListQueue<Position<E>>();
            llQ.enqueue(root());
            while(!llQ.isEmpty())
            {
                p = llQ.dequeue();
                for(Position<E> c: children(p))
                {
                    llQ.enqueue(c);
                }
                snapshot.add(p);
            }
        }
        return snapshot;
    }

    public class ElementIterator implements Iterator<E>
    {
        Iterator<Position<E>> posIt = positions().iterator();
        @Override
        public boolean hasNext() {
            return posIt.hasNext();
        }

        @Override
        public E next() {
            return posIt.next().getElement();
        }
        public void remove()
        {
            posIt.remove();
        }
    }
}