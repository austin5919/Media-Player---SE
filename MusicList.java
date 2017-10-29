import java.util.*;

/**
 * This class is a data structure for the information associated with each song. 
 * It determines which song will come next based on elements in the queue and 
 * whether the MusicList has enabled/disabled shuffle.
 */
public class MusicList {
    
    //Fields
    private ArrayList<ArrayList<String>> list;
    private ArrayList<ArrayList<String>> queue;
    private ArrayList<String> currentSong;
    private boolean shuffle;
    
    /**
     * Constructor for class MusicList with no current songs. Initialized with no songs.
     */
    public MusicList() {
        this.list = new ArrayList<ArrayList<String>>();
        this.queue = new ArrayList<ArrayList<String>>();
        this.shuffle = false;
        this.currentSong = null;
    }
    
    /**
     * Constructor for class MusicList. Initialized it with songs.
     *
     * @param list  ArrayList containing the songs to initialize the list with.
     */
    public MusicList(ArrayList<ArrayList<String>> list) {
        this.list = list;
        this.queue = new ArrayList<ArrayList<String>>();
    }
    
    /**
     * Adds a new song to the data structure.
     *
     * @param name  String representing the song name.
     * @param duration  String representing the duration of the song.
     * @param path  String representing the path of the song.
     */
    public void addSong(String name, String duration, String path) {
		ArrayList<String> song = new ArrayList<String>();
		song.add(name);
		song.add(duration);
		song.add(path);
		this.addSong(song);
    }

    /**
     * Adds a new song to the data structure.
     *
     * @param song  ArrayList containing the name, duration, and path of the song.
     */
    public void addSong(ArrayList<String> song) {
        if (this.getSongByPath(song.get(2)) == null) {
			this.list.add(song);
		} else {
			System.out.println("Song with that path is already in MusicList: " + song.get(2));
		}
		
    }
    
    /**
     * Adds a new song to the data structure.
     *
     * @param song  ArrayList containing the name, duration, and path of the song.
     * @throws  IllegalArgumentException if the song to be added to the queue was not in this MusicList.
     */
    public void addToQueue(ArrayList<String> song) throws IllegalArgumentException {
        if (this.containsSong(song)) {
			this.queue.add(song);
        } else {
            throw new IllegalArgumentException("That song is not in in the list. You must add it to the MusicList before it can be added to the queue: " + song);
        }
    }
	
    
    /**
     * Adds a new song to the data structure.
     *
     * @param song  ArrayList containing the name, duration, and path of the song.
     * @throws  IllegalArgumentException if the song was not in this MusicList.
     */
    public void removeSong(ArrayList<String> song) throws IllegalArgumentException {
        if (this.containsSong(song)) {
            this.list.remove(song);
        } else {
            throw new IllegalArgumentException("You cannot remove a song that does not exist in the MusicList!: " + song);
        }
        
    }
        
    /**
     * Checks if a song is contained within the MusicList. This checks based on 
     * object reference.
     *
     * @param song  ArrayList containing the name, duration, and path of the song.
     * @return  True if song was found within the MusicList; otherwise false.
     */
    public boolean containsSong(ArrayList<String> song) {
        for (ArrayList<String> s : this.list) {
            if (s == song) {
                return true;
            }
        }
        return false;
    }
	
    /**
     * Gets the next song.
     *
     * @return  The next song to be played. If the queue is not empty, it returns one using FIFO;
     * otherwise it returns a song from the available list. If shuffle is true, a random song from
     * list is returned; otherwise it returns whicher song follows the previous one.
     */
    public ArrayList<String> getNext() {
        ArrayList<String> song;
        if (this.list.isEmpty()) {
            throw new IllegalArgumentException("There are no songs in this MusicList.");
        } 
        if (!this.queue.isEmpty()) {
            this.currentSong = this.queue.remove(0);
        } else {
            if (this.shuffle) {
                this.currentSong = this.list.get((int)(Math.random()*(this.list.size()-1)));
            } else {
                int indexLastSong = this.list.indexOf(this.currentSong);
                this.currentSong = this.list.get((++indexLastSong)%this.list.size());
            }
        }
        return currentSong;
        
    }
    
    /**
     * Get a song by song name.
     *
     * @param name  The song name to search the MusicList for.
     * @return  The first instance of a song that matches the given song name; otherwise null.
     */
    public ArrayList<String> getSongByName(String name) {
        return this.getSong(0, name);
    }
    
    /**
     * Get a song by duration.
     *
     * @param name  The song duration to search the MusicList for.
     * @return  The first instance of a song that matches the given song duration; otherwise null.
     */
    public ArrayList<String> getSongByDuration(String name) {
        return this.getSong(1, name);
    }
    
    /**
     * Get a song by path.
     *
     * @param name  The song file path to search the MusicList for.
     * @return  The first instance of a song that matches the given song file path; otherwise null.
     */
    public ArrayList<String> getSongByPath(String name) {
        return this.getSong(2, name);
    }

    /**
     * Gets the list of songs contained by this MusicList.
     *
     * @return  The full list of songs contained by this MusicList.
     */
    public ArrayList<ArrayList<String>> getAllSongs() {
        return this.list;
    }
    
    /**
     * Gets a portion of the songs contained by this MusicList. (a subset of the full list)
     *
     * @param songNames  The list of songs to find in this MusicList.
     * @return  A subset of the list of songs of this MusicList.
     * @throws  IllegalArgumentException if at least of the songs is not in this MusicList
     */
    public ArrayList<ArrayList<String>> getSubsetByNames(ArrayList<String> songNames) throws IllegalArgumentException {
        return this.getSubset(0, songNames);
    }
    
    
    
    // Helper method that checks the String at the given index for each song. If it matches, the song is returned; otherwise return null.
    // i.e. To find a song in the MusicList whose song name is "Tequila", you would use getSong(0, "Tequila") because song name is index 0.
    // i.e. Similarly, finding a song whose path is "/songs/Tequila.mp3" would be getSong(2, "/songs/Tequila.mp3").
    private ArrayList<String> getSong(int index, String detail) {
        for (ArrayList<String> song : this.list) {
            if (song.get(index) == detail) {
                return song;
            }
        }
        return null;
    }
    
    // Helper method that returns a subset of songs that are contained within this MusicList. 
    // If at least one song is not found within this MusicList then an IllegalArgumentException is thrown.
    private ArrayList<ArrayList<String>> getSubset(int index, ArrayList<String> details) throws IllegalArgumentException {
        ArrayList<ArrayList<String>> subset = new ArrayList<ArrayList<String>>(); 
        for (String detail : details) {
            ArrayList<String> song = this.getSong(index, detail);
            if (song == null) {
                throw new IllegalArgumentException("There is no song that matched '" + detail + "' at index: " + index);
            }
            subset.add(song);
        }
        return subset;
    }
    
    /**
     * Sets the shuffle setting.
     *
     * @param setting  A boolean value representing the state of the shuffle with true indicated shuffle is enabled and false indicating it is disabled.
     */
    public void setShuffle(boolean setting) {
        this.shuffle = setting;
    }
    
    
    /**
     * Returns a string version of this.
     *
     * @return A string representation of this MusicList.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("List:\n");
        for (int i = 0; i<this.list.size(); i++) {
            s.append("[" + i + "] - ");
            s.append(this.list.get(i) + "\n");
        }
        s.append("Queue:\n");
        for (int i = 0; i<this.queue.size(); i++) {
            s.append("[" + i + "] - ");
            s.append(this.queue.get(i) + "\n");
        }
        return s.toString();
    }
    
    /**
     * Unit test.
     *
     * @param args Command-line parameters for this test. Currently unused
     */
    public static void main(String[] args) {
        MusicList listA = new MusicList();
        listA.addSong(new ArrayList<String>(Arrays.asList("Apple","00:32","./songs/apple.mp3")));
        listA.addSong(new ArrayList<String>(Arrays.asList("Banana","00:45","./songs/banana.mp3")));
        listA.addSong(new ArrayList<String>(Arrays.asList("Carrot","01:17","./songs/carrot.mp3")));
        listA.addSong(new ArrayList<String>(Arrays.asList("Donut","00:26","./songs/donut.mp3")));
        listA.addSong(new ArrayList<String>(Arrays.asList("Eggroll","00:53","./songs/eggroll.mp3")));
        listA.addSong(new ArrayList<String>(Arrays.asList("Flour","00:56","./songs/flour.mp3")));
        listA.addSong(new ArrayList<String>(Arrays.asList("Grape","02:03","./songs/grape.mp3")));
        listA.addSong(new ArrayList<String>(Arrays.asList("Honey","02:03","./songs/grape.mp3")));
        System.out.println(listA);
        
        try {
            listA.removeSong(new ArrayList<String>(Arrays.asList("Eggroll","00:53","./songs/eggroll.mp3")));
        } catch (Exception e) {
            System.out.println("Couldn't remove a song by creating identical ArrayList with same " +
                "song info because it checks object references in memory. This is intentional and good!\n");
        }
        
        System.out.println("Removing Donut song by using getSongByName() and removeSong()");
        listA.removeSong(listA.getSongByName("Donut"));
        System.out.println(listA);
        
        System.out.println("Testing nextSong():");
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        
        System.out.println("\nEnabling Shuffle...");
        listA.setShuffle(true);
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        
        System.out.println("\nAdding Grape, Carrot, and whichever song has 00:53 duration (Flour) to queuem, in that order:");
        System.out.println("Disabling Shuffle");
        listA.setShuffle(false);
        listA.addToQueue(listA.getSongByName("Grape"));
        listA.addToQueue(listA.getSongByPath("./songs/carrot.mp3"));
        listA.addToQueue(listA.getSongByDuration("00:56"));
        System.out.println(listA);
        
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        System.out.println("Next Song: " + listA.getNext());
        
        System.out.println("\nCreating subset MusicList with Apple, Banana, and Carrot:\n");
        ArrayList<String> subsetSongNames = new ArrayList<String>();
        subsetSongNames.addAll(Arrays.asList("Apple","Banana","Carrot"));
        MusicList listB = new MusicList(listA.getSubsetByNames(subsetSongNames));
        System.out.println("Subset MusicList: \n" + listB);
        System.out.println("Main MusicList: \n" + listA);
        
        System.out.println("Modifying the song Apple to demonstrate how changing one changes them all:");
        ArrayList<String> apple = listA.getSongByName("Apple");
        apple.set(0,"Zebra");
        apple.set(1,"13:37");
        apple.set(2,"./songs/best/zebra.mp3");
        System.out.println("Subset MusicList: \n" + listB);
        System.out.println("Main MusicList: \n" + listA);
		
		System.out.println("Attempting to add a song with the same file path as a pre-existing song:");
		listA.addSong(new ArrayList<String>(Arrays.asList("Example","00:00","./songs/banana.mp3")));
		
		System.out.println("\nAttempting to add a song to the queue that isn't in the main list:");
		try {
            listA.addToQueue(new ArrayList<String>(Arrays.asList("Example","00:00","./songs/banana.mp3")));
        } catch (Exception e) {
            System.out.println(e);
        }
		
		System.out.println("\nAttempting to add the same song to the queue more than once (this is allowed):");
		listA.addToQueue(listA.getSongByName("Banana"));
		listA.addToQueue(listA.getSongByName("Carrot"));
		listA.addToQueue(listA.getSongByName("Banana"));
		listA.addToQueue(listA.getSongByName("Banana"));
		System.out.println(listA);
    }
    
}
