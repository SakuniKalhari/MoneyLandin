package lk.ijse.moneylanding.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.moneylanding.db.DBConnection;
import lk.ijse.moneylanding.model.Customer;
import lk.ijse.moneylanding.tableModel.AmountTM;

/**
 * FXML Controller class
 *
 * @author ~saku~
 */
public class CustomerDetailController implements Initializable {
    
    int selectcid=0;
    private static ArrayList<Customer> searchCustomer(String nic) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Select * From Customer where nic='" + nic + "'";
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<Customer> cus=new ArrayList<>();
        while (rst.next()) {
            Customer c = new Customer(rst.getString("nic"), rst.getString("name"), rst.getString("address"), rst.getString("contact"), rst.getDouble("amount"), rst.getString("date"));
            cus.add(c);
        }
        return cus;
    }
    
    @FXML
    private Label lblomount;
    
        public ObservableList<String> fillcomboBox() throws ClassNotFoundException, SQLException {
        try {
            String sql = "select dailyamount from dailyamount";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ObservableList<String> option1 = FXCollections.observableArrayList();
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                option1.add(rst.getString("dailyamount"));
            }
            return option1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CustomerDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private TextField txtid;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtcontact;
    @FXML
    private TextField txtdate;
    @FXML
    private Button btnback;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private JFXComboBox<Double> cmbamount;
    @FXML
    private JFXTextArea txtaddress;
    @FXML
    private JFXTextField txtnic;
    @FXML
    private TableView<AmountTM> tblamount;

    ObservableList<Double> optionl = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
     ObservableList<Double> amount1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblamount.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblamount.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        
        tblamount.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("dailyamount"));
        tblamount.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
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
    private void txtnic(ActionEvent event) throws ClassNotFoundException, SQLException {
        String nic = txtnic.getText();

       // Customer customer = CustomerDetailController.searchCustomer(nic);
        ArrayList<Customer> cus=CustomerDetailController.searchCustomer(nic);
        if (cus != null) {
            txtname.setText(cus.get(0).getName());
            txtaddress.setText(cus.get(0).getAddress());
            txtcontact.setText(cus.get(0).getContact());
            txtdate.setText(cus.get(0).getDate());
            amount1=FXCollections.observableArrayList();
            for(int i=0;i<cus.size();i++){
                amount1.add(cus.get(i).getAmount());
            }            
            cmbamount.setItems(amount1);
        }
    }
    
    private void loadSelectDetails(int cid) throws ClassNotFoundException, SQLException {
        String dailyamount;
        String date;

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM dailyamount where cid="+cid);
        ResultSet rst = pst.executeQuery();
      
        ArrayList<AmountTM> allam = new ArrayList<>();
        while (rst.next()) {
            dailyamount =Double.toString(rst.getDouble(3));
            date = rst.getString(4);
            AmountTM am = new AmountTM(dailyamount,date);
            allam.add(am);            
        }
       // System.out.println(allam.get(0).getDailyamount()+" dskfjds");
        tblamount.setItems(FXCollections.observableArrayList(allam));
    }

    
    public int cidamount(Double amount) throws ClassNotFoundException, SQLException {
        try {
            String sql = "select * from customer where amount="+amount;
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
               return rst.getInt(1);              
            }
        }catch (Exception ex) {
           // Logger.getLogger(CustomerDetailController.class.getName()).log(Level.SEVERE, null, ex);
          // System.out.println("Exception in cidamount");
        }
        return -1;
    }

    @FXML
    private void cmbAmountAc(ActionEvent event) {          
        Double amount=cmbamount.getSelectionModel().getSelectedItem();
        Double payed=0.0;
        try{
            selectcid=cidamount(amount);
            try {
           // System.out.println(cmbamount.getSelectionModel().getSelectedItem());
            //Double output =1000.00;// 
            loadSelectDetails(selectcid);
            payed=DailyamountSum();
            if(payed==amount){
                infoBox("", null, "Full Paid");
            }
            } catch (Exception ex) {
           // System.out.println("exception");
        }
        }catch(Exception ex){
            //System.out.println("cidamount calling exception");
        }
    }
    
    private Double DailyamountSum() throws ClassNotFoundException, SQLException {        
        Double dailyamount=0.0;
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM dailyamount where cid="+selectcid);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            dailyamount +=rst.getDouble(3);;            
        }        
        lblomount.setText(Double.toString(dailyamount));
        return dailyamount;
    }
    
    private void infoBox(String infoMessage, String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }    
}
