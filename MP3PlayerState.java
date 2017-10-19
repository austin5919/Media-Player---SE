/**
 * an interface to implement the state pattern
 */
public interface MP3PlayerState {

    //load list of playlist
    public void loadListOfPlaylist();

    //TODO:load playlist
    //TODO: add to playlist


    public void loadNewTrack(String selectedSong);
    public void playSong();
    public void addSong(String songName,String songPath);
    public void createPlaylist();
    public void switchToLibrary();
    public void switchToPlaylist();

}
