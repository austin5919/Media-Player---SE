/**
 * this class creates song objects
 */
public class Song {

    private String songName;
    private String songDuration;
    private String songPath;

    /**
     * empty constuctor to be able to call it without
     * passing in anything
     */
    public Song(){

        this.songName = "";
        this.songPath = "";
        this.songDuration = "";
    }

    /**
     * takes in a few variables and sets them
     * @param newName
     * @param newDuration
     * @param newPath
     */
    public Song(String newName, String newDuration, String newPath){

        this.songName = newName;
        this.songPath = newPath;
        this.songDuration = newDuration;

    }

    /**
     * gets song name
     * @return
     */
    public String getSongName() {
        return songName;
    }

    /**
     * sets songName
     * @param songName
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * gets songDuration
     * @return
     */
    public String getSongDuration() {
        return songDuration;
    }

    /**
     * sets songDuration
     * @param songDuration
     */
    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    /**
     * gets song path
     * @return
     */
    public String getSongPath() {
        return songPath;
    }

    /**
     * sets song path
     * @param songPath
     */
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
}
