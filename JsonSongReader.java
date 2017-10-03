import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSongReader {
    
    private JSONParser parser;
    private Object object;
    private JSONObject jsonObject;
    
    public JsonSongReader(){
           setJsonParsers(); 
    }
    
    private void setJsonParsers(){
        this.parser = new JSONParser();
       
        try {
            this.object = this.parser.parse(new FileReader("playlist.json"));
            this.jsonObject = (JSONObject) this.object;
        } catch (IOException | ParseException e) {
            System.out.println("something went wrong setting up the parsers: " + e);
        }
    }
    
    private JSONArray getListObject(String newList){
        JSONArray list = (JSONArray) this.jsonObject.get(newList);
        return list;
    }
    public String getLastUsedPlaylist(){
        Long id = (Long) jsonObject.get("playlistID");
        JSONArray list = getListObject("playlistName");
        return list.get(id.intValue()).toString();
    }
    
    public ArrayList getFromList(String newList){
        ArrayList playList = new ArrayList();
        JSONArray list = getListObject(newList);
        
        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            playList.add(iterator.next());
        }
        
        return playList;
    }
    
}
