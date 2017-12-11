import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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
    private ArrayList<Song> listOfSong;
    private ArrayList<String> states;
    private ArrayList<ArrayList<ArrayList<String>>> collection;

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
            Button playButton, Label nameOfSong, Label timeLabel, CheckBox checkBox, Stage primaryStage
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
        this.listOfSong = new ArrayList<>();
        this.states = new ArrayList<>();
        this.collection = new ArrayList<>();

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
        guiObjects.setCheckBox(checkBox);
        guiObjects.setPrimaryStage(primaryStage);
        guiObjects.setCollection(new ArrayList<>());

        //set handlers and load information
        loadInformation();
        setHandlers();
    }

    private void loadInformation() {

        guiObjects.getCheckBox().setVisible(false);

        cleanDirectory();
        ArrayList<String> libraryContent = new ArrayList<>();
        if (new File(libraryPath).exists()) {
            //load library.data
            read.setListOfPath(this.libraryPath);
            libraryContent = read.getListOfPath();
        } else {
            new File("./collections.data").delete();
            libraryContent = fetchResourceFile();
        }

        //Use library.data to set up the MusicList and load display
        //they must be set at the same time because of the delay
        //caused by set on ready when getting duration.
        new Updates().updateMusicList(
                guiObjects.getDisplayTableView(),
                selectedIndex,
                player.getMusicList(),
                libraryContent
        );


        //Load the ComboBoxContent.data
        read.setListOfPath(comboBoxContent);

        //Use the ComboBoxContent.data to update the ComboBox
        ArrayList<String> comboBoxContent = read.getListOfPath();
        new Updates().updateComboBox(guiObjects.getComboBox(), comboBoxContent);

        //load the shuffle data
        read.setListOfPath("./shuffle.data");
        if(!new File("./shuffle.data").exists()){
            ObservableList<String> observableList = guiObjects.getComboBox().getItems();
            for(String str : observableList){
                if(!str.equals("Library") && !str.equals("New Playlist")){
                    states.add(str + "-O");
                }
            }
        }else{
            for(String str : read.getListOfPath()){
                String check = str.substring(str.indexOf(0)+ 1,str.indexOf("-"));
                //System.out.println(check);
                if(guiObjects.getComboBox().getItems().contains(check)){
                    states.add(str);
                }

            }
        }

        new File("./shuffle.data").delete();


        //load
        if(new File("./collections.data").exists()){

            ArrayList<ArrayList<String>> playCollection = (ArrayList<ArrayList<String>>) Serialization.read("./collections.data");
            ArrayList<ArrayList<String>> filtered = new ArrayList<>();

            for(ArrayList<String> s : playCollection){

                String check = s.get(0).replace("./","").replace(".data","");
                if(guiObjects.getComboBox().getItems().contains(check)){
                    filtered.add(s);
                }
            }

            guiObjects.setCollection(filtered);
        }
    }

    private ArrayList<String> fetchResourceFile() {
        File filePath = new File("./songs");
        File[] fileCollections = filePath.listFiles();
        ArrayList<String> newFileCollections = new ArrayList<>();

        for (File file : fileCollections) {
            newFileCollections.add("./songs/"+ file.getName());
            new Write().storeData(libraryPath, "./songs/"+ file.getName());
        }
        return newFileCollections;
    }

    private void cleanDirectory() {

        File filePath = new File("./");
        File[] fileCollections = filePath.listFiles();
        ArrayList<String> newFileCollections = new ArrayList<>();

        for (File file : fileCollections) {
            if (file.getName().contains(".data")) {
                newFileCollections.add(file.getName().toString());
                //System.out.println(file.getName().toString());
            }
        }

        if (!newFileCollections.contains("library.data")) {
            for (String file : newFileCollections) {
                if (!file.equals("ComboBoxContent.data")) {
                    new File("./" + file).delete();
                }
            }
        }

        if (!newFileCollections.contains("ComboBoxContent.data")) {
            for (String file : newFileCollections) {
                if (!file.equals("library.data")) {
                    new File("./" + file).delete();
                }
            }
        }

        cleanTempDir();

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

        guiObjects.getPrimaryStage().setOnHidden(this::primaryStageHandler);

        //set ok button handler
        guiObjects.getOkButton().setOnAction(this::okButtonHandler);

        //set cancel button handler
        guiObjects.getCancelButton().setOnAction(this::cancelButtonHandler);

        //set handler for play button
        guiObjects.getPlayButton().setOnAction(this::playButtonHandler);
        guiObjects.getCheckBox().setOnAction(this::checkBoxHandler);
    }

    private void checkBoxHandler(ActionEvent e){
        ArrayList<String> temp = new ArrayList<>();
        for(String stat : states){
            //System.out.println(stat);
            temp.add(stat);
        }

        states = new ArrayList<>();

        for(String str : temp){
            String newStr = str.substring(str.indexOf(0)+1, str.indexOf("-"));
            //System.out.println(newStr);
            if(newStr.equals(guiObjects.getComboBox().getSelectionModel().getSelectedItem())){
                if(guiObjects.getCheckBox().isSelected()){
                    states.add(newStr + "-S");
                    handleShuffle("SHUFFLE");
                }else{
                    states.add(newStr + "-O");
                    handleShuffle("NO-SHUFFLE");
                }
            }else{
                states.add(str);
            }
        }
    }

    private void primaryStageHandler(WindowEvent e){
        for (String str : states){
            new Write().storeData("./shuffle.data",str);
        }

        Serialization.write(guiObjects.getCollection(),"./collections.data");
        for(ArrayList<String> s : guiObjects.getCollection()){
            for(String ss : s){
                //System.out.println(ss);
            }
        }

        //new File("./ComboBoxContent.data").delete();

        ObservableList<String> observableList = guiObjects.getComboBox().getItems();
        ArrayList<String> strWriter = new ArrayList<>();
        for(String str : observableList){
            strWriter.add(str);
        }

        Serialization.write(strWriter,"./ComboBoxContent.data");


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
            this.currentComboBoxSelection = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();

            //check if the selected song is in the library
            if (player.getMusicList().exist(selectedSong) && new File(selectedSong).exists()) {
                //TODO:stuff here

                cleanTempDir();


                for (Song readPath : player.getMusicList().getSubset()) {
                    new Write().storeData("./tList.data", readPath.getPath());
                }
                player.focusValue(
                        currentComboBoxSelection,
                        guiObjects.getComboBox()
                );

                player.loadNewTrack(
                        selectedSong,
                        guiObjects.getSongName(),
                        guiObjects.getTimer(),
                        guiObjects.getDisplayTableView()
                );


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

    private void cleanTempDir(){

        //add to library
        if(new File("./tList.data").exists()){
            new File("./tList.data").delete();
        }

        if(new File("./focus.data").exists()){
            new File("./focus.data").exists();
        }
    }

    //define click settings
    private void handleCLicks(TableView<Song> display) {
        this.selectedIndex = display.getSelectionModel().getSelectedIndex();
        this.selectedSong = display.getItems().get(selectedIndex).getPath();
        display.getSelectionModel().clearSelection();
        display.getFocusModel().focus(selectedIndex);
    }

    private void songPlaying() {
        guiObjects.getSongName().setText(guiObjects.getDisplayTableView().getItems().get(selectedIndex).getName());
        guiObjects.getTimer().setText("(00:00" + "/" + guiObjects.getDisplayTableView().getItems().get(selectedIndex).getDuration() + ")");
        this.startTimer = 0;
    }

    //Context menu handlers functionality
    //the handlers are set using a loop because we do not
    //know how many options the list will have. To generate
    //options will use the ComboBox current options. this will
    //make it consistent
    private void setHandlersContextMenu() {
        //clear it on call
        guiObjects.getContextMenu().getItems().clear();
        //make sure that i do not include the library and the current selected object
        String selected = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();

        //create a menu that will consist of multiple menu items
        //i will set the title as add to play list
        Menu menu = new Menu("Select Other Playlist");
        Menu cMenu = new Menu("Current Playlist");

        //get all the information in the current combo box
        ObservableList<String> observableList = guiObjects.getComboBox().getItems();

        //create a play menu item
        //this option will play a song when chosen
        MenuItem play = new MenuItem("Play Selected Song");
        play.setOnAction(event -> {
            //call a method to perform the functionality of each option
            contextMenuOnclick(play.getText(), "NONE", "NONE");
        });



        MenuItem remove = new MenuItem("Remove Selected Song");

        remove.setOnAction(event -> {
            //call a method to perform the functionality of each option
            contextMenuOnclick(remove.getText(), "NONE", "NONE");
        });

        Menu removePlaylist = new Menu("Selected A Playlist To Remove");

        cMenu.getItems().add(removePlaylist);
        cMenu.getItems().add(remove);
        cMenu.getItems().add(play);

        //create new menu items each with their own handler
        for (String choices : observableList) {
            //check if the combo box is empty
            //this will avoid doing pointless iteration
            if (observableList.isEmpty()) {
                break;
            }

            if (!choices.equals("Library") && !choices.equals("New Playlist") && !choices.equals(selected)) {
                Menu subMenu = new Menu(choices.toString());

                MenuItem addSong = new MenuItem("Add Selected Song");
                addSong.setOnAction(event -> {
                    //call a method to perform the functionality of each option
                    contextMenuOnclick(addSong.getText(), addSong.getParentMenu().getText(), "NONE");
                });

                subMenu.getItems().add(addSong);
                subMenu.getItems().add(addPlaylistMenu(choices.toString(), observableList));
                menu.getItems().add(subMenu);

            } else if (choices.equals("New Playlist")) {
                MenuItem menuItem = new MenuItem(choices.toString());
                menuItem.setOnAction(event -> {
                    //call a method to perform the functionality of each option
                    contextMenuOnclick(menuItem.getText(), "NONE", "NONE");
                });
                menu.getItems().add(menuItem);
            } else if (choices.equals(selected)) {
                if (!choices.equals("Library")) {
                    //create a play menu item
                    //this option will play a song when chosen
                    MenuItem playFromStart = new MenuItem("Play Playlist From Beginning");
                    playFromStart.setOnAction(event -> {
                        //call a method to perform the functionality of each option
                        contextMenuOnclick(playFromStart.getText(), "NONE", "NONE");
                    });

                    Read read = new Read();
                    read.setListOfPath("./"+ choices.toString() + ".data");

                    for(String str : read.getListOfPath()){
                        if(str.contains(".data")){
                            String playlist = str.substring(str.indexOf("/")+1, str.indexOf(".data"));
                            MenuItem pList = new MenuItem(playlist);
                            pList.setOnAction(event -> {
                                //call a method to perform the functionality of each option
                                contextMenuOnclick(pList.getParentMenu().getText(), pList.getText(), "NONE");
                            });
                            removePlaylist.getItems().add(pList);
                        }
                    }

                    MenuItem moveUp = new MenuItem("Move Selected Song Up");
                    MenuItem moveDown = new MenuItem("Move Selected Song Down");

                    moveUp.setOnAction(event -> {
                        //call a method to perform the functionality of each option
                        contextMenuOnclick(moveUp.getText(), "NONE", "NONE");
                    });

                    moveDown.setOnAction(event -> {
                        //call a method to perform the functionality of each option
                        contextMenuOnclick(moveDown.getText(), "NONE", "NONE");
                    });


                    //cMenu.getItems().add(moveUp);
                    //cMenu.getItems().add(moveDown);

                    //load the playlist here


                    cMenu.getItems().add(playFromStart);
                    cMenu.getItems().add(addPlaylistMenu(choices.toString(), observableList));
                }else if(choices.equals("Library")){
                    for (String str : observableList) {
                        if(!str.equals("Library") && !str.equals("New Playlist")){
                            MenuItem libRemove = new MenuItem(str.toString());
                            libRemove.setOnAction(event -> {
                                //call a method to perform the functionality of each option
                                contextMenuOnclick(libRemove.getParentMenu().getText(), libRemove.getText(), "NONE");
                            });
                            removePlaylist.getItems().add(libRemove);
                        }
                    }

                }
            }
        }

        //update the context menu
        guiObjects.getContextMenu().getItems().addAll(menu, cMenu);
    }

    private Menu addPlaylistMenu(String choices, ObservableList<String> observableList) {
        Menu menu = new Menu("Add A Playlist");

        for (String innerChoices : observableList) {
            //TODO: exclude library and new playlist
            if(!innerChoices.equals("Library") && !innerChoices.equals("New Playlist")){
                //TODO: exclude itself from the choices.
                if(!innerChoices.equals(choices)){
                    MenuItem menuItem = new MenuItem(innerChoices.toString());

                    menuItem.setOnAction(event -> {
                        //call a method to perform the functionality of each option
                        contextMenuOnclick(menu.getText(), choices.toString(), menuItem.getText());
                    });

                    menu.getItems().add(menuItem);
                }
            }
        }

        return menu;
    }

    //set the context menu clicks functionality
    private void contextMenuOnclick(String contextMenuSelection, String selectedPlaylist, String toBeAddedPlaylist) {
        //check for the value of the selected MenuItem
        if (contextMenuSelection.equals("New Playlist")) {
            //selec the new play list object on the combo box
            //this will prompt a pop up window with further choices
            guiObjects.getComboBox().getSelectionModel().select("New Playlist");
        } else if (contextMenuSelection.equals("Play Selected Song")) {
            //check if the selected song is in the library
            if (player.getMusicList().exist(selectedSong) && new File(selectedSong).exists()) {
                //TODO: stuff here
                this.currentComboBoxSelection = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
                //TODO:stuff here

                cleanTempDir();


                for (Song readPath : player.getMusicList().getSubset()) {
                    new Write().storeData("./tList.data", readPath.getPath());
                }
                player.focusValue(
                        currentComboBoxSelection,
                        guiObjects.getComboBox()
                );

                player.loadNewTrack(
                        selectedSong,
                        guiObjects.getSongName(),
                        guiObjects.getTimer(),
                        guiObjects.getDisplayTableView()
                );

            } else {
                //we should never see this. We will only see ths if the music list breaks
                System.out.println("song is not in library..you should never see this");
            }
        } else if (contextMenuSelection.equals("Play Playlist From Beginning")) {
            //check if the selected item from the display is null
            if (guiObjects.getDisplayTableView().getFocusModel().getFocusedItem() == null) {
                guiObjects.getDisplayTableView().getSelectionModel().select(0);
                handleCLicks(guiObjects.getDisplayTableView());
            }

            //check if the selected song is in the library
            if (player.getMusicList().exist(selectedSong) && new File(selectedSong).exists()) {

                this.selectedSong = guiObjects.getDisplayTableView().getItems().get(0).getPath();
                guiObjects.getDisplayTableView().getFocusModel().focus(0);
                this.selectedIndex = 0;

                this.currentComboBoxSelection = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();
                //TODO:stuff here

                cleanTempDir();

                for (Song readPath : player.getMusicList().getSubset()) {
                    new Write().storeData("./tList.data", readPath.getPath());
                }
                player.focusValue(
                        currentComboBoxSelection,
                        guiObjects.getComboBox()
                );

                player.loadNewTrack(
                        selectedSong,
                        guiObjects.getSongName(),
                        guiObjects.getTimer(),
                        guiObjects.getDisplayTableView()
                );


            } else {
                //we should never see this. We will only see ths if the music list breaks
                System.out.println("song is not in library..you should never see this");
            }

        } else if (contextMenuSelection.equals("Add Selected Song")) {
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
            player.addSongToPlaylist(song, dataPath, guiObjects.getCollection());

            //String current = guiObjects.getComboBox().getSelectionModel().getSelectedItem().toString();

                handleShuffle("NO-SHUFFLE");

        } else if (contextMenuSelection.equals("Add A Playlist")) {

            String dataPath = "./" + selectedPlaylist + ".data";
            String playlist = "./" + toBeAddedPlaylist + ".data";

            Read read = new Read();
            read.setListOfPath(dataPath);

            for (String links : read.getListOfPath()) {
                if (links.equals(playlist)) {
                    System.out.println("playlist is already in the playlist.");
                    return;
                }
            }

            //System.out.println(contextMenuSelection + " to " + dataPath + " ==> " + playlist);
            player.addPlaylistToPlaylist(playlist,dataPath, guiObjects.getComboBox(),guiObjects.getCollection());
            guiObjects.getContextMenu().hide();
            setHandlersContextMenu();
            handleShuffle("NO-SHUFFLE");
        }else if(contextMenuSelection.equals("Move Selected Song Up")){

            //System.out.println(target);
        }else if(contextMenuSelection.equals("Move Selected Song Down")){

            //System.out.println(target);
        }else if(contextMenuSelection.equals("Remove Selected Song")){
            //create path from selected menu item value and get selected song information
            String dataPath = "./" + guiObjects.getComboBox().getSelectionModel().getSelectedItem() + ".data";
            Song song = guiObjects.getDisplayTableView().getItems().get(selectedIndex);

            //System.out.println(dataPath);
            //System.out.println(song.getPath());
            player.remove(dataPath,song, guiObjects.getDisplayTableView(),selectedIndex);

        }else if(contextMenuSelection.equals("Selected A Playlist To Remove")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    player.removePlay(guiObjects.getDisplayTableView(),selectedPlaylist,guiObjects.getComboBox(),states,selectedIndex);
                    guiObjects.getContextMenu().hide();
                    setHandlersContextMenu();
                    if(player.getMP3Player().equals(player.getPlaylistMode())){
                        handleShuffle("NO-SHUFFLE");
                    }
                }
            });
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
                guiObjects.getCheckBox().setVisible(false);
                //switch to library mode
                player.setMP3Player(player.getLibraryMode());
                player.stopPlayer();
                new Updates().updateTableViewAll(guiObjects.getDisplayTableView(), selectedIndex, player.getMusicList().getLibrary(), selectedSong, "L");
                System.out.println(new Player().totalDuration(guiObjects.getDisplayTableView(),selectedSong,selectedIndex));

            } else if (guiObjects.getComboBox().getSelectionModel().getSelectedItem() == "New Playlist") {
                //show the create playlist stage and wait for user input
                //set the combo box selection to the old after the wait is done
                guiObjects.getStage().showAndWait();
                guiObjects.getComboBox().getSelectionModel().select(oldSelection);
            } else {
                guiObjects.getCheckBox().setVisible(true);

                for(String stat : states){
                    String newStat = stat.substring(stat.indexOf("-")+1, stat.length());
                    String box = stat.substring(stat.indexOf(0)+ 1, stat.indexOf("-"));
                    if(box.equals(guiObjects.getComboBox().getSelectionModel().getSelectedItem())){
                        //System.out.println(newStat);
                        if(newStat.equals("S")){
                            guiObjects.getCheckBox().setSelected(true);
                        }else{
                            guiObjects.getCheckBox().setSelected(false);
                        }
                    }
                }

                player.setMP3Player(player.getPlaylistMode());
                if(guiObjects.getCheckBox().isSelected()){
                    handleShuffle("SHUFFLE");
                }else{
                    handleShuffle("NO-SHUFFLE");
                }


            }
            setHandlersContextMenu();
        });
    }

    private void handleShuffle(String mode){

        player.stopPlayer();

        Read reader = new Read();
        reader.setListOfPath("./" + guiObjects.getComboBox().getSelectionModel().getSelectedItem() + ".data");

        ArrayList<String> templist = reader.getListOfPath();

        if(mode.equals("SHUFFLE")){
            Collections.shuffle(templist);
        }

        player.getMusicList().subSet(templist, guiObjects.getComboBox(),states, guiObjects.getCollection());
        new Updates().updateTableViewAll(guiObjects.getDisplayTableView(), -1, player.getMusicList().getSubset(), selectedSong,"P");
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
        String relativePath = "./songs/" + theFile.getName();
        ArrayList<String> newSongs = new ArrayList<>();
        //System.out.println(songPath);
        newSongs.add(songPath);

        if (!player.getMusicList().exist(songPath) && new File(songPath).exists() && !player.getMusicList().exist(relativePath)) {
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                //place text content in comboBox content
                ArrayList<String> comboBoxContent = new ArrayList<>();
                comboBoxContent.add(guiObjects.getTextField().getText());

                //update combo box
                new Updates().updateComboBox(guiObjects.getComboBox(), comboBoxContent);
                setHandlersContextMenu();

                //serialize the text field value
                write.storeData("./ComboBoxContent.data", guiObjects.getTextField().getText());
                states.add(guiObjects.getTextField().getText()+ "-O");
                //close stage
                guiObjects.getStage().close();
            }
        });

    }

    //cancel button handler functionality
    private void cancelButtonHandler(ActionEvent e) {
        //close stage
        guiObjects.getStage().close();
    }

    private void playButtonHandler(ActionEvent e) {

    }
}
