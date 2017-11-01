
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This defines my actions when the manageMp3PlayerState is on libraryMode state.
 */
public class LibraryMode implements MP3Player {

    //private Write xmlWrite = new Write();
    private ManageMP3PlayerState manageMp3PlayerState;
    private String libraryPath = "./library.data";

    /**
     * Take in a manageMp3PlayerState and set some variables to make
     * life easier.
     *
     * @param manageMp3PlayerState Takes the ManageMP3PlayerState class.
     */
    public LibraryMode(ManageMP3PlayerState manageMp3PlayerState) {
        this.manageMp3PlayerState = manageMp3PlayerState;
    }

    /**
     * Load new song
     *
     * @param selectedSong Takes in the new song to be played.
     */
    @Override
    public void loadNewTrack(String selectedSong) {
        manageMp3PlayerState.getPlayer().stop();
        manageMp3PlayerState.getPlayer().setMediaPlayer(selectedSong);
    }

    /**
     * Play a song.
     */
    @Override
    public void playSong() {
        manageMp3PlayerState.getPlayer().play();
        //this.manageMp3PlayerState.setMP3Player(this.manageMp3PlayerState.getPlayingLibrary());
    }

    /**
     * Adds song to library.
     *
     * @param newSongs Takes in path of new song
     */
    @Override
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        //add to library
        for (String readPath : newSongs) { new Write().storeData(libraryPath, readPath); }
        new Updates().updateMusicList(tableView, selectedIndex, manageMp3PlayerState.getMusicList(),newSongs);
    }

    @Override
    public void addSongToPlaylist(Song song, String dataPath) {
        System.out.println(song.getPath());
        System.out.println(dataPath);

    }
}
