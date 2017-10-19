/**
 * this class handles the music player when it is not in library and
 * playing a song
 */
public class PlaylistMode implements MP3PlayerState {

    private String path = "./default-playlist.xml";
    private MP3Player mp3Player;

    private Library library;
    private Components comp;
    private Player player;
    /**
     * takes in a musicplayer class
     * @param mp3Player
     */
    public PlaylistMode(MP3Player mp3Player){
        this.mp3Player = mp3Player;
        this.comp = mp3Player.getComponents();
        this.library = new Library();
        this.player = mp3Player.getPlayer();

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
    public void addSong(String songName, String songPath) {
        //this.library.addSong(new Player(songPath),this.cosongName, songPath);
        //also add to playlist
    }

    @Override
    public void createPlaylist() {

        System.out.println("code to create playlist is under construction..!!");
        System.out.println("the file chooser can still browse files but nothings happens...!!");
        System.out.println("to test the file chooser functionality please go back to the Library...!!");
    }

    /**
     * changes the state to the idleLibrary state
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
