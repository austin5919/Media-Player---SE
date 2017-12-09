import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        int index = tableView.getFocusModel().getFocusedIndex();
        player.timer(timer,tableView, path,index);
        autoPlay(path, index);
    }

    private void autoPlay(String path, int focusedIndex) {
        int newIndex = focusedIndex;

        player.setMediaPlayer(path);
        player.getMediaPlayer().setOnReady(() -> {
            player.getMediaPlayer().stop();
            player.getMediaPlayer().play();

            if(new File("./focus.data").exists()){
                new File("./focus.data").delete();
            }
            new Write().storeData("./focus.data",String.valueOf(newIndex));
            new Write().storeData("./focus.data", path);

            if(current.equals(comboBox.getSelectionModel().getSelectedItem().toString())){
                //changes(path);
                tableView.getFocusModel().focus(newIndex);
            }else if(comboBox.getSelectionModel().getSelectedItem().toString().equals("Library")){
                //changes(path);
                tableView.getFocusModel().focus(newIndex);
            }

            String name = new File(path).getName();
            songName.setText(name.replace(".mp3", ""));

            player.getMediaPlayer().setOnEndOfMedia(() -> {


                if (!getNext(path).equals("Null")) {
                    autoPlay(getNext(path), newIndex + 1);
                } else {
                    return;
                }
            });

        });
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
        //System.out.println(dataPath);
        //System.out.println(song.getName());

        Read read = new Read();
        read.setListOfPath(dataPath);
        ArrayList<Song> arrayList = new ArrayList<>();

        for(Song data : tableView.getItems()){
            if(!data.equals(song)){
                arrayList.add(data);
            }
        }
        new File(dataPath).delete();
        //update display and music list
        new Updates().removePlay(tableView, selectedIndex, manageMp3PlayerState.getMusicList(), arrayList,dataPath);

    }

    @Override
    public void removePlaylist(TableView<Song> song, String dataPath, ComboBox comboBox, ArrayList<String> shuffle, int selectedIndex) {
        //System.out.println(dataPath);
        //System.out.println();

        String playlist = "./" + comboBox.getSelectionModel().getSelectedItem().toString() + ".data";
        Read read = new Read();
        read.setListOfPath(playlist);

        new File(playlist).delete();
        ArrayList<String> arrayList = new ArrayList<>();

        for(String str : read.getListOfPath()){
            if(!str.equals("./"+dataPath+".data")){
                //System.out.println(str);
                arrayList.add(str);
            }
        }
        song.getItems().clear();

        for(String str : read.getListOfPath()){
            if(!str.equals("./"+dataPath+".data")){
                //System.out.println(str);
                new Write().storeData(playlist,str);
                arrayList.add(str);
            }
        }



    }

    @Override
    public void addPlaylistToPlaylist(String playlist, String dataPath, ComboBox comboBox,ArrayList<ArrayList<String>> collection) {

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
        if(player.getMediaPlayer() != null){
            player.getMediaPlayer().stop();
        }
    }
}
