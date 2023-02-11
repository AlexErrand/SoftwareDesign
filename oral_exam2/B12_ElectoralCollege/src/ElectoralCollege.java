import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Driver for the ElectoralColege program
 * */
public class ElectoralCollege extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL myFxml = getClass().getResource("ElectoralCollege.fxml");
        if(myFxml == null){
            System.out.println("ERROR, FILE NOT FOUND");
        }
        else {

            Parent root = FXMLLoader.load(myFxml);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Electoral College 2022");
            primaryStage.setScene(scene);
            primaryStage.show();


        }
    }
}
