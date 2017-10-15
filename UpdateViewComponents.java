import javafx.application.Platform;
import javafx.collections.ObservableList;

public class UpdateViewComponents {

    private readXml xmlRead = new readXml();

    public void updateSongListToTableView(MusicPlayer musicplayer){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try{

                    musicplayer.getPlayList().removeAll();

                }catch(Exception e){}

                xmlRead.setTracks("./" + musicplayer.getPlayListName().getSelectionModel().getSelectedItem() + ".xml");
                musicplayer.setPlayList(xmlRead.getTracks());
                musicplayer.getDisplay().setItems(musicplayer.getPlayList());

                musicplayer.getDisplay().getFocusModel().focus(musicplayer.getSelectedIndex());

            }
        });
    }

    public void addIndividualSongsToTableView(MusicPlayer musicPlayer, SongActions songAct, ObservableList library, String path){

        songAct.getMediaPlayer().setOnReady(() -> {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    songAct.createSongObject(musicPlayer.getBrowserSongName(),musicPlayer.getBrowserPath());

                    if(new Exist().CheckList(musicPlayer.getBrowserSongName(),library)){

                        try {
                            musicPlayer.getDisplay().getItems().add(songAct.getSong());
                            new WriteXml().AppendChildToXml(path,songAct.getSong());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else{

                        System.out.println("this song is already in the library");
                    }
                }
            });
        });
    }

}
