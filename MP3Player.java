import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This class holds the information needed to handle
 * the states the MP3 player could be in.
 */
public class MP3Player {

    private Player player;
    private MusicList musicList;

    private MP3PlayerState MP3PlayerState;

    private MP3PlayerState libraryMode;
    private MP3PlayerState playlistMode;


    /**
     * A constructor to set my starting states.
     */
    public MP3Player() {
        this.libraryMode = new LibraryMode(this);
        this.playlistMode = new PlaylistMode(this);
        this.MP3PlayerState = libraryMode;
        this.musicList = new MusicList();
        this.player = new Player();
    }

    /**
     * Gets the player.
     *
     * @return The Player class.
     */
    public Player getPlayer() {
        return player;
    }

    public MusicList getMusicList() {
        return musicList;
    }

    /**
     * Gets the Library mode.
     *
     * @return The Library mode state.
     */
    public MP3PlayerState getLibraryMode() {
        return libraryMode;
    }

    /**
     * Gets the Playlist mode.
     *
     * @return The playlistMode state
     */
    public MP3PlayerState getPlaylistMode() {
        return playlistMode;
    }

    /**
     * Set the MP3PlayerState.
     *
     * @param MP3PlayerState Sets the new state of the MP3Player.
     */
    public void setMP3PlayerState(MP3PlayerState MP3PlayerState) {
        this.MP3PlayerState = MP3PlayerState;
    }

    /**
     * Load new song in to mediaplayer.
     *
     * @param selectedSong Takes in the new song to be played.
     */
    public void loadNewTrack(String selectedSong) {
        MP3PlayerState.loadNewTrack(selectedSong);
    }

    /**
     * Runs playSong method on current MP3PlayerState of the music player.
     */
    public void playSong() {
        MP3PlayerState.playSong();
    }

    /**
     * Calls the add song method based on the state of MP3Player.
     *
     * @param newSongs The path of the new song.
     */
    public void addSong(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        MP3PlayerState.addSong(tableView, selectedIndex, newSongs);
    }

    /**
     * Calls the switchToLibrary method based on the current MP3PlayerState of the music player.
     */
    public void switchToLibrary() {
        setMP3PlayerState(getLibraryMode());
    }

    /**
     * Calls the switchToOtherPlaylist method based on the current MP3PlayerState of the music player.
     */
    public void switchToPlaylist() {
        setMP3PlayerState(getPlaylistMode());
    }

    /**
     * Calls the createPlaylist method based on the current state of the MP3Player.
     */
    public void createPlaylist(ComboBox comboBox, String oldSelection, Stage stage) {
        stage.showAndWait();
        comboBox.getSelectionModel().select(oldSelection);
    }

    /**
     * Load list of playlist based on the current state of the MP3 Player.
     */
    public void loadListOfPlaylist() {
        MP3PlayerState.loadListOfPlaylist();
    }
}
