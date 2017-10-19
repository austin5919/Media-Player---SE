import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.media.MediaPlayer;

public class Library {

    //library songs
    private ObservableList<Song> listOfSongs;
    private String libraryPath = "./library.xml";
    //get library songs
    public ObservableList<Song> getListOfSongs() {
        return listOfSongs;
    }

    //reload the library
    public void refreshLibrary(){

        readXml read = new readXml();
        read.setListOfSongs(this.libraryPath);

        this.listOfSongs = read.getListOfSongs();
    }

    public void addsongtoLibrary(Song song){

        try {

            new WriteXml().AppendChildToXml(libraryPath,song);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
