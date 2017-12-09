import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class equals my library mode and will execute methods
 * based on that mode.
 */
public class LibraryMode implements MP3Player {

    //local variables
    private ManageMP3PlayerState manageMp3PlayerState;
    private Player player;
    private String libraryPath;
    private Label songName;
    private Label timer;
    private String current;
    private ComboBox<String> comboBox;
    private TableView<Song> tableView;

    /**
     * @param manageMp3PlayerState the ManageMP3Player class
     * @param player               the Player class
     */
    public LibraryMode(ManageMP3PlayerState manageMp3PlayerState, Player player) {
        this.manageMp3PlayerState = manageMp3PlayerState;
        this.player = player;
        this.libraryPath = "./library.data";
    }

    /**
     * This method takes the path of a song and feeds it to the
     * Player class to create a MediaPlayer i can use to play
     * the song.
     *
     * @param path the path to the song i want to play.
     */
    @Override
    public void loadNewTrack(String path, Label songName, Label timer, TableView<Song> tableView) {
        player.Dispose();
        this.songName = songName;
        this.timer = timer;
        this.tableView = tableView;
        TableView<Song> oneSong = new TableView<>();

        for (Song song : tableView.getItems()) {
            if (path.equals(song.getPath())) {
                oneSong.getItems().add(song);
                break;
            }
        }
        int selectedIndex = tableView.getSelectionModel().getFocusedIndex();
        player.timer(timer, oneSong, path, selectedIndex);

        singleplay(path);
    }

    private void singleplay(String path) {
        player.setMediaPlayer(path);
        player.getMediaPlayer().setOnReady(new Runnable() {
            @Override
            public void run() {
                player.getMediaPlayer().play();
                String name = new File(path).getName();
                songName.setText(name.replace(".mp3", ""));
            }
        });
    }

    /**
     * play the song loaded in to the media player
     */
    @Override
    public void focusValue(String current, ComboBox<String> comboBox) {
        this.current = current;
        this.comboBox = comboBox;
    }

    /**
     * this methods adds songs to the library.data and updates the display
     * and music list they must be updated at the same time because the
     * set on ready might not trigger right away and we need the music list
     * up to date before updating the display
     *
     * @param tableView     the table view i want to update
     * @param selectedIndex the selected index to preserve the focus
     * @param newSongs      the new song i want to add
     */
    @Override
    public void addSongToLibrary(TableView<Song> tableView, int selectedIndex, ArrayList<String> newSongs) {
        //add to library
        for (String readPath : newSongs) {
            new Write().storeData(libraryPath, readPath);
        }

        //update display and music list
        new Updates().updateMusicList(tableView, selectedIndex, manageMp3PlayerState.getMusicList(), newSongs);
    }

    /**
     * This method takes in a song and adds it to a playlist.
     *
     * @param song     the song object i want to add to playlist
     * @param dataPath the path of the playlist i want add songs to
     */
    @Override
    public void addSongToPlaylist(Song song, String dataPath, ArrayList<ArrayList<String>> collection) {

        //store the song link in the appropriate path
        new Write().storeData(dataPath, song.getPath());

        boolean build = true;
        for(ArrayList<String> str : collection){
            if(str.get(0).equals(dataPath)){
                str.add(song.getPath());
                build = false;
            }
        }

        if(build == true){
            ArrayList<String> temp = new ArrayList<>();
            temp.add(dataPath);
            temp.add(song.getPath());
            collection.add(temp);
        }


    }

    @Override
    public void removeSong(String dataPath, Song song,TableView<Song> tableView, int selectedIndex) {
        dataPath = libraryPath;
        Read read = new Read();
        read.setListOfPath(dataPath);
        ArrayList<Song> arrayList = new ArrayList<>();

        for(Song data : tableView.getItems()){
            if(!data.equals(song)){
                //System.out.println(data.getName());
                arrayList.add(data);
            }
        }
        new File(dataPath).delete();
        //update display and music list
        new Updates().remoceLib(tableView, selectedIndex, manageMp3PlayerState.getMusicList(), arrayList);


    }

    @Override
    public void removePlaylist(TableView<Song> song, String dataPath, ComboBox comboBox, ArrayList<String> shuffle, int selectedIndex) {
        //remove path
        new File("./" + dataPath + ".data").delete();
        comboBox.getItems().remove(dataPath);
    }

    @Override
    public void addPlaylistToPlaylist(String playlist, String dataPath, ComboBox comboBox, ArrayList<ArrayList<String>> collection) {

        Read read = new Read();
        read.setListOfPath(playlist);

        new Write().storeData(dataPath, playlist);

        boolean build = true;
        for(ArrayList<String> str : collection){
            if(str.get(0).equals(dataPath)){
                for(String r : read.getListOfPath()){
                    str.add(r);
                }
                build = false;
            }
        }

        if(build == true){
            ArrayList<String> temp = new ArrayList<>();
            temp.add(dataPath);
            for(String r : read.getListOfPath()){
                temp.add(r);
            }
            collection.add(temp);
        }
    }

    @Override
    public void stopPlayer() {
        if (player.getMediaPlayer() != null) {
            player.getMediaPlayer().stop();
        }
    }
}
