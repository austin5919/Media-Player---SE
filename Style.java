/**
 * this class simply handles the styles
 */
public class Style {

    /**
     * sets dimension
     *
     * @param radius takes in a radius to determined the shape of button
     * @param width takes in a width and sets it as the min and max width
     * @param heigth takes in a heigth and sets its the min and max heigth
     * @return returns new style
     */
    public String setDimensions(int radius, int width, int heigth){

        return  "-fx-background-radius:" + radius + "em;" +
                "-fx-min-width:" + width + "px;" +
                "-fx-min-height:"  + heigth + "px;"+
                "-fx-max-width:" + width + "px;" +
                "-fx-max-height:" + heigth + "px;";
    }

    /**
     * sets settings for tableview using css file
     *
     * @return returns a path to the TableView css file
     */
    public String tableView(){
        return "./CssFiles/TableView.css";
    }

}
