import java.util.ArrayList;

/**
 * This class holds the information needed to handle
 * the states the MP3 player could be in.
 */
public class MP3Player {

    private Player player;
    private Components components;
    private MusicList musicList;
    private Library library;

    private MP3PlayerState MP3PlayerState;

    private MP3PlayerState libraryMode;
    private MP3PlayerState playlistMode;


    /**
     * A constructor to set my starting states.
     */
    public MP3Player() {
        this.libraryMode = new LibraryMode(this);
        this.playlistMode = new PlaylistMode(this);
        this.MP3PlayerState = libraryMode;
        this.components = new Components();
        this.musicList = new MusicList();
        this.library = new Library();
        this.player = new Player();
    }

    /**
     * Gets the player.
     *
     * @return The Player class.
     */
    public Player getPlayer() {
        return player;
    }

    public MusicList getMusicList() {
        return musicList;
    }

    /**
     * Gets the Components.
     *
     * @return The Components class.
     */
    public Components getComponents() {
        return components;
    }

    /**
     * Gets the Library.
     *
     * @return The Library class
     */
    public Library getLibrary() {
        return library;
    }

    /**
     * Gets the Library mode.
     *
     * @return The Library mode state.
     */
    public MP3PlayerState getLibraryMode() {
        return libraryMode;
    }

    /**
     * Gets the Playlist mode.
     *
     * @return The playlistMode state
     */
    public MP3PlayerState getPlaylistMode() {
        return playlistMode;
    }

    /**
     * Set the MP3PlayerState.
     *
     * @param MP3PlayerState Sets the new state of the MP3Player.
     */
    public void setMP3PlayerState(MP3PlayerState MP3PlayerState) {
        this.MP3PlayerState = MP3PlayerState;
    }

    /**
     * Load new song in to mediaplayer.
     *
     * @param selectedSong Takes in the new song to be played.
     */
    public void loadNewTrack(String selectedSong) {
        this.MP3PlayerState.loadNewTrack(selectedSong);
    }

    /**
     * Runs playSong method on current MP3PlayerState of the music player.
     */
    public void playSong() {
        this.MP3PlayerState.playSong();
    }

    /**
     * Calls the add song method based on the state of MP3Player.
     *
     * @param newSongs The path of the new song.
     */
    public void addSong(ArrayList<String> newSongs) {
        this.MP3PlayerState.addSong(newSongs);
    }

    /**
     * Calls the switchToLibrary method based on the current MP3PlayerState of the music player.
     */
    public void switchToLibrary() {
        this.MP3PlayerState.switchToLibrary();
    }

    /**
     * Calls the switchToOtherPlaylist method based on the current MP3PlayerState of the music player.
     */
    public void switchToPlaylist() {
        this.MP3PlayerState.switchToPlaylist();
    }

    /**
     * Calls the createPlaylist method based on the current state of the MP3Player.
     */
    public void createPlaylist() {
        this.MP3PlayerState.createPlaylist();
    }

    /**
     * Load list of playlist based on the current state of the MP3 Player.
     */
    public void loadListOfPlaylist() {
        this.MP3PlayerState.loadListOfPlaylist();
    }
}
