import java.util.ArrayList;

/**
 * this class reads the information in a serialized path
 */
public class Read {

    private ArrayList<String> listOfPath;

    /**
     * a constructor to set local variables
     */
    public Read() {
        this.listOfPath = new ArrayList<>();
    }

    //getter
    public ArrayList<String> getListOfPath() {
        return listOfPath;
    }

    /**
     * read the .data file and place the results in a variable
     *
     * @param path the path where the .data can be found
     */
    public void setListOfPath(String path) {
        ArrayList<String> list = (ArrayList<String>) Serialization.read(path);
        if (list == null) {
            list = new ArrayList<String>();
        } // Create new file if one does not exist
        this.listOfPath = list;
    }
}
