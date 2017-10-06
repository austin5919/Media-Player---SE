
/**
 * this class handles the actions when 
 * the music player is on play state
 * @author jose
 * @author austin
 */
public class Playing implements State{
    MusicPlayer musicplayer;
    
    /**
     * receive the MusicPlayer.java class
     * @param newMusicplayer 
     */
    public Playing(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}
    
    @Override
    public void createSongObject(String songName, String songLink) {
        this.musicplayer.setDeadMediaPlayer(this.musicplayer.loadSong(songLink));
        this.musicplayer.theNewSongObject(songName,songLink);
    }

    @Override
    public void refreshPlaylist() {
        
    }

    @Override
    public void playSong() {
        this.musicplayer.play();
        this.musicplayer.setState(this.musicplayer.getPlayingState());
    }
    
}
