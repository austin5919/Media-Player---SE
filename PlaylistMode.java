import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This class handles the music player when its not
 * playing from the library. We have not started coding this
 * class yet.
 */
public class PlaylistMode implements MP3Player {
    private ManageMP3PlayerState manageMp3PlayerState;
    private Player player;
    private String libraryPath;


    /**
     * @param manageMp3PlayerState Takes in the ManageMP3PlayerState class.
     */
    public PlaylistMode(ManageMP3PlayerState manageMp3PlayerState, Player player) {
        this.manageMp3PlayerState = manageMp3PlayerState;
        this.player = player;
        this.libraryPath = "./library.data";
    }

    @Override
    public void loadNewTrack(String selectedSong, TableView<Song> songs) {
        player.Dispose();
        player.setSongs(songs);
    }

    @Override
    public void playSong(Label timer, String duration, int startTimer, Label songName) {
        this.player.setAutoPlay(timer,duration,startTimer, songName);
    }

    @Override
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        //add to library
        for (String readPath : newSongs) {
            new Write().storeData(libraryPath, readPath);
        }
        //update display and music list
        new Updates("BYPASS").updateMusicList(tableView, selectedIndex, manageMp3PlayerState.getMusicList(), newSongs);
    }

    @Override
    public void addSongToPlaylist(Song song, String dataPath) {
        //store the song link in the appropriate path
        new Write().storeData(dataPath,song.getPath());
    }
}
