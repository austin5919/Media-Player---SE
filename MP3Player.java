import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This interface simply defines the public method
 * of the playlistMode and libraryMode classes.
 */
public interface MP3Player {
    void loadNewTrack(String path, Label songName, Label timer, TableView<Song> tableView);

    void focusValue(String current, ComboBox<String> comboBox);

    void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs);

    void addSongToPlaylist(Song song, String dataPath, ArrayList<ArrayList<String>> collection);
    void removeSong(String dataPath, Song song,TableView<Song> tableView, int selectedIndex);
    void removePlaylist(TableView<Song> song, String dataPath,ComboBox comboBox,ArrayList<String> shuffle, int selectedIndex);

    void addPlaylistToPlaylist(String playlist, String dataPath, ComboBox comboBox, ArrayList<ArrayList<String>> collection);
    void stopPlayer();
}
