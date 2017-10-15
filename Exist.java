import javafx.collections.ObservableList;

import java.io.File;

/**
 * this class validates variables/paths
 */
public class Exist {

    /**
     * checks if a given path exist
     * @param path
     * @return
     */
    public boolean CheckFile(String path){
        File xmlFile = new File(path);
        return xmlFile.exists();
    }

    /**
     * checks if a value exist within a given list
     * @param target
     * @param library
     * @return
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
}
