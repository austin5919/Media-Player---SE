import javafx.application.Platform;
import javafx.scene.control.ComboBox;

public class SafetyMeasures implements SafeUpdate {

    EventHandler eventHandler;
    public SafetyMeasures(EventHandler eventHandler){
        this.eventHandler = eventHandler;
    }

    @Override
    public void updateCombobox(ComboBox comboBox, String input) {
        Platform.runLater(() -> {
            new Updates().updateComboBox(comboBox,input);
            this.eventHandler.setHandlersContextMenu();
        });
    }
}
