import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * This class will update the GUI components.
 */
public class Updates {
    private Components components;
    private MusicList musicList;

    public Updates() {
        this.components = null;
        this.musicList = null;
    }

    public Updates(Components components, MusicList musicList) {
        this.components = components;
        this.musicList = musicList;
    }

    public void addListOfSongs(ArrayList<String> listOfSongs) {

        for (String readPath : listOfSongs) {
            Player player = new Player(readPath);
            player.getMediaPlayer().setOnReady(new Runnable() {
                @Override
                public void run() {
                    if (new File(readPath).exists()) {

                        Platform.runLater(() -> {
                            String songName = new File(readPath).getName().replace(".mp3", "");
                            final String duration = player.getDuration();
                            musicList.addSong(songName, duration, readPath);
                            components.getDisplay().getItems().add(new Song(songName, duration, readPath));
                            components.getDisplay().getFocusModel().focus(components.getSelectedIndex());
                        });

                    } else {
                        System.out.println("could not find file : " + readPath);
                    }
                }
            });
        }
    }

    public void switchDisplay(ArrayList<ArrayList<String>> list) {

    }

    public void updateContextMenu(ContextMenu contextMenu, Menu menu, MenuItem play) {
        Platform.runLater(() -> {
            if (!contextMenu.getItems().isEmpty()) {
                contextMenu.getItems().clear();
            }
            contextMenu.getItems().addAll(menu, play);
            //contextMenu.getItems().addAll(menu);
        });
    }
}
