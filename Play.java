public class Play implements SongState{
    
    MusicPlayer player;
    
    public Play(MusicPlayer player){
        this.player = player;
    }

    @Override
    public void playSong() {
        
        this.player.player.play();
        this.player.setState(this.player.getLaodingSongState());
        
    }

    @Override
    public void stopSong() {
        //do nothing
    }

    @Override
    public void loadSong(String song) {
        //do nothing
    }
    
}
