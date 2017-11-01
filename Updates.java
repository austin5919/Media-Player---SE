import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;

/**
 * This class will update the GUI components.
 */
public class Updates {

    public void updateMusicList(
            TableView<Song> tableView, int selectedIndex,
            MusicList musicList, ArrayList<String> listOfSongs) {

        for (String readPath : listOfSongs) {
            Player player = new Player(readPath);
            player.getMediaPlayer().setOnReady(new Runnable() {
                @Override
                public void run() {
                    if (new File(readPath).exists()) {
                        String songName = new File(readPath).getName().replace(".mp3", "");
                        final String duration = player.getDuration();

                        musicList.addSong(songName, duration, readPath);

                        Song song = new Song(songName, duration, readPath);
                        updateTableView(tableView, selectedIndex, song);
                    } else {
                        System.out.println("could not find file : " + readPath);
                    }
                }
            });
        }

    }

    private void updateTableView(TableView<Song> tableView, int selectedIndex, Song song) {
        tableView.getItems().add(song);
        tableView.getFocusModel().focus(selectedIndex);
    }

    public void updateComboBox(ComboBox comboBox, ArrayList<String> arrayList) {

        for (String readContent : arrayList) {

            if (comboBox.getItems().contains(readContent)) {
                System.out.println("this playlist alreayd exist");
            } else {
                comboBox.getItems().add(readContent);
            }
        }
    }

    public void switchDisplays(TableView<Song> tableView, ObservableList<Song> observableList) {
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });
    }
}
