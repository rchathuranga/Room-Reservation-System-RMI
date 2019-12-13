package lk.ijse.roomreservation.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/lk/ijse/roomreservation/view/Login.fxml"));
        Parent root= loader.load();
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
