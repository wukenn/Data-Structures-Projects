package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Kenneth Wu
 * queue class for singly linked list
 * @param <Type>
 * any object type
 */
public class Queue<Type> implements Iterable<Type>
{
    protected String name;
    protected Node head;
    protected Node tail;
    protected int queueSize;

    /**
     * Constructor for class Queue that accepts 1 parameter
     * @param dataName
     * queue's name
     */
    public Queue(String dataName)
    {
        name = dataName;
        head = null;
        tail = null;
        queueSize = 0;
    }

    /**
     * complete constructor for class Queue
     * @param newData
     * new data that will get into the queue
     * @param dataName
     * queue's name
     */
    public Queue(Type newData, String dataName)
    {
        name = dataName;
        if(newData == null)
        {
            head = null;
            queueSize = 0;
        }
        else
        {
            head = new Node(newData);
            queueSize = 1;
        }
    }


    /**
     * inserting newData into top of queue
     * @param newData
     * the newData that will be inserted
     * @return
     * boolean value based on the condition
     */
    public boolean enqueue(Type newData)
    {
        if(newData == null)
            return false;

        if(isEmpty())
        {
            tail = new Node(newData);
            head = tail;
        }
        else
        {
            tail.next = new Node(newData);
            tail = tail.next;
        }

        queueSize++;
        return true;
    }


    /**
     * removes item from the bottom of the queue
     * @return
     * return bottom queue's data
     * @throws NoSuchElementException
     */
    public Type dequeue() throws NoSuchElementException
    {
        Node headTemp = head;
        if(!isEmpty())
        {
            head = head.next;
            headTemp.next = null;
        }
        else
            throw new NoSuchElementException();

        if(head == null && tail != null)
            tail = null;

        queueSize--;
        return headTemp.getData();
    }


    /**
     * To Check the queue's top data
     * @return
     * return top data
     */
    public Type peek()
    {
        if (head == null)
            return null;
        return head.getData();
    }


    /**
     * To check if the queue is empty
     * @return
     * return true if queue size is empty and false if it isn't
     */
    public boolean isEmpty()
    {
        if(queueSize == 0)
            return true;
        return false;
    }


    /**
     * To show queue objects
     */
    public String toString()
    {
        String returnString = name + ": \n[ ";
        QueueIterator iterator = new QueueIterator();

        while(iterator.hasNext())
        {
            returnString += iterator.next();
            if(iterator.hasNext())
                returnString += ", ";
        }
        returnString += " ]";
        return returnString;
    }


    /**
     * Accessor name of the data
     * @return
     * return name of the data
     */
    public Object getName()
    {
        return name;
    }

    /**
     * Accessor size of the queue
     * @return
     * size of the queue
     */
    public int size()
    {
        return queueSize;
    }



    @Override
    public Iterator<Type> iterator()
    {
        // TypeODO Auto-generated method stub
        return new QueueIterator();
    }


    /**
     * An inner class called node to store the object's data
     * @author Kenneth Wu
     *
     */
    class Node
    {
        private Type data;
        private Node next;
        /**
         * Constructor for Node
         * @param newData
         *  new data that we want to keep in the node
         */
        public Node(Type newData)
        {
            this.data = newData;
            next = null;
        }

        /**
         * Used for accessing data
         * @return
         * returning the data of the node
         */
        public Type getData() { return data; }

    }

    /**
     * Iterator for Queue
     * @author Kenneth Wu
     *
     */
    private class QueueIterator implements Iterator<Type>
    {
        private int currentIndex;
        private Node currentNode;
        protected Node lastNodeReturned = null;

        /**
         * Constructor for StackIterator's objects
         */
        QueueIterator()
        {
            currentNode = head;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext()
        {
            if(currentIndex < queueSize)
                return true;
            else
                return false;
        }


        @Override
        public Type next()
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