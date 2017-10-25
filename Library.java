import javafx.collections.ObservableList;

/**
 * This class handles all the functionalities of the library
 */
public class Library {

    //library songs
    private ObservableList<Song> listOfSongs;
    private String libraryPath = "./library.data";

    /**
	 * Gets the list of songs.
	 *
     * @return  An ObservableList filled with songs.
     */
    public ObservableList<Song> getListOfSongs() {
        return listOfSongs;
    }

    /**
     * Reloads the library and re sets the observable list.
     */
    public void refreshLibrary(){

        readXml read = new readXml();
        read.setListOfSongs(this.libraryPath);

        this.listOfSongs = read.getListOfSongs();
    }

    /**
     * Reads the xml file and adds new song.
     *
     * @param song  Takes in a song object and uses it to add new song.
     */
    public void addsongtoLibrary(Song song){

        try {

            new WriteXml().AppendChildToXml(libraryPath,song);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
