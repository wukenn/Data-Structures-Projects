package hashTables;


// FHhashQP class --------------------------------------------

public class FHhashQP<E>
{
    protected static final int ACTIVE = 0;
    protected static final int EMPTY = 1;
    protected static final int DELETED = 2;


    static final int INIT_TABLE_SIZE = 97;
    static final double INIT_MAX_LAMBDA = 0.49;


    protected HashEntry<E>[] mArray;
    protected int mSize;
    protected int mLoadSize;
    protected int mTableSize;
    protected double mMaxLambda;



    /**
     * set the table size
     * @param tableSize
     * desired table size
     */
    // public methods ---------------------------------
    public FHhashQP(int tableSize)
    {
        mLoadSize = mSize = 0;
        if (tableSize < INIT_TABLE_SIZE)
            mTableSize = INIT_TABLE_SIZE;
        else
            mTableSize = nextPrime(tableSize);

        allocateArray();  // uses mTableSize;
        mMaxLambda = INIT_MAX_LAMBDA;
    }



    /**
     * initial table size
     */
    public FHhashQP()
    {
        this(INIT_TABLE_SIZE);
    }


    /**
     * adding object to array
     * @param x
     * desired object to be inserted
     * @return
     * array with added object
     */

    public boolean insert( E x)
    {
        int bucket = findPos(x);

        if ( mArray[bucket].state == ACTIVE )
            return false;


        mArray[bucket].data = x;
        mArray[bucket].state = ACTIVE;
        mSize++;

        // check load factor
        if( ++mLoadSize > mMaxLambda * mTableSize )
            rehash();

        return true;
    }



    /**
     * remove object from array
     * @param x
     * desired object to be removed
     * @return
     * array with removed object
     */
    public boolean remove( E x )
    {
        int bucket = findPos(x);


        if ( mArray[bucket].state != ACTIVE )
            return false;


        mArray[bucket].state = DELETED;
        mSize--; // mLoadSize not dec'd because it counts any non-EMP location
        return true;
    }


    /**
     * find object in array
     * @param x
     * desired object to be found
     * @return
     * boolean value based on the passing condition
     */
    public boolean contains(E x )
    {
        return mArray[findPos(x)].state == ACTIVE;
    }


    /**
     * @return
     * size of the array
     */
    public int size()  { return mSize; }


    /**
     * method to empty the array
     */
    void makeEmpty()
    {
        int k, size = mArray.length;

        for(k = 0; k < size; k++)
            mArray[k].state = EMPTY;
        mSize = mLoadSize = 0;
    }



    /**
     * set upper limit on the table's load factor
     * @param lam
     * upper limit
     * @return
     * the upper limit for the table's load factor
     */
    public boolean setMaxLambda( double lam )
    {
        if (lam < .1 || lam > INIT_MAX_LAMBDA )
            return false;
        mMaxLambda = lam;
        return true;
    }



    // protected methods of class ----------------------
    /**
     * used to find position of object
     * @param x
     * desired object to be positioned
     * @return
     * return object
     */
    int findPos( E x )
    {
        int kthOddNum = 1;
        int index = myHash(x);


        while ( mArray[index].state != EMPTY
                && !mArray[index].data.equals(x) )
        {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if ( index >= mTableSize )
                index -= mTableSize;
        }
        return index;

    }



    /**
     * recalculate hash code
     */
    protected void rehash()
    {

        // we save old list and size then we can reallocate freely
        HashEntry<E>[] oldArray = mArray;
        int k, oldTableSize = mTableSize;;


        mTableSize = nextPrime(2*oldTableSize);


        // allocate a larger, empty array
        allocateArray();  // uses mTableSize;


        // use the insert() algorithm to re-enter old data
        mSize = mLoadSize = 0;
        for(k = 0; k < oldTableSize; k++)
            if (oldArray[k].state == ACTIVE)
                insert( oldArray[k].data );
    }



    /**
     * calculate hash code for particular key
     * @param x
     * key
     * @return
     * hash value
     */
    protected int myHash(E x)
    {
        int hashVal;


        hashVal = x.hashCode() % mTableSize;
        if(hashVal < 0)
            hashVal += mTableSize;


        return hashVal;
    }



    /**
     * method to find the next closest prime number
     * @param n
     * prime number
     * @return
     * closest prime number
     */
    protected static int nextPrime(int n)
    {
        int k, candidate, loopLim;


        // loop doesn't work for 2 or 3
        if (n <= 2 )
            return 2;
        else if (n == 3)
            return 3;


        for (candidate = (n%2 == 0)? n+1 : n ; true ; candidate += 2)
        {
            // all primes > 3 are of the form 6k +/- 1
            loopLim = (int)( (Math.sqrt((double)candidate) + 1)/6 );


            // we know it is odd.  check for divisibility by 3
            if (candidate%3 == 0)
                continue;

            // now we can check for divisibility of 6k +/- 1 up to sqrt
            for (k = 1; k <= loopLim; k++)

            {
                if (candidate % (6*k - 1) == 0)
                    break;
                if (candidate % (6*k + 1) == 0)
                    break;
            }


            if (k > loopLim)
                return candidate;
        }

    }



    /**
     * Calculate array size
     */
    void allocateArray()
    {
        int k;

        mArray = new HashEntry[mTableSize];
        for (k = 0; k < mTableSize; k++)
            mArray[k] = new HashEntry<E>();
    }

}
