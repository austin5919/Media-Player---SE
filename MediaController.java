package bestmusicplayer;

import java.io.File;
import javafx.event.ActionEvent;;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
/**
 * This class will handle all of my events that are
 * triggered by the view components.
 * 
 * @author austin
 * @author jose
 */
public class MediaController {
    MusicPlayer player = new MusicPlayer();
    TableView<Song> playList;
    
    private Button playButton;
    private Button pauseButton;
    private Button fileChooserButton;
    
    MediaController(){
        
    }
    
    /**
     * This method takes in two buttons and assigns a listener
     * to each one. It sets the buttons as a private field in this
     * class, and also sets them as a private field in the MusicPlayer.java
     * class. 
     * 
     * @param playButton
     * @param pauseButton 
     */
    public void setPlayPauseSongButton(Button playButton, Button pauseButton){
        this.playButton = playButton;
        this.pauseButton = pauseButton;
        this.player.setPlayPauseButton(playButton, pauseButton);
        this.playButton.setOnAction(this::playPauseButtonActionEvent);
        this.pauseButton.setOnAction(this::playPauseButtonActionEvent);
        
    }
    
    /*
     * An event that is triggered by the play or pause button. It calls
     * the MediaPlayer.java class and based on the state of the MusicPlayer
     * it will play a song or pause the song.
     */
    private void playPauseButtonActionEvent(ActionEvent event){
        this.player.playSongSingleClick();
        update();
        
    }
    
    /**
     * This method takes in a tableView and passes it to the
     * MusicPlayer.java class to be populated with songs. It then
     * fetches the updated play list from the MusicPlayer.java class
     * and sets it as a private field in this class to be re-used. It
     * also sets a MouseEvent to the TableView.
     * 
     * @param newPlaylist 
     */
    public void setTableView(TableView newPlaylist){
        this.player.populatePlaylist(newPlaylist);
        this.playList = this.player.getPlayList();
        this.playList.getSelectionModel().selectFirst();
        this.playList.setOnMouseClicked(this::playListMouseEvent);
    }
    
    /*
     * this handles the events of the TableView. It calls Musicplayer.java
     * to play the song double clicked.
     */
    private void playListMouseEvent(MouseEvent event){
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            player.playSongDoubleClick();
            event.consume();
            update();
        }else{
            player.changeDetected();
            update();
        }
    }
    
    /*
     * Go in to the MusicPlayer.java to update
     * this class private fields.
     */
    private void update(){
        this.playButton = this.player.getPlayButton();
        this.pauseButton = this.player.getPauseButton();
        this.playList = this.player.getPlayList();
    }
    
    //TODO:set the filer chooser
    public Button setFileChooserButton(Button button){
        this.fileChooserButton = button;
        this.fileChooserButton.setText("Browse a file");
        this.fileChooserButton.setOnAction(this::handleFileChooserButtonAction);
        return button;
    }
    
    //TODO:setfile chooser button
    private void handleFileChooserButtonAction(ActionEvent event) {
        FileChooser getFile = new FileChooser();
        File  theFile = getFile.showOpenDialog(null);
        
        if (theFile != null){
            System.out.println(theFile.getName());
            System.out.println(theFile.toString());
            //player.loadSong(theFile.toString());
            //player.playSong();
        }
        
        //play song. i will need to pass in the playlist
            //so that i could get selected items. I will need to
            //pass in the musicPlayer so that i could play the music
            //Song song = new Song();
            //song.setSongName("testing things");
            //song.setSongLenght("45test");
            //song.setSongLink("dddddddddddddd");
            //this.playList.getItems().addAll(song);
        
        //System.out.println("bling");
    }
}