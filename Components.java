import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class holds all the components i will
 * constantly be updating from the GUI(View).
 */
public class Components {

    private ContextMenu menu;
    private ComboBox comboBox;
    private TableView<Song> display;
    private Stage stage;
    private TextField textField;


    private int selectedIndex;
    private String menuSelection;

    /**
     * gets the stage
     *
     * @return a stage to be used
     */
    public Stage getStage() { return stage; }

    /**
     * sets the stage
     *
     * @param stage
     */
    public void setStage(Stage stage) { this.stage = stage; }

    /**
     * gets the textfield
     *
     * @return a textfield to be used or updated
     */
    public TextField getTextField() { return textField; }

    /**
     * sets the textfield
     *
     * @param textField
     */
    public void setTextField(TextField textField) { this.textField = textField; }

    /**
     * i will need to setTheMenu selction to get the selected
     * createContent
     *
     * @param menuSelection recieves createContent from the update class
     */
    public void setMenuSelection(String menuSelection) {
        this.menuSelection = menuSelection;
    }

    /**
     * fetch the selected menu createContent
     *
     * @return returning the current selected menu createContent
     */
    public String getMenuSelection() {
        return menuSelection;
    }

    /**
     * Gets the combo box.
     *
     * @return The comboBox/dropdown list.
     */
    public ComboBox getComboBox() {
        return comboBox;
    }

    /**
     * Sets the ComboBox to be used
     *
     * @param comboBox Takes in a comboBox and sets it.
     */
    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    /**
     * Gets the selected index.
     *
     * @return The current song selected index.
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Sets the selected index.
     *
     * @param selectedIndex The current song selected index to be set.
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }


    /**
     * Gets the Menu.
     *
     * @return The right click menu.
     */
    public ContextMenu getMenu() {
        return menu;
    }

    /**
     * Sets the Menu.
     *
     * @param menu Takes in the right click menu.
     */
    public void setMenu(ContextMenu menu) {
        this.menu = menu;
    }

    /**
     * Gets the display.
     *
     * @return The TableView of Song which makes up the display.
     */
    public TableView<Song> getDisplay() {
        return display;
    }

    /**
     * Sets the display.
     *
     * @param display Takes the TableView of Song to be set.
     */
    public void setDisplay(TableView<Song> display) {
        this.display = display;
    }
}
