import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;

/**
 * This class sets the handlers and their functionality
 * for each GUI component.
 */
public class EventHandle {
    //local variables
    private String selectedSong;
    private String libraryPath;
    private String comboBoxContent;
    private int selectedIndex;
    private String newDuration;
    private int startTimer;
    private GUIObjects guiObjects;
    private ManageMP3PlayerState player;
    private String currentComboBoxSelection;
    private Read read;
    private Write write;

    /**
     * this constructor will set important GUI components and local variables
     *
     * @param comboBox      a combo box to hold the name of all playlist
     * @param browserButton a button that will bring up the file chooser when clicked
     * @param tableView     a table view to display all the songs
     * @param popupStage    a stage to act as the pop up window to create play list
     * @param textField     a text field so the user can write name of new play list
     * @param okButton      an ok button to submit all changes in the pop up window
     * @param cancelButton  a cancel button to cancel all changes in the pop window
     * @param contextMenu   a context menu to to hold the right click options
     * @param playButton    a button to play a selected song
     * @param nameOfSong    a label to hold song name
     * @param timeLabel     a label to hold song timer
     */
    public EventHandle(
            ComboBox comboBox, Button browserButton, TableView<Song> tableView, Stage popupStage,
            TextField textField, Button okButton, Button cancelButton, ContextMenu contextMenu,
            Button playButton, Label nameOfSong, Label timeLabel
    ) {
        //set local variables
        this.read = new Read();
        this.write = new Write();
        this.player = new ManageMP3PlayerState();
        this.guiObjects = new GUIObjects();
        this.libraryPath = "./library.data";
        this.comboBoxContent = "./ComboBoxContent.data";
        this.selectedIndex = -1;
        this.selectedSong = null;
        this.currentComboBoxSelection = null;

        //components
        guiObjects.setComboBox(comboBox);
        guiObjects.setBrowser(browserButton);
        guiObjects.setDisplayTableView(tableView);
        guiObjects.setStage(popupStage);
        guiObjects.setTextField(textField);
        guiObjects.setOkButton(okButton);
        guiObjects.setCancelButton(cancelButton);
        guiObjects.setContextMenu(contextMenu);
        guiObjects.setPlayButton(playButton);
        guiObjects.setSongName(nameOfSong);
        guiObjects.setTimer(timeLabel);
        guiObjects.setTimeline(new Timeline());

        //set handlers and load information
        loadInformation();
        setHandlers();
    }

    private void loadInformation() {
        cleanDirectory();
        //load library.data
        read.setListOfPath(this.libraryPath);
        ArrayList<String> libraryContent = read.getListOfPath();

        //Use library.data to set up the MusicList and load display
        //they must be set at the same time because of the delay
        //caused by set on ready when getting duration.
        new Updates().updateMusicList(
                guiObjects.getDisplayTableView(),
                selectedIndex, player.getMusicList(),
                libraryContent
        );

        //Load the ComboBoxContent.data
        read.setListOfPath(comboBoxContent);

        //Use the ComboBoxContent.data to update the ComboBox
        ArrayList<String> comboBoxContent = read.getListOfPath();
        new Updates().updateComboBox(guiObjects.getComboBox(), comboBoxContent);
    }

    private void cleanDirectory(){

        File filePath = new File("./");
        File[] fileCollections = filePath.listFiles();
        ArrayList<String> newFileCollections = new ArrayList<>();

        for(File file : fileCollections){
            if(file.getName().contains(".data")) {
                newFileCollections.add(file.getName().toString());
                //System.out.println(file.getName().toString());
            }
        }

        if(!newFileCollections.contains("library.data")){
            for(String file : newFileCollections){
                if(!file.equals("ComboBoxContent.data")){
                    new File("./" + file).delete();
                }
            }
        }

        if(!newFileCollections.contains("ComboBoxContent.data")){
            for (String file : newFileCollections){
                if(!file.equals("library.data")){
                    new File("./" + file).delete();
                }
            }
        }

    }

    //set handlers functionality
    private void setHandlers() {
        //set the context menu handlers
        setHandlersContextMenu();

        //set the main display handlers.
        guiObjects.getDisplayTableView().setOnMouseClicked(this::handleDisplayTableEvents);

        //set combo box handler
        comboBoxHandler();

        //set file chooser functionality
        guiObjects.getBrowser().setOnAction(this::browserButtonAction);

        //set stage handler on hidden
        guiObjects.getStage().setOnHidden(this::stageHandler);

        //set ok button handler
        guiObjects.getOkButton().setOnAction(this::okButtonHandler);

        //set cancel button handler
        guiObjects.getCancelButton().setOnAction(this::cancelButtonHandler);

        //set handler for play button
        guiObjects.getPlayButton().setOnAction(this::playButtonHandler);
    }

    //main display handler functionality
    private void handleDisplayTableEvents(MouseEvent e) {
        //hide the context menu with each new click
        //it does not matter if its null or not.
        guiObjects.getContextMenu().hide();

        //check if the selection equals null and return if it does
        if (guiObjects.getDisplayTableView().getSelectionModel().getSelectedItem() == null) {
            return;
        }

        //check for the primary button and the secondary button
        //the both will do different things. primary plays music on click
        //and the secondary prompts a context menu with a list of choices
        if (e.getButton() == MouseButton.PRIMARY) {
            //set the clicks functionality
            //i modified the default functionality to behave
            //how i want them to. just pass in the tableView
            //and everything will take care of itself.
            handleCLicks(guiObjects.getDisplayTableView());

            //check if the selected song is in the library
            if (player.getMusicList().exist(selectedSong) && new File(selectedSong).exists()) {
                //update currentBoxSelection
                //this.currentComboBoxSelection = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
                //System.out.println(currentComboBoxSelection);
                //load the music player and play a song
                player.loadNewTrack(selectedSong,guiObjects.getDisplayTableView());
                this.currentComboBoxSelection = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
                if(this.player.getMP3Player().equals(this.player.getLibraryMode())){
                    songPlaying();
                    player.playSong(guiObjects.getTimer(),guiObjects.getDisplayTableView().getItems().get(selectedIndex).getDuration(),startTimer, guiObjects.getSongName());
                }else{
                    songAutoPlaying();
                    player.playSong(guiObjects.getTimer(),newDuration,startTimer,guiObjects.getSongName());
                }

            } else {
                //we should never see this. We will only see ths if the music list breaks
                System.out.println("song is not in library..you should never see this" + selectedSong);
            }

        } else if (e.getButton() == MouseButton.SECONDARY) {
            //set the clicks functionality
            //i modified the default functionality to behave
            //how i want them to. just pass in the tableView
            //and everything will take care of itself.
            handleCLicks(guiObjects.getDisplayTableView());

            //show context menu
            guiObjects.getContextMenu().show(guiObjects.getDisplayTableView(), e.getScreenX(), e.getScreenY());
        }
    }

    //define click settings
    private void handleCLicks(TableView<Song> display) {
        this.selectedIndex = display.getSelectionModel().getSelectedIndex();
        this.selectedSong = display.getItems().get(selectedIndex).getPath();
        display.getSelectionModel().clearSelection();
        display.getFocusModel().focus(selectedIndex);
    }

    private void songPlaying(){
        guiObjects.getSongName().setText(guiObjects.getDisplayTableView().getItems().get(selectedIndex).getName());
        guiObjects.getTimer().setText("(00:00" + "/" + guiObjects.getDisplayTableView().getItems().get(selectedIndex).getDuration() + ")");
        this.startTimer = 0;
    }

    private void songAutoPlaying(){
        int duration = 0;
        for(int i =0;i < guiObjects.getDisplayTableView().getItems().size();i++) {
            if(guiObjects.getDisplayTableView().getItems().get(i).getPath().equals(selectedSong)){
                this.startTimer = duration;
            }
            String parse = guiObjects.getDisplayTableView().getItems().get(i).getDuration();
            String minutes = parse.substring(0, parse.indexOf(":"));
            String seconds = parse.substring(parse.indexOf(":") + 1, parse.length());
            duration = duration + ((Integer.valueOf(minutes)*60) + (Integer.valueOf(seconds)));
        }

        this.newDuration = new Player().formatDuration(duration * 1000);

        guiObjects.getSongName().setText(guiObjects.getDisplayTableView().getItems().get(selectedIndex).getName());
        guiObjects.getTimer().setText("(00:00" + "/" + newDuration + ")");
    }

    //Context menu handlers functionality
    //the handlers are set using a loop because we do not
    //know how many options the list will have. To generate
    //options will use the ComboBox current options. this will
    //make it consistent
    private void setHandlersContextMenu() {
        //clear it on call
        guiObjects.getContextMenu().getItems().clear();

        //create a menu that will consist of multiple menu items
        //i will set the title as add to play list
        Menu menu = new Menu("Select A Playlist");

        //get all the information in the current combo box
        ObservableList<String> observableList = guiObjects.getComboBox().getItems();

        //create new menu items each with their own handler
        for (String choices : observableList) {
            //check if the combo box is empty
            //this will avoid doing pointless iteration
            if (observableList.isEmpty()) {
                break;
            }

            //make sure that i do not include the library and the current selected object
            String selected = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
            if (!choices.equals("Library") && !choices.equals("New Playlist")) {
                Menu subMenu = new Menu(choices.toString());

                if(!choices.equals(selected)){
                    MenuItem addSong = new MenuItem("Add Selected Song");
                    addSong.setOnAction(event -> {
                        //call a method to perform the functionality of each option
                        contextMenuOnclick(addSong.getText(), addSong.getParentMenu().getText(),"NONE");
                    });

                    subMenu.getItems().add(addSong);
                }

                Menu innerSubMenu = new Menu("Add a Playlist (under construction)");

                for (String innerChoices : observableList) {
                    if (!innerChoices.equals(subMenu.getText()) && !innerChoices.equals("Library") && !innerChoices.equals("New Playlist")) {
                        MenuItem innerMenuItem = new MenuItem(innerChoices.toString());
                        innerMenuItem.setOnAction(event -> {
                            //call a method to perform the functionality of each option
                            /*
                            contextMenuOnclick(
                                    innerMenuItem.getParentMenu().getText(),
                                    innerMenuItem.getParentMenu().getParentMenu().getText(),
                                    innerMenuItem.getText()
                            );
                            */
                        });
                        //innerSubMenu.getItems().add(innerMenuItem);
                    }
                }

                subMenu.getItems().add(innerSubMenu);
                menu.getItems().add(subMenu);

            } else if (choices.equals("New Playlist")) {
                MenuItem menuItem = new MenuItem(choices.toString());
                menuItem.setOnAction(event -> {
                    //call a method to perform the functionality of each option
                    contextMenuOnclick(menuItem.getText(),"NONE","NONE");
                });
                menu.getItems().add(menuItem);
            }
        }

        //create a play menu item
        //this option will play a song when chosen
        MenuItem play = new MenuItem("Play Song");
        play.setOnAction(event -> {
            //call a method to perform the functionality of each option
            contextMenuOnclick(play.getText(),"NONE", "NONE");
        });

        //update the context menu
        guiObjects.getContextMenu().getItems().addAll(menu, play);
    }

    //set the context menu clicks functionality
    private void contextMenuOnclick(String contextMenuSelection, String selectedPlaylist, String toBeAddedPlaylist) {
        //check for the value of the selected MenuItem
        if (contextMenuSelection.equals("New Playlist")) {
            //selec the new play list object on the combo box
            //this will prompt a pop up window with further choices
            guiObjects.getComboBox().getSelectionModel().select("New Playlist");
        } else if (contextMenuSelection.equals("Play Song")) {
            //check if the selected song is in the library
            if (player.getMusicList().exist(selectedSong) && new File(selectedSong).exists()) {
                //load the music player and play a song
                player.loadNewTrack(selectedSong,guiObjects.getDisplayTableView());
                this.currentComboBoxSelection = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
                if(this.player.getMP3Player().equals(this.player.getLibraryMode())){
                    songPlaying();
                    player.playSong(guiObjects.getTimer(),guiObjects.getDisplayTableView().getItems().get(selectedIndex).getDuration(),startTimer, guiObjects.getSongName());
                }else{
                    songAutoPlaying();
                    player.playSong(guiObjects.getTimer(),newDuration,startTimer,guiObjects.getSongName());
                }
            } else {
                //we should never see this. We will only see ths if the music list breaks
                System.out.println("song is not in library..you should never see this");
            }
        } else if(contextMenuSelection.equals("Add Selected Song")){
            //create path from selected menu item value and get selected song information
            String dataPath = "./" + selectedPlaylist + ".data";
            Song song = guiObjects.getDisplayTableView().getItems().get(selectedIndex);

            //we want to check if the song is already in playlist
            Read read = new Read();
            read.setListOfPath(dataPath);

            for (String links : read.getListOfPath()) {
                if (links.equals(song.getPath())) {
                    System.out.println("song is already in the playlist.");
                    return;
                }
            }

            //add song to play list
            player.addSongToPlaylist(song, dataPath);
        }else if(contextMenuSelection.equals("Add a Playlist")){
            System.out.println("under construction");
        }
    }
    //set combo box handler functionality
    private void comboBoxHandler() {
        guiObjects.getComboBox().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            //check if new selection is null
            if (newSelection == null) {
                return;
            }

            //switch modes according to the combo box selection
            //if Combo box equals new playlist we want to get the pop window
            //if it equal library just switch to library mode
            //if it equals a play list just switch to playlist mode
            if (guiObjects.getComboBox().getSelectionModel().getSelectedItem() == "Library") {
                //switch to library mode
                player.setMP3Player(player.getLibraryMode());
                new Updates().updateTableViewAll(guiObjects.getDisplayTableView(),selectedSong,player.getMusicList().getMockupSong());

            } else if (guiObjects.getComboBox().getSelectionModel().getSelectedItem() == "New Playlist") {
                //show the create playlist stage and wait for user input
                //set the combo box selection to the old after the wait is done
                guiObjects.getStage().showAndWait();
                guiObjects.getComboBox().getSelectionModel().select(oldSelection);
            } else {
                //switch to playlist mode
                player.setMP3Player(player.getPlaylistMode());
                Read reader = new Read();
                reader.setListOfPath("./" + guiObjects.getComboBox().getSelectionModel().getSelectedItem() + ".data");
                player.getMusicList().subSet(reader.getListOfPath());
                String newSelected = null;
                if(currentComboBoxSelection == guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString()){
                    newSelected = selectedSong;
                }else{
                    newSelected = null;
                }
                new Updates().updateTableViewAll(guiObjects.getDisplayTableView(),newSelected, player.getMusicList().getSubset());
            }
            setHandlersContextMenu();
        });
    }

    //set file chooser handler functionality
    private void browserButtonAction(ActionEvent e) {
        //create file chooser
        FileChooser getFile = new FileChooser();

        //filter the search to just .mp3 and .mp4
        getFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("filter search", "*.mp3", "*.mp4"));
        File theFile = getFile.showOpenDialog(null);

        //check if file is null
        //this should never happen because of the filter
        if (theFile == null) {
            return;
        }

        //get the song path and add it to an array list
        String songPath = theFile.getAbsolutePath();
        ArrayList<String> newSongs = new ArrayList<>();
        newSongs.add(songPath);

        if (!player.getMusicList().exist(songPath) && new File(songPath).exists()) {
            //add song
            player.addSongToLibrary(guiObjects.getDisplayTableView(), selectedIndex, newSongs);
        }
    }

    //set stage handler functionality
    //stage handler
    private void stageHandler(WindowEvent e) {
        //simply clear the text field
        guiObjects.getTextField().clear();
    }

    //ok button handler functionality
    private void okButtonHandler(ActionEvent e) {
        //place text content in comboBox content
        ArrayList<String> comboBoxContent = new ArrayList<>();
        comboBoxContent.add(guiObjects.getTextField().getText());

        //update combo box
        new Updates().updateComboBox(guiObjects.getComboBox(), comboBoxContent);
        setHandlersContextMenu();

        //serialize the text field value
        write.storeData("./ComboBoxContent.data", guiObjects.getTextField().getText());

        //close stage
        guiObjects.getStage().close();
    }

    //cancel button handler functionality
    private void cancelButtonHandler(ActionEvent e) {
        //close stage
        guiObjects.getStage().close();
    }

    private void playButtonHandler(ActionEvent e) {
        //check if the selected item from the display is null
        if (guiObjects.getDisplayTableView().getFocusModel().getFocusedItem() == null) {
            guiObjects.getDisplayTableView().getSelectionModel().select(0);
            handleCLicks(guiObjects.getDisplayTableView());
        }

        //check if the selected song is in the library
        if (player.getMusicList().exist(selectedSong) && new File(selectedSong).exists()) {
            //load the music player and play a song
            player.loadNewTrack(selectedSong,guiObjects.getDisplayTableView());
            this.currentComboBoxSelection = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
            if(this.player.getMP3Player().equals(this.player.getLibraryMode())){
                songPlaying();
                player.playSong(guiObjects.getTimer(),guiObjects.getDisplayTableView().getItems().get(selectedIndex).getDuration(),startTimer,guiObjects.getSongName());
            }else{
                songAutoPlaying();
                player.playSong(guiObjects.getTimer(),newDuration,startTimer,guiObjects.getSongName());
            }

        } else {
            //we should never see this. We will only see ths if the music list breaks
            System.out.println("song is not in library..you should never see this");
        }
    }
}
