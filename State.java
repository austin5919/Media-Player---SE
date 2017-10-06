/**
 * the actions that could be performed with
 * every state
 * @author josea
 */
public interface State {
    public void createSongObject(String songName,String songLink);
    public void refreshPlaylist();
    public void playSong();
}
