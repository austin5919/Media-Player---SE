import java.io.*;
import java.nio.file.Paths;
import java.lang.*;
import javafx.scene.media.*;
import javafx.embed.swing.JFXPanel;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.stage.*;

/**
 * Launches a music player that does all the things I want it to.  Command-line usage: java BestMusicPlayer
 * 
 * 
 * @author Kyle Burke <paithanq@gmail.com>
 */
public class BestMusicPlayer extends Application {
	
	//fields
	private static Label songtitle = new Label("No song currently playing.");
	private static String songname = "Pride of the Wolverines";
	private static String appTitle = "The Best Music Player Ever!";
	
	/**
     * Returns a the String song name.
     *
     * @return  A String of the song name.
     */
	public static String getSongName() {
		return songname;
	}
	
	
	public static Button getButton() { 
		return new Button();
	}

/**
 * Main method to launch the program.
 *
 * @param args  Command-line arguments for the program.  Currently unused.
 */
public static void main(String[] args) {launch(args);}
    
    @Override
    public void start(Stage primaryStage) 
    {
        //solution from stackoverflow user Sagar Damani at: https://stackoverflow.com/questions/14025718/javafx-toolkit-not-initialized-when-trying-to-play-an-mp3-file-through-mediap
        //        final JFXPanel bananarama = new JFXPanel();
        
        
        primaryStage.setTitle(this.appTitle);
        Button button = getButton();
        button.setText("Play Wolverines Pride (3:42)");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //code to play a song modified from stackoverflow user jasonwaste's answer on https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
                String song = "./songs/PrideOfTheWolverines.mp3";
                Media media = new Media(new File(song).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                songtitle.setText(BestMusicPlayer.getSongName());}});
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.add(button, 0, 0);
        gridPane.add(songtitle, 0, 1);
        primaryStage.setScene(new Scene(gridPane, 300, 250));
        primaryStage.show();
    }

} //end of BestMusicPlayer.java
