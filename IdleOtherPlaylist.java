public class IdleOtherPlaylist implements State {

    private MusicPlayer musicplayer;
    public IdleOtherPlaylist(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}

    @Override
    public void loadLibrary() {
        new UpdateViewComponents().updateSongListToTableView(musicplayer);
    }

    @Override
    public void loadNewTrack() {

    }

    @Override
    public void playSong() {

    }

    @Override
    public void browseSong() {

    }

    @Override
    public void switchToLibrary() {
        this.musicplayer.getController().stop();
        this.musicplayer.getDisplay().getSelectionModel().select(-1);
        this.musicplayer.setState(this.musicplayer.getIdleLibrary());
    }

    @Override
    public void switchToOtherPlaylist() {

    }
}
