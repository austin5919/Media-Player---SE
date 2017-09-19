import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller {
    GridPane gridPane;
    Inteface gui = new Inteface();
    Controller c = new Controller();
    Stage prime;

    public static void main(String[] args) {
        Application.launch(Inteface.class, args);
    } 
    
}
