import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class View extends Application {
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
     * Program starting point.
     *
     * @param args Command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the primary stage with all the components needed
     * to perform all functions.
     *
     * @param primaryStage A primary stage to place the components in.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Music Player");
        BorderPane border = new BorderPane();
        GridPane topComponents = topComponents();
        centerComponents();
        //bottomComponents();
        setContextMenu();
        popWindow();


        border.setTop(topComponents);
        border.setCenter(tableView);

        this.eventHandler = new EventHandler(
                listDropDown, browswer,
                tableView, userInput,
                textInput, okButton,
                cancelButton, contextMenu
        );

        primaryStage.setScene(new Scene(border, 700, 700));
        primaryStage.show();
    }

    //popWindow
    private void popWindow() {
        this.userInput = new Stage();
        userInput.setTitle("New Playlist");

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10));

        GridPane center = new GridPane();
        center.setVgap(1);
        this.textInput = new TextField();
        textInput.setMinWidth(280);
        center.add(new Label("Enter Playlist Name"), 0, 0);
        center.add(textInput, 0, 1);

        GridPane bottom = new GridPane();
        bottom.setHgap(1);
        this.okButton = new Button("OK");
        this.cancelButton = new Button("Cancel");
        bottom.add(okButton, 190, 0);
        bottom.add(cancelButton, 195, 0);

        border.setCenter(center);
        border.setBottom(bottom);

        userInput.setScene(new Scene(border, 300, 100));
        userInput.setResizable(false);

        //this.eventHandler.setPopWindow(userInput,okButton,cancelButton,textInput);
    }

    //the context menu part of the GUI
    private void setContextMenu() {
        //create the actual dropdown menu to hold the menu
        this.contextMenu = new ContextMenu();
        //eventHandler.setContextMenu(contextMenu);
    }

    //holds the components for the top sections of the border pane
    private GridPane topComponents() {

        //a grid pane to hold by components
        GridPane topComponents = new GridPane();
        topComponents.setPadding(new Insets(3));
        topComponents.setHgap(1);

        //a combobox to hold the playlist
        this.listDropDown = new ComboBox();
        //listDropDown.setStyle(style.setDimensions(1,120, 27));
        listDropDown.getItems().add("New Playlist");
        listDropDown.getItems().add("Library");

        listDropDown.getSelectionModel().select("Library");

        //a browser button to be able to browse songs
        this.browswer = new Button("Add to library");
        //browswer.setStyle(style.setDimensions(1,65,27));

        //ProgressBar time = new ProgressBar();

        topComponents.add(listDropDown, 250, 0);
        topComponents.add(browswer, 260, 0);
        //topComponents.add(time,0,0);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.ALWAYS);
        topComponents.getColumnConstraints().add(column1);

        //TODO: set handlers for listDropDown
        //TODO: set handlers for browser
        //eventHandler.setTopComponents(listDropDown, browswer);
        //setContextMenu();
        return topComponents;
    }

    //the table view settings
    private void centerComponents() {

        //a tableview to hold the songs
        this.tableView = new TableView<>();
        tableView.getStylesheets().add(style.tableView());
        TableColumn<Song, String> songDuration = columns("Time", "duration");

        //TODO: make duration width shorter
        songDuration.setMaxWidth(200);
        songDuration.setMinWidth(200);

        //set a constraint so we dont see extra columns on the stage
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(columns("Name", "name"), songDuration);

        //TODO: set handlers for tableView
        //eventHandler.setCenterComponents(tableView);
    }

    //table column settings
    private TableColumn<Song, String> columns(String columnName, String objectName) {

        //set the table title along with the object to ocupy it
        TableColumn<Song, String> newColumn = new TableColumn<>(columnName);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(objectName));
        newColumn.setSortable(false);

        return newColumn;
    }
}
