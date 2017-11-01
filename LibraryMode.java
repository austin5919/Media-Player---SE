
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This class equals my library mode and will execute methods
 * based on that mode.
 */
public class LibraryMode implements MP3Player {

    private ManageMP3PlayerState manageMp3PlayerState;
    private String libraryPath = "./library.data";

    /**
     * the constructor to set important values
     *
     * @param manageMp3PlayerState takes in the ManageMP3PlayerState class to be used.
     */
    public LibraryMode(ManageMP3PlayerState manageMp3PlayerState) {
        this.manageMp3PlayerState = manageMp3PlayerState;
    }

    /**
     * loads the media player with a new song that i can play
     *
     * @param selectedSong takes in the new song i want to play and sets it.
     */
    @Override
    public void loadNewTrack(String selectedSong) {
        manageMp3PlayerState.getPlayer().stop();
        manageMp3PlayerState.getPlayer().setMediaPlayer(selectedSong);
    }

    /**
     * plays the current song on the media player.
     */
    @Override
    public void playSong() {
        manageMp3PlayerState.getPlayer().play();
    }

    /**
     * this methods adds songs to the library.data and updates the display and music list
     *
     * @param newSongs takes in an array list of new songs i want to add
     */
    @Override
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        //add to library
        for (String readPath : newSongs) {
            new Write().storeData(libraryPath, readPath);
        }
        new Updates().updateMusicList(tableView, selectedIndex, manageMp3PlayerState.getMusicList(), newSongs);
    }

    /**
     * this method adds songs to a chosen playlist
     *
     * @param song     takes in the song object i want to add to playlist
     * @param dataPath takes in the path of the playlist i am adding songs to
     */
    @Override
    public void addSongToPlaylist(Song song, String dataPath) {
        System.out.println(song.getPath());
        System.out.println(dataPath);

    }
}
