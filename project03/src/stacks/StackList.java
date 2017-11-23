package stacks;

import java.util.Iterator;
/**
 * One object of class StackList is a stack made of a singly linked list which
 * adds and removes a generic object from one end of the list
 *
 * @author Kenneth Wu
 * class for singly linked list
 *
 * @param <T>
 */
public class StackList<T> implements Iterable<T>
{
    protected int stackSize;
    protected String name;
    protected Node top;

    /**
     * constructor for class StackList
     * @param newData
     * the new data will be stored by node top
     * @param name
     * the new Stack's name
     */
    public StackList(T newData, String name)
    {
        this.name = name;
        if(newData == null)
        {
            top = null;
            stackSize = 0;
        }
        else
        {
            top = new Node(newData);
            stackSize = 1;
        }
    }

    /**
     * constructor made for empty StackList
     * @param name
     * the new Stack's name
     */
    public StackList(String name)
    {
        this(null,name);
    }

    /**
     * For adding new item into stack
     * @param newItem
     * point to specified item that we want to be pushed
     */
    public void push(T newItem)
    {
        if(newItem == "" || newItem == null)
            return;

        Node tempNode = new Node(newItem);
        tempNode.next = top;
        top = tempNode;
        stackSize++;
    }

    /**
     * Removing the top item from the stack
     * @return
     * second top object from the stack.
     */
    public T pop()
    {
        if(isEmpty())
            return null;

        Node tempNode;
        tempNode = top;
        top = top.next;
        stackSize--;
        return tempNode.getData();
    }

    /**
     * Peeks top of stack
     * @return
     * top object's data
     */
    public T peek()
    {
        if (top == null)
            return null;
        return top.getData();
    }


    /**
     * Checking if the stack is empty
     * @return
     * return boolean depending whether stack is empty
     */
    public boolean isEmpty()
    {
        if(stackSize == 0)
            return true;
        return false;
    }


    /**
     * To get total size of stack
     * @return
     * the stackSize
     */
    public int size()
    {
        return stackSize;
    }


    /**
     * used for showing the StackList
     * @return
     * the returned string
     */
    public String toString()
    {
        String returnString = name + " with " + stackSize + " links: \n" + "[";
        StackIterator iterator = new StackIterator();

        while(iterator.hasNext())
        {
            returnString += iterator.next();
            if(iterator.hasNext())
                returnString += ", ";
        }
        returnString += " ]";
        return returnString;
    }


    @Override
    public Iterator<T> iterator()
    {
        return new StackIterator();
    }






    /**
     * An inner class that is used for storing each items from the stack
     * @author Kenneth Wu
     *
     */
    class Node
    {
        private T data;
        private Node next;
        /**
         * complete constructor for Node
         * @param newData
         *  new data that we want to keep in the node
         */
        public Node(T newData)
        {
            this.data = newData;
            next = null;
        }


        /**
         * Used for accessing data
         * @return
         * returning the data of the node
         */
        public T getData()
        {
            return data;
        }
    }


    /**
     * Iterator for the stack
     * @author Kenneth Wu
     *
     */
    private class StackIterator implements Iterator<T>
    {
        private int currentIndex;
        private Node currentNode;
        protected Node lastNodeReturned = null;


        /**
         * Constructor for StackIterator's objects
         */
        StackIterator()
        {
            currentNode = top;
            currentIndex = 0;
        }


        @Override
        public boolean hasNext()
        {
            if(currentIndex < stackSize)
                return true;
            else
                return false;
        }


        @Override
        public T next()
        {
            if( !hasNext() )
                throw new java.util.NoSuchElementException();

            lastNodeReturned = currentNode;
            currentNode = currentNode.next;
            currentIndex++;

            return lastNodeReturned.getData();
        }
    }
}

