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

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) { this.comboBox = comboBox; }

    public int getSelectedIndex() { return selectedIndex; }

    public void setSelectedIndex(int selectedIndex) { this.selectedIndex = selectedIndex; }

    public Menu getMenu() { return menu; }

    public void setMenu(Menu menu) { this.menu = menu; }

    public TableView<Song> getDisplay() { return display; }

    public void setDisplay(TableView<Song> display) { this.display = display; }
}
