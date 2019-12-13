package lk.ijse.roomreservation.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.roomreservation.alert.MyAlert;
import lk.ijse.roomreservation.connector.ProxyHandler;
import lk.ijse.roomreservation.dto.MealPackageDTO;
import lk.ijse.roomreservation.service.custom.ManagerService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagePackageController implements Initializable {

    @FXML
    private TableView<MealPackageDTO> tblMealPackage;

    @FXML
    private JFXTextField txtPackageName;

    @FXML
    private JFXTextField txtBreakFast;

    @FXML
    private JFXTextField txtLunch;

    @FXML
    private JFXTextField txtDinner;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtMealPackageId;

    @FXML
    private AnchorPane pnlAlert;

    @FXML
    private Label lblAlert;


    @FXML
    private JFXButton btnReset;

    private ManagerController managerController;

    @FXML
    void btnResetAction(ActionEvent event) {
        txtPackageName.setText("");
        txtMealPackageId.setText("");
        txtBreakFast.setText("");
        txtLunch.setText("");
        txtDinner.setText("");
    }


    @FXML
    void btnAddAction(ActionEvent event) {
        String packageName = txtPackageName.getText();
        String breadFast = txtBreakFast.getText();
        String lunch = txtLunch.getText();
        String dinner = txtDinner.getText();

        try {
            MealPackageDTO mealPackage=new MealPackageDTO(packageName,breadFast,lunch,dinner);
            boolean b = service.addMealPackage(mealPackage);
            if(b){
                myAlert.show(MyAlert.AlertType.DONE,"Meal Package Added Successfully");
                service.notifyObservers();
            }else {
                myAlert.show(MyAlert.AlertType.DONE,"Meal Package Fail to Add");
            }
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        Matcher matcher = Pattern.compile("^[0-9]{0,4}$").matcher(txtMealPackageId.getText());
        if(matcher.matches() && !txtMealPackageId.getText().isEmpty())
        try {
            int id = Integer.parseInt(txtMealPackageId.getText());
            boolean b = service.deleteMealPackage(id);
            if(b){
                myAlert.show(MyAlert.AlertType.DONE,"Deleted Successfully");
                service.notifyObservers();
            }else {
                myAlert.show(MyAlert.AlertType.FAIL,"Operation Failed");
            }
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        Matcher matcher = Pattern.compile("^[0-9]{0,4}$").matcher(txtMealPackageId.getText());
        if (matcher.matches() && !txtMealPackageId.getText().isEmpty()){
            try {
                int id = Integer.parseInt(txtMealPackageId.getText());
                String packageName = txtPackageName.getText();
                String breadFast = txtBreakFast.getText();
                String lunch = txtLunch.getText();
                String dinner = txtDinner.getText();

                boolean b = service.updateMealPackage(new MealPackageDTO(
                        id, packageName, breadFast, lunch, dinner
                ));

                if(b){
                    myAlert.show(MyAlert.AlertType.DONE,"Updated Successfully");
                    service.notifyObservers();
                }else {
                    myAlert.show(MyAlert.AlertType.FAIL,"Operation Failed");
                }
                loadTable();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }


    @FXML
    void txtMealPackageIdAction(ActionEvent event) {
        Matcher matcher = Pattern.compile("^[0-9]{0,4}$").matcher(txtMealPackageId.getText());
        if (matcher.matches() && !txtMealPackageId.getText().isEmpty()) {
            try {
                int id = Integer.parseInt(txtMealPackageId.getText());
                MealPackageDTO mealPack = service.searchMealPackage(id);
                if (null != mealPack) {
                    txtPackageName.setText(mealPack.getPackageName());
                    txtBreakFast.setText(mealPack.getBreakfast());
                    txtLunch.setText(mealPack.getLunch());
                    txtDinner.setText(mealPack.getDinner());
                } else {
                    myAlert.show(MyAlert.AlertType.WARNING, "No Meal Package Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            myAlert.show(MyAlert.AlertType.WARNING, "Invalid Key Input");
        }
    }


    @FXML
    void tblMealPackageMouseAction(MouseEvent event) {
        MealPackageDTO selectedItem = tblMealPackage.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            txtMealPackageId.setText(String.valueOf(selectedItem.getId()));
            txtPackageName.setText(selectedItem.getPackageName());
            txtBreakFast.setText(selectedItem.getBreakfast());
            txtLunch.setText(selectedItem.getLunch());
            txtDinner.setText(selectedItem.getDinner());
        }
    }

    private ManagerService service;
    private MyAlert myAlert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            service = ProxyHandler.getInstance().getService();
            myAlert=new MyAlert(pnlAlert,lblAlert);
            tblMealPackage.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tblMealPackage.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("packageName"));
            tblMealPackage.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("breakfast"));
            tblMealPackage.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lunch"));
            tblMealPackage.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dinner"));
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadTable(){
        try {
            ArrayList<MealPackageDTO> allMealPackage = service.getAllMealPackage();
            tblMealPackage.setItems(FXCollections.observableArrayList(allMealPackage));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void setManagerController(ManagerController managerController) {
        this.managerController = managerController;
    }
}
