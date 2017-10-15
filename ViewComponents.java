import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * this class holds all the components i will
 * constantly be updating from the GUI(View)
 */
public class ViewComponents {

    private TableView<Song> display;
    private ComboBox playListName;


    /**
     * gets the tableView to display songs
     * @return
     */
    public TableView<Song> getDisplay() {
        return display;
    }

    /**
     * sets the tableView to display songs
     * @param display
     */
    public void setDisplay(TableView<Song> display) {
        this.display = display;
    }

    /**
     * gets the comboBox that will hold all the playlist
     * @return
     */
    public ComboBox getPlayListName() {
        return playListName;
    }

    /**
     * sets the comboBox that will hold all playlist
     * @param playListName
     */
    public void setPlayListName(ComboBox playListName) {
        this.playListName = playListName;
    }
}
