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
public class readXml {

    private ObservableList<Song> listOfSongs;
    //private ArrayList<String> listOfPlaylist;
    private Document doc;

    /**
     * Set the observable list to hold my values.
     */
    readXml(){
        this.listOfSongs = FXCollections.observableArrayList();
        //this.listOfPlaylist = new ArrayList<>();
    }

    /**
     * Gets the observable list.
     *
     * @return An observable list filled with songs.
     */
    public ObservableList<Song> getListOfSongs() { return listOfSongs; }

    /**
     * We will read the files and fill the results in to the observablelist
     * throw an exception if file cant be found.
     *
     * @param path  The path to read the list of songs from.
     */
    public void setListOfSongs(String path){

        try {

            setDoc(path);

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
                    if(new Exist().CheckList(songName,this.listOfSongs) && new Exist().CheckFile(songPath)){
                        this.listOfSongs.add(new Song(songName,songDuration,songPath));
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
    /*
    public void getListOfPlaylist(String path){

        try{

            setDoc(path);

            //get the parent element
            NodeList nodeList = doc.getElementsByTagName("playlist");

            //read each parent element and return the childs
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);

                //iterate through each child node
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //create a variable for each node
                    String playlistName = eElement.getElementsByTagName("playlistName").item(0).getTextContent();

                    //check if song already exist to avoid duplicates and check if the path exist to avoid
                    //errors
                    if(new Exist().CheckArray(playlistName, this.listOfPlaylist)){
                        this.listOfPlaylist.add(playlistName);
                    }else{
                        System.out.println("this playlist does not exist anymore. Deleting it from the xml file");
                        eElement.getParentNode().removeChild(eElement);
                        new WriteXml().write(doc,path);
                    }
                }
            }

        }catch(Exception e){

        }
    }
   */

    /**
     * This sets up the document.
     *
     * @param path  Takes in a path to know what files is being read.
     */
    private void setDoc(String path){

        try{

            //set the file path and the doc builder
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            this.doc = doc;

        }catch(Exception e){ }
    }
}
