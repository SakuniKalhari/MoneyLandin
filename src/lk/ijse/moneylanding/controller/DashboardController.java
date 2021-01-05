package lk.ijse.moneylanding.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane changePane;
    @FXML
    private Button btnaddcustomer;
    @FXML
    private Button btnaddcollector;
    @FXML
    private Button btncustomerdetail;
    @FXML
    private Button btnattendance;
    @FXML
    private Button btncollectordetail;
    @FXML
    private Button btndailyamount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnaddcustomer(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Add Customer.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btnaddcollector(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Add Collector.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btncustomerdetail(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Customer Detail.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btnattendance(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Attendance.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btncollectordetail(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Collector Detail.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btndailyamount(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Daily Amount.fxml"));
        changePane.getChildren().setAll(pane);
    }
}
