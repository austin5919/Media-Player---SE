import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * this class will set all the view components and then pass
 * them to the MediaController.java class to be used.
 * 
 * @author austin
 * @author jose 
 */
public class BestMusicPlayer extends Application{
    MediaController control = new MediaController();
    Style style = new Style();
    
    /**
     * here is where the program starts
     * @param args 
     */
    public static void main(String[] args) {launch(args);}
    
    /**
     * This method will set my primary stage and the secondary main layout
     * which will be a BorderPane. It also calls other methods to 
     * set the layouts that the BorderPane will hold.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage){
        //get style for the stage to keep the view at a fixed size
        primaryStage = style.stageStyle(primaryStage,"Music Player",515, 500);
        
        //BorderPnae
        BorderPane border = new BorderPane(); 
        
        //Hbox to hold the actual playlist
        HBox displaySongSection = displaySongSection();
        border.setTop(displaySongSection);
        
        //GridPane to hold buttons
        GridPane bottomSection = borderBottomSection();
        border.setBottom(bottomSection);
        
        
        border.setStyle(style.borderPaneStyle(250,300));
        primaryStage.setScene(new Scene(border, 500, 500));
        primaryStage.show(); 
    }
    
    /*
     * This method will hold all my buttons and set them
     * in the MediaController.java class to be used.
     */
    private GridPane borderBottomSection(){
        
        //build gridpane to hold buttons. I chose
        //a gridPane because it allows me to specify
        //the position of the components
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        
        
        //The images for the buttons
        Image playImage = getImage("playButton.png",35);
        Image pauseImage = getImage("pauseButton.png",35);
        Image nextImage = getImage("nextButton.png",35);
        Image previousImage = getImage("previousButton.png",35);
        Image browserImage = getImage("browserButton.png",35);
        
        //the actual buttons
        Button playButton = getButton();
        Button pauseButton = getButton();
        Button nextButton = getButton();
        Button previousButton = getButton();
        Button browserButton = getButton();
        
        //Get the style for the buttons  and set visibility
        //for paused button
        previousButton.setStyle(style.getButtonStyle(33));
        playButton.setStyle(style.getButtonStyle(50));
        playButton.requestFocus();
        pauseButton.setStyle(style.getButtonStyle(50));
        pauseButton.setVisible(false);
        nextButton.setStyle(style.getButtonStyle(33));
        browserButton.setStyle(style.getButtonStyle(40));
        
        //Set the images on the button
        previousButton.setGraphic(new ImageView(previousImage));
        playButton.setGraphic(new ImageView(playImage));
        pauseButton.setGraphic(new ImageView(pauseImage));
        nextButton.setGraphic(new ImageView(nextImage));
        browserButton.setGraphic(new ImageView(browserImage));
        
        //Specify where i want the button to appear
        //inside of the Grid Pane
        gridPane.add(pauseButton, 190, 0);
        gridPane.add(playButton, 190, 0);
        gridPane.add(previousButton, 180, 0);
        gridPane.add(nextButton, 200, 0);
        gridPane.add(browserButton, 315, 0);
        
        control.setPlayPauseSongButton(playButton, pauseButton);
        
        //TODO:pass browser buttons to controller
        
        
        //return the new Grid Pane
        return gridPane;
    }
    
    /*
     * this method is just a way to access images without having to 
     * re-write the whole path
    */
    private Image getImage(String newImage,int size){
        newImage = "file:images/" + newImage;
        return new Image(newImage,size,size,false,false);
    }
    
    /*
     * this method will set the layout for the TableView i will use
     * to hold all the songs. This tableView is then going to be passed
     * to the MediaController to be used.
    */
    private HBox displaySongSection(){
        
        //this will hold the table view i want to add
        //to display the songs
        HBox display = new HBox();
        
        //set the style for the displayHBox
        display.setStyle(style.displayHboxStyle());
        
        //these are the title of the sections i will use
        //inside of the table view
        TableColumn<Song,String> songName = new TableColumn("Name");
        songName.setCellValueFactory(new PropertyValueFactory<>("songName"));
        
        TableColumn<Song,String> songLenght = new TableColumn("Time");
        songLenght.setCellValueFactory(new PropertyValueFactory<>("songLenght"));
        songLenght.setMaxWidth(150);
        songLenght.setMinWidth(150);
        

        //Table view for the songs
        TableView<Song> playList = new TableView();
        playList.setStyle(style.playListStyle(410,470));
        
        //set the colunm and constraints
        playList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        playList.getColumns().addAll(songName,songLenght);
        display.getChildren().addAll(playList);
        control.setTableView(playList);
        
        return display;
    }
    
    /*
     * just returns a button
    */
    private Button getButton(){
        return new Button();
    }
}
