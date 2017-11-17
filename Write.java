import java.io.File;
import java.util.*;

/**
 * this class serializes information passed in to
 * a given path in the form of an array list.
 */
public class Write {

    /**
     * this methods stores a string in a given path
     *
     * @param dataPath Takes in the path to hold the data
     * @param content  Takes in the string we want to store
     * @throws Exception throws an exception if anything goes wrong.
     */
    public void storeData(String dataPath, String content) {
        try {
            ArrayList<String> list;
            //If the file does not exist yet, create it; otherwise read it
            if (!(new File(dataPath).exists())) {
                list = new ArrayList<String>();
            } else {
                list = (ArrayList<String>) Serialization.read(dataPath);
            }

            for (String readPath : list) {
                if (readPath == content) {
                    System.out.println("Duplicate song attempted! Not allowed.");
                    return;
                }
            }

            list.add(content);
            Serialization.write(list, dataPath);
        } catch (Exception e) {
            System.out.println("failed to storeData");
        }
    }
}
