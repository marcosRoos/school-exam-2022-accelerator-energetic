import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load( getClass().getResource("home_page.fxml") );
        Image icon = new Image(  getClass().getResourceAsStream("img/accelerator-logo.png") );
        stage.getIcons().add(icon);
        stage.setTitle("Energéticos Accelerator");
        Scene home = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene( home );
        stage.show();
    }
 
public static void main(String[] args) {
        launch(args);
    }
}