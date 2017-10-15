/**
 * this defines my actions when the musicplayer is on idle library state
 */
public class IdleLibrary implements State{

    private WriteXml xmlWrite = new WriteXml();
    private StateChanges musicplayer;
    String path = "./library.xml";

    ViewComponents comp;
    MusicPlayer songAct;
    //Library lib;

    /**
     * take in a musicplayer and set some variables to make
     * life easier
     * @param newMusicplayer
     */
    public IdleLibrary(StateChanges newMusicplayer){

        this.musicplayer = newMusicplayer;
        this.comp = newMusicplayer.getViewCompClass();
        this.songAct = newMusicplayer.getSongActClass();
    }

    /**
     * load he library
     */
    @Override
    public void loadLibrary() {
        //update tableView
        new UpdateComponents(musicplayer).refreshTableView(path);
    }

    /**
     * load a new song
     */
    @Override
    public void loadNewTrack() {
        this.songAct.stop();
        this.songAct.setMediaPlayer(comp.getSelectedSong());
    }

    /**
     * play a song
     */
    @Override
    public void playSong() {
        songAct.play();
        this.musicplayer.setState(this.musicplayer.getPlayingLibrary());
    }

    /**
     * browse file
     */
    @Override
    public void browseSong() {
        new UpdateComponents(musicplayer).addSingleSong(new MusicPlayer(this.comp.getBrowserPath()), path);
    }

    @Override
    public void switchToLibrary() {

    }

    /**
     * change state to idleOtherPlaylist
     */
    @Override
    public void switchToOtherPlaylist() {
        this.musicplayer.setState(this.musicplayer.getIdleOtherPlaylist());
    }
}
