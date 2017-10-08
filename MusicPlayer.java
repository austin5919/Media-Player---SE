import java.io.File;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * this makes it easier for the MP3 player to interact with the data
 * as well as the GUI components. 
 * @author jose
 * @author austin
 */
public class MusicPlayer {
    private PlayList songObj = new PlayList();
    private State state;
    
    private State idle;
    private State playing;
    
    private Song song;
    
    private MediaPlayer mediaplayer;
    private MediaPlayer deadPlayer;
    private int playingRow;
    private TableView<Song> display;
    
    private String songDuration;
   
    public MusicPlayer(){
        this.idle = new Idle(this);
        this.playing = new Playing(this);
        this.state = idle;
        this.mediaplayer = null;
    }
    
    //allot of getters
    public void setState(State newState){this.state = newState;}
    
    //i set two music player becuase i wanted to be able to add a song to the playlist while
    //the song was still playing. To add song and get duration i have to set up the media which
    //means i would have to interupt the state of the one playing, and then have to go back to it
    //it felt easier to make a deadMediaPlayer to handle adding songs and getting their information
    //both variables are calling the same load media so i am not really duplicating information
    public void setMediaPlayer(MediaPlayer newMediaplayer){this.mediaplayer = newMediaplayer;}
    public void setDeadMediaPlayer(MediaPlayer newMediaPlayer){this.deadPlayer = newMediaPlayer;}
    
    public void setTableViewDisplay(TableView<Song> newDisplay){this.display = newDisplay;}
    
    public void setPlayingRow(int i) {this.playingRow = i;}
    
    public void setSongObject(Song song){this.song = song;}
    
    private double getDuration(){return getDeadMediaPlayer().getMedia().getDuration().toMillis();}
    
    //allot of setters
    public State getIdleState(){return this.idle;}
    public State getPlayingState(){return this.playing;}
    
    public int getPlayingRow(){return this.playingRow;}
    
    public MediaPlayer getMediaPlayer(){return this.mediaplayer;}
    public MediaPlayer getDeadMediaPlayer(){return this.deadPlayer;}
    
    public TableView<Song> getTableViewDisplay(){return this.display;}
    
    public ObservableList<Song> getObservalbeList(){return this.songObj.getObserveableList();}
    
    public Song getSongObject(){return this.song;}
    
    public PlayList getPlaylistObj (){return songObj;}
    
    //add song to playlist
    public void createSongObject(String songName, String songLink) {this.state.createSongObject(songName, songLink);}
    
    public void refreshPlaylist(){this.state.refreshPlaylist();}
    public void playSong(){this.state.playSong();}
    
    /**
     * this method loads a song in to the media
     * so that it can be manipulated by the MP3 player
     * @param newSongLink 
     * @return  
     */
    public MediaPlayer loadSong(String newSongLink){
        Media media = new Media(new File(newSongLink).toURI().toString());
        MediaPlayer player = (new MediaPlayer(media));
        
        return player;
    }
    
    /**
     * this method receives a song and updates the 
     * table view as well as update the play list arrays.
     * It also calls for a j-son rewrite. So the new song 
     * is track able
     * @param song 
     */
    public void addSongObjectToTableView(Song song){
        Platform.runLater(() -> {
            System.out.println(songObj.existAlready(song.getSongName()));
            if(songObj.existAlready(song.getSongName()) == false){
                this.display.getItems().add(song);
                if(this.getTableViewDisplay().getItems().size() == 1){
                    this.getTableViewDisplay().getFocusModel().focus(-1);
                }
                songObj.addSong(song);
                
            }else{
                System.out.println("this song already exist");
            }
        });
    }
    
    /**
     * this method creates a new song object that will soon be
     * added to the table view and sent to the play list class
     * @param songName
     * @param songLink 
     */
    public void theNewSongObject(String songName,String songLink){
        getDeadMediaPlayer().setOnReady(() -> {
            
            Song songObject = new Song();
            
            songObject.setSongName(songName);
            songObject.setSongLenght(formatDuration(getDuration()));
            songObject.setSongLink(songLink);
            
            addSongObjectToTableView(songObject);
            
        });
    }
    
    /**
     * this stops the song
     */
    public void stop(){mediaplayer.stop();}
    
    /**
     *this plays a song
     */
    public void play(){
        if(mediaplayer != null){
            stop();
        }
        
        setMediaPlayer(loadSong(this.getTableViewDisplay().getFocusModel().getFocusedItem().getSongLink()));
        this.mediaplayer.play();
        
        
    }
    
    /**
     * simply formats the duration i get from the media player
     * in to a hh:ss 
     */
    private String formatDuration(double duration){
        return String.format("%02d:%02d",  
            TimeUnit.MILLISECONDS.toMinutes((long) duration) -  TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
            TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }
}