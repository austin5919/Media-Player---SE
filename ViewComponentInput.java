import javafx.collections.ObservableList;

/**
 * this class handles the information used to
 * feed to the GUI components
 */
public class ViewComponentInput {

    private ObservableList<Song> playList;
    /**
     * gets the playlist
     * @return
     */
    public ObservableList<Song> getPlayList() { return playList; }

    /**
     * sets the playlist
     * @param playList
     */
    public void setPlayList(ObservableList<Song> playList) { this.playList = playList; }
}
