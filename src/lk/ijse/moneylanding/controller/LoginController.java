package lk.ijse.moneylanding.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.User;

/**
 *
 * @author ~saku~
 */
public class LoginController implements Initializable {

    private static boolean checkUser(User check) throws SQLException, ClassNotFoundException {
        String qry = "Select username ,password  from user where username =? and password  =?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(qry);
        pst.setString(1, check.getUsername());
        pst.setString(2, check.getPassword());
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return true;
        }
        return false;
    }

    @FXML
    private Label label;
    @FXML
    private Button btnlogin;
    @FXML
    private TextField txtname;
    @FXML
    private Button btncancel;
    @FXML
    private ImageView img;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Hyperlink hLForgotPassword;
    @FXML
    private Label lblname;
    @FXML
    private Label lblpassword;
    @FXML
    private Hyperlink hlsignup;
    private StackPane SatckPane;
    @FXML
    private StackPane StackPane;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void txtname(ActionEvent event) {
        txtpassword.requestFocus();
    }

    @FXML
    private void btncancel(ActionEvent event) throws Exception {
        System.exit(0);
    }

    @FXML
    private void txtpassword(ActionEvent event) {
        btnlogin.requestFocus();
    }

    @FXML
    private void btnlogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        try {
            if (txtname.getText().trim().isEmpty() && txtpassword.getText().trim().isEmpty()) {
                lblname.setText("User Name is Empty");
                lblpassword.setText("Password is Empty");
            } else if (txtname.getText().trim().isEmpty()) {
                lblname.setText("User Name is Empty");
            } else if (txtpassword.getText().trim().isEmpty()) {
                lblpassword.setText("Password is Empty");
            }
            String username = txtname.getText();
            String Password = txtpassword.getText();
            User check = new User(username, Password);
            boolean checkUser;

            checkUser = LoginController.checkUser(check);
            if (checkUser) {
                Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Home.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) this.loginPane.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                infoBox("Login Successfull", null, "Success");
            } else {
                infoBox("Please Enter correct User Name and Password", null, "Failed");
                clearAll();
                txtname.requestFocus();
            }
        } catch (NullPointerException | ClassNotFoundException | IOException | SQLException nl) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, nl);
//        infoBox("Login Successfull", null, "Success");
//        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/Home.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) this.loginPane.getScene().getWindow();
//        stage.close();
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.setResizable(false);
//        stage.show();
        }
    }

    @FXML
    private void hLForgotPassword(MouseEvent event) {
        showDialog();
    }

    public void showDialog() {
        infoBox("", null,"It looks like you forgot your username or password,then you don't \n" 
                + "worry. Simply please get in touch with or knock on my office door and it will be happy to help." 
                + "\n Thank You, \n T.D.Sakuni Kalhari,\n Your Manager.");
//        Text title = new Text("Help With Forgotten Credentials");
//        title.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 13));
//        String content = "It looks like you forgot your username or password,\nthen you don't want"
//                + "worry. Simply please get in \ntouch with or knock on my office door and it will be \nhappy to help.\n\n"
//                + "Thanks\nT.D.Sakuni Kalhari, Your Manager";
//        JFXDialogLayout dialogContent = new JFXDialogLayout();
//        dialogContent.setHeading(title);
//        dialogContent.setPrefWidth(280);
//        dialogContent.setBody(new Text(content));
//        JFXButton close = new JFXButton("Close");
//        close.setButtonType(JFXButton.ButtonType.RAISED);
//        close.setStyle("-fx-background-color: #FF9A00; -fx-text-fill: white");
//        dialogContent.setActions(close);
//        JFXDialog dialog = new JFXDialog(SatckPane, dialogContent, JFXDialog.DialogTransition.TOP);
//        dialog.setOverlayClose(false);
//        close.setOnAction(event -> {
//            dialog.close();
//               });
//        dialog.show();
//        dialog.setOnDialogOpened(event -> loginPane.setEffect(new GaussianBlur(5d)));
//        dialog.setOnDialogClosed(event -> loginPane.setEffect(new GaussianBlur(0d)));
    }

    private void clearAll() {
        txtname.setText("");
        txtpassword.setText("");
    }

    private void infoBox(String infoMessage, String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    private void hlsignup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/moneylanding/view/User Registration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.loginPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void txtnameMouse(MouseEvent event) {
        lblname.setText("");
    }

    @FXML
    private void txtpasswordMouse(MouseEvent event) {
        if (txtname.getText().trim().isEmpty()) {
            lblname.setText("Email is Empty");
            lblpassword.setText("");
        } else {
            lblname.setText("");
        }
    }
}