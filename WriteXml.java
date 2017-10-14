import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * this class writes xml files using given information
 */
public class WriteXml {

    private Document doc;
    private Element root;
    private Text lineBreak;

    public void AppendChildToXml(String path, Song songObj) throws Exception{

        try {

            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

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

            Element song = doc.createElement("song");
            root.appendChild(song);


            Element songName = doc.createElement("songName");
            songName.appendChild(doc.createTextNode(songObj.getSongName()));
            song.appendChild(songName);

            Element songDuration = doc.createElement("songDuration");
            songDuration.appendChild(doc.createTextNode(songObj.getSongDuration()));
            song.appendChild(songDuration);

            Element songPath = doc.createElement("songPath");
            songPath.appendChild(doc.createTextNode(songObj.getSongPath()));
            song.appendChild(songPath);
            //System.out.println(path);
            write(doc,path);

        } catch (Exception e) {
            System.out.println("was not able to fully complete the writting process");
        }
    }

    public void write(Document doc, String path)throws Exception{

        DOMSource source = new DOMSource(doc);
        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFactory.newTransformer();
        aTransformer.setOutputProperty(OutputKeys.INDENT,"no");
        aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        StreamResult result = new StreamResult(path);
        aTransformer.transform(source, result);
    }
}
