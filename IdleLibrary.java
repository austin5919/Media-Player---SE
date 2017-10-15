/**
 * this defines my actions when the musicplayer is on idle library state
 */
public class IdleLibrary implements State{

    private WriteXml xmlWrite = new WriteXml();
    private MusicPlayer musicplayer;

    ViewComponentInput input;
    ViewComponentOutput output;
    ViewComponents comp;
    SongActions songAct;

    /**
     * take in a musicplayer and set some variables to make
     * life easier
     * @param newMusicplayer
     */
    public IdleLibrary(MusicPlayer newMusicplayer){

        this.musicplayer = newMusicplayer;

        this.input = newMusicplayer.getViewCompInClass();
        this.output = newMusicplayer.getViewCompOutClass();
        this.comp = newMusicplayer.getViewCompClass();
        this.songAct = newMusicplayer.getSongActClass();
    }

    /**
     * load he library
     */
    @Override
    public void loadLibrary() {

        new UpdateViewComponents(musicplayer).updateSongListToTableView();
    }

    /**
     * load a new song
     */
    @Override
    public void loadNewTrack() {
        this.songAct.stop();
        this.songAct.setMediaPlayer(output.getSelectedSong());
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
        new UpdateViewComponents(musicplayer).addIndividualSongsToTableView(new SongActions(output.getBrowserPath()),
                input.getPlayList(),"./library.xml");
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
