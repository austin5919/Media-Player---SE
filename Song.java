package bestmusicplayer;

public class Song {
    private String songName;
    private String songLenght;
    private String songLink;
    
    public Song(){
        this.songName = "";
        this.songLink = "";
        this.songLenght = "";
    }
    public Song(String newSongName, String newSongLenght, String newSongLink){
        this.songName = newSongName;
        this.songLink = newSongLink;
        this.songLenght = newSongLenght;
    }
    
    public void setSongName(String newSong){
        this.songName = newSong;
    }
    
    public String getSongName(){
        return this.songName;
    }
    
    public void setSongLenght(String newLenght){
        this.songLenght = newLenght;
    }
    
    public String getSongLenght(){
        return this.songLenght;
    }
    
    public void setSongLink(String newLink){
        this.songLink = newLink;
    }
    
    public String getSongLink(){
        return this.songLink;
    }
}
