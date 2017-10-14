public class IdleLibrary implements State{

    private WriteXml xmlWrite = new WriteXml();


    private MusicPlayer musicplayer;
    public IdleLibrary(MusicPlayer newMusicplayer){this.musicplayer = newMusicplayer;}

    @Override
    public void loadLibrary() {

        try{

            musicplayer.getLibrary().removeAll();

        }catch(Exception e){

            System.out.println("Library is empty there is no need to clear it");

        }

        new UpdateViewComponents().updateSongListToTableView(musicplayer);
    }

    @Override
    public void loadNewTrack() {
        this.musicplayer.getController().stop();
        this.musicplayer.getController().setMediaPlayer(this.musicplayer.getSelectedSong());
    }

    @Override
    public void playSong() {
        this.musicplayer.getController().play();
        this.musicplayer.setState(this.musicplayer.getPlayingLibrary());
    }

    @Override
    public void browseSong() {

        SongActions songAct = new SongActions(this.musicplayer.getBrowserPath());
        new UpdateViewComponents().addIndividualSongsToTableView(musicplayer, songAct, musicplayer.getLibrary(),"./library.xml");
        this.musicplayer.setState(this.musicplayer.getLastState());
    }

    @Override
    public void updatePlayListSection() {
        this.musicplayer.setState(this.musicplayer.getIdleOtherPlaylist());
    }
}
