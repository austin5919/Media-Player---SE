import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * This class handles the music player when its not
 * playing from the library. We have not started coding this
 * class yet.
 */
public class PlaylistMode implements MP3PlayerState {

    private String path = "./default-playlist.xml";
    private MP3Player mp3Player;

    /**
     * @param mp3Player Takes in the MP3Player class.
     */
    public PlaylistMode(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    @Override
    public void loadListOfPlaylist() {

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
