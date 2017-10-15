public class PlayingLibrary implements State{

    private MusicPlayer musicplayer;

    ViewComponentInput input;
    ViewComponentOutput output;
    ViewComponents comp;
    SongActions songAct;

    /**
     * takes in the musicplayer class and uses it to define
     * a few variables to use in the other methods.
     * @param newMusicplayer
     */
    public PlayingLibrary(MusicPlayer newMusicplayer){
        this.musicplayer = newMusicplayer;

        this.input = newMusicplayer.getViewCompInClass();
        this.output = newMusicplayer.getViewCompOutClass();
        this.comp = newMusicplayer.getViewCompClass();
        this.songAct = newMusicplayer.getSongActClass();
    }

    @Override
    public void loadLibrary() {

    }

    /**
     * load a new song in to the media player
     */
    @Override
    public void loadNewTrack() {
        this.songAct.stop();
        this.songAct.setMediaPlayer(this.output.getSelectedSong());
    }

    /**
     * play the song in the current media player
     */
    @Override
    public void playSong() {
        this.songAct.play();
    }

    /**
     * browse a song and add it to the TableView
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
     * switch to the OtherPlaylist idle state
     */
    @Override
    public void switchToOtherPlaylist() {
        this.musicplayer.setState(this.musicplayer.getIdleOtherPlaylist());
    }
}
