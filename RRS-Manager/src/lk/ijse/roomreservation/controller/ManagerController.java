package lk.ijse.roomreservation.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.roomreservation.alert.MyAlert;
import lk.ijse.roomreservation.connector.ProxyHandler;
import lk.ijse.roomreservation.dto.*;
import lk.ijse.roomreservation.observer.Observer;
import lk.ijse.roomreservation.service.custom.ManagerService;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerController implements Initializable, Observer {

    @FXML
    private JFXTextField txtCusNIC;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtCusContact;

    @FXML
    private TextArea txtCusAddress;

    @FXML
    private Label lblPackageName;

    @FXML
    private Label lblBreakFast;

    @FXML
    private Label lblLunch;

    @FXML
    private Label lblDinner;

    @FXML
    private JFXTextField txtNoOfAdults;

    @FXML
    private JFXTextField txtNoOfKids;

    @FXML
    private TableView<CustomDTO> tblReservationDetail;

    @FXML
    private JFXButton btnReserve;

    @FXML
    private JFXDatePicker dtpStartDate;

    @FXML
    private JFXDatePicker dtpEndDate;

    @FXML
    private JFXToggleButton tglBtnAC;

    @FXML
    private JFXButton btnSearchRoom;

    @FXML
    private TableView<RoomDTO> tblRoom;

    @FXML
    private JFXTextField txtBreakFast;

    @FXML
    private JFXTextField txtLunch;

    @FXML
    private JFXTextField txtDinner;

    @FXML
    private JFXButton btnSelectPackage;

    @FXML
    private TableView<MealPackageDTO> tblPackages;

    @FXML
    private JFXButton btnAddRoom;

    @FXML
    private JFXButton btnAddPackage;

    @FXML
    private JFXTextField txtPackageName;

    @FXML
    private AnchorPane pnlAlert;

    @FXML
    private Label lblAlert;


    private ManagerService service;
    private MyAlert myAlert;
    private ArrayList<CustomDTO> reservationCustomList = new ArrayList<>();
    private CustomerDTO customer;
    private MealPackageDTO mealPackage;
    private ArrayList<ReservationDetailDTO> reservationDetailList = new ArrayList<>();
    private Stage primaryStage;

    @FXML
    void btnReserveAction(ActionEvent event) {
        Matcher matchNIC = Pattern.compile("(^[0-9]{12}$)|(^[0-9]{9}v$)").matcher(txtCusNIC.getText());
        Matcher matchName = Pattern.compile("(^[A-z]{0,20}$)").matcher(txtCusName.getText());
        Matcher matchContact = Pattern.compile("^[0-9]{10}$").matcher(txtCusContact.getText());
        Matcher matchAdult = Pattern.compile("^[0-9]{0,2}$").matcher(txtNoOfAdults.getText());
        Matcher matchKids = Pattern.compile("^[0-9]{0,2}$").matcher(txtNoOfKids.getText());

        if (matchNIC.matches() && !txtCusNIC.getText().isEmpty()) {
            if (matchName.matches() && !txtCusName.getText().isEmpty()) {
                if (matchContact.matches() && !txtCusContact.getText().isEmpty()) {
                    if (matchAdult.matches() && !txtNoOfAdults.getText().isEmpty()) {
                        if (matchKids.matches() && !txtNoOfKids.getText().isEmpty()) {

                            String nic = txtCusNIC.getText();
                            String name = txtCusName.getText();
                            String contact = txtCusContact.getText();
                            String address = txtCusAddress.getText();
                            int adults = Integer.parseInt(txtNoOfAdults.getText());
                            int kids = Integer.parseInt(txtNoOfKids.getText());

                            if (customer == null) {
                                customer = new CustomerDTO(nic, name, contact, address);
                            }
                            if(null != mealPackage) {
                                if(reservationDetailList.size()>0) {

                                    int guest=0;

                                    for (CustomDTO c :reservationCustomList) {
                                        guest+=c.getCapacity();
                                    }

                                    if(guest==(adults+kids)){
                                        reserveRoom(customer, adults, kids);
                                    }else {
                                        myAlert.show(MyAlert.AlertType.WARNING,
                                                "Guest Count Doesn't Meet Room Capacity");
                                    }
                                }else {
                                    myAlert.show(MyAlert.AlertType.WARNING,"Select Rooms For Reservation");
                                }
                            }else {
                                myAlert.show(MyAlert.AlertType.WARNING,"Select Meal Package For Reservation");
                            }
                        }
                    }
                }
            }
        }
    }

    private void reserveRoom(CustomerDTO customer, int adults, int kids) {
        try {
            ReservationDTO reservation = new ReservationDTO(
                    customer, adults, kids,
                    reservationDetailList, mealPackage.getId()
            );
            boolean reserved = service.reserveRooms(reservation);
            if (reserved) {
                myAlert.show(MyAlert.AlertType.DONE, "Reservation Completed");
                service.notifyObservers();

                reservationCustomList=new ArrayList<>();
                reservationDetailList=new ArrayList<>();
                dtpEndDate.setValue(null);
                dtpStartDate.setValue(null);

                txtCusContact.setText("");
                txtCusAddress.setText("");
                txtCusName.setText("");
                txtNoOfAdults.setText("");
                txtNoOfKids.setText("");
                txtCusNIC.setText("");
                lblDinner.setText("");
                lblLunch.setText("");
                lblBreakFast.setText("");
                lblPackageName.setText("");

                loadTables();
            } else {
                myAlert.show(MyAlert.AlertType.FAIL, "Failure Occur When Reserving");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchRoomAction(ActionEvent event) {
        try {
            LocalDate startDate = dtpStartDate.getValue();
            LocalDate endDate = dtpEndDate.getValue();

            if (startDate != null && endDate != null) {
                long l = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
                if (l >= 0) {
                    CustomDTO custom = new CustomDTO(tglBtnAC.isSelected(), 0, startDate, endDate);
                    ArrayList<RoomDTO> arrayList = new ArrayList();

                    ArrayList<CustomDTO> availableRooms = service.getAvailableRooms(custom);

                    for (CustomDTO c : availableRooms) {
                        arrayList.add(new RoomDTO(
                                c.getRoomId(), c.isWithAC(), c.getCapacity()
                        ));
                    }
                    tblRoom.setItems(FXCollections.observableArrayList(arrayList));
                } else {
                    myAlert.show(MyAlert.AlertType.WARNING, "Illegal Date Range. Please Change the Dates");
                }
            } else {
                myAlert.show(MyAlert.AlertType.WARNING, "Insert Date Duration Before Searching");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnSelectPackageAction(ActionEvent event) {
        if (mealPackage != null) {
            lblPackageName.setText(mealPackage.getPackageName());
            lblBreakFast.setText(mealPackage.getBreakfast());
            lblLunch.setText(mealPackage.getLunch());
            lblDinner.setText(mealPackage.getDinner());
        }
    }

    @FXML
    void tblReservationAction(MouseEvent event) {
        CustomDTO selectedItem = tblReservationDetail.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            reservationCustomList.remove(selectedItem);
            for (int i = 0; i < reservationDetailList.size(); i++) {
                ReservationDetailDTO rd=reservationDetailList.get(i);
                if(rd.getRoomId()==selectedItem.getRoomId()){
                    reservationDetailList.remove(i);
                }
            }
        }
        loadTables();
    }

    @FXML
    void dtpStartDateAction(ActionEvent event) {
        disableDates(dtpEndDate, dtpStartDate.getValue());
    }

    @FXML
    void tblRoomMouseClick(MouseEvent event) {
        RoomDTO selectedItem = tblRoom.getSelectionModel().getSelectedItem();
        LocalDate start = dtpStartDate.getValue();
        LocalDate end = dtpEndDate.getValue();

        if (start != null && end != null) {
            if (selectedItem != null) {
                ReservationDetailDTO rd = new ReservationDetailDTO(0, selectedItem.getId(), start, end);
                reservationDetailList.add(rd);
                boolean contains = true;
                for (CustomDTO c : reservationCustomList) {
                    if (c.getRoomId() == selectedItem.getId()) {
                        contains = false;
                    }
                }
                if (contains) {
                    CustomDTO customDTO = new CustomDTO(selectedItem.getId(),
                            selectedItem.isWithAC(), selectedItem.getCapacity(), start, end);
                    reservationCustomList.add(customDTO);
                }
                loadTables();
            }
        }
    }


    @FXML
    void txtCusNICKeyRelease(KeyEvent event) {
        if (Pattern.compile("(^[0-9]{12}$)|(^[0-9]{9}v$)").
                matcher(txtCusNIC.getText()).matches() && !txtCusNIC.getText().isEmpty()) {
                String nic = txtCusNIC.getText();
            try {
                customer = service.searchCustomer(nic);
                if (customer != null) {
                    txtCusName.setText(customer.getName());
                    txtCusContact.setText(customer.getContact());
                    txtCusAddress.setText(customer.getAddress());
                    myAlert.show(MyAlert.AlertType.DONE, "Customer Founded");
                }else {
                    myAlert.show(MyAlert.AlertType.NOTICE, "No Customer Founded And You Can Add New Customer");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            customer = null;
            txtCusName.setText("");
            txtCusAddress.setText("");
            txtCusContact.setText("");
            myAlert.show(MyAlert.AlertType.WARNING, "Invalid Key");
        }
    }

    @FXML
    void tglBtnACActon(ActionEvent event) {
        try {
            ArrayList<RoomDTO> arrayList = service.searchRoomByAC(tglBtnAC.isSelected());
            tblRoom.setItems(FXCollections.observableArrayList(arrayList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tblPackagesMouseClick(MouseEvent event) {
        MealPackageDTO selectedItem = tblPackages.getSelectionModel().getSelectedItem();
        if (null != selectedItem) {
            mealPackage = selectedItem;
            txtPackageName.setText(selectedItem.getPackageName());
            txtBreakFast.setText(selectedItem.getBreakfast());
            txtLunch.setText(selectedItem.getLunch());
            txtDinner.setText(selectedItem.getDinner());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            service = ProxyHandler.getInstance().getService();
            service.register(this);
            initiateTables();
            loadTables();
            myAlert = new MyAlert(pnlAlert, lblAlert);

            ArrayList<RoomDTO> allRooms = service.getAllRooms();
            tblRoom.setItems(FXCollections.observableArrayList(allRooms));
        } catch (Exception e) {
            e.printStackTrace();
        }



        disableDates(dtpStartDate, LocalDate.now());
        disableDates(dtpEndDate, LocalDate.now());
    }

    private void disableDates(DatePicker dp, LocalDate localDate) {
        dp.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = localDate;
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    private void loadTables() {
        try {
            ArrayList<MealPackageDTO> allPacks = service.getAllMealPackage();
            tblPackages.setItems(FXCollections.observableArrayList(allPacks));

            tblReservationDetail.setItems(FXCollections.observableArrayList(reservationCustomList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initiateTables() {
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("withAC"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("capacity"));

        tblPackages.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("packageName"));
        tblPackages.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breakfast"));
        tblPackages.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lunch"));
        tblPackages.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dinner"));

        tblReservationDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblReservationDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("withAC"));
        tblReservationDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("capacity"));
        tblReservationDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tblReservationDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("endDate"));

    }

    private ManagePackageController packageController;
    private Stage stagePackage = new Stage();

    @FXML
    void btnAddPackageAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ManagePackage.fxml"));
            Parent root = loader.load();
            packageController = loader.getController();
            packageController.setManagerController(this);
            Scene scene = new Scene(root);
            stagePackage.setScene(scene);
            stagePackage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ManageRoomController roomController;
    private Stage stageRoom = new Stage();

    @FXML
    void btnAddRoomAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ManageRoom.fxml"));
            Parent root = loader.load();
            roomController = loader.getController();
            roomController.setManagerController(this);
            Scene scene = new Scene(root);
            stageRoom.setScene(scene);
            stageRoom.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() throws Exception {
        loadTables();
    }

    public void setStage(Stage stage){
        this.primaryStage=stage;
        primaryStage.setOnCloseRequest(event -> {
            try {
                service.unregister(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
