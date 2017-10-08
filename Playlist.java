import java.io.File;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class handles the actual data for the
 * play list. It keeps track of all the songs
 * in the play list, so that they don't get lost
 * once the MP3 player is closed.
 * 
 * @author austin
 * @author jose
 */
public class PlayList{
    
    //Three array list to hold
    //song name, length and link
    ArrayList<String> songName;
    ArrayList<String> songLength;
    ArrayList<String> songLink;
    
    //An observable list to populate the Tableview
    private ObservableList<Song> songsDetails = null;
    
    /**
     * A constructor to initialize my array list
     * and start the read j-son file process
     */
    public PlayList(){
        
        this.songName = new ArrayList<>();
        this.songLength = new ArrayList<>();
        this.songLink = new ArrayList<>();
        getDefaultSongName();
        getDefaultSongLength();
        getDefaultLink();
        setObserveableList();
    }
    
    private void getDefaultSongName(){
        
        this.songName.add("Schmetterling");
        this.songName.add("PrideOfTheWolverines");
        this.songName.add("Prefunk Loop");
        this.songName.add("Mark_Ronson_Uptown_Funk_ft_Bruno_Mars");
        this.songName.add("Lizzy");
        this.songName.add("Here We Go");
        this.songName.add("Forest Frolic Loop");
    }
    
    private void getDefaultSongLength(){
        
        this.songLength.add("01:02");
        this.songLength.add("03:42");
        this.songLength.add("00:20");
        this.songLength.add("04:30");
        this.songLength.add("00:31");
        this.songLength.add("00:57");
        this.songLength.add("00:37");
    }
    
    private void getDefaultLink(){
        
        this.songLink.add("./songs/Schmetterling.mp3");
        this.songLink.add("./songs/PrideOfTheWolverines.mp3");
        this.songLink.add("./songs/Prefunk Loop.mp3");
        this.songLink.add("./songs/Mark_Ronson_Uptown_Funk_ft_Bruno_Mars.mp3");
        this.songLink.add("./songs/Lizzy.mp3");
        this.songLink.add("./songs/Here We Go.mp3");
        this.songLink.add("./songs/Forest Frolic Loop.mp3");
    }
    /**
     * receive a song object and place their content
     * in between three array list inside this class.
     * @param song 
     */
    public void addSong(Song song){
        this.songName.add(song.getSongName());
        this.songLength.add(song.getSongLenght());
        this.songLink.add(song.getSongLink());
    }
    
    /**
     * receives and song name and uses it to check
     * if it already exist in the play list
     * @param songName
     * @return 
     */
    public boolean existAlready(String songName){
        
        for(int i = 0; i < this.songName.size();i++){
          if(this.songName.get(i) == null ? songName == null : this.songName.get(i).equals(songName)){
              
              return true;
          }
        }
        
        return false;
    }
    
    /**
     * clears the Observe-able list for me
     * if there is anything to clear.
     */
    public void clearAll(){
        if(this.songsDetails != null){
            this.songsDetails.clear();
        }else{
            System.out.println("There is nothing to clear");
        }
        
    }
    
    /*
     * simply checks if a link exist of not. 
     */
    private boolean isLinkValid(String link){
        return new File(link).exists();
    }
    
    /*
     * this method populates my Observable list using information
     * that is found in the arrays in this class.
     */
    private void setObserveableList(){
        
        clearAll();
        this.songsDetails =  FXCollections.observableArrayList();
        for(int i = 0; i < this.songName.size();i++){
            this.songsDetails.add(new Song(this.songName.get(i),this.songLength.get(i),this.songLink.get(i)));
        }
    }
    
    /**
     * allows me to retrieve the Observable list in this
     * class as well as others.
     * @return 
     */
    public ObservableList<Song> getObserveableList(){
        return this.songsDetails;
    }
}