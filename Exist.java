import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

/**
 * this class validates variables/paths
 */
public class Exist {

    /**
     * checks if a given path exist
     *
     * @param path takes in a path to check
     * @return returns a boolean
     */
    public boolean CheckFile(String path){

        File xmlFile = new File(path);
        return xmlFile.exists();
    }

    /**
     * checks if a value exist within a given list
     *
     * @param target takes in a target value
     * @param library takes in a observable list to check
     * @return retuns true if empty and false if not
     */
    public boolean CheckList(String target, ObservableList<Song> library){

        //check if its empty and return if so
        if(library.isEmpty()){
            return  true;
        }

        //look through the library to check if the target is found
        for(int i = 0; i < library.size();i++){

            if(library.get(i).getSongName().equals(target)){
                return false;
            }
        }

        return true;
    }
    /*
    public boolean CheckArray(String target, ArrayList<String> listOfPlaylist){

        //check if its empty and return if so
        if(listOfPlaylist.isEmpty()){
            return  true;
        }

        //look through the library to check if the target is found
        for(int i = 0; i < listOfPlaylist.size();i++){

            if(listOfPlaylist.get(i).toString().equals(target)){
                return false;
            }
        }

        return true;
    }
    */
}
