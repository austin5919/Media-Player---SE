import javafx.scene.media.MediaPlayer;


public class MusicPlayer {
    
    MediaPlayer player;
    
    private SongState playing;
    private SongState loading;
    
    private SongState mediaState;
    
    MusicPlayer(){ 
        
        this.playing = new Play(this);
        this.loading = new LoadSong(this);
        
        this.mediaState = loading;
    
    }
    
    public void setState(SongState newMediaState){ this.mediaState = newMediaState; }
    
    //play song state
    public void playSong(){ this.mediaState.playSong();}
    public SongState getPlayState(){return this.playing;}
    
    //stop song state
    public void stopSong(){this.mediaState.stopSong();}
    
    //empty song state
    public void loadSong(String song){this.mediaState.loadSong(song);}
    public SongState getLaodingSongState(){return this.loading;}
    
}
