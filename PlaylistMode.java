import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

/**
 * This class handles the music player when its not
 * playing from the library. We have not started coding this
 * class yet.
 */
public class PlaylistMode implements MP3Player {
    private ManageMP3PlayerState manageMp3PlayerState;
    private Player player;
    private String libraryPath;
    private Label songName;
    private Label timer;
    private String current;
    private ComboBox<String> comboBox;
    private TableView<Song> tableView;


    /**
     * @param manageMp3PlayerState Takes in the ManageMP3PlayerState class.
     */
    public PlaylistMode(ManageMP3PlayerState manageMp3PlayerState, Player player) {
        this.manageMp3PlayerState = manageMp3PlayerState;
        this.player = player;
        this.libraryPath = "./library.data";
    }

    @Override
    public void loadNewTrack(String path, Label songName, Label timer,
                             TableView<Song> tableView) {
        player.Dispose();
        this.songName = songName;
        this.timer = timer;
        this.tableView = tableView;
        player.timer(timer,tableView, path);

        autoPlay(path);
    }

    private void autoPlay(String path) {
        player.setMediaPlayer(path);
        player.getMediaPlayer().setOnReady(() -> {
            player.getMediaPlayer().stop();
            player.getMediaPlayer().play();

            if(new File("./focus.data").exists()){
                new File("./focus.data").delete();
            }
            new Write().storeData("./focus.data",path);

            if(current.equals(comboBox.getSelectionModel().getSelectedItem().toString())){
                changes(path);
            }else if(comboBox.getSelectionModel().getSelectedItem().toString().equals("Library")){
                changes(path);
            }

            String name = new File(path).getName();
            songName.setText(name.replace(".mp3", ""));

            player.getMediaPlayer().setOnEndOfMedia(() -> {


                if (!getNext(path).equals("Null")) {
                    autoPlay(getNext(path));
                } else {
                    return;
                }
            });

        });
    }

    private void changes(String path){

        int selectedIndex = 0;
        for (Song song : tableView.getItems()) {
            if (song.getPath().equals(path)) {
                break;
            }
            selectedIndex = selectedIndex + 1;
        }

        tableView.getFocusModel().focus(selectedIndex);
    }

    private String getNext(String target) {

        Read reader = new Read();
        reader.setListOfPath("./tList.data");
        ArrayList<String> songs = reader.getListOfPath();

        if (target.equals(songs.get(songs.size() - 1))) {
            return "Null";
        }

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).equals(target)) {
                return songs.get(i + 1);
            }
        }

        System.out.println("you should never see this..");

        return "Null";
    }

    @Override
    public void focusValue(String current, ComboBox<String> comboBox) {
        this.current = current;
        this.comboBox = comboBox;
    }

    @Override
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        //add to library
        for (String readPath : newSongs) {
            new Write().storeData(libraryPath, readPath);
        }
        //update display and music list
        new Updates("BYPASS").updateMusicList(tableView, selectedIndex, manageMp3PlayerState.getMusicList(), newSongs);
    }

    @Override
    public void addSongToPlaylist(Song song, String dataPath) {
        //store the song link in the appropriate path
        new Write().storeData(dataPath, song.getPath());
    }
}
