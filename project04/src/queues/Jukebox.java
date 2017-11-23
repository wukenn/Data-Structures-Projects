package queues;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs1c.SongEntry;
/**
 * A helper class that used for organizing the queue.
 * One object of Jukebox consist of favorite, lounge and roadtrip playlist.
 * @author Kenneth Wu
 *
 */
public class Jukebox
{

    private Queue<SongEntry> favoritePL;
    private Queue<SongEntry> loungePL;
    private Queue<SongEntry> roadTripPL;

    /**
     * A constructor for Jukebox class
     */
    Jukebox()
    {
        favoritePL = new Queue<SongEntry>("favorites");
        loungePL = new Queue<SongEntry>("lounge");
        roadTripPL = new Queue<SongEntry>("roadTrip");
    }


    /**
     * used for adding songs to one of the three queues
     * @param requestFile
     * user's requested songs
     * @param allSongs
     * total songs
     */
    public void fillPlaylists(String requestFile, SongEntry[] allSongs)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(requestFile));
            String string = in.readLine();
            String tempStr;

            while((tempStr = in.readLine()) != null)
                string += "," + tempStr;

            String[] playlistNameAndSongTitle = string.split(",");

            SongEntry searchedSong = null;

            for(int i = 0; i < playlistNameAndSongTitle.length; i += 2)
            {
                String playListName = playlistNameAndSongTitle[i];

                for(int j = 0; j < allSongs.length; j++)
                {
                    if(playlistNameAndSongTitle[i + 1].equals(allSongs[j].getTitle()))
                    {
                        searchedSong = allSongs[j];
                        break;
                    }
                }

                if(playListName.equals("favorites"))
                    favoritePL.enqueue(searchedSong);
                else if (playListName.equals("lounge"))
                    loungePL.enqueue(searchedSong);
                else if(playListName.equals("road trip"))
                    roadTripPL.enqueue(searchedSong);
            }
            in.close();
        }
        catch (IOException e){}
    }


    /**
     * Accessor for favoritePL
     * @return
     * return favorite playlist
     */
    public Queue<SongEntry> getFavoritePL()
    {
        return favoritePL;
    }


    /**
     * Accessor for loungePL
     * @return
     * return lounge playlist
     */
    public Queue<SongEntry> getLoungePL()
    {
        return loungePL;
    }


    /**
     * Accessor for roadTripPL
     * @return
     * return roadTrip playlist
     */
    public Queue<SongEntry> getRoadTripPL()
    {
        return roadTripPL;
    }
}