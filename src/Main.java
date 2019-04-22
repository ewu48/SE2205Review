import Tree.AbstractBinaryTrees;

public class Main {
    public static void main(String args[])
    {
        AbstractBinaryTrees<Integer> Tree = new AbstractBinaryTrees<>();
        Tree.addRoot(0);
        Tree.addLeft(Tree.root,1);
        Tree.addRight(Tree.root,2);
        System.out.println(Tree.size());
        Tree.printTree();


    }
}
