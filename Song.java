/**
 * this class creates song objects to make it more
 * simple to store them. It saves having to build multiple
 * list
 */
public class Song {

    private String songName;
    private String songDuration;
    private String songPath;

    /**
     * empty constructor to be able to call it without
     * passing in anything
     */
    public Song(){

        this.songName = "";
        this.songPath = "";
        this.songDuration = "";
    }

    /**
     * takes in a few variables and sets them
     *
     * @param newName takes in the new songs name
     * @param newDuration takes in the new songs duration
     * @param newPath takes in the new songs path
     */
    public Song(String newName, String newDuration, String newPath){

        this.songName = newName;
        this.songPath = newPath;
        this.songDuration = newDuration;

    }

    /**
     * gets song name
     *
     * @return returns the songs name
     */
    public String getSongName() {

        return songName;
    }

    /**
     * sets songName
     *
     * @param songName takes in a song name and sets it
     */
    public void setSongName(String songName) {

        this.songName = songName;
    }

    /**
     * gets songDuration
     *
     * @return returns the current song duration
     */
    public String getSongDuration() {

        return songDuration;
    }

    /**
     * sets songDuration
     *
     * @param songDuration takes in current song duration and sets it
     */
    public void setSongDuration(String songDuration) {

        this.songDuration = songDuration;
    }

    /**
     * gets song path
     * @return returns the current song path
     */
    public String getSongPath() {

        return songPath;
    }

    /**
     * sets song path
     * @param songPath takes in a songpath and sets it
     */
    public void setSongPath(String songPath) {

        this.songPath = songPath;
    }
}
