/**
 * this class handles the music player when it is not in library and
 * playing a song
 */
public class IdleOtherPlaylist implements State {

    private MusicPlayer musicplayer;

    /**
     * takes in a musicplayer class
     * @param newMusicplayer
     */
    public IdleOtherPlaylist(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}

    /**
     * loads the the display with new songs
     */
    @Override
    public void loadLibrary() {
        new UpdateViewComponents(musicplayer).updateSongListToTableView();
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

    /**
     * changes the state to the idleLibrary state
     */
    @Override
    public void switchToLibrary() {
        this.musicplayer.setState(this.musicplayer.getIdleLibrary());
    }

    @Override
    public void switchToOtherPlaylist() {

    }
}
