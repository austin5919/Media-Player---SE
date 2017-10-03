import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class will handle data manipulation and perform
 * the actual actions of the MP3 player.
 * 
 * @author austin
 * @author jose
 */
public class MusicPlayer {
    private JsonSongReader json = new JsonSongReader();
    
    //mediaPlayerStates
    private UserPrivaledge playing;
    private UserPrivaledge paused;
    private UserPrivaledge stopped;
    private UserPrivaledge songChangedAtPlay;
    private UserPrivaledge songChangedAtPause;
    
    //state holder
    private UserPrivaledge mediaState;
    
    //fields neccesary to play media and update media
    private ObservableList<Song> songsDetails;
    private MediaPlayer mediaPlayer;
    private TableView<Song> playList;
    private Button playButton;
    private Button pauseButton;
    
    //i need to keep track of the previous song selected
    private String previousSong;
    
    /**
     * This constructor will set the value of 
     * some of my private fields. The ones i really
     * on start.
     */
    public MusicPlayer(){ 
        
        this.playing = new Playing(this);
        this.paused = new Paused(this);
        this.stopped = new Stopped(this);
        this.songChangedAtPlay = new ChangeDetectedAtPlay(this);
        this.songChangedAtPause = new ChangeDetectedAtPause(this);
        
        this.mediaPlayer = null;
        this.mediaState = stopped;
    
    }
    
    /**
     * This method will set the state of the MP3 player
     * @param newMediaState 
     */
    public void setState(UserPrivaledge newMediaState){ this.mediaState = newMediaState;}
    
    /**
     * This method will set the buttons that were passed in from the
     * MediaController.java class to be used in this class.
     * 
     * @param playButton
     * @param pauseButton 
     */
    public void setPlayPauseButton(Button playButton, Button pauseButton){
        this.playButton = playButton;
        this.pauseButton = pauseButton;
    }
    
    /**
     * this will set the the previous song equal to the current song.
     * this will allow me to check if the current equals the previous
     * song
     */
    public void setPreviousSong(){
        this.previousSong = playList.getSelectionModel().getSelectedItem().getSongName();
    }
    
    /**
     * This method will populate my TableView with songs
     * to be used to play Media and display songs.
     */
    public void setSongDetails(){
        
        //create an Observablelist since thats what a table works best with
        this.songsDetails =  FXCollections.observableArrayList();
        
        //get playList i used last from json
        String playlist = json.getLastUsedPlaylist();
        
        //populate the Song Object with song and place them on the Observable list
        for(int i = 0; i < json.getFromList(playlist + "SongNames").size();i++){
            this.songsDetails.add(new Song(
                    json.getFromList(playlist + "SongNames").get(i).toString(),
                    json.getFromList(playlist + "SongLenghs").get(i).toString(),
                    json.getFromList(playlist + "SongLinks").get(i).toString())
            );
        }
       
        this.playList.setItems(songsDetails);
    }
    
    /**
     * Set the TableView passed in from the MediaController Class and
     * call the getPlaylist() method that will get the song details to 
     * put on the TableVIew
     * 
     * @param newPlayList 
     */
    public void populatePlaylist(TableView<Song> newPlayList){
        this.playList = newPlayList;
        this.mediaState.getPlaylist();
    }
    
    /**
     * This just returns the playing state
     * 
     * @return 
     */
    public UserPrivaledge getPlayState(){return this.playing;}
    
    /**
     * This returns the stop state
     * 
     * @return 
     */
    public UserPrivaledge getStopState(){return this.stopped;}
    
    /**
     * This returns the paused state
     * 
     * @return 
     */
    public UserPrivaledge getPauseState(){return this.paused;}
    
    /**
     * returns the song change state at play
     * 
     * @return 
     */
    public UserPrivaledge getSongChangedStateAtPlay(){return this.songChangedAtPlay;}
    
    /**
     * returns the song changed state at pause
     * 
     * @return 
     */
    public UserPrivaledge getSongChangedStateAtPause(){return this.songChangedAtPause;}
    
    /**
     * Returns the playButton and this is how i will update
     * the playButton on the MediaController class
     * 
     * @return 
     */
    public Button getPlayButton(){return playButton;}
    
    /**
     * Returns the pauseButton and this is how i will update
     * the pauseButton on the MediaController class
     * 
     * @return 
     */
    public Button getPauseButton(){return pauseButton;}
    
    /**
     * Returns the tableView which i will use to update the
     * TableView in the controller class
     * 
     * @return 
     */
    public TableView<Song> getPlayList(){return this.playList;}
    
    /**
     * this get me the previous value to that i can use
     * to check if previous song equals the current
     * 
     * @return 
     */
    public String getPrevious(){return this.previousSong;}
    
    /**
     * simply checks if the previous and current value is the same
     * and returns true or false
     * 
     * @return 
     */
    public Boolean isIdentical(){
        return (playList.getSelectionModel().getSelectedItem().getSongName() == null ? getPrevious() 
                == null : playList.getSelectionModel().getSelectedItem().getSongName().equals(getPrevious()));
    }
    
    /**
     * This will handle the actions that belong to
     * the tableView. This action requires a double
     * click to activate
     */
    public void playSongDoubleClick(){this.mediaState.playSongDoubleClick();}
    
    /**
     * This method will check if the index current clicked is the same as the
     * previous. And change the state according to the results at playing time
     */
    public void changeDetected(){this.mediaState.changeDetected();}
    
    /**
     * This handles the action generated by the play
     * and pause button. 
     */
    public void playSongSingleClick(){this.mediaState.playSongSingleClick();}
    
    /**
     * This method stops the song
     */
    public void StopMedia(){mediaPlayer.stop();}
    
    /**
     * This method pauses the media and sets the
     * visibility of the play and pause button
     */
    public void pauseMedia(){
        
        mediaPlayer.pause();

    }
    
    /**
     * This method resumes a song from the 
     * previous paused point and sets play and
     * pause button visibility
     */
    public void Resume(){
        this.mediaPlayer.play();
    }
    
    /**
     * this plays song and sets pause and play
     * button visibility
     */
    public void playMedia(){
        Media media = new Media(new File(this.playList.getSelectionModel().getSelectedItem().getSongLink()).toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
        this.mediaPlayer.play();
    }
    
    /**
     * shows pause button
     */
    public void showPauseButton(){
        
        playButton.setVisible(false);
        pauseButton.setVisible(true);
        this.playList.requestFocus();
    }
    
    /**
     * show play button
     */
    public void showPlayButton(){
        
        playButton.setVisible(true);
        pauseButton.setVisible(false);
        this.playList.requestFocus();
    }
}
