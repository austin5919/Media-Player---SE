import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This class equals my library mode and will execute methods
 * based on that mode.
 */
public class LibraryMode implements MP3Player {

    //local variables
    private ManageMP3PlayerState manageMp3PlayerState;
    private Player player;
    private String libraryPath;

    /**
     * @param manageMp3PlayerState the ManageMP3Player class
     * @param player               the Player class
     */
    public LibraryMode(ManageMP3PlayerState manageMp3PlayerState, Player player) {
        this.manageMp3PlayerState = manageMp3PlayerState;
        this.player = player;
        this.libraryPath = "./library.data";
    }

    /**
     * This method takes the path of a song and feeds it to the
     * Player class to create a MediaPlayer i can use to play
     * the song.
     *
     * @param selectedSong the path to the song i want to play.
     */
    @Override
    public void loadNewTrack(String selectedSong, TableView<Song> songs) {
        //trash the old media player
        player.Dispose();

        //create a new media player
        player.setMediaPlayer(selectedSong);
    }

    /**
     * play the song loaded in to the media player
     */
    @Override
    public void playSong(Label timer, String duration,int startTimer,Label songName) {
        player.play(timer, duration);
    }

    /**
     * this methods adds songs to the library.data and updates the display
     * and music list they must be updated at the same time because the
     * set on ready might not trigger right away and we need the music list
     * up to date before updating the display
     *
     * @param tableView     the table view i want to update
     * @param selectedIndex the selected index to preserve the focus
     * @param newSongs      the new song i want to add
     */
    @Override
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        //add to library
        for (String readPath : newSongs) {
            new Write().storeData(libraryPath, readPath);
        }

        //update display and music list
        new Updates().updateMusicList(tableView, selectedIndex, manageMp3PlayerState.getMusicList(), newSongs);
    }

    /**
     * This method takes in a song and adds it to a playlist.
     *
     * @param song     the song object i want to add to playlist
     * @param dataPath the path of the playlist i want add songs to
     */
    @Override
    public void addSongToPlaylist(Song song, String dataPath) {
        //store the song link in the appropriate path
        new Write().storeData(dataPath,song.getPath());
    }
}
