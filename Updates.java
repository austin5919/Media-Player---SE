import javafx.collections.ObservableList;

/**
 * This class will update the GUI components.
 */
public class Updates {
    /**
     * This method updates the main display TableView.
     *
     * @param listOfSongs  Takes in a list of songs to be added to a tableView.
     * @param components  Takes in the components class to access the display TableView.
     */
    public void updateDisplay(ObservableList<Song> listOfSongs,Components components){
        components.getDisplay().setItems(listOfSongs);
        components.getDisplay().getFocusModel().focus(components.getSelectedIndex());
    }
}
