package hashTables;

/**
 * @author Kenneth Wu
 * used for checking if the array is occupied or not
 * @param <E>
 * any object
 */


//HashEntry class for use by FHhashQP -----------------------
public class HashEntry<E>

{
    public E data;
    public int state;


    public HashEntry( E x, int st )
    {
        data = x;
        state = st;
    }


    public HashEntry()
    {
        this(null, FHhashQP.EMPTY);
    }
}
