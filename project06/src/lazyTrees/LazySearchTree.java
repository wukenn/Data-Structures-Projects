package lazyTrees;

import java.util.NoSuchElementException;

/**
 * Class that implements Lazy Deletion
 * @author Kenneth Wu
 *
 * @param <E>
 * any objects E
 */
public class LazySearchTree<E extends Comparable< ? super E > >
        implements Cloneable
{
    protected int mSizeHard;
    protected int mSize;
    protected LazySTNode mRoot;

    /**
     * incomplete constructor
     */
    public LazySearchTree()
    {
        clear();
    }



    /**
     * accessor for soft size of tree
     * @return
     * return soft size of the tree
     */
    public boolean empty()
    {
        return (mSize == 0);
    }


    /**
     * accessor for soft size of tree
     * @return
     * return soft size of the tree
     */
    public int size()
    {
        return mSize;
    }


    /**
     * accessor for hard size of tree
     * @return
     * return the hard size of the tree
     */
    public int sizeHard()
    {
        return mSizeHard;
    }

    /**
     * used for clearing the size of the tree
     */
    public void clear()
    {
        mSizeHard = 0;
        mSize = 0;
        mRoot = null;
    }

    /**
     * used for looking for the minimum value of the tree
     * @return
     * return the minimum data value
     */
    public E findMin()
    {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    /**
     * used for looking for the maximum value of the tree
     * @return
     * return the maximum data value
     */
    public E findMax()
    {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }

    /**
     * used for looking a node on the tree
     * @param x
     * any node
     * @return
     * return the node data
     */
    public E find( E x )
    {
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    /**
     * used for checking if the tree contains node x
     * @param x
     * any node
     * @return
     * return boolean value based on the passing condition
     */
    public boolean contains(E x)
    { return find(mRoot, x) != null; }

    /**
     * used for inserting node into the tree
     * @param x
     * any node
     * @return
     * return boolean value based on the passing condition
     */
    public boolean insert( E x )
    {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }


    /**
     * used for removing node into the tree
     * @param x
     * any node
     * @return
     * return boolean value based on the passing condition
     */
    public boolean remove( E x )
    {
        int oldSize = mSize;
        remove(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * Allows client to truly remove all deleted notes
     * @return
     */
    public boolean collectGarbage()
    {
        int oldSize = mSizeHard;
        collectGarbage(mRoot);
        return (oldSize != mSizeHard);
    }

    /**
     * used for traversing all the nodes in the trees
     * both hard and soft
     * @param func
     * functor class
     */
    public < F extends Traverser<? super E > >
    void traverseHard(F func)
    {
        traverseHard(func, mRoot);
    }

    /**
     * used for traversing only soft nodes in the trees
     * @param func
     * functor class
     */
    public <F extends Traverser<? super E >>
    void traverseSoft(F func)
    {
        traverseSoft(func, mRoot);
    }

    /**
     * used for cloning the tree
     */
    public Object clone() throws CloneNotSupportedException
    {
        LazySearchTree<E> newObject = (LazySearchTree<E>)super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;
        newObject.mSizeHard = mSizeHard;

        return newObject;
    }

    // private helper methods ----------------------------------------
    /**
     * helps public findmin to find the minimum value in the tree
     * @param root
     * any node in the tree
     * @return
     * minimum value of the tree
     */

    protected LazySTNode findMin( LazySTNode root )

    {
        if (root == null)
            return null;
        LazySTNode nodeTemp;
        if((nodeTemp = findMin(root.lftChild)) != null)
            return nodeTemp;
        if(root.deleted != true)
            return root;
        if((nodeTemp = findMin(root.rtChild)) != null)
            return nodeTemp;
        return null;

    }

    /**
     * helper method for removeHard
     * @param root
     * @return
     * Hard minimum value of tree
     */
    protected LazySTNode findMinHard( LazySTNode root)
    {
        if (root == null)
            return null;
        if (root.lftChild == null)
            return root;
        return findMinHard(root.lftChild);
    }

    /**
     * helps public findmax to find the maximum value in the tree
     * @param root
     * any node in the tree
     * @return
     * the maximum value of the tree
     */

    protected LazySTNode findMax( LazySTNode root )

    {
        if (root == null)
            return null;

        LazySTNode nodeTemp;
        if((nodeTemp = findMax(root.rtChild)) != null)
            return nodeTemp;
        if(root.deleted != true)
            return root;
        if((nodeTemp = findMax(root.lftChild)) != null)
            return nodeTemp;
        return null;

    }


    /**
     * helper method for removeHard
     * @param root
     * @return
     * Hard maximum value of tree
     */
    protected LazySTNode findMaxHard( LazySTNode root)
    {
        if (root == null)
            return null;
        if (root.rtChild == null)
            return root;
        return findMax(root.rtChild);
    }

    /**
     * helps public insert for inserting node in the tree
     * @param root
     * any node in the tree
     * @param x
     * new Node
     * @return
     * give new node a new root based on the passing condition
     */
    protected LazySTNode insert( LazySTNode root, E x)
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
        {
            mSizeHard++;
            mSize++;
            return new LazySTNode(x, null, null, false);
        }

        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            root.lftChild = insert(root.lftChild, x);
        else if ( compareResult > 0 )
            root.rtChild = insert(root.rtChild, x);
        else
        {
            if(root.deleted == true)
            {
                root.deleted = false;
                mSize++;
            }
        }
        return root;
    }

    /**
     * help public remove to remove a node
     * @param root
     * any node in the tree
     * @param x
     * node that is wanted to be removed
     */
    protected void remove( LazySTNode root, E x)
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return;

        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            remove(root.lftChild, x);
        else if ( compareResult > 0 )
            remove(root.rtChild, x);

            //found the node
        else
        {
            root.deleted = true;
            mSize--;
        }
    }


    /**
     *hard removal of a node in the tree
     * @param root
     * any node in the tree
     * @param x
     * node that is going to be hard removed
     * @return
     * a new root based on passing conditions
     */
    protected LazySTNode removeHard( LazySTNode root, E x)

    {
        int compareResult;
        LazySTNode nodeTemp;
        if(root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0 )
            root.lftChild = removeHard(root.lftChild, x);
        else if (compareResult > 0 )
            root.rtChild = removeHard(root.rtChild, x);



            // found the node
        else if (root.lftChild != null && root.rtChild != null)
        {
            nodeTemp = findMinHard(root.rtChild);
            root.deleted = nodeTemp.deleted;
            root.data = nodeTemp.data;
            root.rtChild = removeHard(root.rtChild, root.data);
        }


        else
        {
            root = (root.lftChild != null)? root.lftChild : root.rtChild;
            mSizeHard--;
        }

        return root;
    }


    /**
     * helper method for traversehard
     * @param func
     * functor class
     * @param treeNode
     * node in the tree
     */
    protected <F extends Traverser<? super E>>
    void traverseHard(F func, LazySTNode treeNode)
    {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }


    /**
     * A helper method for public collectGarbage
     * @param root
     */
    protected void collectGarbage(LazySTNode root)
    {
        if(root == null)
            return;
        collectGarbage(root.lftChild);

        if(root.deleted)
            removeHard(mRoot, root.data);

        collectGarbage(root.rtChild);
    }

    /**
     * helper method for traversesoft
     * @param func
     * functor class
     * @param treeNode
     * any node in the tree
     */
    protected <F extends Traverser<? super E>>
    void traverseSoft(F func, LazySTNode treeNode)
    {
        if (treeNode == null)
            return;

        traverseSoft(func, treeNode.lftChild);
        if(treeNode.deleted == false)
            func.visit(treeNode.data);
        traverseSoft(func, treeNode.rtChild);
    }

    /**
     * helper method for find
     * @param root
     * any node in the tree
     * @param x
     * desired node
     * @return
     * return node if it's in the tree
     */
    protected LazySTNode find( LazySTNode root, E x)
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return find(root.lftChild, x);
        if (compareResult > 0)
            return find(root.rtChild, x);
        if (compareResult == 0 && root.deleted == true)
            return null;
        return root;   // found
    }

    /**
     * helper method for cloningsubtree
     * @param root
     * any node in the tree
     * @return
     * new node in the tree
     */
    protected LazySTNode cloneSubtree(LazySTNode root)
    {
        LazySTNode newNode;
        if (root == null || root.deleted == true)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode
                (
                        root.data,
                        cloneSubtree(root.lftChild),
                        cloneSubtree(root.rtChild),
                        true
                );
        return newNode;
    }


    /**
     * helper method to findheight
     * @param treeNode
     * any node in the tree
     * @param height
     * height of the tree
     * @return
     * return the height of the tree
     */
    protected int findHeight( LazySTNode treeNode, int height)
    {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight)? leftHeight : rightHeight;
    }

    //inner class
    /**
     * Inner class for LazySearchTree
     * @author Kenneth Wu
     */
    class LazySTNode
    {
        // use public access so the tree or other classes can access members
        public LazySTNode lftChild, rtChild;
        public E data;
        public LazySTNode myRoot;  // needed to test for certain error
        public boolean deleted;

        /**
         * complete parameter
         * @param x
         * new node
         * @param lft
         * left hand of the node
         * @param rt
         * right hand of the node
         * @param del
         * boolean value of the node
         */
        public LazySTNode( E x, LazySTNode lft, LazySTNode rt, boolean del )
        {
            lftChild = lft;
            rtChild = rt;
            data = x;
            deleted = del;
        }

        /**
         * incomplete parameter
         */
        public LazySTNode()
        {
            this(null, null, null, true);
        }
    }
}
