import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

/**
 * This class validates variables/paths.
 */
public class Exist {

    /**
     * Checks if a value exist within a given list.
     *
     * @param target  Takes in a target value.
     * @param library Takes in a observable list to check.
     * @return True if empty; otherwise false.
     */
    public boolean CheckList(String target, ArrayList<String> library) {

        //check if its empty and return if so
        if (library.isEmpty()) {
            return true;
        }

        //look through the library to check if the target is found
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).equals(target)) {
                return false;
            }
        }

        return true;
    }
}
