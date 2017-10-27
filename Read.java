import javafx.collections.ObservableList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.FXCollections;

/**
 * This clas reads an xml file using given
 * information.
 */
public class Read {
    private ArrayList<String> listOfPath;

    /**
     * Set the observable list to hold my values.
     */
    Read() {

        this.listOfPath = new ArrayList<>();
    }

    /**
     * gets a list of paths
     *
     * @return returns a collection of paths
     */
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
