package hashTables;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import cs1c.SongEntry;


public class TableGenerator
{
    protected FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;
    protected FHhashQPwFind<String, SongsCompGenre> tableOfGenres;
    protected ArrayList<String> genreNames = new ArrayList<String>();


    /**
     * complete constructor for tableGenerator
     */
    public TableGenerator()
    {
        tableOfSongIDs = new FHhashQPwFind<Integer, SongCompInt>();
        tableOfGenres = new FHhashQPwFind<String, SongsCompGenre>();
        genreNames = new ArrayList<String>();
    }


    /**
     * used for populating tableOfSongIDs
     * @param allSongs
     * array that has all of the songs in the library
     * @return
     * return populated tableofsongIDs
     */
    protected FHhashQPwFind<Integer,
            SongCompInt> populateIDtable(SongEntry[] allSongs)
    {
        // TODO Auto-generated method stub
        for(int k = 0; k < allSongs.length; k++)
            tableOfSongIDs.insert(new SongCompInt(allSongs[k]));
        return tableOfSongIDs;
    }



    /**
     * used for populating tableofGenres
     * Sort genres to each destinated array
     * @param allSongs
     * array that has all of the songs in the library
     * @return
     * return populated tableofgenres
     */
    protected FHhashQPwFind<String, SongsCompGenre>
    populateGenreTable(SongEntry[] allSongs)
    {
        // TODO Auto-generated method stub
        for(int k = 0; k < allSongs.length; k++)
        {
            SongsCompGenre newGenre = new SongsCompGenre(allSongs[k].getGenre());
            try
            {
                tableOfGenres.find(newGenre.getName()).addSong(allSongs[k]);
            }


            catch(NoSuchElementException e)
            {
                genreNames.add(newGenre.getName());
                tableOfGenres.insert(newGenre);
                tableOfGenres.find(newGenre.getName()).addSong(allSongs[k]);
            }
        }
        return tableOfGenres;
    }


    /**
     * return arraylist with same genre
     * @return
     * arraylist with same genre
     */
    public ArrayList<String> getGenreNames()
    {
        return genreNames;
    }

}
