package dbgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Michael's Account on 12/3/2015.
 */
public class EditDatabase {
    private user user1;
    private fetchTableData ftd;
    @FXML private TextField newVendorName;
    @FXML private TextField newSuppliesName;
    @FXML private TextField newWeddingName;
    @FXML private DatePicker newDate;
    @FXML private TextField newWeddingPrice;
    @FXML private TextField newAmountPaid;
    @FXML private TextField newEmployeeFirstName;
    @FXML private TextField newEmployeeLastName;
    @FXML private TextField newEmployeeStreet;
    @FXML private TextField newEmployeeCity;
    @FXML private TableView<Employee> EmployeeTable;
    @FXML private TableView<Vendors> vendorsTable;
    @FXML private TableView<Supplies> suppliesTable;
    @FXML private TableView<Weddings> weddingsTable;



    private Login l;
    private Employee e;
    private Vendors v;
    private Supplies s;
    private Weddings w;
    private boolean checkIfPaid;
    private Double newAmt;




    public EditDatabase(){
        ftd = new fetchTableData();
        l = new Login();
        e = new Employee();
        v = new Vendors();
        s = new Supplies();
        w = new Weddings();



    }


    public void addVendorToDatabase(){
        v = new Vendors();
        int id = ftd.getLastVendorID() + 1;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        PreparedStatement stmt;
        String updateQuery = "INSERT INTO Vendors(VendorID,VendorName) values(?,?)";
        try {

            stmt = user.getConn().prepareStatement(updateQuery);
            String vendName = newVendorName.getText();
            stmt.setInt(1, id);
            stmt.setString(2,vendName );
            stmt.executeUpdate();
            newVendorName.clear();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("error!");
        }
    }

    @FXML
    void getNewVendor() throws IOException, SQLException{
        addVendorToDatabase();
    }

    public void addWeddingToDatabase(){
        w = new Weddings();
        int id = ftd.getLastWeddingsID() + 1;
        System.out.println(id);
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        PreparedStatement stmt;
        String updateQuery = "INSERT INTO Weddings(WeddingID,WeddingName,WeddingDate,WeddingPrice,AmountPaid) values(?,?,?,?,?)";
        try {

            stmt = user.getConn().prepareStatement(updateQuery);

            String wedName = newWeddingName.getText();
            LocalDate localWedDate = newDate.getValue();
            Date wedDate = Date.valueOf(localWedDate);
            String strWedPrice = newWeddingPrice.getText();
            Double wedPrice = Double.parseDouble(strWedPrice);
            String strAmtPaid = newAmountPaid.getText();
            System.out.println(strAmtPaid);
            Double amtPaid = Double.parseDouble(strAmtPaid);


            //check to see if the client has paid. if they haven't, enter 0.0 enter the amtPaid


            stmt.setInt(1, id);
            stmt.setString(2, wedName);
            stmt.setDate(3,wedDate);
            stmt.setDouble(4, wedPrice);
            stmt.setDouble(5,amtPaid);
            stmt.executeUpdate();
            newWeddingName.clear();
            newWeddingPrice.clear();
            newAmountPaid.clear();

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error!");
        }
    }





    @FXML void getNewWedding() throws IOException,SQLException{
        addWeddingToDatabase();
    }

    public void addEmployeeToDatabase(){
         e = new Employee();
        int id = ftd.getLastEmployeeID() + 1;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }

        PreparedStatement stmt;

        String updateQuery = "INSERT INTO EMPLOYEE(EMPLOYEEID,FIRSTNAME,LASTNAME,STREET,CITY) values(?,?,?,?,?)";
        try {
            stmt = user.getConn().prepareStatement(updateQuery);
            String newFN = newEmployeeFirstName.getText();
            String newLN = newEmployeeLastName.getText();
            String newSt = newEmployeeStreet.getText();
            String newC = newEmployeeCity.getText();
            stmt.setInt(1, id);
            stmt.setString(2, newFN);
            stmt.setString(3, newLN);
            stmt.setString(4, newSt);
            stmt.setString(5, newC);
            stmt.executeUpdate();
            newEmployeeFirstName.clear();
            newEmployeeLastName.clear();
            newEmployeeStreet.clear();
            newEmployeeCity.clear();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("error!");
        }
    }

    @FXML
    void getNewEmployee() throws IOException, SQLException {
        //button on fxml that calls method
        addEmployeeToDatabase();
    }

    public void addNewSuppliesToDatabase(){
        s = new Supplies();
        int id = ftd.getLastSuppliesID() + 1;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        PreparedStatement stmt;
        String updateQuery = "INSERT INTO SUPPLIES(SUPPLIESID,SUPPLIESName) values(?,?)";
        try {

            stmt = user.getConn().prepareStatement(updateQuery);
            String supplyName = newSuppliesName.getText();
            stmt.setInt(1, id);
            stmt.setString(2,supplyName);
            stmt.executeUpdate();
            newSuppliesName.clear();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("error!");
        }
    }
    @FXML void getNewSupplies()throws IOException,SQLException{
        addNewSuppliesToDatabase();
    }


    //delete



}
