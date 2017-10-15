import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class MusicPlayer {

    private SongActions controller = new SongActions();

    private TableView<Song> display;
    private ObservableList<Song> playList;
    private ComboBox playListName;

    private String selectedSong;

    private String browserSongName;
    private String browserPath;

    private State state;

    private State idleLibrary;
    private State playingLibrary;
    private State idleOtherPlaylist;
    private State playingOtherPlaylist;

    public MusicPlayer(){

        this.idleLibrary = new IdleLibrary(this);
        this.playingLibrary = new PlayingLibrary(this);
        this.idleOtherPlaylist = new IdleOtherPlaylist(this);
        this.state = idleLibrary;
    }

    public TableView<Song> getDisplay() {
        return display;
    }

    public void setDisplay(TableView<Song> display) {
        this.display = display;
    }

    public SongActions getController() { return controller; }

    public State getIdleOtherPlaylist() { return idleOtherPlaylist; }

    public String getBrowserSongName() { return browserSongName; }

    public ComboBox getPlayListName() { return playListName; }

    public void setBrowserSongName(String browserSongName) { this.browserSongName = browserSongName; }

    public String getBrowserPath() { return browserPath; }

    public ObservableList<Song> getPlayList() { return playList; }

    public void setPlayList(ObservableList<Song> playList) { this.playList = playList; }

    public void setBrowserPath(String browserPath) { this.browserPath = browserPath; }

    public void setPlayListName(ComboBox playListName) { this.playListName = playListName; }

    public State getIdleLibrary() { return idleLibrary; }

    public State getPlayingLibrary() { return playingLibrary; }

    public void setState(State state) { this.state = state; }

    public String getSelectedSong() {
        return selectedSong;
    }

    public void setSelectedSong(String selectedSong) {
        this.selectedSong = selectedSong;
    }

    public void loadLibrary(){this.state.loadLibrary();}
    public void loadNewTrack(){this.state.loadNewTrack();}
    public void playSong(){this.state.playSong();}
    public void browseSong(){this.state.browseSong();}
    public void switchToLibrary(){this.state.switchToLibrary();}
    public void switchToOtherPlaylist(){this.state.switchToOtherPlaylist();}
}
