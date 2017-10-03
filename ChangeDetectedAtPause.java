/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestmusicplayer;

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
