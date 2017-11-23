package hashTables;
import java.util.ArrayList;
import cs1c.SongEntry;



class SongsCompGenre implements Comparable<String>
{
    String songsGenre;
    ArrayList<SongEntry> songList;


    /**
     * complete constructor for the class
     * @param genre
     * any kind of string object
     */
    public SongsCompGenre(String genre)

    {

        songsGenre = genre;

        songList = new ArrayList<SongEntry>();

    }



    /**
     * method to find if one value equal another
     * @param other
     * another object
     * @return
     * return boolean value based on the passing parameter
     *
     *
     */
    public boolean equals(SongsCompGenre other)

    {

        return this.songsGenre.equals(other.songsGenre);

    }



    /**
     * method to find if one values equal another
     */
    // we'll use compareTo() to implement our find on key
    public int compareTo(String otherGenre)
    {
        return songsGenre.compareTo(otherGenre);
    }


    /**
     * method to find songsgenre hash size
     */
    public int hashCode()
    {
        return songsGenre.hashCode();
    }



    /**
     * used to add new song to the list
     * @param e
     * new song
     */
    public void addSong( SongEntry e)
    {
        if(e.getGenre().equals(songsGenre) && !songList.contains(e))
            songList.add(e);
    }



    /**
     * method to find song's name
     * @return
     * song's name
     *
     */
    public String getName()
    {
        // TODO Auto-generated method stub
        return songsGenre;
    }



    /**
     * method to find songlist data
     * @return
     * the data of the song list
     */
    public ArrayList<SongEntry> getData()
    {
        // TODO Auto-generated method stub
        return songList;
    }

}
