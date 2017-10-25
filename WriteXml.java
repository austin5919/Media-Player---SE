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
public class WriteXml {

    private Document doc;
    private Element root;
    private Text lineBreak;

    /**
     * Append new child to an existing xml or create an xml with new song node.
     *
     * @param path  Takes in the path of an xml file and adds content to it.
     * @param song  Takes in a song object and uses it to build the nodes.
     * @throws Exception Throws an exception if anything goes wrong.
     */
    public void AppendChildToXml(String path, Song song) throws Exception{

		
		ArrayList<Song> list;
		//If the file does not exist yet, create it; otherwise read it
		if (!(new File(path).exists())){
			list = new ArrayList<Song>();
		} else {
			list = (ArrayList<Song>) Serialization.read(path);
		}
		
		//if (!(new File(path).exists()) == null) { list = new ArrayList<Song>(); } // Create new file if one does not exist
		for (Song checkSong : list) {
			if (checkSong.getSongPath() == song.getSongPath()) {
				System.out.println("Duplicate song attempted! Not allowed.");
				return;
			}
		}
		list.add(song);
		Serialization.write(list,path);
		
		/*
        try {

            //set file path and doc builder
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            //check if file exist. if so set it in doc builder
            //if not create the file
            //TODO:check if the xml file actually Exist
            if(new Exist().CheckFile(path)){

                this.doc = dBuilder.parse(xmlFile);
                this.root = doc.getDocumentElement();

            }else{
               this.doc = dBuilder.newDocument();
               this.root = doc.createElement("library");
               doc.appendChild(this.root);
            }

            doc.getDocumentElement().normalize();

			Element songE = doc.createElement("song");
            root.appendChild(songE);

            //set songname child
            Element songName = doc.createElement("songName");
            songName.appendChild(doc.createTextNode(song.getSongName()));
            songE.appendChild(songName);

            //set songDuration child
            Element songDuration = doc.createElement("songDuration");
            songDuration.appendChild(doc.createTextNode(song.getSongDuration()));
            songE.appendChild(songDuration);

            //set songE path child
            Element songPath = doc.createElement("songPath");
            songPath.appendChild(doc.createTextNode(song.getSongPath()));
            songE.appendChild(songPath);

            //writes to a xml file
            //System.out.println(path);
            write(doc,path);

        } catch (Exception e) {
            System.out.println("was not able to add song or create playlist");
        }
		*/
    }

    /**
     * Writes a new file with the defined doc information.
     *
     * @param doc  Takes in a document with add information to be turn in to an xml file.
     * @param path  Takes in a path to know where to write to.
     * @throws Exception  Throws an exception if anything goes wrong.
     */
    public void write(Document doc, String path)throws Exception{

        //sets the transformer and writes new file
        DOMSource source = new DOMSource(doc);
        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFactory.newTransformer();
        aTransformer.setOutputProperty(OutputKeys.INDENT,"no");
        aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        StreamResult result = new StreamResult(path);
        aTransformer.transform(source, result);
    }
}
