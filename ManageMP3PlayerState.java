import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * this class will manage the state of the ManageMP3PlayerState
 */
public class ManageMP3PlayerState {
    private Player player;
    private MusicList musicList;
    private MP3Player MP3Player;
    private MP3Player libraryMode;
    private MP3Player playlistMode;


    /**
     * A constructor to set my starting states.
     */
    public ManageMP3PlayerState() {
        this.libraryMode = new LibraryMode(this);
        this.playlistMode = new PlaylistMode(this);
        this.MP3Player = libraryMode;
        this.musicList = new MusicList();
        this.player = new Player();
    }

    //getters and setters

    public Player getPlayer() {
        return player;
    }

    public MusicList getMusicList() {
        return musicList;
    }

    public MP3Player getLibraryMode() {
        return libraryMode;
    }

    public MP3Player getPlaylistMode() {
        return playlistMode;
    }

    public void setMP3Player(MP3Player MP3Player) {
        this.MP3Player = MP3Player;
    }

    /**
     * @param selectedSong Takes in the new song to be played it will then be passed to the interface.
     *                     the class that will perform the method will be based on the ManageMP3PlayerState
     *                     current state
     */
    public void loadNewTrack(String selectedSong) {
        MP3Player.loadNewTrack(selectedSong);
    }

    /**
     * calls the play song method on the interface. the
     * class that will perform the method will based on
     * the ManageMP3PlayerState current state
     */
    public void playSong() {
        MP3Player.playSong();
    }

    /**
     * @param newSongs receives a path to pass in to the interface. The class that will perform the
     *                 method will be based on the ManageMP3PlayerState current state
     */
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        MP3Player.addSongToLibrary(tableView, selectedIndex, newSongs);
    }

    /**
     * simply switches the state to library mode
     */
    public void switchToLibrary() {
        setMP3Player(getLibraryMode());
    }

    /**
     * simply switches the state to play list mode
     */
    public void switchToPlaylist() {
        setMP3Player(getPlaylistMode());
    }

    /**
     * this method calls the stage and when it closes it changes the combo selection
     * back to the old selection
     */
    public void createPlaylist(ComboBox comboBox, String oldSelection, Stage stage) {
        stage.showAndWait();
        comboBox.getSelectionModel().select(oldSelection);
    }

    /**
     * @param song     receives a song that will be passed in to the interface.
     * @param dataPath receives a path that will passed in to the interface. he class that will perform the method
     *                 will be based on the ManageMP3PlayerState current state
     */
    public void addSongToPlaylist(Song song, String dataPath) {
        MP3Player.addSongToPlaylist(song, dataPath);
    }
}
