public class IdleLibrary implements State{

    private WriteXml xmlWrite = new WriteXml();


    private MusicPlayer musicplayer;
    public IdleLibrary(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}

    @Override
    public void loadLibrary() {

        new UpdateViewComponents().updateSongListToTableView(musicplayer);
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
        new UpdateViewComponents().addIndividualSongsToTableView(musicplayer,
                new SongActions(this.musicplayer.getBrowserPath()), musicplayer.getPlayList(),"./library.xml");
    }

    @Override
    public void switchToLibrary() {

    }

    @Override
    public void switchToOtherPlaylist() {
        this.musicplayer.setState(this.musicplayer.getIdleOtherPlaylist());
    }
}
