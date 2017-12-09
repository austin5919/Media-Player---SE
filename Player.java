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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * This class handles the media player.
 */
public class Player {
    //local variable
    private MediaPlayer mediaPlayer;
    private Timeline timeLine;
    int counter;

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
    public String formatDuration(double duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) duration)),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) duration)));
    }

    public void timer(Label timer, TableView<Song> tableView, String path, int selectedIndex){
        if(timeLine != null){
            timeLine.stop();
        }

        String totalDuration = totalDuration(tableView, path, selectedIndex);

        this.timeLine = new Timeline();
        timeLine.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                counter++;
                timer.setText(String.valueOf("(" + formatDuration( counter * 1000) + "/" + totalDuration + ")"));
                if(formatDuration(counter * 1000).equals(totalDuration)){
                    timeLine.stop();
                }
                getMediaPlayer().setOnStopped(new Runnable() {
                    @Override
                    public void run() {
                        timeLine.stop();
                    }
                });
            }
        });
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.playFromStart();
    }

    public String totalDuration(TableView<Song> tableView, String path, int selectedIndex){

        int duration = 0;

        for (int i = 0; i < tableView.getItems().size(); i++) {
            if(tableView.getItems().size() != 1){
                if (selectedIndex == i) {
                    this.counter = duration;
                }
            }else{

                if (tableView.getItems().get(i).getPath().equals(path)) {
                    this.counter = duration;
                }
            }


            String parse = tableView.getItems().get(i).getDuration();
            String minutes = parse.substring(0, parse.indexOf(":"));
            String seconds = parse.substring(parse.indexOf(":") + 1, parse.length());
            duration = duration + ((Integer.valueOf(minutes) * 60) + (Integer.valueOf(seconds)));
        }


        return formatDuration(duration * 1000);
    }

    /**
     * plays the song
     */
    public void play(Label timer, String duration) {
        mediaPlayer.play();
    }

    private void autoPlayer(String path){

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