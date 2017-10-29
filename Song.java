import java.io.*;

/**
 * This class creates song objects to make it more
 * simple to store them. It saves having to build multiple
 * lists.
 */
public class Song {

    private String songName;
    private String songDuration;
    private String songPath;


    /**
     * Constuctor requiring the name, duration, and path of the song.
     *
     * @param newName     The song name.
     * @param newDuration The song duration.
     * @param newPath     Takes in the new songs path.
     */
    public Song(String newName, String newDuration, String newPath) {
        this.songName = newName;
        this.songPath = newPath;
        this.songDuration = newDuration;
    }

    /**
     * Gets song name.
     *
     * @return Song name.
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Sets the song name.
     *
     * @param songName The String to be set as the song name.
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Gets the song duration.
     *
     * @return The current song duration.
     */
    public String getSongDuration() {
        return songDuration;
    }

    /**
     * Sets the song Duration.
     *
     * @param songDuration The String to be set as the duration for the current song.
     */
    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    /**
     * Gets the path of the current song.
     *
     * @return Path of current song.
     */
    public String getSongPath() {
        return songPath;
    }

    /**
     * Sets the song path for the current song.
     *
     * @param songPath The String to be set as the path for the current song.
     */
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
}
