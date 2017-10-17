/**
 * this class holds all the information i will need
 * to properly change the states of the mp3 player
 */
public class StateChanges {

    private MusicPlayer songActClass = new MusicPlayer();
    private ViewComponents viewCompClass = new ViewComponents();
    private MyPlayList myPlayListClass = new MyPlayList();

    private State state;

    private State idleLibrary;
    private State playingLibrary;
    private State idlePlaylist;
    private State playingPlaylist;


    /**
     * a constructor to set my starting states
     */
    public StateChanges(){

        this.idleLibrary = new IdleLibrary(this);
        this.playingLibrary = new PlayingLibrary(this);
        this.idlePlaylist = new IdlePlaylist(this);
        this.state = idleLibrary;
    }

    /**
     * gets the MyPlayList class
     * @return
     */
    public MyPlayList getMyPlayListClass() { return myPlayListClass; }

    /**
     * get the song class
     * @return
     */
    public MusicPlayer getSongActClass() { return songActClass; }

    /**
     * gets the viewComponent Class
     * @return
     */
    public ViewComponents getViewCompClass() { return viewCompClass; }

    /**
     * gets the idleLibrary state
     * @return
     */
    public State getIdleLibrary() { return idleLibrary; }

    /**
     * gets the playingLibrary state
     * @return
     */
    public State getPlayingLibrary() { return playingLibrary; }

    /**
     * get idle playlist
     * @return
     */
    public State getIdlePlaylist() { return idlePlaylist; }

    /**
     * get playing playlist
     * @return
     */
    public State getPlayingPlaylist() { return playingPlaylist; }

    /**
     * sets the musicplayer state
     * @param state
     */
    public void setState(State state) { this.state = state; }

    /**
     * calls the load library method based on the current state of the music player
     */
    public void loadLibrary(){this.state.loadLibrary();}

    /**
     * calls the loadNewTrack method based on the current state of the music player
     */
    public void loadNewTrack(){this.state.loadNewTrack();}

    /**
     * calls the playSong method based on the current state of the music player
     */
    public void playSong(){this.state.playSong();}

    /**
     * calls the browser song method based on the current state of the music player
     */
    public void browseSong(){this.state.browseSong();}

    /**
     * calls the switchToLibrary method based on the current state of the music player
     */
    public void switchToLibrary(){this.state.switchToLibrary();}

    /**
     * calls the switchToOtherPlaylist method based on the current state of the music player
     */
    public void switchToPlaylist(){this.state.switchToPlaylist();}
    public void createPlaylist(){this.state.createPlaylist();}
}
