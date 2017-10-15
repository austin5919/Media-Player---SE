import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class EventHandler {

    private MusicPlayer player = new MusicPlayer();

    //private TableView<Song> display;

    public void setTopComponents(ComboBox playList, Button browse) {
        this.player.setPlayListName(playList);

        playList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            if (newSelection != null) {

                if(this.player.getPlayListName().getSelectionModel().getSelectedItem() == "Library"){

                    this.player.switchToLibrary();

                }else if(this.player.getPlayListName().getSelectionModel().getSelectedItem() == "Create Playlist"){

                    //TODO:Create playlist


                    this.player.switchToOtherPlaylist();

                    System.out.println("code to create playlist is under construction..!!");
                    System.out.println("the file chooser can still browse files but nothings happens...!!");
                    System.out.println("to test the file chooser functionality please go back to the Library...!!");

                }else{

                    this.player.switchToOtherPlaylist();
                }

                this.player.loadLibrary();
            }
        });


       browse.setOnAction(this::browserButtonAction);

    }

    private void browserButtonAction(ActionEvent e){

        FileChooser getFile = new FileChooser();
        File theFile = getFile.showOpenDialog(null);

        if (theFile != null){

            String songName = theFile.getName().replace(".mp3", "");
            this.player.setBrowserSongName(songName);

            String songPath = theFile.getAbsolutePath();
            this.player.setBrowserPath(songPath);
            this.player.browseSong();

        }

    }

    /**
     * this method will set my TableView to be able to view
     * songs
     * @param display
     */
    public void setCenterComponents(TableView<Song> display) {

        this.player.setDisplay(display);
        this.player.loadLibrary();
        display.setOnMouseClicked(this::handleDisplayTableEvents);

    }

    //this method simply handles the actions of the tableView
    private void handleDisplayTableEvents(MouseEvent e){

        if(this.player.getDisplay().getSelectionModel().getSelectedItem() != null){

            this.player.setBackgroundPlayer(this.player.getDisplay());
            this.player.setSelectedIndex(this.player.getDisplay().getSelectionModel().getSelectedIndex());
            this.player.setSelectedSong(this.player.getBackgroundPlayer().getItems().get(this.player.getSelectedIndex()).getSongPath());
            this.player.getDisplay().getSelectionModel().clearSelection();
            this.player.getDisplay().getFocusModel().focus(this.player.getSelectedIndex());
            this.player.loadNewTrack();
            this.player.playSong();

            //System.out.println(this.player.getDisplay().getItems().get(this.player.getSelectedIndex()).getSongName());

        }
    }
}
