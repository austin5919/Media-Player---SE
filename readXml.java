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

    readXml(){ this.library = FXCollections.observableArrayList(); }

    public ObservableList<Song> getTracks() { return library; }

    public void setTracks(String path){

        try {

            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("song");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                Node nNode = nodeList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String songName = eElement.getElementsByTagName("songName").item(0).getTextContent();
                    String songDuration = eElement.getElementsByTagName("songDuration").item(0).getTextContent();
                    String songPath  = eElement.getElementsByTagName("songPath").item(0).getTextContent();

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

        } catch (Exception e) {
            System.out.println("could not find the library or playlist file");
        }
    }
}
