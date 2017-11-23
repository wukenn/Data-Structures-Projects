package hashTables;

public class FHhashQPwFind<KeyType, E extends Comparable<KeyType> >
        extends FHhashQP<E>
{
    String name;

    /**
     * method to look
     * @param key
     * user's desired key for hashing
     * @return
     * return the found object/exception based on the passing condition
     */
    public E find(KeyType key)
    {
        if( mArray[findPosKey(key)].state == ACTIVE )
            return mArray[findPosKey(key)].data;

        throw new java.util.NoSuchElementException();
    }

    /**
     * method that use key to hash
     * @param key
     * user's desired key for hashing
     * @return
     * hashed key
     */
    private int myHashKey (KeyType key)
    {
        int hashKey;

        hashKey = key.hashCode() % mTableSize;
        if(hashKey < 0)
            hashKey += mTableSize;

        return hashKey;
    }


    /**
     * method to find the position of the key
     * @param key
     * intended key
     * @return
     * position of the key
     */
    private int findPosKey( KeyType key)
    {
        int kthOddNum = 1;
        int index = myHashKey(key);

        while ( mArray[index].state != EMPTY
                && mArray[index].data.compareTo(key) != 0 )
        {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if ( index >= mTableSize )
                index -= mTableSize;
        }
        return index;
    }

    /**
     * return name of the data
     * @return
     * name of the data
     */
    public String getName()
    {
        // TODO Auto-generated method stub
        return name ;
    }
}