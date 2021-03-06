/**
 * This class simply handles the styles.
 */
public class Style {

    /**
     * Sets dimension based on the values that are passed in
     *
     * @param radius Takes in a radius to determine the shape of button.
     * @param width  Takes in a width and sets it as the min and max width.
     * @param heigth Takes in a heigth and sets its the min and max heigth.
     * @return the desired style settings.
     */
    public String setDimensions(int radius, int width, int heigth) {
        return "-fx-background-radius:" + radius + "em;" +
                "-fx-min-width:" + width + "px;" +
                "-fx-min-height:" + heigth + "px;" +
                "-fx-max-width:" + width + "px;" +
                "-fx-max-height:" + heigth + "px;";
    }

    /**
     * @return a path to the Css file to be added to the components.
     */
    public String tableView() {
        return "./CssFiles/TableView.css";
    }

}
