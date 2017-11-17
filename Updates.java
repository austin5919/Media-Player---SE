import javafx.application.Platform;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;

/**
 * This class will update components when called.
 */
public class Updates {

    private String mode;
    public Updates(String mode){
        this.mode = mode;
    }

    public Updates(){
        this.mode = "default";
    }

    /**
     * This method updates the music list and display. They are done together because the
     * set on ready may not execute right away and we need the library up to date when interacting
     * with the display
     *
     * @param tableView     takes the main display so that we can update it as we add to library
     * @param selectedIndex takes in the selected index to set the focus on the proper cell
     * @param musicList     takes in the music list so we can add the songs to it. Our main library
     * @param listOfSongs   takes in the list of songs we want to add
     */
    public void updateMusicList(
            TableView<Song> tableView, int selectedIndex,
            MusicList musicList, ArrayList<String> listOfSongs
    ) {
        //read the content of the array list one by one
        for (String readPath : listOfSongs) {
            //create an instance of the media player to
            //calculate duration
            Player player = new Player();
            player.setMediaPlayer(readPath);
            player.getMediaPlayer().setOnReady(new Runnable() {
                @Override
                public void run() {
                    //check if the file still exist
                    if (new File(readPath).exists()) {
                        //create a song name variable from the path read
                        String songName = new File(readPath).getName().replace(".mp3", "");

                        //get the duration and put it in a variable
                        final String duration = player.getDuration();

                        //add the song name, duration and path itself to music list
                        musicList.addSong(songName, duration, readPath);
                        //System.out.println(musicList.getSongByPath(readPath));

                        //create a song object and pass it in to the update
                        //table view method along with the selected index and
                        //the display table view
                        Song song = new Song(songName, duration, readPath);
                        if(mode.equals("default")){
                            updateTableView(tableView, selectedIndex, song);
                        }else{
                            //nothing
                        }
                    } else {
                        //you should never see this
                        System.out.println("could not find file : " + readPath);
                    }

                    //dispose of the media player once done using it.
                    player.getMediaPlayer().dispose();
                }
            });
        }
    }

    //update display
    private void updateTableView(TableView<Song> tableView, int selectedIndex, Song song) {
        //add song to the display and update the focus index
        tableView.getItems().add(song);
        tableView.getFocusModel().focus(selectedIndex);
    }

    public void updateTableViewAll(TableView<Song> tableView, String selectedSong, ArrayList<Song> list) {
        tableView.getItems().clear();
        if(list.isEmpty()){
            return;
        }

        tableView.getItems().addAll(list);

        int selectedIndex = 0;
        for(Song song : tableView.getItems()){
            if(song.getPath().equals(selectedSong)){
                break;
            }
            selectedIndex = selectedIndex + 1;
        }
        tableView.getFocusModel().focus(selectedIndex);
    }


    /**
     * This method properly adds objects to the choices of a combo box.
     *
     * @param comboBox  takes in the combo box we want to update
     * @param arrayList takes in the list of string we want to add to the combo box
     */
    public void updateComboBox(ComboBox comboBox, ArrayList<String> arrayList) {
        //read content in array list one by one
        for (String readContent : arrayList) {
            //check if the string already exist in the combo box
            //options.
            if (comboBox.getItems().contains(readContent)) {
                System.out.println("this playlist already exist");
            } else {
                //add to combo box
                comboBox.getItems().add(readContent);
            }
        }
    }
}