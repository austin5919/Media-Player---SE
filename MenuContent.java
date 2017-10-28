import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuContent implements ContextMenuState {
    EventHandler events;

    public MenuContent(EventHandler events) {
        this.events = events;
    }

    @Override
    public void content(ComboBox comboBox, ContextMenu contextMenu) {
        ObservableList<String> items = comboBox.getItems();
        Menu menu = new Menu("Add to playlist");

        for (String choices : items) {
            if (items.isEmpty()) {
                break;
            }

            String selected = comboBox.getSelectionModel().getSelectedItem().toString();
            if (choices != "Library" && choices != selected) {

                MenuItem newMenuItem = new MenuItem(choices.toString());
                newMenuItem.setOnAction(event -> {
                    this.events.contextMenuHandler(newMenuItem.getText());
                });
                menu.getItems().add(newMenuItem);
            }
        }
        menu.getItems().add(new MenuItem("Do Nothing"));

        if (!contextMenu.getItems().isEmpty()) {
            contextMenu.getItems().clear();
        }

        contextMenu.getItems().addAll(menu);
    }
}
