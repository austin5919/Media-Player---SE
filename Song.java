/**
 * This class creates song objects to make it more
 * simple to store them. It saves having to build multiple
 * lists.
 */
public class Song {

    private String name;
    private String duration;
    private String path;


    /**
     * Constuctor requiring the name, duration, and path of the song.
     *
     * @param newName     The song name.
     * @param newDuration The song duration.
     * @param newPath     Takes in the new songs path.
     */
    public Song(String newName, String newDuration, String newPath) {
        this.name = newName;
        this.path = newPath;
        this.duration = newDuration;
    }

    /**
     * Gets song name.
     *
     * @return Song name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the song name.
     *
     * @param songName The String to be set as the song name.
     */
    public void setName(String songName) {
        this.name = songName;
    }

    /**
     * Gets the song duration.
     *
     * @return The current song duration.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the song Duration.
     *
     * @param songDuration The String to be set as the duration for the current song.
     */
    public void setDuration(String songDuration) {
        this.duration = songDuration;
    }

    /**
     * Gets the path of the current song.
     *
     * @return Path of current song.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the song path for the current song.
     *
     * @param songPath The String to be set as the path for the current song.
     */
    public void setPath(String songPath) {
        this.path = songPath;
    }
	
	/**
     * Unit test.
     *
     * @param args Command-line parameters for this test. Currently unused
     */
    public static void main(String[] args) {
	}
}
