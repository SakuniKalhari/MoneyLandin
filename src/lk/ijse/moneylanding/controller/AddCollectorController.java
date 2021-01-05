package lk.ijse.moneylanding.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.Collector;
import lk.ijse.moneylanding.tableModel.CollectorTM;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class AddCollectorController implements Initializable {

    private static boolean addcollector(Collector collec) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO collector(nic,name,address,contact,salary) VALUES(?,?,?,?,?)");
        pst.setObject(1, collec.getNic());
        pst.setObject(2, collec.getName());
        pst.setObject(3, collec.getAddress());
        pst.setObject(4, collec.getContact());
        pst.setObject(5, collec.getSalary());
        return pst.executeUpdate() > 0;
    }

    private static boolean deleteCollector(String nic) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM collector WHERE nic=?");
        pst.setObject(1, nic);
        return pst.executeUpdate() > 0;
    }

    private static boolean updateCollector(Collector collector) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("UPDATE collector SET name=?,address=?,contact=?,salary =? WHERE nic=?");
        pst.setObject(1, collector.getName());
        pst.setObject(2, collector.getAddress());
        pst.setObject(3, collector.getContact());
        pst.setObject(4, collector.getSalary());
        pst.setObject(5, collector.getNic());
        return pst.executeUpdate() > 0;
    }

    @FXML
    private TableView<CollectorTM> tblcollector;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtcontact;
    @FXML
    private TextField txtsalary;
    @FXML
    private Button btnsave;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextArea txtaddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblcollector.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblcollector.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblcollector.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblcollector.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblcollector.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblcollector.getColumns().get(5).setStyle("-fx-alignment: CENTER;");

        //----------------------------------------------------------------------
        tblcollector.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("clid"));
        tblcollector.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblcollector.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblcollector.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblcollector.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblcollector.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("salary"));

        try {
            loadAllCollector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCollectorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tblcollector(MouseEvent event) {
        CollectorTM collector = tblcollector.getSelectionModel().getSelectedItem();

        txtid.setText(collector.getNic());
        txtname.setText(collector.getName());
        txtaddress.setText(collector.getAddress());
        txtcontact.setText(collector.getContact());
        txtsalary.setText(collector.getSalary());
    }

    @FXML
    private void txtname(ActionEvent event) {
        txtaddress.requestFocus();
    }

    @FXML
    private void txtid(ActionEvent event) {
        txtname.requestFocus();
    }

    @FXML
    private void txtcontact(ActionEvent event) {
        txtsalary.requestFocus();
    }

    @FXML
    private void txtsalary(ActionEvent event) {
        btnsave.requestFocus();
    }

    @FXML
    private void btnsave(ActionEvent event) {
        String nic = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String contact = txtcontact.getText();
        double salary = Double.parseDouble(txtsalary.getText());

        Collector collector = new Collector(nic, name, address, contact, salary);

        try {
            boolean addcollector = AddCollectorController.addcollector(collector);
            if (addcollector) {
                loadAllCollector();
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
            Logger.getLogger(AddCollectorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnupdate(ActionEvent event) {
        String nic = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String contact = txtcontact.getText();
        double salary = Double.parseDouble(txtsalary.getText());

        Collector collector = new Collector(nic, name, address, contact, salary);

        try {
            boolean updateCollector = AddCollectorController.updateCollector(collector);
            if (updateCollector) {
                loadAllCollector();
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
            Logger.getLogger(AddCollectorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btndelete(ActionEvent event) {
        String nic = txtid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure to Delete ?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation dialog");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                boolean customer = AddCollectorController.deleteCollector(nic);
                if (customer) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setContentText("Collector Deleted...!");
                    newAlert.setTitle(null);
                    newAlert.setHeaderText(null);
                    newAlert.showAndWait();
                    clearAll();
                    loadAllCollector();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AddCollectorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     private void txtaddress(MouseEvent event) {
        txtcontact.requestFocus();
    }

    private void loadAllCollector() throws ClassNotFoundException, SQLException {
        String clid;
        String nic;
        String name;
        String address;
        String contact;
        String salary;

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM collector");
        ResultSet rst = pst.executeQuery();

        ArrayList<CollectorTM> allCollector = new ArrayList<>();
        while (rst.next()) {
            clid = rst.getString(1);
            nic = rst.getString(2);
            name = rst.getString(3);
            address = rst.getString(4);
            contact = rst.getString(5);
            salary = rst.getString(6);
            CollectorTM collector = new CollectorTM(clid, nic, name, address, contact, salary);
            allCollector.add(collector);
        }
        tblcollector.setItems(FXCollections.observableArrayList(allCollector));
    }

    public void clearAll() {
        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
        txtsalary.setText("");
        txtid.requestFocus();
    }
}
