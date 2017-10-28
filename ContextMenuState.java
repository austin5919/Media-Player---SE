import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;

public interface ContextMenuState {
    public void createContent(ComboBox comboBox);
    public void updateContextMenu(ContextMenu contextMenu);
}
