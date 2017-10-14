public class Style {

    public String setDimensions(int radius, int width, int heigth){

        return  "-fx-background-radius:" + radius + "em;" +
                "-fx-min-width:" + width + "px;" +
                "-fx-min-height:"  + heigth + "px;"+
                "-fx-max-width:" + width + "px;" +
                "-fx-max-height:" + heigth + "px;";
    }

    public String tableView(){
        return "./CssFiles/TableView.css";
    }

}
