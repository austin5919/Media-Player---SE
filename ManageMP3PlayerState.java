import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

/**
 * this class will manage the state of the MP3Player.
 * Using this class The MP3Player interface is able to
 * call a class to execute a chosen method based on the
 * MP3Player state of this class. The current states are
 * libraryMode which prompts the MP3Player to call the
 * LibraryMode class and playlistMode which prompts the
 * MP3Player to call the PlaylistMode class.
 */
public class ManageMP3PlayerState {
    //local variables
    private Player player;
    private MusicList musicList;
    private MP3Player MP3Player;
    private MP3Player libraryMode;
    private MP3Player playlistMode;


    /**
     * a constructor to set my starting states.
     */
    public ManageMP3PlayerState() {
        this.player = new Player();
        this.musicList = new MusicList();
        this.libraryMode = new LibraryMode(this, player);
        this.playlistMode = new PlaylistMode(this, player);
        this.MP3Player = libraryMode;
    }

    /**
     * gets the MusicList class and this
     * class is the main library and used
     * by the EventHandle, the PlaylistMode
     * and the LibraryMode.This method
     * makes the MusicList accessible to
     * them without having to create a
     * new instance every time.
     *
     * @return the MusicList class
     */
    public MusicList getMusicList() {
        return musicList;
    }

    /**
     * gets the libraryMode state. This state
     * tells MP3Player interface that i want
     * to use the LibraryMode class
     *
     * @return the libraryMode state
     */
    public MP3Player getLibraryMode() {
        return libraryMode;
    }

    /**
     * gets the playlistMode state. This state
     * tells MP3Player interface that i want
     * to use the PlayListMode class
     *
     * @return the playlistMode state
     */
    public MP3Player getPlaylistMode() {
        return playlistMode;
    }

    /**
     * sets the state of the MP3Player.
     * Currently there is only two which are
     * the libraryMode and the playlistMode
     * and they help the interface perform
     * methods accordingly.
     *
     * @param MP3Player the MP3Player state
     */
    public void setMP3Player(MP3Player MP3Player) {
        this.MP3Player = MP3Player;
    }

    public MP3Player getMP3Player() {
        return MP3Player;
    }

    /**
     * this method passes the path of a song to the loadNewTrack
     * method in the MP3Player interface.
     *
     * @param path the path of the song i want to load
     */
    public void loadNewTrack(String path, Label songName, Label timer, TableView<Song> tableView) {
        MP3Player.loadNewTrack(path, songName, timer,tableView);
    }

    /**
     * this method calls the focusValue method in the
     * MP3Player interface.
     */
    public void focusValue(String current, ComboBox<String> comboBox) {
        MP3Player.focusValue(current,comboBox);
    }

    /**
     * this method passes an array list of song
     * to the addSongToLibrary method in the MP3Player
     * interface.
     *
     * @param tableView     the table view i want to add to
     * @param selectedIndex the selected focus to preserve it
     * @param newSongs      the songs i want to add
     */
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        MP3Player.addSongToLibrary(tableView, selectedIndex, newSongs);
    }

    /**
     * this method passes in a song and a path to the addSongToPlaylist
     * method in the MP3Player interface.
     *
     * @param song     the song i want to add
     * @param dataPath the path of the playlist i want to add song to
     */
    public void addSongToPlaylist(Song song, String dataPath, ArrayList<ArrayList<String>> collection) {
        MP3Player.addSongToPlaylist(song, dataPath, collection);
    }

    public void addPlaylistToPlaylist(String playlist, String dataPath, ComboBox comboBox, ArrayList<ArrayList<String>> collection){
        //System.out.println(comboBox.getSelectionModel().getSelectedItem().toString());
        MP3Player.addPlaylistToPlaylist(playlist,dataPath, comboBox, collection);
    }

    public void stopPlayer(){
        MP3Player.stopPlayer();
    }

    public void remove(String dataPath, Song song,TableView<Song> tableView, int selectedIndex){
        MP3Player.removeSong(dataPath,song,tableView,selectedIndex);
    }
    public void removePlay(TableView<Song> song, String dataPath,ComboBox comboBox, ArrayList<String> shuffle, int selectedIndex){
        MP3Player.removePlaylist(song, dataPath,comboBox,shuffle,selectedIndex);
    }
}
