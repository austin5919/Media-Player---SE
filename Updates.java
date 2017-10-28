import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.util.ArrayList;

/**
 * This class will update the GUI components.
 */
public class Updates {
    private ArrayList<String> listOfSongs;
    private Components components;

    public Updates() {

    }

    /**
     * constructor
     *
     * @param listOfSongs an ArrayList of songs just in case we want to add multiple songs
     * @param components  the components class
     */
    public Updates(ArrayList<String> listOfSongs, Components components) {
        this.listOfSongs = listOfSongs;
        this.components = components;
    }

    /**
     * This method updates the main display TableView. Pass
     * in "Add" to add song to the existing Displaylist and Pass
     * in "Rebuild" to completely refresh the DisplayList
     */
    public void updateDisplay(String updateMethod) {

        ObservableList<Song> list = FXCollections.observableArrayList();

        for (String readPath : this.listOfSongs) {

            Player player = new Player(readPath);
            player.getMediaPlayer().setOnReady(() -> {

                if (new File(readPath).exists()) {
                    String songName = new File(readPath).getName().replace(".mp3", "");
                    String duration = player.getDuration();
                    list.add(new Song(songName, duration, readPath));

                    if (updateMethod == "Add") {
                        Add(songName, duration, readPath);
                    }

                } else {
                    System.out.println("could not find file : " + readPath);
                }

                String checkPath = this.listOfSongs.get(this.listOfSongs.size() - 1);
                if (readPath == checkPath && updateMethod == "Rebuild") {
                    Rebuild(list);
                }

            });
        }
    }

    //add song to existing display
    private void Add(String songName, String duration, String readPath) {
        Platform.runLater(() -> {
            this.components.getDisplay().getItems().add(new Song(songName, duration, readPath));
            this.components.getDisplay().getFocusModel().focus(this.components.getSelectedIndex());
        });
    }

    //completely rebuild display
    private void Rebuild(ObservableList<Song> list) {
        Platform.runLater(() -> {
            this.components.getDisplay().getItems().removeAll();
            this.components.getDisplay().setItems(list);
            this.components.getDisplay().getFocusModel().focus(this.components.getSelectedIndex());
        });
    }

    public void updateContextMenu(ContextMenu contextMenu, Menu menu, MenuItem play){
        Platform.runLater(() -> {
            if(!contextMenu.getItems().isEmpty()){
                contextMenu.getItems().clear();
            }
            //contextMenu.getItems().addAll(menu,play);
            contextMenu.getItems().addAll(menu);

        });
    }
}
