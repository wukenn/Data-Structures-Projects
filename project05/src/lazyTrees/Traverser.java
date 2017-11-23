package lazyTrees;

/**
 * class used for visiting every nodes
 * @author Kenneth Wu
 *
 * @param <E>
 * any node in the tree
 */
public interface Traverser<E>
{
    public void visit(E x);
}