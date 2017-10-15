import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * this class handles the listeners for each component
 * on the GUI
 */
public class EventHandler {

    private MusicPlayer player = new MusicPlayer();

    private ViewComponentOutput output;
    private ViewComponents comp;
    private MyPlayList myplay;

    /**
     * setting a few variables at launch to make
     * debugging easier
     */
    public EventHandler(){

        this.output = this.player.getViewCompOutClass();
        this.comp = this.player.getViewCompClass();
        this.myplay = this.player.getMyPlayListClass();
    }

    /**
     * sets the handlers for the ComboBox and the file chooser button
     * @param playList
     * @param browse
     */
    public void setTopComponents(ComboBox playList, Button browse) {

        //set the playlist comboBox
        this.comp.setPlayListName(playList);

        //call the comboBox handler
        comboBoxHandler(playList);

        //call the file chooser handler
        browse.setOnAction(this::browserButtonAction);

    }

    //comboBox handler
    private void comboBoxHandler(ComboBox playList){

        playList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            //check if new selection is null
            if (newSelection != null) {

                //check if selection equals library, create new or other playlist
                if(this.comp.getPlayListName().getSelectionModel().getSelectedItem() == "Library"){

                    //switch to library state
                    this.player.switchToLibrary();

                }else if(this.comp.getPlayListName().getSelectionModel().getSelectedItem() == "Create Playlist"){

                    //TODO:Create playlist

                    //switch to other playlist state
                    this.player.switchToOtherPlaylist();

                    System.out.println("code to create playlist is under construction..!!");
                    System.out.println("the file chooser can still browse files but nothings happens...!!");
                    System.out.println("to test the file chooser functionality please go back to the Library...!!");

                }else{

                    //switch to other playlist state
                    this.player.switchToOtherPlaylist();
                }

                //load library
                this.player.loadLibrary();
            }
        });

    }

    //he handler for the file chooser
    private void browserButtonAction(ActionEvent e){

        //set the file chooser
        FileChooser getFile = new FileChooser();
        File theFile = getFile.showOpenDialog(null);

        //check if what you pick is null
        if (theFile != null){

            //set the song name
            String songName = theFile.getName().replace(".mp3", "");
            this.output.setBrowserSongName(songName);

            //set the song path
            String songPath = theFile.getAbsolutePath();
            this.output.setBrowserPath(songPath);

            //call browser method
            this.player.browseSong();

        }

    }

    /**
     * this method will set my TableView to be able to view
     * songs
     * @param display
     */
    public void setCenterComponents(TableView<Song> display) {

        //set the display
        this.comp.setDisplay(display);

        //load the library
        this.player.loadLibrary();

        //set display handler
        display.setOnMouseClicked(this::handleDisplayTableEvents);

    }

    //this method simply handles the actions of the tableView
    private void handleDisplayTableEvents(MouseEvent e){

        //check if selection is null
        if(this.player.getViewCompClass().getDisplay().getSelectionModel().getSelectedItem() != null){

            //set the background player for later on auto play uses if with have to implement it
            this.myplay.setBackgroundPlayer(this.comp.getDisplay());

            //set the selection index. i will use this to recover the focused index when switch back to
            //a main playlist
            this.output.setSelectedIndex(this.comp.getDisplay().getSelectionModel().getSelectedIndex());

            //set the selected song to play it
            this.output.setSelectedSong(this.myplay.getBackgroundPlayer().getItems().get(this.output.getSelectedIndex()).getSongPath());

            //make the selected item in the display null, this way when you click blank
            //spaces it doesnt fire the handler
            this.comp.getDisplay().getSelectionModel().clearSelection();

            //set the focus towards the selected index. this immitates the selected item
            //without actually selecting it.
            this.comp.getDisplay().getFocusModel().focus(this.output.getSelectedIndex());

            //load the new song in to the media player
            this.player.loadNewTrack();

            //play the song
            this.player.playSong();
        }
    }
}
