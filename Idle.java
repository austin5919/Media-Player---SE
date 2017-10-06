

/**
 * this class will determine
 * the action taken when the
 * music player is in Idle state
 * 
 * @author jose
 * @author austin
 */
public class Idle implements State{
    
    MusicPlayer musicplayer;
    
    /**
     * receive the MusicPlayer.java class
     * @param newMusicplayer 
     */
    public Idle(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}
    
    @Override
    public void createSongObject(String songName, String songLink) {
        this.musicplayer.setDeadMediaPlayer(this.musicplayer.loadSong(songLink));
        this.musicplayer.theNewSongObject(songName,songLink);
    }

    @Override
    public void refreshPlaylist() {
        refresh();
    }
    
    /*
     * Made this private because it should of be posible
     * when the MP3 player is Idle
     */
    private void refresh(){
        this.musicplayer.getTableViewDisplay().setItems(this.musicplayer.getObservalbeList());
    }

    @Override
    public void playSong() {
        this.musicplayer.play();
        this.musicplayer.setState(this.musicplayer.getPlayingState());
    }
}
