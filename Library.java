import javafx.collections.ObservableList;

/**
 * this class handles all the functionalities of the library
 */
public class Library {

    //library songs
    private ObservableList<Song> listOfSongs;
    private String libraryPath = "./library.xml";

    /**
     * @return returns an observablie list filled with songs
     */
    public ObservableList<Song> getListOfSongs() {
        return listOfSongs;
    }

    /**
     * reloads the library and re sets the observable list
     */
    public void refreshLibrary(){

        readXml read = new readXml();
        read.setListOfSongs(this.libraryPath);

        this.listOfSongs = read.getListOfSongs();
    }

    /**
     * reads the xml file and adds new song
     *
     * @param song takes in a song object and uses it to add new song
     */
    public void addsongtoLibrary(Song song){

        try {

            new WriteXml().AppendChildToXml(libraryPath,song);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
