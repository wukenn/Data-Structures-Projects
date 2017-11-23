package lazyTrees;

/**
 * Class used to print the tree
 * @author Kenneth Wu
 *
 * @param <E>
 * any Node in tree
 */
public class PrintObject<E> implements Traverser<E>
{
    public void visit(E x)
    {
        System.out.print( x + " ");
    }
};