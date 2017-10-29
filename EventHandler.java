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
    ContextMenuState contextMenu;
    ContextMenuState contextMenuState;

    private MP3Player player;
    private String selectedSong;

    /**
     * Setting a few variables at launch to make
     * debugging easier.
     */
    public EventHandler() {
        this.player = new MP3Player();
        this.contextMenu = new MenuContent(this);
        this.contextMenuState = contextMenu;
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
     * @param contextMenu Takes in a context menu to hold menu/menuItems.
     */
    public void setContextMenu(ContextMenu contextMenu) {
        this.player.getComponents().setMenu(contextMenu);
        contextMenuState.createContent(this.player.getComponents().getComboBox());
        contextMenuState.updateContextMenu(this.player.getComponents().getMenu());
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
            } else if (comboBox.getSelectionModel().getSelectedItem() == "New Playlist") {
                this.player.createPlaylist();
            } else {
                //switch to other playlist state
                //this.player.switchToPlaylist();
                System.out.println("this action will put you in playlist state. Commented it out for now");
                System.out.println("you are still able to switch to this playlist to show that the Context");
                System.out.println("updated correctly");
            }
            this.contextMenuState.createContent(this.player.getComponents().getComboBox());
            contextMenuState.updateContextMenu(this.player.getComponents().getMenu());
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
        this.player.getComponents().getMenu().hide();
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
            this.player.getComponents().getMenu().show(display, e.getScreenX(), e.getScreenY());
        }
    }

    //handles the clicks settings
    private void handleCLicks(TableView<Song> display) {
        this.player.getComponents().setSelectedIndex(display.getSelectionModel().getSelectedIndex());
        this.selectedSong = display.getItems().get(this.player.getComponents().getSelectedIndex()).getPath();
        display.getSelectionModel().clearSelection();
        display.getFocusModel().focus(this.player.getComponents().getSelectedIndex());
    }

    public void contextMenuHandler(String contextMenuSelection) {
        //System.out.println(contextMenuSelection);
        if(contextMenuSelection == "New Playlist"){
            System.out.println("code to create playlist starts here");
            return;
        }

        if(contextMenuSelection == "Play Song"){
            //handleCLicks(this.player.getComponents().getDisplay());
            this.player.loadNewTrack(this.selectedSong);
            this.player.playSong();
            return;
        }

        System.out.println("code to add to playlist starts here");

    }
}
