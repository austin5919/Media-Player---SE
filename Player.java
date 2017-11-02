import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * This class handles the media player.
 */
public class Player {
    //local variable
    private MediaPlayer mediaPlayer;

    /**
     * a constructor to set local variables.
     */
    public Player() {
        this.mediaPlayer = null;
    }

    /**
     * gets the media player. The media player will
     * be used to manipulate the current loaded song
     * to the users will.
     *
     * @return the media player
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * creates a media and then attaches to the media player
     *
     * @param path takes in a path to set the media.
     */
    public void setMediaPlayer(String path) {
        //set the media use it to create the media player
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);

        //wait 200 milliseconds to give the player time to initialize
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {

        }

        //set the player
        this.mediaPlayer = player;
    }

    /**
     * this method gets the duration of the current song in millis
     * and then reformats it to mm:ss
     *
     * @return duration of the current song in mm:ss.
     */
    public String getDuration() {
        return formatDuration(getMediaPlayer().getMedia().getDuration().toMillis());
    }

    //formats duration to mm:ss
    private String formatDuration(double duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }

    /**
     * plays the song
     */
    public void play() {
        mediaPlayer.play();
    }

    /**
     * stops the song
     */
    public void stop() {
        //checks if the media player is null
        if (mediaPlayer == null) {
            return;
        }

        //stops media player if not null
        mediaPlayer.stop();
    }

    /**
     * deletes the media player
     */
    public void Dispose() {
        //check if media player is null
        if (mediaPlayer == null) {
            return;
        }

        //trash the media player
        mediaPlayer.dispose();
    }
}