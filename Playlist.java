import java.io.*;
import java.util.*;

/**
 * Keeps track of a collection of songs within a directory.  Command-line usage: java Playlist
 * 
 * @author Austin Ash
 * @author Jose Cruz
 */

public class Playlist {
    
    //fields
    File songsDirectory;
    ArrayList<String> songNames = new ArrayList<String>();
    Media currentSong;
/*    = new Media(new File(song).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
    */
    
    /**
     * Constructor for class Playlist.
     * 
     * @param directory  A String representing the initial directory to look for MP3 Files
     */
    public Playlist(String directory) {
        this.setSongsDirectory(directory);
    }
    
    /**
     * Constructor for class Playlist
     * 
     * @param directory  A File representing the initial directory to look for MP3 Files
     */
    public Playlist(File directory) {
        this.setSongsDirectory(directory);
    }
    
    /**
     *
     *
     */
    
    
    /**
     * Sets the directory for search for songs.
     * 
     * @param directory  A String representing the directory to look for MP3 files
     * @throws IllegalArgumentException  If directory does not exist.
     */
    public void setSongsDirectory(String directory) {
        File dir = new File(directory);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Must be a valid directory: " + directory);
        }
        this.songsDirectory = dir;
    }
    
    /**
     * Sets the directory for search for songs.
     * 
     * @param directory  A File representing the directory to look for MP3 files
     * @throws IllegalArgumentException  If directory does not exist.
     */
    public void setSongsDirectory(File directory) {
        this.setSongsDirectory(directory.toString());
    }
    
    /**
     * Returns a string version of this.
     *
     * @return  A string representation of this Playlist.
     */
    public String toString() {
        return "Coming soon...";
    }

    /**
     * Unit test.
     *
     *@param args  Command-line parameters for this test. Currently unused
     */
    public static void main(String[] args) {
        Playlist playlist = new Playlist("./songs");
        Boolean allWorking = true;
        Boolean testPass = true;
        

        
        // Test A: setSongsDirectory - Rejecting bad strings
        try {
            playlist.setSongsDirectory("Monkeys!");
            System.out.println("setSongsDirectory did not catch the error properly! :(");
            testPass = false;
        } catch(IllegalArgumentException e) {
            //e.getMessage();
            testPass = true;
        }
        allWorking = allWorking && testPass;
        System.out.println("Test A Pass?: " + testPass);
        
        
        // Test B 
        try {
            playlist.setSongsDirectory("./ThisShouldFailToo");
            System.out.println("setSongsDirectory did not catch the error properly! :(");
            testPass = false;
        } catch(IllegalArgumentException e) {
            //e.getMessage();
            testPass = true;
        }
        allWorking = allWorking && testPass;
        System.out.println("Test B Pass?: " + testPass);
        
        
        // Test C 
        try {
            playlist.setSongsDirectory("./Songs");
            testPass = true;
        } catch(IllegalArgumentException e) {
            System.out.println("setSongsDirectory could not find ./Songs (But should have) :(");
            //e.getMessage();
            testPass = false;
        }
        allWorking = allWorking && testPass;
        System.out.println("Test C Pass?: " + testPass);
        
        
        // Test D 
        try {
            playlist.setSongsDirectory(new File("./Songs"));
            testPass = true;
        } catch(IllegalArgumentException e) {
            System.out.println("setSongsDirectory could not find ./Songs (But should have) :(");
            //e.getMessage();
            testPass = false;
        }
        allWorking = allWorking && testPass;
        System.out.println("Test D Pass?: " + testPass);
        
        
        // Test E 
        try {
            playlist.setSongsDirectory(new File("./BlaBlaBla"));
            System.out.println("setSongsDirectory didn't reject a bad input :(");
            testPass = false;
        } catch(IllegalArgumentException e) {
            //e.getMessage();
            testPass = true;
        }
        allWorking = allWorking && testPass;
        System.out.println("Test E Pass?: " + testPass);
        
        
        // Test F 
        try {
            playlist.setSongsDirectory(new File("./Songs/PrideOfTheWolverines.mp3"));
            System.out.println("setSongsDirectory didn't reject a bad input (a file, not directory) :(");
            testPass = false;
        } catch(IllegalArgumentException e) {
            //e.getMessage();
            testPass = true;
        }
        allWorking = allWorking && testPass;
        System.out.println("Test F Pass?: " + testPass);

        // print the overall result of the tests.
        if (allWorking) {
            System.out.println("Everything works!");
        } else {
            System.out.println("At least one test failed!!!");
        }
    }

    
    
} //end of Playlist.java
