public class PlayingLibrary implements State{
    private MusicPlayer musicplayer;
    public PlayingLibrary(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}

    @Override
    public void loadLibrary() {

    }

    @Override
    public void loadNewTrack() {
        this.musicplayer.getController().stop();
        this.musicplayer.getController().setMediaPlayer(this.musicplayer.getSelectedSong());
    }

    @Override
    public void playSong() {
        this.musicplayer.getController().play();
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
