import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import javafx.collections.FXCollections;

/**
 * this clas reads an xml file using given
 * information
 */
public class readXml {

    ObservableList<Song> library;

    /**
     * define the observable list to hold my values
     */
    readXml(){ this.library = FXCollections.observableArrayList(); }

    /**
     * gets the observable list
     * @return
     */
    public ObservableList<Song> getTracks() { return library; }

    /**
     * we will read the files and fill the results in to the observablelist
     * throw an exception if file cant be found
     * @param path
     */
    public void setTracks(String path){

        try {

            //set the file path and the doc builder
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            //get the parent element
            NodeList nodeList = doc.getElementsByTagName("song");

            //read each parent element and return the childs
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);

                //iterate through each child node
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //create a variable for each node
                    String songName = eElement.getElementsByTagName("songName").item(0).getTextContent();
                    String songDuration = eElement.getElementsByTagName("songDuration").item(0).getTextContent();
                    String songPath  = eElement.getElementsByTagName("songPath").item(0).getTextContent();

                    //check if song already exist to avoid duplicates and check if the path exist to avoid
                    //errors
                    if(new Exist().CheckList(songName,this.library) && new Exist().CheckFile(songPath)){
                        this.library.add(new Song(songName,songDuration,songPath));
                    }else{
                        System.out.println("you have more than 1 " + songName + ".mp3 in your playlist or library " +
                                "or the path does not exist anymore. Deleting the duplicates or broken songs");

                        eElement.getParentNode().removeChild(eElement);
                        new WriteXml().write(doc,path);
                    }
                }
            }

        } catch (Exception e) { }
    }
}
