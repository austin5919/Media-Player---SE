import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

/**
 * This class validates variables/paths.
 */
public class Exist {

    /**
     * Checks if a given path exist.
     *
     * @param path  A path to check if exists.
     * @return  True if path exists; otherwise false.
     */
    public boolean CheckFile(String path){

        File xmlFile = new File(path);
        return xmlFile.exists();
    }

    /**
     * Checks if a value exist within a given list.
     *
     * @param target  Takes in a target value.
     * @param library  Takes in a observable list to check.
     * @return  True if empty; otherwise false.
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
