public class IdleOtherPlaylist implements State {

    private MusicPlayer musicplayer;
    public IdleOtherPlaylist(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}

    @Override
    public void loadLibrary() {

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
    public void updatePlayListSection() {
        this.musicplayer.setState(this.musicplayer.getIdleLibrary());
    }
}
