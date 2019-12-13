package lk.ijse.roomreservation.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
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
import lk.ijse.roomreservation.dto.RoomDTO;
import lk.ijse.roomreservation.service.custom.ManagerService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageRoomController implements Initializable {
    @FXML
    private TableView<RoomDTO> tblRoom;

    @FXML
    private JFXTextField txtCapacity;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtRoomId;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXToggleButton tglBtnAC;

    @FXML
    private AnchorPane pnlAlert;

    @FXML
    private Label lblAlert;

    private ManagerController managerController;

    @FXML
    void btnAddAction(ActionEvent event) {
        if(checkValidity(txtCapacity.getText())) {
            boolean selected = tglBtnAC.isSelected();
            int capacity = Integer.parseInt(txtCapacity.getText());
            try {
                boolean b = service.addRoom(new RoomDTO(selected, capacity));
                if (b) {
                    myAlert.show(MyAlert.AlertType.DONE, "Room Added Successfully");
                    service.notifyObservers();
                } else {
                    myAlert.show(MyAlert.AlertType.FAIL, "Operation Fail");
                }
                loadTable();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            Matcher matcher = Pattern.compile("^[0-9]{0,3}$").matcher(txtRoomId.getText());
            if(matcher.matches() && !txtRoomId.getText().isEmpty()) {
                int id = Integer.parseInt(txtRoomId.getText());
                boolean b = service.deleteRoom(id);
                if(b){
                    myAlert.show(MyAlert.AlertType.DONE,"Delete Successfully");
                    service.notifyObservers();
                }else {
                    myAlert.show(MyAlert.AlertType.FAIL,"Operation Failed");
                }
                loadTable();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnResetAction(ActionEvent event) {
        txtCapacity.setText("");
        txtRoomId.setText("");
        loadTable();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        try {
            if(checkValidity(txtRoomId.getText())){
                if(checkValidity(txtCapacity.getText())){
                    int id = Integer.parseInt(txtRoomId.getText());
                    int capacity = Integer.parseInt(txtCapacity.getText());
                    boolean selected = tglBtnAC.isSelected();

                    boolean b = service.updateRoom(new RoomDTO(id, selected, capacity));
                    if(b){
                        myAlert.show(MyAlert.AlertType.DONE,"Update Successfully");
                        service.notifyObservers();
                    }else{
                        myAlert.show(MyAlert.AlertType.DONE,"Operation Failed");
                    }
                    loadTable();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void tblRoomMouseAction(MouseEvent event) {
        RoomDTO selectedItem = tblRoom.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            txtRoomId.setText(String.valueOf(selectedItem.getId()));
            txtCapacity.setText(String.valueOf(selectedItem.getCapacity()));
            tglBtnAC.setSelected(selectedItem.isWithAC());
        }
    }

    @FXML
    void tglBtnACAction(ActionEvent event) {
        try {
            ArrayList<RoomDTO> arrayList = service.searchRoomByAC(tglBtnAC.isSelected());
            tblRoom.setItems(FXCollections.observableArrayList(arrayList));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void txtRoomIdAction(ActionEvent event) {
        try {
            if(checkValidity(txtRoomId.getText())){
                int id = Integer.parseInt(txtRoomId.getText());
                RoomDTO roomDTO = service.searchRoom(id);
                if(roomDTO!=null) {
                    txtCapacity.setText(String.valueOf(roomDTO.getCapacity()));
                    tglBtnAC.setSelected(roomDTO.isWithAC());
                }else {
                    myAlert.show(MyAlert.AlertType.WARNING,"No Room Found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidity(String txt){
        Matcher matcher = Pattern.compile("^[0-9]{0,3}$").matcher(txt);
        if(matcher.matches() && !txt.isEmpty()) {
            return true;
        }
        return false;
    }

    private ManagerService service;
    private MyAlert myAlert;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            service= ProxyHandler.getInstance().getService();
            myAlert=new MyAlert(pnlAlert,lblAlert);

            tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("withAC"));
            tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("capacity"));

            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadTable(){
        try {
            ArrayList<RoomDTO> allRooms = service.getAllRooms();
            tblRoom.setItems(FXCollections.observableArrayList(allRooms));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void setManagerController(ManagerController managerController) {
        this.managerController = managerController;
    }
}
