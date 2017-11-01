
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This defines my actions when the mp3Player is on libraryMode state.
 */
public class LibraryMode implements MP3PlayerState {

    //private Write xmlWrite = new Write();
    private MP3Player mp3Player;
    private String libraryPath = "./library.data";

    /**
     * Take in a mp3Player and set some variables to make
     * life easier.
     *
     * @param mp3Player Takes the MP3Player class.
     */
    public LibraryMode(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    /**
     * Load list of playlist. Under construction.
     */
    @Override
    public void loadListOfPlaylist() {
        //TODO:check if file that contains list of playlist exist
    }

    /**
     * Load new song
     *
     * @param selectedSong Takes in the new song to be played.
     */
    @Override
    public void loadNewTrack(String selectedSong) {
        mp3Player.getPlayer().stop();
        mp3Player.getPlayer().setMediaPlayer(selectedSong);
    }

    /**
     * Play a song.
     */
    @Override
    public void playSong() {
        mp3Player.getPlayer().play();
        //this.mp3Player.setMP3PlayerState(this.mp3Player.getPlayingLibrary());
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
        new Updates().updateMusicList(tableView, selectedIndex, mp3Player.getMusicList(),newSongs);
    }

    @Override
    public void addSongToPlaylist(Song song, String dataPath) {
        System.out.println(song.getPath());
        System.out.println(dataPath);

    }
}
