package lk.ijse.moneylanding.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnaddcustomer;
    @FXML
    private Button btncustomerdetail;
    @FXML
    private Button btnaddcollector;
    @FXML
    private Button btncollectordetail;
    @FXML
    private Button btnattendance;
    private AnchorPane homePane;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane pnldashboard;
    @FXML
    private AnchorPane changePane;
    @FXML
    private Label lblmain;
    @FXML
    private Button btndashboard;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private Button btnDailyamount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //lblmain.setText("Money Landing System");
            AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Dashboard.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btnaddcustomer(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Add Customer.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btncustomerdetail(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Customer Detail.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btnaddcollector(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Add Collector.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btncollectordetail(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Collector Detail.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btnattendance(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Attendance.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btndashboard(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Dashboard.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void btnLogoutAC(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Login.fxml"));
        Scene temp = new Scene(root);
        Stage stage = (Stage) this.main.getScene().getWindow();
        stage.setScene(temp);
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnDailyamount(MouseEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Daily Amount.fxml"));
        changePane.getChildren().setAll(pane);
//        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Daily Report.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) this.homePane.getScene().getWindow();
//        stage.close();
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.setResizable(false);
//        stage.show();
    }
}
