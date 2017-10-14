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
        this.musicplayer.setLastState(this.musicplayer.getPlayingLibrary());
        this.musicplayer.setState(this.musicplayer.getIdleLibrary());
        this.musicplayer.browseSong();
    }
}
