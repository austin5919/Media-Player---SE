import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class handles all the functionalities of the library
 */
public class Library implements Serializable{

    private String libraryPath = "./library.data";
    ArrayList<String> library;

    public ArrayList<String> getLibrary() {
        return library;
    }

    /**
     * Reloads the library and re sets the observable list.
     */
    public void refreshLibrary(){

        Read read = new Read();
        read.setListOfPath(this.libraryPath);
        this.library = read.getListOfPath();
    }

    //TODO: change this method to take in a string

    /**
     * Reads the xml file and adds new song.
     *
     * @param newSongs Takes in a song object and uses it to add new song.
     */
    public void addsongtoLibrary(ArrayList<String> newSongs){

        for(String readPath : newSongs){

            new Write().storeData(libraryPath,readPath);
        }
    }
}
