public class Paused implements UserPrivaledge{
    
    MusicPlayer player;
    
    public Paused(MusicPlayer player){
        this.player = player;
    }

    @Override
    public void getPlaylist() {
        //doNothing
    }

    @Override
    public void playSongDoubleClick() {
        
        this.player.setState(this.player.getPlayState());
        this.player.playSongDoubleClick();
    }

    @Override
    public void playSongSingleClick() {
        this.player.setPreviousSong();
        this.player.showPauseButton();
        this.player.Resume();
        this.player.setState(this.player.getPlayState());
    }

    @Override
    public void changeDetected() {
       if(this.player.isIdentical() != true){
           this.player.setState(this.player.getSongChangedStateAtPause());
            
       }
    }
}
