package lk.ijse.moneylanding.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.User;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class UserRegistrationController implements Initializable {

    private static boolean registrationUser(User userRegistration) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO user(username,password) VALUES(?,?)");
        pst.setObject(1, userRegistration.getUsername());
        pst.setObject(2, userRegistration.getPassword());
        return pst.executeUpdate() > 0;
    }

    @FXML
    private AnchorPane userPane;
    @FXML
    private JFXTextField txtpassword;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXTextField txtconfirmpassword;
    @FXML
    private JFXButton btnCancle;
    @FXML
    private JFXButton btnRegister;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 
    @FXML
    private void txtusernamekey(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                txtpassword.requestFocus();
                break;
            case DOWN:
                txtpassword.requestFocus();
                break;
            default:
                break;
        }
    }

    @FXML
    private void txtUsername(ActionEvent event) {
        if (Pattern.compile("^[A-Z]{1}[a-z]+\\s[A-Z]{1}[a-z]+$").matcher(txtusername.getText()).matches()) {
           // btnRegister.setDisable(true);
            txtpassword.requestFocus();
        } else {
            txtusername.requestFocus();
            Alert a = new Alert(Alert.AlertType.ERROR, "Input User Name format is Invalid...\nYou need to use format Like this \"Sakuni Kalhari\"", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void txtpasswordKey(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                txtconfirmpassword.requestFocus();
                break;
            case DOWN:
                txtconfirmpassword.requestFocus();
                break;
            case UP:
                txtusername.requestFocus();
            default:
                break;
        }
    }

    @FXML
    private void txtpasswordMouse(MouseEvent event) {
               if (txtusername.getText().trim().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING, "You can't continue this task because you didn't enter your User Name");
            a.show();
        }
    }

    @FXML
    private void txtPassword(ActionEvent event) {
               if (Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,10}$").matcher(txtpassword.getText()).matches()) {
            txtconfirmpassword.requestFocus();
        } else {
            txtpassword.requestFocus();
            Alert a = new Alert(Alert.AlertType.ERROR, "Input Password format is Invalid...\nYou need to use format Like this \"Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:\"", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void btnRegisterKey(KeyEvent event) {
              switch (event.getCode()) {
//            case ENTER:
//                btnRegister.setVisible(true);
//                break;
            case LEFT:
                btnCancle.requestFocus();
            default:
                break;
        }
    }

    @FXML
    private void btnRegisterAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
         String userName = txtusername.getText();
        String userPassword = txtpassword.getText();
        User userRegistration = new User(userName, userPassword);
        boolean registration = UserRegistrationController.registrationUser(userRegistration);
        if (registration) {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage = (Stage) this.userPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Login.fxml"));
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
            stage.setResizable(false);

            FadeTransition trans = new FadeTransition(Duration.millis(2000), root);
            trans.setFromValue(0);
            trans.setToValue(1.0);
            trans.play();

            Alert a = new Alert(Alert.AlertType.ERROR, "Please Login to your Account for Manage this System", ButtonType.OK);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "You Are Not Register.,Please try again", ButtonType.OK);
        }
    }

    @FXML
    private void txtconfirmpasswordKey(KeyEvent event) {
         switch (event.getCode()) {
            case ENTER:
                btnRegister.requestFocus();
                break;
            case DOWN:
                btnRegister.requestFocus();
                break;
            case UP:
                txtpassword.requestFocus();
            default:
                break;
        }
    }

    @FXML
    private void txtconfirmpasswordAc(ActionEvent event) {
         //btnRegister.setDisable(false);
        if (Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,10}$").matcher(txtpassword.getText()).matches()) {
            if (!(txtpassword.getText() == txtconfirmpassword.getText())) {
                btnRegister.requestFocus();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Input Confirm Password is Incorrect.. Please ReInput Correct Password");
                a.show();
            }
        } else {
            txtpassword.requestFocus();
            Alert a = new Alert(Alert.AlertType.ERROR, "Input Password format is Invalid...\nYou need to use format Like this \"Minimum six characters, at least one uppercase letter, one lowercase letter and one number:\"", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void btnCancleKey(KeyEvent event) {
        
        switch (event.getCode()) {
//            case ENTER:
//                btnCancle.setVisible(true);
//                break;
            case RIGHT:
                btnRegister.requestFocus();
                break;
            case UP:
                txtconfirmpassword.requestFocus();
                break;
            default:
                break;
        }
    }

    @FXML
    private void btnCancleAc(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/moneylanding/view/Login.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) this.userPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();

        FadeTransition trans = new FadeTransition(Duration.millis(2000), parent);
        trans.setFromValue(0);
        trans.setToValue(1.0);
        trans.play();
    }

    public void clearAll() {
        txtusername.setText("");
        txtpassword.setText("");
        txtconfirmpassword.setText("");
        txtusername.requestFocus();   
    }
}