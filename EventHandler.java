import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;

/**
 * This class handles the listeners for each component
 * on the GUI.
 */
public class EventHandler {
    private String selectedSong;
    private String libraryPath;
    private int selectedIndex;


    private GUIObjects guiObjects;
    private MP3Player player;
    private Read reader;

    SafeUpdate safeUpdate;

    /**
     * Setting a few variables at launch to make
     * debugging easier.
     */
    public EventHandler() {
        this.reader = new Read();
        this.player = new MP3Player();
        this.guiObjects = new GUIObjects();

        this.libraryPath = "./library.data";
        this.selectedIndex = -1;
        this.selectedSong = null;

        this.safeUpdate = new SafetyMeasures(this);
    }

    /**
     * Sets the handlers for the ComboBox and the file chooser button.
     *
     * @param comboBox      Takes in the ComboBox for the dropdown.
     * @param addSongButton Takes in a button to handle the file chooser.
     */
    public void setTopComponents(ComboBox comboBox, Button addSongButton) {
        guiObjects.setComboBox(comboBox);
        comboBox = guiObjects.getComboBox();

        addSongButton.setOnAction(this::browserButtonAction);
        comboBoxHandler(comboBox);
    }

    /**
     * Sets up the right click menu. Still under construction.
     *
     * @param contextMenu Takes in a context menu to hold menu/menuItems.
     */
    public void setContextMenu(ContextMenu contextMenu) {
        guiObjects.setContextMenu(contextMenu);
        setHandlersContextMenu();
        player.loadListOfPlaylist();
    }

    public void setHandlersContextMenu(){
        ComboBox comboBox = guiObjects.getComboBox();
        ContextMenu contextMenu = guiObjects.getContextMenu();
        Menu menu = new Menu("Add To Playlist");
        ObservableList<String> observableList = comboBox.getItems();

        for (String choices : observableList) {
            if (observableList.isEmpty()) {
                break;
            }

            String selected = comboBox.getSelectionModel().getSelectedItem().toString();
            if (choices != "Library" && choices != selected) {
                MenuItem newMenuItem = new MenuItem(choices.toString());
                newMenuItem.setOnAction(event -> {
                    contextMenuOnclick(newMenuItem.getText());
                });
                menu.getItems().add(newMenuItem);
            }
        }

        MenuItem play = new MenuItem("Play Song");
        play.setOnAction(event -> {
            contextMenuOnclick(play.getText());
        });

        updateContextMenu(contextMenu,menu,play);

    }

    private void updateContextMenu(ContextMenu contextMenu,Menu menu, MenuItem menuItem){
        new Updates().updateContextMenu(contextMenu, menu, menuItem);
    }

    /**
     * set the popWindow
     *
     * @param stage        takes in a stage with the components i need
     * @param okButton     takes in an okButton to submit
     * @param cancelButton takes in a cancel button to cancel
     * @param input        takes in the actual textBox to extract the information
     */
    public void setPopWindow(Stage stage, Button okButton, Button cancelButton, TextField input) {
        guiObjects.setStage(stage);
        guiObjects.setTextField(input);

        stage = guiObjects.getStage();

        okButton.setOnAction(this::okButtonHandler);
        cancelButton.setOnAction(this::cancelButtonHandler);
        stage.setOnHidden(this::stageHandler);
    }

    //stage handler
    private void stageHandler(WindowEvent e) {
        TextField textField = guiObjects.getTextField();
        textField.clear();
    }

    //okButton handler
    private void okButtonHandler(ActionEvent e) {
        TextField textField = guiObjects.getTextField();
        String input = textField.getText();

        ComboBox comboBox = guiObjects.getComboBox();
        safeUpdate.updateCombobox(comboBox,input);
        //new Updates().updateComboBox(comboBox, input);
        Stage stage = guiObjects.getStage();
        stage.close();
    }

    //cancelButton handler
    private void cancelButtonHandler(ActionEvent e) {
        Stage stage = guiObjects.getStage();
        stage.close();
    }

    //comboBox handler
    private void comboBoxHandler(ComboBox playList) {
        playList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                return;
            }

            ComboBox comboBox = guiObjects.getComboBox();
            if (comboBox.getSelectionModel().getSelectedItem() == "Library") {
                player.switchToLibrary();
            } else if (comboBox.getSelectionModel().getSelectedItem() == "New Playlist") {
                Stage stage = guiObjects.getStage();
                player.createPlaylist(comboBox,(String)oldSelection,stage);
            } else {
                //switch to other playlist state
                //this.player.switchToPlaylist();
                System.out.println("this action will put you in playlist state. Commented it out for now");
                System.out.println("you are still able to switch to this playlist to show that the Context");
                System.out.println("updated correctly");
            }
            setHandlersContextMenu();
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

        //variables i need to pass in
        String songPath = theFile.getAbsolutePath();
        ArrayList<String> newSongs = new ArrayList<>();
        newSongs.add(songPath);
        TableView<Song> tableView = guiObjects.getTableView();

        //add song
        player.addSong(tableView, selectedIndex, newSongs);
    }

    /**
     * sets up the display
     *
     * @param tableView takes in the tableView to hold all the songs
     */
    public void setCenterComponents(TableView<Song> tableView) {
        guiObjects.setTableView(tableView);
        reader.setListOfPath(this.libraryPath);

        //variables i need to pass in
        tableView = guiObjects.getTableView();
        MusicList musicList = player.getMusicList();
        ArrayList<String> newSongs = reader.getListOfPath();

        //update
        new Updates().updateMusicList(tableView, selectedIndex, musicList, newSongs);

        //handler
        tableView.setOnMouseClicked(this::handleDisplayTableEvents);
    }

    //this method simply handles the actions of the tableView
    private void handleDisplayTableEvents(MouseEvent e) {
        ContextMenu contextMenu = guiObjects.getContextMenu();
        contextMenu.hide();
        TableView<Song> display = guiObjects.getTableView();
        if (display.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        if (e.getButton() == MouseButton.PRIMARY) {
            handleCLicks(display);

            if (player.getMusicList().containsSong(player.getMusicList().getSongByPath(selectedSong))) {
                //System.out.println(this.player.getMusicList().containsSong(this.player.getMusicList().getSongByPath(selectedSong)));
                player.loadNewTrack(selectedSong);
                player.playSong();
            } else {
                System.out.println("song is not in library..you should never see this");
            }

        } else if (e.getButton() == MouseButton.SECONDARY) {
            handleCLicks(display);
            contextMenu.show(display, e.getScreenX(), e.getScreenY());
        }
    }

    //handles the clicks settings
    private void handleCLicks(TableView<Song> display) {
        //System.out.println(this.player.getComponents().getDisplay().getSelectionModel().getSelectedItem().getDuration());
        this.selectedIndex = display.getSelectionModel().getSelectedIndex();
        this.selectedSong = display.getItems().get(selectedIndex).getPath();
        display.getSelectionModel().clearSelection();
        display.getFocusModel().focus(selectedIndex);
    }

    private void contextMenuOnclick(String contextMenuSelection) {
        //System.out.println(contextMenuSelection);
        if (contextMenuSelection == "New Playlist") {
            ComboBox comboBox = guiObjects.getComboBox();
            comboBox.getSelectionModel().select("New Playlist");
            return;
        }

        if (contextMenuSelection == "Play Song") {
            if (player.getMusicList().containsSong(player.getMusicList().getSongByPath(selectedSong))) {
                player.loadNewTrack(selectedSong);
                player.playSong();
            } else {
                System.out.println("song is not in library..you should never see this");
            }
            return;
        }
        System.out.println("code to add to playlist starts here");
    }
}
