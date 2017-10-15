/**
 * this class handles the music player when it is not in library and
 * playing a song
 */
public class IdleOtherPlaylist implements State {

    private StateChanges musicplayer;
    String path = "./default-playlist.xml";
    /**
     * takes in a musicplayer class
     * @param newMusicplayer
     */
    public IdleOtherPlaylist(StateChanges newMusicplayer){this.musicplayer = newMusicplayer;}

    /**
     * loads the the display with new songs
     */
    @Override
    public void loadLibrary() {

        //update tableView
        new UpdateComponents(musicplayer).refreshTableView(path);
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
