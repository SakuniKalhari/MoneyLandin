package lk.ijse.moneylanding.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.Collector;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class CollectorDetailController implements Initializable {

    private static Collector searchCollector(String clid) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Select * From Collector where clid='" + clid + "'";
        ResultSet rst = stm.executeQuery(SQL);
        if (rst.next()) {
            return new Collector(rst.getString("nic"), rst.getString("name"), rst.getString("address"), rst.getString("contact"),rst.getDouble("salary"));
        }
        return null;
    }

    @FXML
    private TextField txtname;
    @FXML
    private JFXTextArea txtaddress;
    @FXML
    private TextField txtcontact;
    @FXML
    private Button btnback;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private JFXTextField txtsalary;
    @FXML
    private JFXTextField txtid;
    @FXML
    private JFXTextField txtnic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnback(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.loginPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void txtid(ActionEvent event) throws ClassNotFoundException, SQLException {
        String clid = txtid.getText();

        Collector collector = CollectorDetailController.searchCollector(clid);
        if (collector != null) {
            txtnic.setText(collector.getNic());
            txtname.setText(collector.getName());
            txtaddress.setText(collector.getAddress());
            txtcontact.setText(collector.getContact());
            txtsalary.setText (String.valueOf(collector.getSalary()));
        }
    }
}
