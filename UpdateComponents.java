import javafx.application.Platform;

/**
 * this class updates the GUI compoents
 */
public class UpdateComponents {
    private readXml read = new readXml();
    ViewComponents comp;

    //StateChanges musicplayer;

    /**
     * takes in a a musicplayer and a few variables to make
     * methods easier
     * @param newMusicplayer
     */
    public UpdateComponents(StateChanges newMusicplayer){
        this.comp = newMusicplayer.getViewCompClass();

    }

    /**
     * update the TableView with a new list of songs
     */
    public void refreshTableView(String path){

        read.setListOfSongs(path);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                comp.getDisplay().setItems(read.getListOfSongs());
                comp.getDisplay().getFocusModel().focus(comp.getSelectedIndex());
            }
        });
    }

    /**
     * updates the tableview by adding a single song
     * @param songAct
     * @param path
     */
    public void addSingleSong(MusicPlayer songAct, String path){

        songAct.getMediaPlayer().setOnReady(() -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    read.setListOfSongs(path);
                    songAct.createSongObject(comp.getBrowserSongName(),comp.getBrowserPath());

                    if(new Exist().CheckList(comp.getBrowserSongName(),read.getListOfSongs())){

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
