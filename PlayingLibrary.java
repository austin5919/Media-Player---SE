public class PlayingLibrary implements State{

    private StateChanges musicplayer;
    String path = "./library.xml";

    ViewComponents comp;
    MusicPlayer songAct;

    /**
     * takes in the musicplayer class and uses it to define
     * a few variables to use in the other methods.
     * @param newMusicplayer
     */
    public PlayingLibrary(StateChanges newMusicplayer){
        this.musicplayer = newMusicplayer;
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
        this.songAct.setMediaPlayer(this.comp.getSelectedSong());
    }

    /**
     * play the song in the current media player
     */
    @Override
    public void playSong() { this.songAct.play(); }

    /**
     * browse a song and add it to the TableView
     */
    @Override
    public void browseSong() {
        new UpdateComponents(musicplayer).addSingleSong(new MusicPlayer(comp.getBrowserPath()),path);
    }

    @Override
    public void createPlaylist() {

        System.out.println("code to create playlist is under construction..!!");
        System.out.println("the file chooser can still browse files but nothings happens...!!");
        System.out.println("to test the file chooser functionality please go back to the Library...!!");
        this.comp.getPlayListName().getSelectionModel().select("Library");

    }

    @Override
    public void switchToLibrary() {

    }

    /**
     * switch to the OtherPlaylist idle state
     */
    @Override
    public void switchToPlaylist() {
        this.musicplayer.setState(this.musicplayer.getIdlePlaylist());
    }
}
