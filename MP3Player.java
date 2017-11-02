import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This interface simply defines the public method
 * of the playlistMode and libraryMode classes.
 */
public interface MP3Player {
    void loadNewTrack(String selectedSong);
    void playSong();
    void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs);
    void addSongToPlaylist(Song song,String dataPath);
}
