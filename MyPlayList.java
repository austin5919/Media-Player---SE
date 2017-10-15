import javafx.scene.control.TableView;

/**
 * this is the playlist used for purpose such as autoplay
 */
public class MyPlayList {

    private TableView<Song> backgroundPlayer;

    /**
     * gets the TableView
     * @return
     */
    public TableView<Song> getBackgroundPlayer() { return backgroundPlayer; }

    /**
     * sets the TableView
     * @param backgroundPlayer
     */
    public void setBackgroundPlayer(TableView<Song> backgroundPlayer) {
        this.backgroundPlayer = backgroundPlayer;
    }
}
