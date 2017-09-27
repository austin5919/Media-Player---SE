import java.io.File;
import javafx.event.ActionEvent;;
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
     MusicPlayer player = new MusicPlayer();
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
        
        player.loadSong(this.firstSong);
        player.playSong();
    }
	
    /**
     * controls the behavior of a button
     * this method handles events that belong to
     * this secondSongButton
     * @param event helps handle events
     */
    private void handleSecondSongButtonAction(ActionEvent event) {
        
       player.loadSong(this.secondSong);
       player.playSong();
    }
    
    /**
     * controls the behavior of a button
     * this method handles events that belong to
     * this thirdSongButton
     * @param event helps handle events
     */
    private void handleThirdSongButtonAction(ActionEvent event) {
        player.loadSong(this.thirdSong);
        player.playSong();
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
            player.loadSong(theFile.toString());
            player.playSong();
        }
        
        //System.out.println("bling");
    }
}