import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;

public interface ContextMenuState {
    public void content(ComboBox comboBox, ContextMenu contextMenu);
}
