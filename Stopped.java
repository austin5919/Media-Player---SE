/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestmusicplayer;


public class Stopped implements UserPrivaledge{
    
    MusicPlayer player;
    
    public Stopped(MusicPlayer player){
        this.player = player;
    }

    @Override
    public void getPlaylist() {
        player.setSongDetails();
    }

    @Override
    public void playSongDoubleClick() {
        this.player.showPauseButton();
        this.player.setPreviousSong();
        this.player.playMedia();
        this.player.setState(this.player.getPlayState());
    }

    @Override
    public void playSongSingleClick() {
        this.player.showPauseButton();
        this.player.setPreviousSong();
        this.player.playMedia();
        this.player.setState(this.player.getPlayState());
    }

    @Override
    public void changeDetected() {
    }
}