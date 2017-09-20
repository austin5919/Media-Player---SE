import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

/**
 * controls the view events and interacts with data
 * 
 * @author Austin Ash
 * @author Jose Cruz
 */
public class MediaController {
    MediaController(){
        
    }
    
    private Button firstSongButton;
    private String firstSong = "./songs/PrideOfTheWolverines.mp3";
    
    private Button secondSongButton;
    private String secondSong = "./songs/Prefunk Loop.mp3"; 
    
    private Button thirdSongButton;
    private String thirdSong = "./songs/Here We Go.mp3"; 
    
    private Button fileChooserButton;
    
    private MediaPlayer mediaPlayer;
	
    /**
     * sets the values and settings of the button passed in
     * at the moment this one controls the firstSongButton
     * @param button a button to help handle click events
     * @return a button
     */
    public Button setFirstSongButton(Button button){
        this.firstSongButton = button;
        this.firstSongButton.setText("Pride Of The Wolverines");
        this.firstSongButton.setOnAction(this::handleFirstSongButtonAction);
        return button;
    }
    
    /**
     * sets the values and settings of the button passed in
     * at the moment this one controls the secondSongButton
     * @param button to help handle click events
     * @return a button
     */
    public Button setSecondSongButton(Button button){
        this.secondSongButton = button;
        this.secondSongButton.setText("Prefunk Loop");
        this.secondSongButton.setOnAction(this::handleSecondSongButtonAction);
        return button;
    }
	
    /**
     * sets the values and settings of the button passed in
     * at the moment this one controls the thirdSongButton
     * @param button a button to help handle click events
     * @return a button
     */
    public Button setThirdSongButton(Button button){
        this.thirdSongButton = button;
        this.thirdSongButton.setText("Here We Go");
        this.thirdSongButton.setOnAction(this::handleThirdSongButtonAction);
        return button;
    }
	
    /**
     * sets the values and settings of the button passed in
     * at the moment this one controls the fileChooserButton
     * @param button a button to help handle click events
     * @return a button
     */
    public Button setFileChooserButton(Button button){
        this.fileChooserButton = button;
        //System.out.println();
        this.fileChooserButton.setText("Browse a file");
        this.fileChooserButton.setOnAction(this::handleFileChooserButtonAction);
        return button;
    }
	
    /**
     * controls the behavior of a button
     * this method handles events that belong to
     * this firstSongButton
     * @param event helps handle events
     */
    private void handleFirstSongButtonAction(ActionEvent event) {
     
        setMediaPlayer(this.firstSong);
        playMedia();
        
        //System.out.println("dasdada");
    }
	
    /**
     * controls the behavior of a button
     * this method handles events that belong to
     * this secondSongButton
     * @param event helps handle events
     */
    private void handleSecondSongButtonAction(ActionEvent event) {
        
        setMediaPlayer(this.secondSong);
        playMedia();
        //System.out.println("walaa");
    }
    
    /**
     * controls the behavior of a button
     * this method handles events that belong to
     * this thirdSongButton
     * @param event helps handle events
     */
    private void handleThirdSongButtonAction(ActionEvent event) {
        setMediaPlayer(this.thirdSong);
        playMedia();
        //System.out.println("bling");
    }
	
    /**
     * controls the behavior of a button
     * this method handles events that belong to
     * this fileChooserButton
     * @param event helps handle events
     */
    private void handleFileChooserButtonAction(ActionEvent event) {
        FileChooser getFile = new FileChooser();
        File  theFile = getFile.showOpenDialog(null);
        
        if (theFile != null){
            //System.out.println(theFile);
            setMediaPlayer(theFile.toString());
            playMedia();
        }
        
        //System.out.println("bling");
    }
    
    /**
     * checks if there is anything playing and pauses if
     * there is and then sets a new instance of a song
     * @param song takes in the song you want to play
     */
    private void setMediaPlayer(String song){
        
        if(isMediaPlayerNull() == false){
                mediaPlayer.stop();
        } 
        
        Media media = new Media(new File(song).toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
    }
    
    /**
     * simply plays the instance of a media created
     */
    private void playMedia(){
        
        if(isMediaPlayerNull() == false){
             mediaPlayer.play();
        }else{
           System.out.println("call loadSong method first, there is nothing to play");
        } 
    }
    
    /**
     * checks if the media instance is null
     * @return false or true depending on the status of the media
     */
    private boolean isMediaPlayerNull(){
        if(mediaPlayer == null){
            return true;
        }
        
        return false;
    }
}
