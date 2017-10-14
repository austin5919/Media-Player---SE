public class IdleLibrary implements State{

    private WriteXml xmlWrite = new WriteXml();
    private readXml xmlRead = new readXml();

    private MusicPlayer musicplayer;
    public IdleLibrary(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}

    @Override
    public void loadLibrary() {
        refresh();
    }

    @Override
    public void loadNewTrack() {
        this.musicplayer.getController().stop();
        this.musicplayer.getController().setMediaPlayer(this.musicplayer.getSelectedSong());
    }

    @Override
    public void playSong() {
        this.musicplayer.getController().play();
        this.musicplayer.setState(this.musicplayer.getPlayingLibrary());
    }

    @Override
    public void browseSong() {

        SongActions songAct = new SongActions(this.musicplayer.getBrowserPath());
        songAct.addNewSong(musicplayer, musicplayer.getLibrary(),"./library.xml");
        this.musicplayer.setState(this.musicplayer.getLastState());
    }

    private void refresh(){
        this.xmlRead.setTracks("./library.xml");
        this.musicplayer.setLibrary(this.xmlRead.getTracks());
        this.musicplayer.getDisplay().setItems(this.musicplayer.getLibrary());
    }
}
