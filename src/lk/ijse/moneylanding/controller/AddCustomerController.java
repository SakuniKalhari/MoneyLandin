package lk.ijse.moneylanding.controller;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.Customer;
import lk.ijse.moneylanding.tableModel.CustomerTM;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class AddCustomerController implements Initializable {

    private static boolean addcustomer(Customer cust) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO customer(nic,name,address,contact,amount,date) VALUES(?,?,?,?,?,?)");
        pst.setObject(1, cust.getNic());
        pst.setObject(2, cust.getName());
        pst.setObject(3, cust.getAddress());
        pst.setObject(4, cust.getContact());
        pst.setObject(5, cust.getAmount());
        pst.setObject(6, cust.getDate());
        return pst.executeUpdate() > 0;

    }

    static boolean deleteCustomer(String cid) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM customer WHERE cid=?");
        pst.setObject(1, cid);
        return pst.executeUpdate() > 0;
    }

    private static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("UPDATE customer SET name=?,address=?,contact=?,amount =? WHERE nic=?");
        pst.setObject(1, customer.getName());
        pst.setObject(2, customer.getAddress());
        pst.setObject(3, customer.getContact());
        pst.setObject(4, customer.getAmount());
        //  pst.setObject(5, customer.getDate());
        pst.setObject(5, customer.getNic());
        return pst.executeUpdate() > 0;
    }

    @FXML
    private AnchorPane loginPane;
    @FXML
    private TableView<CustomerTM> tblcustomer;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtnic;
    @FXML
    private TextField txtcontact;
    @FXML
    private TextField txtamount;
    @FXML
    private Button btnsave;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextArea txtaddress;
    @FXML
    private DatePicker dPCurrent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblcustomer.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblcustomer.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblcustomer.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblcustomer.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblcustomer.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblcustomer.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
        tblcustomer.getColumns().get(6).setStyle("-fx-alignment: CENTER;");

        //----------------------------------------------------------------------
        tblcustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblcustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblcustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblcustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblcustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblcustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblcustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            loadAllCustomer();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tblcustomer(MouseEvent event) {
        CustomerTM customer = tblcustomer.getSelectionModel().getSelectedItem();

        txtnic.setText(customer.getNic());
        txtname.setText(customer.getName());
        txtaddress.setText(customer.getAddress());
        txtcontact.setText(customer.getContact());
        txtamount.setText(customer.getAmount());
        //dPCurrent.setText(customer.getDate());
        // dPCurrent.setValue((String)customer.getDate());
    }

    @FXML
    private void txtname(ActionEvent event) {
        txtaddress.requestFocus();
    }

    @FXML
    private void txtnic(ActionEvent event) {
        txtname.requestFocus();
    }

    @FXML
    private void txtcontact(ActionEvent event) {
        txtamount.requestFocus();
    }

    @FXML
    private void txtamount(ActionEvent event) {
        dPCurrent.requestFocus();
    }

    @FXML
    private void btnupdate(ActionEvent event) {
        String nic = txtnic.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String contact = txtcontact.getText();
        double amount = Double.parseDouble(txtamount.getText());
        String date = null;// dPCurrent.getValue();
        Customer customer = new Customer(nic, name, address, contact, amount, date);

        try {
            boolean updateCustomer = AddCustomerController.updateCustomer(customer);
            if (updateCustomer) {
                loadAllCustomer();
                clearAll();

                //Alert
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Done");

                alert.show();
            } else {
                //Alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Not Done");

                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btndelete(ActionEvent event) {
        String cid = txtnic.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure to Delete ?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation dialog");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                boolean customer = AddCustomerController.deleteCustomer(cid);
                if (customer) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setContentText("Customer Deleted...!");
                    newAlert.setTitle(null);
                    newAlert.setHeaderText(null);
                    newAlert.showAndWait();
                    clearAll();
                    loadAllCustomer();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadAllCustomer() throws ClassNotFoundException, SQLException {
        String cid;
        String nic;
        String name;
        String address;
        String contact;
        String amount;
        String date;

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM customer");
        ResultSet rst = pst.executeQuery();

        ArrayList<CustomerTM> allCustomer = new ArrayList<>();
        while (rst.next()) {
            cid = rst.getString(2);
            nic = rst.getString(1);
            name = rst.getString(3);
            address = rst.getString(4);
            contact = rst.getString(5);
            amount = rst.getString(6);
            date = rst.getString(7);
            CustomerTM customer = new CustomerTM(nic, cid, name, address, contact, amount, date);
            allCustomer.add(customer);
        }
        tblcustomer.setItems(FXCollections.observableArrayList(allCustomer));
    }

    public void clearAll() {
        txtnic.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
        txtamount.setText("");
        dPCurrent.setValue(LocalDate.now());
        txtnic.requestFocus();
    }

    @FXML
    private void btnsaveAc(ActionEvent event) throws ClassNotFoundException, SQLException {

        String nic = txtnic.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String contact = txtcontact.getText();
        double amount = Double.parseDouble(txtamount.getText());
        String date = dPCurrent.getValue().toString();

        Customer customer = new Customer(nic, name, address, contact, amount, date);

        try {
            boolean addcustomer = AddCustomerController.addcustomer(customer);
            if (addcustomer) {
                loadAllCustomer();
                clearAll();

                //Alert
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Done");

                alert.show();
            } else {
                //Alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Not Done");

                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void acDPCu(ActionEvent event) {
        btnsave.requestFocus();
    }
}
