import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LoadSong implements SongState{
    
    MusicPlayer player;
    
    public LoadSong(MusicPlayer player){
        this.player = player;
    }

    @Override
    public void playSong() {
        //do nothing
    }

    @Override
    public void stopSong() {
        
        if(this.player.player != null){
            this.player.player.stop();
        }
    }

    @Override
    public void loadSong(String song) {
        
        this.player.stopSong();
        
        Media media = new Media(new File(song).toURI().toString());
        this.player.player = new MediaPlayer(media);
        this.player.setState(this.player.getPlayState());

    }
}
