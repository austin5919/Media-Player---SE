import javafx.application.Platform;

/**
 * This defines my actions when the mp3Player is on libraryMode state.
 */
public class LibraryMode implements MP3PlayerState {

    //private WriteXml xmlWrite = new WriteXml();
    private MP3Player mp3Player;
    private String libraryPath = "./library.data";

    private Library library;
    private Components components;
    private Player player;
    //Library library;

    /**
     * Take in a mp3Player and set some variables to make
     * life easier.
     *
     * @param mp3Player  Takes the MP3Player class.
     */
    public LibraryMode(MP3Player mp3Player){

        this.mp3Player = mp3Player;
        this.components = mp3Player.getComponents();
        this.library = new Library();
        this.player = mp3Player.getPlayer();
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
     * @param selectedSong  Takes in the new song to be played.
     */
    @Override
    public void loadNewTrack(String selectedSong) {
        this.player.stop();
        this.player.setMediaPlayer(selectedSong);
    }

    /**
     * Play a song.
     */
    @Override
    public void playSong() {

        player.play();
        //this.mp3Player.setMP3PlayerState(this.mp3Player.getPlayingLibrary());

    }

    /**
     * Adds song to library.
     *
     * @param songName  Takes in the name of new song.
     * @param songPath  Takes in path of new song
     */
    @Override
    public void addSong(String songName, String songPath) {

        Player deadPlayer = new Player(songPath);
        deadPlayer.getMediaPlayer().setOnReady(() -> {
            Platform.runLater(() -> {
                //add to library
                this.library.addsongtoLibrary(new Song(songName,deadPlayer.getDuration(deadPlayer.getMediaPlayer()),songPath));
                this.library.refreshLibrary();
                new Updates().updateDisplay(this.library.getListOfSongs(), this.components);

            });
         });
    }

    /**
     * Coming soon...
     */
    @Override
    public void createPlaylist() {

        System.out.println("code to create playlist is under construction..!!");
        System.out.println("the file chooser can still browse files but nothings happens...!!");
        System.out.println("to test the file chooser functionality please go back to the Library...!!");
        this.components.getComboBox().getSelectionModel().select("Library");
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
