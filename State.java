/**
 * an interface to implement the state pattern
 */
public interface State {
    public void loadLibrary();
    public void loadNewTrack();
    public void playSong();
    public void browseSong();
    public void createPlaylist();
    public void switchToLibrary();
    public void switchToPlaylist();
}
