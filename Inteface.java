
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

public class Inteface extends Application{
    
    //fields
    private static Label songtitle = new Label("No song currently playing.");
    private static String songname = "Pride of the Wolverines";
    private static String appTitle = "The Best Music Player Ever!";
    GridPane gridPane;
    Stage prime;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(this.appTitle);
        
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        //gridPane.add(getButton("button1"), 0, 0);
        gridPane.add(songtitle, 0, 1);
        
        primaryStage.setScene(new Scene(gridPane, 300, 250));
        prime = primaryStage;
        prime.show();
		
    }
    
    public Button getButton(String title){
        
        //gridPane.add(new Button(title), 0, 0);
        return new Button();
    }
       
}
