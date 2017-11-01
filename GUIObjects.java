import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUIObjects {
    private TableView<Song> displayTableView;
    //private ArrayList<ArrayList<Song>> playlistCollection;
    //private ArrayList<Song> library;
    private ComboBox comboBox;
    private ContextMenu contextMenu;
    private Stage stage;
    private TextField textField;
    private Button browswer;
    private Button okButton;
    private Button cancelButton;

    public TableView<Song> getDisplayTableView() {
        return displayTableView;
    }

    public void setDisplayTableView(TableView<Song> tableView) {
        this.displayTableView = tableView;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public Button getBrowswer() {
        return browswer;
    }

    public void setBrowswer(Button browswer) {
        this.browswer = browswer;
    }

    public Button getOkButton() {
        return okButton;
    }

    public void setOkButton(Button okButton) {
        this.okButton = okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }
}
