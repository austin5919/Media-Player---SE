import javafx.application.Platform;

/**
 * this defines my actions when the mp3Player is on idle library state
 */
public class LibraryMode implements MP3PlayerState {

    //private WriteXml xmlWrite = new WriteXml();
    private MP3Player mp3Player;
    private String libraryPath = "./library.xml";

    private Library library;
    private Components components;
    private Player player;
    //Library library;

    /**
     * take in a mp3Player and set some variables to make
     * life easier
     * @param mp3Player
     */
    public LibraryMode(MP3Player mp3Player){

        this.mp3Player = mp3Player;
        this.components = mp3Player.getComponents();
        this.library = new Library();
        this.player = mp3Player.getPlayer();
    }

    @Override
    public void loadListOfPlaylist() {
        //TODO:check if file that contains list of playlist exist
    }

    /**
     * load a new song
     */
    @Override
    public void loadNewTrack(String selectedSong) {
        this.player.stop();
        this.player.setMediaPlayer(selectedSong);
    }

    /**
     * play a song
     */
    @Override
    public void playSong() {

        player.play();
        //this.mp3Player.setMP3PlayerState(this.mp3Player.getPlayingLibrary());

    }

    /**
     * browse file
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

    @Override
    public void createPlaylist() {

        System.out.println("code to create playlist is under construction..!!");
        System.out.println("the file chooser can still browse files but nothings happens...!!");
        System.out.println("to test the file chooser functionality please go back to the Library...!!");
        this.components.getComboBox().getSelectionModel().select("Library");
    }

    @Override
    public void switchToLibrary() {
        System.out.println("we are already in library state");
    }

    /**
     * change state to idleOtherPlaylist
     */
    @Override
    public void switchToPlaylist() {
        this.mp3Player.setMP3PlayerState(this.mp3Player.getPlaylistMode());
    }
}
