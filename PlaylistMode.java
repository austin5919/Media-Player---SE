import java.util.ArrayList;

/**
 * This class handles the music player when its not
 * playing from the library. We have not started coding this
 * class yet.
 */
public class PlaylistMode implements MP3PlayerState {

    private String path = "./default-playlist.xml";
    private MP3Player mp3Player;
    private Components comp;

    /**
     * @param mp3Player Takes in the MP3Player class.
     */
    public PlaylistMode(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
        this.comp = mp3Player.getComponents();
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
    public void addSong(ArrayList<String> newSongs) {
        //this.library.addSong(new Player(songPath),this.cosongName, songPath);
        //also add to playlist
    }

    @Override
    public void createPlaylist() {
        System.out.println("code to create playlist is under construction..!!");
        this.mp3Player.getComponents().getComboBox().getSelectionModel().select("Library");
    }

    /**
     * Changes the state to the idleLibrary state
     */
    @Override
    public void switchToLibrary() {
        this.mp3Player.setMP3PlayerState(this.mp3Player.getLibraryMode());
    }

    @Override
    public void switchToPlaylist() {
        System.out.println("we are already in playlist state");
    }

}
