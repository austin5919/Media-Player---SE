import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.ArrayList;

/**
 * This class handles the listeners for each component
 * on the GUI.
 */
public class EventHandler {

    private MP3Player player = new MP3Player();
    private Components components;
    private Library library;
    private String selectedSong;

    /**
     * Setting a few variables at launch to make
     * debugging easier.
     */
    public EventHandler(){
        this.components = this.player.getComponents();
        this.library = this.player.getLibrary();
    }

    /**
     * Sets the handlers for the ComboBox and the file chooser button.
     *
     * @param comboBox  Takes in the ComboBox for the dropdown.
     * @param addSongButton  Takes in a button to handle the file chooser.
     */
    public void setTopComponents(ComboBox comboBox, Button addSongButton) {

        //set the playlist comboBox
        this.components.setComboBox(comboBox);

        //call the comboBox handler
        comboBoxHandler(comboBox);

        //call the file chooser handler
        addSongButton.setOnAction(this::browserButtonAction);

    }

    /**
     * Sets up the right click menu. Still under construction.
     *
     * @param addToPlaylist  Takes in a menu.
     * @param dropMenu  Takes in a context menu to hold menu/menuItems.
     */
    public void setContextMenu(Menu addToPlaylist, ContextMenu dropMenu){

        //TODO:set up the context menu

        //set the MenuItems
        this.components.setMenu(addToPlaylist);

        this.player.loadListOfPlaylist();


    }

    //comboBox handler
    private void comboBoxHandler(ComboBox playList){

        playList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            //check if new selection is null
            if (newSelection != null) {

                //check if selection equals library, create new or other playlist
                if(this.components.getComboBox().getSelectionModel().getSelectedItem() == "Library"){

                    //switch to library state
                    this.player.switchToLibrary();


                }else if(this.components.getComboBox().getSelectionModel().getSelectedItem() == "Create Playlist"){

                    this.player.createPlaylist();

                }else{

                    //switch to other playlist state
                    this.player.switchToPlaylist();
                }
            }
        });

    }

    //he handler for the file chooser
    private void browserButtonAction(ActionEvent e){

        //set the file chooser
        FileChooser getFile = new FileChooser();
        getFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("filter search", "*.mp3","*.mp4"));
        File theFile = getFile.showOpenDialog(null);

        //check if what you pick is null
        if (theFile != null){

            //set the song path
            String songPath = theFile.getAbsolutePath();

            this.library.refreshLibrary();
            if(new Exist().CheckList(songPath, library.getLibrary())){

                ArrayList<String> newSongs = new ArrayList<>();
                newSongs.add(songPath);
                //call browser method
                this.player.addSong(newSongs);

            }else{
                System.out.println("song is already in the library");
            }
        }

    }

    /**
     * sets up the display
     *
     * @param songTableView takes in the tableView to hold all the songs
     */
    public void setCenterComponents(TableView<Song> songTableView) {

        //set the display
        this.components.setDisplay(songTableView);
        this.library.refreshLibrary();

        new Updates(this.library.getLibrary(),components).refreshDisplay();

        //set display handler
        songTableView.setOnMouseClicked(this::handleDisplayTableEvents);

    }

    //this method simply handles the actions of the tableView
    private void handleDisplayTableEvents(MouseEvent e){


        //check if selection is null
        if(e.getButton() == MouseButton.PRIMARY && this.components.getDisplay().getSelectionModel().getSelectedItem() != null){
            this.components.setSelectedIndex(this.components.getDisplay().getSelectionModel().getSelectedIndex());
            this.selectedSong = this.components.getDisplay().getItems().get(this.components.getSelectedIndex()).getSongPath();

            this.components.getDisplay().getSelectionModel().clearSelection();

            this.components.getDisplay().getFocusModel().focus(this.components.getSelectedIndex());

            //check if the song actually exist
            if(new File(this.selectedSong).exists()){

                //load the new song in to the media player
                this.player.loadNewTrack(this.selectedSong);

                //play the song
                this.player.playSong();

            }else{
                System.out.println("selected song does not exist");
                //this.components.getDisplay().getItems().remove(this.components.getSelectedIndex());
            }


        }else if(e.getButton() == MouseButton.SECONDARY && this.components.getDisplay().getSelectionModel().getSelectedItem() != null){

            this.components.setSelectedIndex(this.components.getDisplay().getSelectionModel().getSelectedIndex());
            this.selectedSong = this.components.getDisplay().getItems().get(this.components.getSelectedIndex()).getSongPath();

            this.components.getDisplay().getSelectionModel().clearSelection();

            this.components.getDisplay().getFocusModel().focus(this.components.getSelectedIndex());

            System.out.println("under construction");

        }
    }
}
