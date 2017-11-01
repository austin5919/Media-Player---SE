import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This interface simply defines the public method
 * of the playlistMode and libraryMode classes.
 */
public interface MP3Player {

    //TODO:load playlist
    //TODO: add to playlist
    public void loadNewTrack(String selectedSong);
    public void playSong();
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs);
    public void addSongToPlaylist(Song song,String dataPath);

}
