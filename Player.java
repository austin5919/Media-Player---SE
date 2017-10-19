import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * this class simply handles everything the song should be doing
 */
public class Player {

    private MediaPlayer mediaPlayer;

    /**
     * empty constructor to call without passing in anything
     */
    public Player(){ }

    /**
     * takes in a path and sets te mediaplayer
     * @param path
     */
    public Player(String path){ setMediaPlayer(path); }

    /**
     * gets the mediaplayer
     * @return
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * sets the media player
     * @param path
     */
    public void setMediaPlayer(String path){

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);

        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {

        }

        this.mediaPlayer = player;

    }

    /**
     * gets the duration
     * @return
     */
    public String getDuration(MediaPlayer mediaPlayer){
        return formatDuration(mediaPlayer.getMedia().getDuration().toMillis());
    }


    /**
     * formats durations to minutes : seconds
     * @param duration
     * @return
     */
    private String formatDuration(double duration){

        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) duration) -  TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }

    /**
     * play media
     */
    public void play(){
        this.mediaPlayer.play();
    }

    /**
     * stop media
     */
    public void stop() {

        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
    }
}