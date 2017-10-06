

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * This class simply sets the handlers for the 
 * GUI components and sets the components to 
 * interact with the MusicPlayer.java class
 * @author austin
 * @author jose
 */
public class MediaController {
    
    MusicPlayer musicplayer = new MusicPlayer();
    
    /**
     * sets my buttons
     * @param fileChooser 
     */
    public void setButton(Button fileChooser){
        
        //assign a handler to the filerChooser
        fileChooser.setOnAction(this::handleFileChooserButtonAction);
    }
    
    /**
     * sets the display table view
     * @param display 
     */
    public void setDisplayTable(TableView<Song> display){
        
        this.musicplayer.setTableViewDisplay(display);
        this.musicplayer.refreshPlaylist();
        //assign a handler to the tableview
        display.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.musicplayer.playSong();
                //System.out.println("new selection : " + obs.getValue().getSongName());
            }
            //System.out.println("new selection : " + obs.getValue().getSongName());
        });
        
        display.setOnMousePressed(this::handleDisplayTableEvents);
        
        
    }
    
    /*
     * handles file chooser click event
     */
    private void handleFileChooserButtonAction(ActionEvent event) {
        //create a new file chooser
        FileChooser getFile = new FileChooser();
        File  theFile = getFile.showOpenDialog(null);
        
        //check that the theFile 
        //holder is not null
        if (theFile != null){
            
            //get name of song
            String songName = theFile.getName().replace(".mp3", "");
            
            //get songLink
            String songLink = theFile.getAbsolutePath();
            //add song
            this.musicplayer.createSongObject(songName,songLink);
            
        }
    }
    
    
    /*
     * handles the tableView click event
     */
    private void handleDisplayTableEvents(MouseEvent event){
        
        
    }
}
