/**
 *
 * @author josea
 */
public class ChangeDetectedAtPause implements UserPrivaledge{
    MusicPlayer player;
    
    public ChangeDetectedAtPause(MusicPlayer player){
        this.player = player;
    }
    
    @Override
    public void getPlaylist() {
        
    }

    @Override
    public void playSongDoubleClick() {
        this.player.setState(this.player.getPlayState());
        this.player.playSongDoubleClick();
        
    }

    @Override
    public void playSongSingleClick() {
       
        this.player.setState(this.player.getPlayState());
        this.player.playSongDoubleClick();
        
    }

    @Override
    public void changeDetected() {
        
       if(this.player.isIdentical() == true){
            this.player.setState(this.player.getPauseState());
       } 
    }
}
