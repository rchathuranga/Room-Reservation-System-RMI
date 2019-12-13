package lk.ijse.roomreservation.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.roomreservation.connector.ProxyHandler;
import lk.ijse.roomreservation.service.custom.ManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginAction(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/Manager.fxml"));
            Parent root= loader.load();
            Stage primaryStage= (Stage) btnLogin.getScene().getWindow();
            ManagerController controller = loader.getController();
            controller.setStage(primaryStage);
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ManagerService service;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            service= ProxyHandler.getInstance().getService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
