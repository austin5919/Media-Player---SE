import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * This class writes xml files using given information.
 */
public class Write {

    /**
     * Append new child to an existing xml or create an xml with new song node.
     *
     * @param dataPath    Takes in the path of an xml file and adds createContent to it.
     * @param contentPath Takes in a song object and uses it to build the nodes.
     * @throws Exception Throws an exception if anything goes wrong.
     */
    public void storeData(String dataPath, String contentPath) {
        try {
            ArrayList<String> list;
            //If the file does not exist yet, create it; otherwise read it
            if (!(new File(dataPath).exists())) {
                list = new ArrayList<String>();
            } else {
                list = (ArrayList<String>) Serialization.read(dataPath);
            }

            //if (!(new File(path).exists()) == null) { list = new ArrayList<Song>(); } // Create new file if one does not exist
            for (String readPath : list) {
                if (readPath == contentPath) {
                    System.out.println("Duplicate song attempted! Not allowed.");
                    return;
                }
            }

            list.add(contentPath);
            Serialization.write(list, dataPath);
        } catch (Exception e) {
            System.out.println("failed to storeData");
        }
    }
}
