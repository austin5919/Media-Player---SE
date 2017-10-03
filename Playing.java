package bestmusicplayer;


public class Playing implements UserPrivaledge{
    
    MusicPlayer player;
    
    public Playing(MusicPlayer player){
        this.player = player;
    }

    @Override
    public void getPlaylist() {
        //doNothing
    }

    @Override
    public void playSongDoubleClick() {
        this.player.setPreviousSong();
        this.player.showPauseButton();
        this.player.StopMedia();
        this.player.playMedia();
    }

    @Override
    public void playSongSingleClick(){
        this.player.setPreviousSong();
        this.player.showPlayButton();
        this.player.pauseMedia();
        this.player.setState(this.player.getPauseState());
    }

    @Override
    public void changeDetected() {
        
        if(this.player.isIdentical() != true){
            this.player.showPlayButton();
            this.player.setState(this.player.getSongChangedStateAtPlay());
            
        }else{
            this.player.showPauseButton();
        }
    }
}
