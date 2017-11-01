import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;

/**
 * This class will update components when called.
 */
public class Updates {

    /**
     * This method updates the music list and display. They are done together because the
     * set on ready may not execute right away and we need the library up to date when interacting
     * with the display
     *
     * @param tableView     takes the main display so that we can update it as we add to library
     * @param selectedIndex takes in the selected index to set the focus on the proper cell
     * @param musicList     takes in the music list so we can add the songs to it. Our main library
     * @param listOfSongs   takes in the list of songs we want to add
     */
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

    //update display
    private void updateTableView(TableView<Song> tableView, int selectedIndex, Song song) {
        tableView.getItems().add(song);
        tableView.getFocusModel().focus(selectedIndex);
    }

    /**
     * This method properly adds objects to the choices of a combo box.
     *
     * @param comboBox  takes in the combo box we want to update
     * @param arrayList takes in the list of string we want to add to the combo box
     */
    public void updateComboBox(ComboBox comboBox, ArrayList<String> arrayList) {

        for (String readContent : arrayList) {

            if (comboBox.getItems().contains(readContent)) {
                System.out.println("this playlist alreayd exist");
            } else {
                comboBox.getItems().add(readContent);
            }
        }
    }
}
