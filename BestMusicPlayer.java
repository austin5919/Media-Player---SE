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
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * the main driver, and gui interface.
 * @author Austin Ash
 * @author Jose Cruz
 */
public class BestMusicPlayer extends Application{
    
    /**
     * This is where our project starts.
     * @param args command line arguments
     */
    public static void main(String[] args) {launch(args);}
    private static String appTitle = "The Best Music Player Ever!";
    
    //interact with the control class
    MediaController control = new MediaController();
	
    /**
     * Starts my view interface
     * @param primaryStage the primary stage for view interface
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage){
        
        
            primaryStage.setTitle(this.appTitle);
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(5));
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setVgap(10);
            
            //buttons
            Button firstSongButton = getButton();
            gridPane.add(control.setFirstSongButton(firstSongButton), 0, 0); 
            
            //buttons
            Button secondSongButton = getButton();
            gridPane.add(control.setSecondSongButton(secondSongButton), 0, 2); 
            
            //buttons
            Button thirdSongButton = getButton();
            gridPane.add(control.setThirdSongButton(thirdSongButton), 0, 3);
			
            //labels
            
            //buttons
            Button fileChooserButton = getButton();
            gridPane.add(control.setFileChooserButton(fileChooserButton), 0, 6);
            
            primaryStage.setScene(new Scene(gridPane, 300, 250));
	    primaryStage.show(); 
    }
	
    /**
     * creates button
     * @return a button instance
     */
    private Button getButton(){
        return new Button();
    }
    
    /**
     * creates label
     * @return a label instance
     */
    private Label getLabel(){
        return new Label();
    }
}
