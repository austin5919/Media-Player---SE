/**
 * this class holds all the information i will need
 * to properly change the states of the mp3 player
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
     * get the song class
     * @return
     */
    public Player getPlayer() {

        return player;
    }

    /**
     * gets the viewComponent Class
     * @return
     */
    public Components getComponents() {

        return components;
    }

    public Library getLibrary() {

        return library;
    }

    /**
     * gets the libraryMode MP3PlayerState
     * @return
     */
    public MP3PlayerState getLibraryMode() {

        return libraryMode;
    }

    public MP3PlayerState getPlaylistMode() {
        return playlistMode;
    }

    /**
     * sets the musicplayer MP3PlayerState
     * @param MP3PlayerState
     */
    public void setMP3PlayerState(MP3PlayerState MP3PlayerState) {

        this.MP3PlayerState = MP3PlayerState;
    }

    /**
     * calls the loadNewTrack method based on the current MP3PlayerState of the music player
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
     * calls the browser song method based on the current MP3PlayerState of the music player
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

    public void createPlaylist(){

        this.MP3PlayerState.createPlaylist();
    }

    public void loadListOfPlaylist(){

        this.MP3PlayerState.loadListOfPlaylist();
    }
}
