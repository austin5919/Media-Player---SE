import javafx.collections.ObservableList;

/**
 * this class will update the GUI components
 */
public class Updates {
    /**
     * this method updates the main display TableView
     *
     * @param listOfSongs takes in a list of songs to be added to a tableView
     * @param components takes in the components class to access the display TableView
     */
    public void updateDisplay(ObservableList<Song> listOfSongs,Components components){
        components.getDisplay().setItems(listOfSongs);
        components.getDisplay().getFocusModel().focus(components.getSelectedIndex());
    }
}
