import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

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
       
        //initialize();
    }
    
    /**
     * initializes the loadJson() method
     * i wrapped loadJson in an public method
     * just i case i need to use it again.
     */
    public void initialize(){
         loadJson();
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
    
    /**
     * creates a j-son file using the information
     * placed in the array inside this class. Right now
     * it overwrites the information inside the j-son
     * file but that only requires minor changes to 
     * implement a different behavior
     */
    public void createJson(){
       /*
       JSONObject Obj = new JSONObject();
       
       Obj.put("size", this.songName.size());
       
       JSONArray nameObj = new JSONArray();
       JSONArray lengthObj = new JSONArray();
       JSONArray linkObj = new JSONArray();
       
       for(int i = 0; i < this.songName.size();i++){
           nameObj.add(this.songName.get(i));
           lengthObj.add(this.songLength.get(i));
           linkObj.add(this.songLink.get(i));
      
       }
       
       Obj.put("playlistName", nameObj);
       Obj.put("playlistLength",lengthObj);
       Obj.put("playlistLink", linkObj);
       
       try (FileWriter file = new FileWriter("playlist.json")) {

            file.write(Obj.toJSONString());
            file.flush();

        } catch (IOException e) {}
		*/
    }
    
    /*
     * load the j-son file and place the information inside of it
     * in the array list inside this class. Right now i throws and 
     * exception if the file does not exist or is in the incorrect
     * format. Also, it ignores song with links that cannot be verified
     * 
     */
    private void loadJson(){
		/*
        JSONParser parser = new JSONParser();
        
        try{
            Object obj = parser.parse(new FileReader("playlist.json"));
            JSONObject jsonObject = (JSONObject) obj;
            
            long size = (Long) jsonObject.get("size");
            
            JSONArray nameObj = (JSONArray) jsonObject.get("playlistName");
            JSONArray lengthObj = (JSONArray) jsonObject.get("playlistLength");
            JSONArray linkObj = (JSONArray) jsonObject.get("playlistLink");
            
            for(int i = 0; i < size;i++){
                if(isLinkValid(linkObj.get(i).toString()) == true){
                    this.songName.add(nameObj.get(i).toString());
                    this.songLength.add(lengthObj.get(i).toString());
                    this.songLink.add(linkObj.get(i).toString());
                }
            }
        }catch(Exception e){
            System.out.println("the format is incorrect, or file doesnt exist.."
                    + "please double check");
        }
        
        setObserveableList();
		*/
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
