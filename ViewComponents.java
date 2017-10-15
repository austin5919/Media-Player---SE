import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * this class holds all the components i will
 * constantly be updating from the GUI(View)
 */
public class ViewComponents {
    private TableView<Song> display;
    private ComboBox playListName;
    private Button fileChooser;

    private String browserSongName;
    private String browserPath;

    private String selectedSong;
    private int selectedIndex;


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
    public void setDisplay(TableView<Song> display) { this.display = display; }

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

    /**
     * sets the file chooser button
     * @param fileChooser
     */
    public void setFileChooser(Button fileChooser) { this.fileChooser = fileChooser; }

    /**
     * gets browserSongName
     * @return
     */
    public String getBrowserSongName() { return browserSongName; }

    /**
     * set BrowserSongName
     * @param browserSongName
     */
    public void setBrowserSongName(String browserSongName) { this.browserSongName = browserSongName; }

    /**
     * get browser path
     * @return
     */
    public String getBrowserPath() { return browserPath; }

    /**
     * set browser path
     * @param browserPath
     */
    public void setBrowserPath(String browserPath) { this.browserPath = browserPath; }

    /**
     * get selected song
     * @return
     */
    public String getSelectedSong() { return selectedSong; }

    /**
     * set selected song
     * @param selectedSong
     */
    public void setSelectedSong(String selectedSong) { this.selectedSong = selectedSong; }

    /**
     * get selected index
     * @return
     */
    public int getSelectedIndex() { return selectedIndex; }

    /**
     * set selected index
     * @param selectedIndex
     */
    public void setSelectedIndex(int selectedIndex) { this.selectedIndex = selectedIndex; }
}
