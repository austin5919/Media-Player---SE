import javafx.stage.Stage;

/**
 * this class is only meant to hold the style of
 * the GUI components. I decided to separate the style
 * from the actual components as much as i could to
 * make them more re-use able
 * @author josea
 */
public class Style {
    
    public String borderPaneStyle(int height, int width){
        return "-fx-background-color: #e6e6e6;" +
                "-fx-max-width:" + width + ";" +
                "-fx-max-height:"  + height + ";" +
                "-fx-padding: 5;" +
                "-fx-alignment: center;";
    }
    
    public String TableView(){
        return "./CssFiles/TableView.css";
    }
    
    public Stage stageStyle(Stage newStage, String title, int height, int width){
        
        newStage.setTitle(title);
        newStage.setMinWidth(width);
        newStage.setMaxWidth(width);
        
        newStage.setMinHeight(height);
        newStage.setMaxHeight(height);
        
        return newStage;
    }
    
    public String getButtonStyle(int size){
        return "-fx-background-radius: 5em; " + 
                "-fx-min-width:" + size + "px;" +
                "-fx-min-height:"  + size + "px;"+
                "-fx-max-width:" + size + "px;" +
                "-fx-max-height:" + size + "px;";
    }
    
    public String displayHboxStyle(){
        return "-fx-padding: 0;" +
                "-fx-alignment: top-center;" ;
    }
}