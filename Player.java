import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * This class handles the media player.
 */
public class Player {
    //local variable
    private MediaPlayer mediaPlayer;
    private int counter;
    private Timeline timeLine;
    private int startTimer;
    private Label songName;
    private TableView<Song> songs;
    Iterator<Song> search;
    private String selectedSong;
    private int focusedIndex;

    /**
     * a constructor to set local variables.
     */
    public Player() {
        this.mediaPlayer = null;
        this.startTimer = 0;
        this.counter = startTimer;
        this.songs = new TableView<>();
    }

    public void setSongs(TableView<Song> songs) {
        this.songs = songs;
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
    public String formatDuration(double duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }

    private void timer(Label timer, String duration){
        if(timeLine != null){
            timeLine.stop();
        }

        counter = startTimer;

        this.timeLine = new Timeline();
        timeLine.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                counter++;
                timer.setText(String.valueOf("(" + formatDuration( counter * 1000) + "/" + duration + ")"));
                if(counter == startTimer){
                    timeLine.stop();
                }else if(formatDuration(counter * 1000).equals(duration)){
                    timeLine.stop();
                }
            }
        });
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.playFromStart();
    }

    /**
     * plays the song
     */
    public void play(Label timer, String duration) {
        timer(timer,duration);
        mediaPlayer.play();
    }

    public void setAutoPlay(Label timer, String duration, int startTimer,Label songName){
        this.startTimer = startTimer;
        this.songName = songName;
        timer(timer,duration);
        this.focusedIndex = songs.getFocusModel().getFocusedIndex();
        autoPlayer(songs.getItems().get(focusedIndex).getPath());


    }

    private void autoPlayer(String path){
        //set the media use it to create the media player
        setMediaPlayer(path);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            stop();
            focusedIndex = focusedIndex + 1;
            if(focusedIndex < songs.getItems().size()){
                songs.getFocusModel().focusNext();
                songName.setText(songs.getItems().get(focusedIndex).getName());
                autoPlayer(songs.getItems().get(focusedIndex).getPath());
            }

            return;
        });
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