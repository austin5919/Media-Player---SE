/**
 * this class simply handles the styles
 */
public class Style {

    /**
     * sets dimension
     * @param radius
     * @param width
     * @param heigth
     * @return
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
     * @return
     */
    public String tableView(){
        return "./CssFiles/TableView.css";
    }

}
