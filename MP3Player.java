/**
 * this class holds the information needed to handle
 * the states the MP3 player could be in.
 */
public class MP3Player {

    private Player player = new Player();
    private Components components = new Components();
    private Library library = new Library();

    private MP3PlayerState MP3PlayerState;

    private MP3PlayerState libraryMode;
    private MP3PlayerState playlistMode;


    /**
     * a constructor to set my starting states
     */
    public MP3Player(){

        this.libraryMode = new LibraryMode(this);
        this.playlistMode = new PlaylistMode(this);
        this.MP3PlayerState = libraryMode;
    }

    /**
     * @return returns the Player class
     */
    public Player getPlayer() {

        return player;
    }

    /**
     * @return returns the Components class
     */
    public Components getComponents() {

        return components;
    }

    /**
     * @return returns the library class
     */
    public Library getLibrary() {

        return library;
    }

    /**
     * @return the libraryMode state
     */
    public MP3PlayerState getLibraryMode() {

        return libraryMode;
    }

    /**
     * @return returns the playlistMode state
     */
    public MP3PlayerState getPlaylistMode() {
        return playlistMode;
    }

    /**
     * @param MP3PlayerState sets the new state of the MP3Player
     */
    public void setMP3PlayerState(MP3PlayerState MP3PlayerState) {

        this.MP3PlayerState = MP3PlayerState;
    }

    /**
     * load new song in to mediaplayer
     *
     * @param selectedSong takes in the new song to be played
     */
    public void loadNewTrack(String selectedSong){

        this.MP3PlayerState.loadNewTrack(selectedSong);
    }

    /**
     * calls the playSong method based on the current MP3PlayerState of the music player
     */
    public void playSong(){

        this.MP3PlayerState.playSong();
    }

    /**
     * calls the add song method based on the state of MP3Player
     *
     * @param songName takes in the song name
     * @param songPath takes in the path of the new song
     */
    public void addSong(String songName, String songPath){

        this.MP3PlayerState.addSong(songName,songPath);
    }

    /**
     * calls the switchToLibrary method based on the current MP3PlayerState of the music player
     */
    public void switchToLibrary(){

        this.MP3PlayerState.switchToLibrary();
    }

    /**
     * calls the switchToOtherPlaylist method based on the current MP3PlayerState of the music player
     */
    public void switchToPlaylist(){

        this.MP3PlayerState.switchToPlaylist();
    }

    /**
     * calls the createPlaylist method based on the current state of the MP3Player
     */
    public void createPlaylist(){

        this.MP3PlayerState.createPlaylist();
    }

    /**
     * load list of playlist based on the current state of the MP3 Player.
     */
    public void loadListOfPlaylist(){

        this.MP3PlayerState.loadListOfPlaylist();
    }
}
