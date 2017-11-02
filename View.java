import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * this class will create the GUI components
 */
public class View extends Application {
    //local variables
    private Style style;
    private EventHandler eventHandler;
    private Stage userInput;
    private TextField textInput;
    private Button okButton;
    private Button cancelButton;
    private ContextMenu contextMenu;
    private ComboBox listDropDown;
    private Button browswer;
    private TableView<Song> tableView;

    public View() {
        //set local variables
        this.style = new Style();
        this.eventHandler = null;
        this.userInput = null;
        this.textInput = null;
        this.okButton = null;
        this.cancelButton = null;
        this.contextMenu = null;
        this.listDropDown = null;
        this.browswer = null;
        this.tableView = null;
    }

    /**
     * @param args Command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method creates the primary stage and passes all the components
     * created to the event handler class. i will consist of a border pane
     * to hold the top,center and bottom layer.
     *
     * @param primaryStage A primary stage to place the components that we
     *                     create.
     */
    @Override
    public void start(Stage primaryStage) {
        //set the title
        primaryStage.setTitle("Music Player");

        //create a border to hold layers of the GUI
        BorderPane border = new BorderPane();

        //create a grid pane to hold the top layer of border
        GridPane topComponents = topComponents();

        //create a table view to occupy center layer of border
        centerComponents();
        //bottomComponents();

        //create a simple context menu
        setContextMenu();

        //build the stage for the pop window
        popWindow();

        //set the top and center layer of the border
        border.setTop(topComponents);
        border.setCenter(tableView);

        //create an event handler instance and pass in all components
        //passing them in to the constructor allows us to make all
        //handlers private.
        this.eventHandler = new EventHandler(
                listDropDown, browswer,
                tableView, userInput,
                textInput, okButton,
                cancelButton, contextMenu
        );
        //set the scene and show the main stage
        primaryStage.setScene(new Scene(border, 700, 700));
        primaryStage.show();
    }

    //build a method for the pop window stage
    //this is the stage that will pop up every time
    //we want to create a playlist
    private void popWindow() {
        //create a stage
        this.userInput = new Stage();
        userInput.setTitle("New Playlist");

        //create a border pane to hold the layers of the stage
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10));

        //create center layer grid pane
        //this layer will be just the tex field
        GridPane center = new GridPane();
        center.setVgap(1);

        //create text input
        this.textInput = new TextField();
        textInput.setMinWidth(280);

        //set center layer
        center.add(new Label("Enter Playlist Name"), 0, 0);
        center.add(textInput, 0, 1);

        //create bottom layer Grid pane
        //this layer will hold two buttons
        GridPane bottom = new GridPane();
        bottom.setHgap(1);

        //create the ok button
        this.okButton = new Button("OK");

        //create the cancel button
        this.cancelButton = new Button("Cancel");

        //add both buttons to the grid pane
        bottom.add(okButton, 190, 0);
        bottom.add(cancelButton, 195, 0);

        //set the center and bottom layer
        border.setCenter(center);
        border.setBottom(bottom);

        //set the scene and set the resizeable option to false.
        userInput.setScene(new Scene(border, 300, 100));
        userInput.setResizable(false);
    }

    //create context menu method for the right click event
    //placed it in a method just in case we want to add stuff
    //at start up in the future.
    private void setContextMenu() {
        //create context menu
        this.contextMenu = new ContextMenu();
    }

    //create a method to handle creating the top layer components
    private GridPane topComponents() {
        //create a grid pane to hold top components
        GridPane topComponents = new GridPane();
        topComponents.setPadding(new Insets(3));
        topComponents.setHgap(1);

        //create a combo box to hold the playlist options
        this.listDropDown = new ComboBox();
        listDropDown.getItems().add("New Playlist");
        listDropDown.getItems().add("Library");
        listDropDown.getSelectionModel().select("Library");

        //create a browser button to be add songs
        this.browswer = new Button("Add to library");
        topComponents.add(listDropDown, 250, 0);
        topComponents.add(browswer, 260, 0);

        //set constraints to the grid pane for resizing purposes
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.ALWAYS);
        topComponents.getColumnConstraints().add(column1);
        return topComponents;
    }

    //create a method to handle the center objects of the GUI
    //it will consist of a table view
    private void centerComponents() {
        //create a table view to hold the songs
        this.tableView = new TableView<>();
        tableView.getStylesheets().add(style.tableView());
        TableColumn<Song, String> songDuration = columns("Time", "duration");

        //make duration width shorter
        songDuration.setMaxWidth(200);
        songDuration.setMinWidth(200);

        //set a constraint so we do not see extra columns on the stage
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(columns("Name", "name"), songDuration);
    }

    //create a columns method for utility purpose
    private TableColumn<Song, String> columns(String columnName, String objectName) {
        //set the table title along with the object to occupy it
        TableColumn<Song, String> newColumn = new TableColumn<>(columnName);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(objectName));
        newColumn.setSortable(false);
        return newColumn;
    }
}
