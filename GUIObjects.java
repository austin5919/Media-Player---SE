import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * this class gets and sets GUI components
 */
public class GUIObjects {
    //local variables
    private TableView<Song> displayTableView;
    private ComboBox comboBox;
    private ContextMenu contextMenu;
    private Stage stage;
    private TextField textField;
    private Button browser;
    private Button okButton;
    private Button cancelButton;
    private Button playButton;
    private Label songName;
    private Label timer;
    private Timeline timeline;

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     * gets the most recent table view used by
     * the GUI. This table view will hold a
     * collection of songs that the user can
     * play.
     *
     * @return the table view used by the GUI
     */
    public TableView<Song> getDisplayTableView() {
        return displayTableView;
    }

    /**
     * takes the table view passed in and sets
     * it as the new table view
     *
     * @param tableView an updated table view
     */
    public void setDisplayTableView(TableView<Song> tableView) {
        this.displayTableView = tableView;
    }

    /**
     * gets the most recent combo box used by
     * the GUI. This combo box will hold a
     * collection of playlist options that the
     * user can choose from.
     *
     * @return the combo box used by the GUI
     */
    public ComboBox getComboBox() {
        return comboBox;
    }

    /**
     * takes the combo box passed in and sets
     * it as the new combo box
     *
     * @param comboBox an updated combo box
     */
    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    /**
     * gets the most recent context menu used by
     * the GUI. This context menu will hold a
     * collection of options similar to the combo
     * box that user can interact with by right
     * clicking any song within the display table
     * view.
     *
     * @return the context menu used by the GUI
     */
    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    /**
     * takes the context menu passed in and sets
     * it as the new context menu
     *
     * @param contextMenu an updated context menu
     */
    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    /**
     * gets the most recent pop menu
     * stage. This stage will pop up
     * when the user wants to create
     * a playlist.
     *
     * @return the pop menu stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * takes the pop menu stage passed in and
     * sets it as the new pop menu stage
     *
     * @param stage an updated pop menu stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * gets the most recent text field used by the pop up
     * menu stage. This text field is used to take in user
     * input.
     *
     * @return the text field used by the pop menu stage
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * takes the text field passed in and sets
     * it as the new text field
     *
     * @param textField an updated text field
     */
    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    /**
     * gets the most recent browser button used by
     * the GUI. This browser button brings up a file
     * chooser when clicked that allows the user to
     * an mp3/mp4 file to add to the library
     *
     * @return the browser button used by the GUI
     */
    public Button getBrowser() {
        return browser;
    }

    /**
     * takes the browser button passed in and sets
     * it as the new browser button
     *
     * @param browser an updated browser button
     */
    public void setBrowser(Button browser) {
        this.browser = browser;
    }

    /**
     * gets the most recent ok button used by the pop up
     * menu stage. This button simply submits the user input
     * on the pop menu stage.
     *
     * @return the ok button used by the pop up menu stage
     */
    public Button getOkButton() {
        return okButton;
    }

    /**
     * takes the ok button passed in and sets
     * it as the new ok button
     *
     * @param okButton an updated ok button
     */
    public void setOkButton(Button okButton) {
        this.okButton = okButton;
    }

    /**
     * gets the most recent cancel button used by the pop up menu
     * stage. this button simply cancels all user activity within
     * the pop up menu stage.
     *
     * @return the cancel button used by the pop up menu stage
     */
    public Button getCancelButton() {
        return cancelButton;
    }

    /**
     * takes the cancel button passed in and sets it
     * as the new ok button
     *
     * @param cancelButton an updated cancel button
     */
    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }

    /**
     * gets the most recent play button used by the GUI
     * stage. this button simply plays a selected song and
     * continues on auto play from there
     *
     * @return the playButton used by the GUI
     */
    public Button getPlayButton() {
        return playButton;
    }

    /**
     * takes the play button passed in and sets it
     * as the new play button
     *
     * @param playButton an updated playButton
     */
    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    /**
     * gets the most recent song
     * name used by the GUI stage.
     *
     * @return the song name to be used by the GUI
     */
    public Label getSongName() {
        return songName;
    }

    /**
     * takes the song name passed in and sets it
     * as the new song name
     *
     * @param songName an updated song name
     */
    public void setSongName(Label songName) {
        this.songName = songName;
    }

    /**
     * gets the most recent song
     * timer used by the GUI stage.
     *
     * @return the timer to be used by the GUI
     */
    public Label getTimer() {
        return timer;
    }

    /**
     * takes the song timer passed in and sets it
     * as the new song timer
     *
     * @param timer an updated song timer
     */
    public void setTimer(Label timer) {
        this.timer = timer;
    }
}
