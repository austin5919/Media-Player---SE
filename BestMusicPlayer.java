import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * This will be the main driver class and it will create
 * all the components for the user view. It will then 
 * pass the components to the MusicController.java class 
 * to be used.
 * @author jose
 * @author austin
 */
public class BestMusicPlayer extends Application{
    
    
    MediaController control = new MediaController();
    
    
    Style style = new Style();
    
    /**
     * the program entry point at launch
     * @param args 
     */
    public static void main(String[] args) {launch(args);}
 
    /**
     * this method will create the primary 
     * stage and all the layouts of the view.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage){
        
        primaryStage = style.stageStyle(primaryStage,"Music Player",515, 500);
        
        BorderPane border = new BorderPane(); 
        
        HBox displaySongSection = displaySongSection();
        border.setTop(displaySongSection);
        
        GridPane bottomSection = borderBottomSection();
        border.setBottom(bottomSection);
        
        border.setStyle(style.borderPaneStyle(250,300));
        primaryStage.setScene(new Scene(border, 500, 500));
        primaryStage.show(); 
    }
    
    /*
     * This method defines the bottom layout 
     * of the border pane, and pass them to
     * the MediaController.java
     */
    private GridPane borderBottomSection(){
        
        GridPane gridPane = new GridPane();
        
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(1);
        
        Image browserImage = getImage("browserButton.png",35);
        
        Button browserButton = getButton();
        
        browserButton.setStyle(style.getButtonStyle(40));
        browserButton.setGraphic(new ImageView(browserImage));
        
        gridPane.add(browserButton, 0, 0);
        control.setButton(browserButton);
        
        return gridPane;
    }
    
    /*
     * This method simply gets and image and sets the
     * size accordingly
     */
    private Image getImage(String newImage,int size){
        newImage = "./images/" + newImage;
        return new Image(newImage,size,size,false,false);
    }
    
    /* 
     * This method defines the display layout 
     * of the border pane, and pass it to the 
     * MediaController.java
     */
    private HBox displaySongSection(){
        
        HBox display = new HBox();
        
        display.setStyle(style.displayHboxStyle());
        
        TableColumn<Song,String> songName = new TableColumn("Name");
        songName.setCellValueFactory(new PropertyValueFactory<>("songName"));
        //songName.setStyle(style.tableSongName());
        
        songName.setSortable(false);
        
        TableColumn<Song,String> songLenght = new TableColumn("Time");
        songLenght.setCellValueFactory(new PropertyValueFactory<>("songLenght"));
        
        
        
        songLenght.setSortable(false);
        
        songLenght.setMaxWidth(150);
        songLenght.setMinWidth(150);
        
        TableView<Song> playList = new TableView();
        //experiment
        playList.getStylesheets().add(style.TableView());
        playList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        playList.getColumns().addAll(songName,songLenght);
        display.getChildren().addAll(playList);
        control.setDisplayTable(playList);
        
        return display;
    }
    
    private Button getButton(){
        return new Button();
    }
}