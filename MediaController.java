import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

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
    
    public Button setFirstSongButton(Button button){
        firstSongButton = button;
        firstSongButton.setText("Pride Of The Wolverines");
        firstSongButton.setOnAction(this::handleButton1Action);
        return button;
    }
    
    public Button setSecondSongButton(Button button){
        secondSongButton = button;
        secondSongButton.setText("Prefunk Loop");
        secondSongButton.setOnAction(this::handleButton2Action);
        return button;
    }
    
    public Button setThirdSongButton(Button button){
        thirdSongButton = button;
        thirdSongButton.setText("Here We Go");
        thirdSongButton.setOnAction(this::handleButton3Action);
        return button;
    }
    
    public Button setFileChooserButton(Button button){
        fileChooserButton = button;
        //System.out.println();
        fileChooserButton.setText("Browse a file");
        fileChooserButton.setOnAction(this::handleButton4Action);
        return button;
    }
    
    private void handleButton1Action(ActionEvent event) {
     
        setMediaPlayer(firstSong);
        playMedia();
        
        //System.out.println("dasdada");
    }
    
    private void handleButton2Action(ActionEvent event) {
        
        setMediaPlayer(secondSong);
        playMedia();
        //System.out.println("walaa");
    }
    
    
    private void handleButton3Action(ActionEvent event) {
        setMediaPlayer(thirdSong);
        playMedia();
        //System.out.println("bling");
    }
    
    private void handleButton4Action(ActionEvent event) {
        FileChooser getFile = new FileChooser();
        File  theFile = getFile.showOpenDialog(null);
        
        if (theFile != null){
            //System.out.println(theFile);
            setMediaPlayer(theFile.toString());
            playMedia();
        }
        
        //System.out.println("bling");
    }
    
    
    private void setMediaPlayer(String song){
        
        if(isMediaPlayerNull() == false){
                mediaPlayer.stop();
        } 
        
        Media media = new Media(new File(song).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }
    
    private void playMedia(){
        
        if(isMediaPlayerNull() == false){
             mediaPlayer.play();
        }else{
           System.out.println("call loadSong method first, there is nothing to play");
        } 
    }
    
    private boolean isMediaPlayerNull(){
        if(mediaPlayer == null){
            return true;
        }
        
        return false;
    }
}
