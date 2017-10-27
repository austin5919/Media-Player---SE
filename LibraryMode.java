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
        this.mp3Player.getPlayer().stop();
        this.mp3Player.getPlayer().setMediaPlayer(selectedSong);
    }

    /**
     * Play a song.
     */
    @Override
    public void playSong() {
        this.mp3Player.getPlayer().play();
        //this.mp3Player.setMP3PlayerState(this.mp3Player.getPlayingLibrary());
    }

    /**
     * Adds song to library.
     *
     * @param newSongs Takes in path of new song
     */
    @Override
    public void addSong(ArrayList<String> newSongs) {
        //add to library
        this.mp3Player.getLibrary().addsongtoLibrary(newSongs);
        this.mp3Player.getLibrary().refreshLibrary();
        new Updates(newSongs, this.mp3Player.getComponents()).updateDisplay("Add");
    }

    /**
     * Coming soon...
     */
    @Override
    public void createPlaylist() {
        System.out.println("code to create playlist is under construction..!!");
        System.out.println("the file chooser can still browse files but nothings happens...!!");
        System.out.println("to test the file chooser functionality please go back to the Library...!!");
        this.mp3Player.getComponents().getComboBox().getSelectionModel().select("Library");
    }

    /**
     * Prints an alert message since we are already in library state.
     */
    @Override
    public void switchToLibrary() {
        System.out.println("we are already in library state");
    }

    /**
     * Use to change the state to playlistMode state.
     */
    @Override
    public void switchToPlaylist() {
        this.mp3Player.setMP3PlayerState(this.mp3Player.getPlaylistMode());
    }
}
