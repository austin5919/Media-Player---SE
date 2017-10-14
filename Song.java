public class Song {

    private String songName;
    private String songDuration;
    private String songPath;

    public Song(){

        this.songName = "";
        this.songPath = "";
        this.songDuration = "";
    }

    public Song(String newName, String newDuration, String newPath){

        this.songName = newName;
        this.songPath = newPath;
        this.songDuration = newDuration;

    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
}
