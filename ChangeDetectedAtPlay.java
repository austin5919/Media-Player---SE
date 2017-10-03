public class ChangeDetectedAtPlay implements UserPrivaledge{
    MusicPlayer player;
    
    public ChangeDetectedAtPlay(MusicPlayer player){
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
        
        if(this.player.isIdentical() != true){
            this.player.showPlayButton();
        }else{
            this.player.showPauseButton();
            this.player.setState(this.player.getPlayState());
        }
    }
}
