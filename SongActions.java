

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * this class simply handles everything the song should be doing
 */
public class SongActions {

    private MediaPlayer mediaPlayer;
    private Song song;

    public Song getSong() { return song; }

    public SongActions(){ }

    public SongActions(String path){ setMediaPlayer(path); }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(String path){

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        this.mediaPlayer = player;
    }

    public void createSongObject(String songName, String songPath){

        Song songObject = new Song();

        songObject.setSongName(songName);
        songObject.setSongDuration(formatDuration(getMediaPlayer().getMedia().getDuration().toMillis()));
        songObject.setSongPath(songPath);

        this.song = songObject;
    }


    private String formatDuration(double duration){

        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) duration) -  TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }

    public void play(){ this.mediaPlayer.play();}

    public void stop() {

        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
    }
}