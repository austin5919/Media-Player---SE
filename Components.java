import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;

/**
 * this class holds all the components i will
 * constantly be updating from the GUI(View)
 */
public class Components {

    private Menu menu;
    private ComboBox comboBox;
    private TableView<Song> display;

    private int selectedIndex;

    /**
     * @return returns the comboBox/dropdown list
     */
    public ComboBox getComboBox() {
        return comboBox;
    }

    /**
     * @param comboBox takes in a comboBox and sets it
     */
    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    /**
     * @return returns the current song selected index
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * @param selectedIndex takes in the current song selected index
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex; }


    /**
     * @return returns the right click menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu takes in the right click menu
     */
    public void setMenu(Menu menu) {

        this.menu = menu;
    }

    /**
     * @return returns the tableView display
     */
    public TableView<Song> getDisplay() {
        return display;
    }

    /**
     * @param display takes in the TableView display and sets it
     */
    public void setDisplay(TableView<Song> display) {
        this.display = display;
    }
}
