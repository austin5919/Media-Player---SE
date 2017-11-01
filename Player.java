import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * This class haddles playing songs.
 */
public class Player {

    private MediaPlayer mediaPlayer;

    /**
     * Empty constructor to call without passing in anything.
     */
    public Player() {
    }

    /**
     * This constructor will simply set the media player so that i can get it when desired
     *
     * @param path takes in a path and sets up the media player using the path.
     */
    public Player(String path) {
        setMediaPlayer(path);
    }

    //getter
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Sets the media player.
     *
     * @param path takes in a path and builds a media player.
     */
    public void setMediaPlayer(String path) {
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {

        }

        this.mediaPlayer = player;
    }

    /**
     * @return duration of the current song.
     */
    public String getDuration() {
        return formatDuration(getMediaPlayer().getMedia().getDuration().toMillis());
    }

    /**
     * formats durations to minutes : seconds
     *
     * @param duration takes in the duration in millis to be used in calculations.
     * @return The duration as a String in format mm:ss.
     */
    private String formatDuration(double duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }

    /**
     * set the media player on play to produce sounds
     */
    public void play() {
        mediaPlayer.play();
    }

    /**
     * sets the media player on stop to stop producing sounds
     */
    public void stop() {
        if (this.mediaPlayer == null) {
            return;
        }
        mediaPlayer.stop();
    }
}