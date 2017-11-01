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
    private String comboBoxContent;
    private int selectedIndex;


    private GUIObjects guiObjects;
    private MP3Player player;
    private Read read;
    private Write write;

    /**
     * Setting a few variables at launch to make
     * debugging easier.
     */
    public EventHandler(
            ComboBox listDropDown, Button browswer,
            TableView<Song> tableView, Stage userInput,
            TextField textInput, Button okButton,
            Button cancelButton, ContextMenu contextMenu
    ) {

        this.read = new Read();
        this.write = new Write();
        this.player = new MP3Player();
        this.guiObjects = new GUIObjects();

        this.libraryPath = "./library.data";
        this.comboBoxContent = "./ComboBoxContent.data";
        this.selectedIndex = -1;
        this.selectedSong = null;

        //components
        guiObjects.setComboBox(listDropDown);
        guiObjects.setBrowswer(browswer);
        guiObjects.setDisplayTableView(tableView);
        guiObjects.setStage(userInput);
        guiObjects.setTextField(textInput);
        guiObjects.setOkButton(okButton);
        guiObjects.setCancelButton(cancelButton);
        guiObjects.setContextMenu(contextMenu);

        loadInformation();
        setHandlers();
    }

    private void loadInformation() {
        //TODO:load library.data
        read.setListOfPath(this.libraryPath);
        ArrayList<String> libraryContent = read.getListOfPath();
        //TODO:Use library.data to set up the MusicList and load display
        /* they must be set at the same time because of the delay
         * caused by set on ready when getting duration.
         */
        new Updates().updateMusicList(
                guiObjects.getDisplayTableView(),
                selectedIndex, player.getMusicList(),
                libraryContent
        );
        //TODO:Load the ComboBoxContent.data
        read.setListOfPath(comboBoxContent);
        //TODO:Use the ComboBoxContent.data to update the ComboBox
        ArrayList<String> comboBoxContent = read.getListOfPath();
        new Updates().updateComboBox(guiObjects.getComboBox(), comboBoxContent);
    }

    //TODO:set handlers functionality
    private void setHandlers() {
        //TODO:set the context menu handlers
        setHandlersContextMenu();
        //TODO:set the main display handlers.
        guiObjects.getDisplayTableView().setOnMouseClicked(this::handleDisplayTableEvents);
        //TODO:set combo box handler
        comboBoxHandler();
        //TODO: set file chooser functionality
        guiObjects.getBrowswer().setOnAction(this::browserButtonAction);
        //TODO: set stage handler on hidden
        guiObjects.getStage().setOnHidden(this::stageHandler);
        //TODO: set ok button handler
        guiObjects.getOkButton().setOnAction(this::okButtonHandler);
        //TODO:set cancel button handler
        guiObjects.getCancelButton().setOnAction(this::cancelButtonHandler);
    }

    //TODO:main display handler functionality
    private void handleDisplayTableEvents(MouseEvent e) {
        //TODO: hide the context menu with each new click
        //it does not matter if its null or not.
        guiObjects.getContextMenu().hide();
        //TODO:check if the selection equals null and return if it does
        if (guiObjects.getDisplayTableView().getSelectionModel().getSelectedItem() == null) {
            return;
        }

        //TODO:check for the primary button and the secondary button
        //the both will do different things. primary plays music on click
        //and the secondary prompts a context menu with a list of choices
        if (e.getButton() == MouseButton.PRIMARY) {
            //TODO:set the clicks functionality
            //i modified the default functionality to behave
            //how i want them to. just pass in the tableView
            //and everything will take care of itself.
            handleCLicks(guiObjects.getDisplayTableView());
            //TODO:check if the selected song is in the library
            if (player.getMusicList().containsSong(player.getMusicList().getSongByPath(selectedSong))) {
                //TODO:load the music player and play a song
                player.loadNewTrack(selectedSong);
                player.playSong();
            } else {
                //we should never see this. We will only see ths if the music list breaks
                System.out.println("song is not in library..you should never see this" + selectedSong);
            }

        } else if (e.getButton() == MouseButton.SECONDARY) {
            //TODO:set the clicks functionality
            //i modified the default functionality to behave
            //how i want them to. just pass in the tableView
            //and everything will take care of itself.
            handleCLicks(guiObjects.getDisplayTableView());
            //TODO:show context menu
            guiObjects.getContextMenu().show(guiObjects.getDisplayTableView(), e.getScreenX(), e.getScreenY());
        }
    }

    //TODO:define click settings
    private void handleCLicks(TableView<Song> display) {
        this.selectedIndex = display.getSelectionModel().getSelectedIndex();
        this.selectedSong = display.getItems().get(selectedIndex).getPath();
        display.getSelectionModel().clearSelection();
        display.getFocusModel().focus(selectedIndex);
    }

    //TODO:Context menu handlers functionality
    //the handlers are set using a loop because we do not
    //know how many options the list will have. To generate
    //options will use the ComboBox current options. this will
    //make it consistent
    private void setHandlersContextMenu() {
        guiObjects.getContextMenu().getItems().clear();
        //TODO:create a menu that will consist of multiple menu items
        //i will set the title as add to play list
        Menu menu = new Menu("Add To Playlist");
        //TODO:get all the information in the current combo box
        ObservableList<String> observableList = guiObjects.getComboBox().getItems();
        //TODO:reate new menu items each with their own handler
        for (String choices : observableList) {
            //TODO:check if the combo box is empty
            //this will avoid doing pointless iteration
            if (observableList.isEmpty()) {
                break;
            }
            //make sure that i do not include the library and the current selected object
            String selected = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
            if (choices != "Library" && choices != selected) {
                MenuItem newMenuItem = new MenuItem(choices.toString());
                newMenuItem.setOnAction(event -> {
                    //TODO:call a method to perform the functionality of each option
                    contextMenuOnclick(newMenuItem.getText());
                });
                menu.getItems().add(newMenuItem);
            }
        }
        //TODO:create a play menu item
        //this option will play a song when chosen
        MenuItem play = new MenuItem("Play Song");
        play.setOnAction(event -> {
            //TODO:call a method to perform the functionality of each option
            contextMenuOnclick(play.getText());
        });
        //TODO:update the contenxt menu
        guiObjects.getContextMenu().getItems().addAll(menu, play);
    }

    //TODO:set the context menu clicks functionality
    private void contextMenuOnclick(String contextMenuSelection) {
        //System.out.println(contextMenuSelection);
        //TODO:check for the value of the selected MenuItem
        if (contextMenuSelection == "New Playlist") {
            //TODO:selec the new play list object on the combo box
            //this will prompt a pop up window with further choices
            guiObjects.getComboBox().getSelectionModel().select("New Playlist");
        } else if (contextMenuSelection == "Play Song") {
            //TODO:check if the selected song is in the library
            if (player.getMusicList().containsSong(player.getMusicList().getSongByPath(selectedSong))) {
                //TODO:load the music player and play a song
                player.loadNewTrack(selectedSong);
                player.playSong();
            } else {
                //we should never see this. We will only see ths if the music list breaks
                System.out.println("song is not in library..you should never see this");
            }
        } else {
            //TODO:create path from selected menu item value and get selected song information
            String dataPath = "./" + contextMenuSelection + ".data";
            Song song = guiObjects.getDisplayTableView().getItems().get(selectedIndex);
            //TODO:add song to play list
            player.addSongToPlaylist(song, dataPath);
        }
    }

    //TODO: set combo box handler functionality
    private void comboBoxHandler() {
        guiObjects.getComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            //TODO:check if new selection is null
            if (newSelection == null) {
                return;
            }
            //TODO:switch modes according to the combo box selection
            //if Combo box equals new playlist we want to get the pop window
            //if it equal library just switch to library mode
            //if it equals a play list just switch to playlist mode
            if (guiObjects.getComboBox().getSelectionModel().getSelectedItem() == "Library") {
                //TODO:switch to library mode
                player.switchToLibrary();
            } else if (guiObjects.getComboBox().getSelectionModel().getSelectedItem() == "New Playlist") {
                //TODO:call pop window
                player.createPlaylist(guiObjects.getComboBox(), (String) oldSelection, guiObjects.getStage());
            } else {
                //TODO:switch to playlist mode
                System.out.println("under construction");
            }
            setHandlersContextMenu();
        });
    }

    //TODO:set file chooser handler functionality
    private void browserButtonAction(ActionEvent e) {
        //TODO: create file chooser
        FileChooser getFile = new FileChooser();
        //TODO: filter the search to just .mp3 and .mp4
        getFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("filter search", "*.mp3", "*.mp4"));
        File theFile = getFile.showOpenDialog(null);
        //TODO:check if file is null
        //this should never happen because of the filter
        if (theFile == null) {
            return;
        }
        //TODO: get the song path and add it to an array list
        String songPath = theFile.getAbsolutePath();
        ArrayList<String> newSongs = new ArrayList<>();
        newSongs.add(songPath);
        //TODO: add song
        player.addSongToLibrary(guiObjects.getDisplayTableView(), selectedIndex, newSongs);
    }

    //TODO: set stage handler functionality
    //stage handler
    private void stageHandler(WindowEvent e) {
        //TODO:simply clear the text field
        guiObjects.getTextField().clear();
    }

    //TODO:ok button handler functionality
    private void okButtonHandler(ActionEvent e) {
        //TODO:place text content in comboBox content
        ArrayList<String> comboBoxContent = new ArrayList<>();
        comboBoxContent.add(guiObjects.getTextField().getText());
        //TODO:update combo box
        new Updates().updateComboBox(guiObjects.getComboBox(), comboBoxContent);
        setHandlersContextMenu();
        //TODO:serialize the text field value
        write.storeData("./ComboBoxContent.data", guiObjects.getTextField().getText());
        //TODO:close stage
        guiObjects.getStage().close();
    }

    //TODO:cancel button handler functionality
    private void cancelButtonHandler(ActionEvent e) {
        //TODO:close stage
        guiObjects.getStage().close();
    }
}
