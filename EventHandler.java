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

    private MP3Player player;
    private String selectedSong;

    /**
     * Setting a few variables at launch to make
     * debugging easier.
     */
    public EventHandler() {
        this.player = new MP3Player();
    }

    /**
     * Sets the handlers for the ComboBox and the file chooser button.
     *
     * @param comboBox      Takes in the ComboBox for the dropdown.
     * @param addSongButton Takes in a button to handle the file chooser.
     */
    public void setTopComponents(ComboBox comboBox, Button addSongButton) {
        this.player.getComponents().setComboBox(comboBox);
        addSongButton.setOnAction(this::browserButtonAction);
        comboBoxHandler(comboBox);
    }

    /**
     * Sets up the right click menu. Still under construction.
     *
     * @param addToPlaylist Takes in a menu.
     * @param dropMenu      Takes in a context menu to hold menu/menuItems.
     */
    public void setContextMenu(Menu addToPlaylist, ContextMenu dropMenu) {
        this.player.getComponents().setMenu(addToPlaylist);
        this.player.loadListOfPlaylist();
    }

    //comboBox handler
    private void comboBoxHandler(ComboBox playList) {
        playList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                return;
            }

            ComboBox comboBox = this.player.getComponents().getComboBox();
            if (comboBox.getSelectionModel().getSelectedItem() == "Library") {
                //switch to library state
                this.player.switchToLibrary();
            } else if (comboBox.getSelectionModel().getSelectedItem() == "Create Playlist") {
                this.player.createPlaylist();
            } else {
                //switch to other playlist state
                this.player.switchToPlaylist();
            }
        });
    }

    //he handler for the file chooser
    private void browserButtonAction(ActionEvent e) {
        FileChooser getFile = new FileChooser();
        getFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("filter search", "*.mp3", "*.mp4"));
        File theFile = getFile.showOpenDialog(null);

        //check if what you pick is null
        if (theFile == null) {
            return;
        }

        //set the song path
        String songPath = theFile.getAbsolutePath();
        this.player.getLibrary().refreshLibrary();

        if (new Exist().CheckList(songPath, this.player.getLibrary().getLibrary())) {
            ArrayList<String> newSongs = new ArrayList<>();
            newSongs.add(songPath);
            //call browser method
            this.player.addSong(newSongs);
        } else {
            System.out.println("song is already in the library");
        }
    }

    /**
     * sets up the display
     *
     * @param songTableView takes in the tableView to hold all the songs
     */
    public void setCenterComponents(TableView<Song> songTableView) {
        this.player.getComponents().setDisplay(songTableView);
        this.player.getLibrary().refreshLibrary();
        new Updates(this.player.getLibrary().getLibrary(), this.player.getComponents()).updateDisplay("Rebuild");
        songTableView.setOnMouseClicked(this::handleDisplayTableEvents);
    }

    //this method simply handles the actions of the tableView
    private void handleDisplayTableEvents(MouseEvent e) {
        TableView<Song> display = this.player.getComponents().getDisplay();
        if (display.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        if (e.getButton() == MouseButton.PRIMARY) {
            handleCLicks(display);

            if (new File(this.selectedSong).exists()) {
                this.player.loadNewTrack(this.selectedSong);
                this.player.playSong();
            } else {
                System.out.println("selected song does not exist");
            }
        } else if (e.getButton() == MouseButton.SECONDARY) {
            handleCLicks(display);
            System.out.println("under construction");
        }
    }

    //handles the clicks settings
    private void handleCLicks(TableView<Song> display) {
        this.player.getComponents().setSelectedIndex(display.getSelectionModel().getSelectedIndex());
        this.selectedSong = display.getItems().get(this.player.getComponents().getSelectedIndex()).getSongPath();
        display.getSelectionModel().clearSelection();
        display.getFocusModel().focus(this.player.getComponents().getSelectedIndex());
    }
}
