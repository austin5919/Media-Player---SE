import javafx.collections.ObservableList;

public class Updates {

    public void updateDisplay(ObservableList<Song> listOfSongs,Components components){
        components.getDisplay().setItems(listOfSongs);
        components.getDisplay().getFocusModel().focus(components.getSelectedIndex());
    }
}
