/**
 * this class will hold the public methods that
 * each Media State should be able to perform. It 
 * serves as a link between the MusicPlayer.java
 * and the other classes/
 * 
 * @author austin
 * @author josea
 */
public interface UserPrivaledge {
    public void getPlaylist();
    public void playSongDoubleClick();
    public void playSongSingleClick();
    public void changeDetected();
}
