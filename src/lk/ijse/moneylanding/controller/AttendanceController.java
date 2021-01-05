package lk.ijse.moneylanding.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.Attendance;
import lk.ijse.moneylanding.tableModel.AttendanceTM;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class AttendanceController implements Initializable {

    private static boolean addattendance(Attendance attendance) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO attendance(clid,date,attendance) VALUES(?,?,?)");
        pst.setObject(1, attendance.getClid());
        pst.setObject(2, attendance.getDate());
        pst.setObject(3, attendance.getAttendance());
        return pst.executeUpdate() > 0;
    }

    @FXML
    private JFXCheckBox Yes;
    @FXML
    private JFXCheckBox No;
    @FXML
    private HBox checkbox;

    public ObservableList<String> fillComboBox() throws ClassNotFoundException, SQLException {
        try {
            String sql = "select clid from collector";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ObservableList<String> option1 = FXCollections.observableArrayList();
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                option1.add(rst.getString("clid"));
            }
            return option1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private AnchorPane loginPane;
    @FXML
    private TableView<AttendanceTM> tblattendance;
    @FXML
    private JFXComboBox<String> cmbid;
    @FXML
    private JFXDatePicker dateepc;
    @FXML
    private JFXButton btnsave;

    ObservableList<String> optionl = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblattendance.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblattendance.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblattendance.getColumns().get(2).setStyle("-fx-alignment: CENTER;");

        //----------------------------------------------------------------------
        tblattendance.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("clid"));
        tblattendance.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblattendance.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("attendance"));

        try {
            loadAllAttendance();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCollectorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cmbid.setItems(fillComboBox());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tblattendance(MouseEvent event) {
        AttendanceTM attendance = tblattendance.getSelectionModel().getSelectedItem();

//            cmbid.setValue(attendance.getId());
//            dateepc.(attendance.getDate());
//            checkbox.setVisible(attendance.getAttendance());
    }

    @FXML
    private void cmbid(ActionEvent event) {
        dateepc.requestFocus();
    }

    @FXML
    private void dateepc(ActionEvent event) {
        checkbox.requestFocus();
    }

    @FXML
    private void btnsave(ActionEvent event) {
        String clid = (String) cmbid.getValue();
        String date = dateepc.getValue().toString();
        String attend = null; //checkbox.getTypeSelector();
        if (Yes.isSelected()) {
            attend = "Yes";
        } else if (No.isSelected()) {
            attend = "No";
        }

        Attendance attendance = new Attendance(clid, date, attend);

        try {
            boolean addattendance = AttendanceController.addattendance(attendance);
            if (addattendance) {
                loadAllAttendance();
                clearAll();

                //Alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Done");

                alert.show();
            } else {
                //Alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Not Done");

                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllAttendance() throws ClassNotFoundException, SQLException {
        String clid;
        String date;
        String attendance;

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM attendance");
        ResultSet rst = pst.executeQuery();

        ArrayList<AttendanceTM> allAttendance = new ArrayList<>();
        while (rst.next()) {
            clid = rst.getString(1);
            date = rst.getString(2);
            attendance = rst.getString(3);
            AttendanceTM attendan = new AttendanceTM(clid, date, attendance);
            allAttendance.add(attendan);
        }
        tblattendance.setItems(FXCollections.observableArrayList(allAttendance));
    }

    public void clearAll() {
        cmbid.setValue("");
        dateepc.setValue(LocalDate.now());
        checkbox.setVisible(true);
    }
    
    @FXML
    private void handlYes() {
        if (Yes.isSelected()) {
            No.setSelected(false);
        }
    }

    @FXML
    private void handlNo() {
        if (No.isSelected()) {
            Yes.setSelected(false);
        }
    }
}
