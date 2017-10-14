import javafx.collections.ObservableList;

import java.io.File;

public class Exist {

    public boolean CheckFile(String path){
        File xmlFile = new File(path);
        return xmlFile.exists();
    }

    public boolean CheckList(String target, ObservableList<Song> library){

        if(library.isEmpty()){
            return  true;
        }

        for(int i = 0; i < library.size();i++){

            if(library.get(i).getSongName().equals(target)){
                return false;
            }
        }

        return true;
    }
}
