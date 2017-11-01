/**
 * This class creates song objects to make it more
 * simple to store them. It saves having to build multiple
 * lists.
 */
import java.io.*;
 
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
        if (!validPath(newPath)) {
            throw new IllegalArgumentException("The was no mp3 at: " + newPath);
        }
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
        if (!validPath(songPath)) {
            throw new IllegalArgumentException("The was no mp3 file at: " + songPath);
        }
        this.path = songPath;
    }
    
    /**
     * Checks if there is a file at path.
     *
     * @return  True if song was found; otherwise false.
     */
    public static boolean validPath(String path) {
        File file = new File(path);
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        if (file.isFile() && fileType.equals(".mp3")) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks if this Song equals another in terms of content, not object refence.
     *
     * @return  True if songs are equal; otherwise false.
     */
    public boolean equals(Song other) {
		return (this.name.equals(other.getName()) &&
            this.duration.equals(other.getDuration()) &&
			this.path.equals(other.getPath()));
    }
    
    
    
    /**
     * Unit test.
     *
     * @param args Command-line parameters for this test. Currently unused
     */
    public static void main(String[] args) {
		boolean allTestsPass = true;
        Song songA = new Song("SongA","01:23","./songs/Lizzy.mp3");
        Song songB = new Song("SongA","01:23","./songs/Lizzy.mp3");
		allTestsPass &= songA.equals(songB);
		
		songA.setPath("./songs/Skill Is Futile.mp3");
		allTestsPass &= !songA.equals(songB);
        
        try {
            Song songC = new Song("SongB","03:21","./song/Blabla.mp3");
            System.out.println("This line should NEVER print!");
            allTestsPass &= false;
        } catch(Exception e) { }
        try {
            songA.setPath("");
            System.out.println("This line should NEVER print!");
			allTestsPass &= false;
        } catch(Exception e) {    }
        
        try {
            songA.setPath("./songs");
            System.out.println("This line should NEVER print!");
			allTestsPass &= false;
        } catch(Exception e) {    }
        
        try {
            songA.setPath("Song.java");
            System.out.println("This line should NEVER print!");
			allTestsPass &= false;
        } catch(Exception e) {    }
        
        try {
            songA.setPath("Song.mp3");
            System.out.println("This line should NEVER print!");
			allTestsPass &= false;
        } catch(Exception e) {    }
        
        if (allTestsPass) {
            System.out.println("Good! All tests passed.");
        } else {
            System.out.println("Oh no! At least one test failed!");
        }
        
        
    }
}
