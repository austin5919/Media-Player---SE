import javafx.application.Platform;
import javafx.collections.ObservableList;

/**
 * this class updates the GUI compoents
 */
public class UpdateViewComponents {
    private readXml xmlRead = new readXml();

    ViewComponentInput input;
    ViewComponentOutput output;
    ViewComponents comp;

    //MusicPlayer musicplayer;

    /**
     * takes in a a musicplayer and a few variables to make
     * methods easier
     * @param newMusicplayer
     */
    public UpdateViewComponents(MusicPlayer newMusicplayer){

        this.input = newMusicplayer.getViewCompInClass();
        this.output = newMusicplayer.getViewCompOutClass();
        this.comp = newMusicplayer.getViewCompClass();

    }

    /**
     * update the TableView with a new list of songs
     */
    public void updateSongListToTableView(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try{

                    input.getPlayList().removeAll();

                }catch(Exception e){}

                xmlRead.setTracks("./" + comp.getPlayListName().getSelectionModel().getSelectedItem() + ".xml");
                input.setPlayList(xmlRead.getTracks());
                comp.getDisplay().setItems(input.getPlayList());

                comp.getDisplay().getFocusModel().focus(output.getSelectedIndex());

            }
        });
    }

    /**
     * updates the tableview by adding a single song
     * @param songAct
     * @param list
     * @param path
     */
    public void addIndividualSongsToTableView(SongActions songAct, ObservableList list, String path){

        songAct.getMediaPlayer().setOnReady(() -> {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    songAct.createSongObject(output.getBrowserSongName(),output.getBrowserPath());

                    if(new Exist().CheckList(output.getBrowserSongName(),list)){

                        try {
                            comp.getDisplay().getItems().add(songAct.getSong());
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
