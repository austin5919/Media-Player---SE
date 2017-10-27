import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

/**
 * This class will update the GUI components.
 */
public class Updates {
    private ArrayList<String> listOfSongs;
    private Components components;

    public Updates(){}

    public Updates(ArrayList<String> listOfSongs,Components components){
        this.listOfSongs = listOfSongs;
        this.components = components;
    }

    /**
     * This method updates the main display TableView.
     */
    public void refreshDisplay(){

        ObservableList<Song> list = FXCollections.observableArrayList();

        for (String readPath : this.listOfSongs) {

            Player player = new Player(readPath);
            player.getMediaPlayer().setOnReady(() -> {

                if(new File(readPath).exists()) {
                    String songName = new File(readPath).getName().replace(".mp3", "");
                    String duration =  player.getDuration();
                    list.add(new Song(songName, duration, readPath));
                }else{
                    System.out.println("could not find file : " + readPath);
                }

                if(readPath == this.listOfSongs.get(this.listOfSongs.size() - 1)) {
                    
                    Platform.runLater(() -> {
                        components.getDisplay().getItems().removeAll();
                        components.getDisplay().setItems(list);
                        components.getDisplay().getFocusModel().focus(components.getSelectedIndex());
                    });
                    
                }

            });
        }
    }

    /**
     * updates display one song at a time
     */
    public void addSongsOneByOne(){

        for (String readPath : this.listOfSongs) {

            Player player = new Player(readPath);
            player.getMediaPlayer().setOnReady(() -> {

                String songName = new File(readPath).getName().replace(".mp3", "");
                String duration =  player.getDuration();

                Platform.runLater(() -> {
                    components.getDisplay().getItems().add(new Song(songName,duration,readPath));
                    components.getDisplay().getFocusModel().focus(components.getSelectedIndex());
                });

            });
        }
    }
}
