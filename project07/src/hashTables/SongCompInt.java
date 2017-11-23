package hashTables;
import cs1c.SongEntry;


public class SongCompInt implements Comparable<Integer>
{
    SongEntry data;
    /**
     * complete constructor
     * @param e
     * any kind of int object
     */
    public SongCompInt(SongEntry e){ data = e; }


    /**
     * make an output for the data
     */
    public String toString() { return data.toString(); }
    /**
     * used to compare ID to the key
     */
    // we'll use compareTo() to implement our find on key
    public int compareTo(Integer key)
    {
        return data.getID() - key;
    }



    /**
     * Compare one data to another
     * @param rhs
     * another data
     * @return
     * return boolean value based on the passing parameter
     */
    // let equals() preserve the equals() provided by embedded data
    public boolean equals( SongCompInt rhs )
    {
        return data.equals(rhs.data);
    }



    /**
     * method to find the data ID
     */
    public int hashCode()

    {

        return data.getID();

    }

}
