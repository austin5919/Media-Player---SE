import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * This class haddles playing songs.
 */
public class Player {

    private MediaPlayer mediaPlayer;

    /**
     * Empty constructor to call
     * without passing in anything.
     */
    public Player() {
    }

    /**
     * Takes in a path and sets te mediaplayer.
     *
     * @param path Takes in a path and sets up the media player.
     */
    public Player(String path) {
        setMediaPlayer(path);
    }

    /**
     * Gets the mediaplayer
     *
     * @return The recent MediaPlayer.
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Sets the media player.
     *
     * @param path Takes in a path and builds a media player.
     */
    public void setMediaPlayer(String path) {
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);

        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {

        }

        this.mediaPlayer = player;
    }

    /**
     * Gets the duration
     *
     * @return Duration of the current song in millis.
     */
    public String getDuration() {
        return formatDuration(getMediaPlayer().getMedia().getDuration().toMillis());
    }


    /**
     * Formats durations to minutes : seconds
     *
     * @param duration The duration in millis.
     * @return The duration as a String in format mm:ss - where mm is the two digit number of minutes and ss is the two digit number of seconds.
     */
    private String formatDuration(double duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }

    /**
     * Play media
     */
    public void play() {
        this.mediaPlayer.play();
    }

    /**
     * Stop media
     */
    public void stop() {
        if (this.mediaPlayer == null) {
            return;
        }

        this.mediaPlayer.stop();
    }
}