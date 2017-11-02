import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This class handles the music player when its not
 * playing from the library. We have not started coding this
 * class yet.
 */
public class PlaylistMode implements MP3Player {

    private String path = "./default-playlist.xml";
    private ManageMP3PlayerState manageMp3PlayerState;

    /**
     * @param manageMp3PlayerState Takes in the ManageMP3PlayerState class.
     */
    public PlaylistMode(ManageMP3PlayerState manageMp3PlayerState, Player player) {
        this.manageMp3PlayerState = manageMp3PlayerState;
    }

    @Override
    public void loadNewTrack(String selectedSong) {

    }

    @Override
    public void playSong() {

    }

    @Override
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        //this.library.addSongToLibrary(new Player(songPath),this.cosongName, songPath);
        //also add to playlist
    }

    @Override
    public void addSongToPlaylist(Song song, String dataPath) {
        System.out.println(song.getPath());
    }

}
