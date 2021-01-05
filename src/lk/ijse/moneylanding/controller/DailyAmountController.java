package lk.ijse.moneylanding.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.Dailyamount;
import lk.ijse.moneylanding.tableModel.AmountTM;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class DailyAmountController implements Initializable {

    private static boolean adddlyamount(Dailyamount dlyamount) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO dailyamount(clid,cid,dailyamount,date) VALUES(?,?,?,?)");
        pst.setObject(1, dlyamount.getClid());
        pst.setObject(2, dlyamount.getCid());
        pst.setObject(3, dlyamount.getDailyamount());
        pst.setObject(4, dlyamount.getDate());
        return pst.executeUpdate() > 0;
    }

    private static boolean deleteAmount(String cid) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM dailyamount WHERE cid=?");
        pst.setObject(1, cid);
        return pst.executeUpdate() > 0;
    }

    @FXML
    private JFXButton btndelete;

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
            Logger.getLogger(DailyAmountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<String> CustomerCID() throws ClassNotFoundException, SQLException {
        try {
            String sql = "select cid from customer";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ObservableList<String> option1 = FXCollections.observableArrayList();
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                option1.add(rst.getString("cid"));
            }
            return option1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DailyAmountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private AnchorPane DailyPane;
    @FXML
    private JFXTextField txtdailyamount;
    @FXML
    private JFXDatePicker datepc;
    @FXML
    private JFXComboBox<String> cmbid;
    @FXML
    private JFXButton btnsave;
    @FXML
    private JFXComboBox<String> cmbcid;
    @FXML
    private TableView<AmountTM> tbldailyamount;

    ObservableList<String> optionl = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbldailyamount.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tbldailyamount.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tbldailyamount.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tbldailyamount.getColumns().get(3).setStyle("-fx-alignment: CENTER;");

        //----------------------------------------------------------------------
        tbldailyamount.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("clid"));
        tbldailyamount.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tbldailyamount.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dailyamount"));
        tbldailyamount.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            loadAllAmount();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DailyAmountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cmbid.setItems(fillComboBox());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DailyAmountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cmbcid.setItems(CustomerCID());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DailyAmountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void datepc(ActionEvent event) {
        btnsave.requestFocus();
    }

    @FXML
    private void cmbcid(ActionEvent event) {
        txtdailyamount.requestFocus();
    }

    @FXML
    private void btnsave(ActionEvent event) throws ClassNotFoundException, SQLException {
        String clid = cmbid.getValue();
        String cid = cmbcid.getValue();
        double dailyamount = Double.parseDouble(txtdailyamount.getText());
        String date = datepc.getValue().toString();

        Dailyamount dlyamount = new Dailyamount(clid, cid, dailyamount, date);
        boolean adddlyamount = DailyAmountController.adddlyamount(dlyamount);
        if (adddlyamount) {
            loadAllAmount();
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
    }

    private void loadAllAmount() throws ClassNotFoundException, SQLException {
        String clid;
        String cid;
        String dailyamount;
        String date;

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM dailyamount");
        ResultSet rst = pst.executeQuery();

        ArrayList<AmountTM> allAmount = new ArrayList<>();
        while (rst.next()) {
            clid = rst.getString(1);
            cid = rst.getString(2);
            dailyamount = rst.getString(3);
            date = rst.getString(4);
            AmountTM dlyamount = new AmountTM(clid, cid, dailyamount, date);
            allAmount.add(dlyamount);
        }
        tbldailyamount.setItems(FXCollections.observableArrayList(allAmount));
    }

    public void clearAll() {
        cmbid.setValue("");
        cmbcid.setValue("");
        txtdailyamount.setText("");
        datepc.setValue(LocalDate.now());
        cmbid.requestFocus();
    }

    @FXML
    private void txtdailyamount(ActionEvent event) {
        datepc.requestFocus();
    }

    @FXML
    private void cmbid(ActionEvent event) {
        cmbcid.requestFocus();
    }

    @FXML
    private void tbldailyamount(MouseEvent event) {
        AmountTM amount = tbldailyamount.getSelectionModel().getSelectedItem();

        cmbid.setValue(amount.getClid());
        cmbcid.setValue(amount.getCid());
        txtdailyamount.setText(amount.getDailyamount());
        datepc.setValue(LocalDate.MAX);
    }

    @FXML
    private void btndelete(ActionEvent event) {
        String cid = cmbcid.getValue();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure to Delete ?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation dialog");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                boolean amount = DailyAmountController.deleteAmount(cid);
                if (amount) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setContentText("Amount Deleted...!");
                    newAlert.setTitle(null);
                    newAlert.setHeaderText(null);
                    newAlert.showAndWait();
                    clearAll();
                    loadAllAmount();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DailyAmountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
