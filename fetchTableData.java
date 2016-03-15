package dbgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * Created by Michael's Account on 12/2/2015.
 *
 * The function of this class is to execute sql queries for every table in the database and
 * return an ObservableList<TableName> for each table. user instantiates the static final object of user.
 * This passes the current user connection to the specified SQL query.
 *
 * The setLastID and getLastID methods set the lastID of the table for use in adding/deleting and object of the table to the database.
 * This prevents duplicate id's from potentially throwing an error.
 */
public class fetchTableData {

    private ObservableList<Employee> employeesList;
    private ObservableList<Vendors> vendorsList;
    //private user1;
    private ResultSet results;
    private Login l;
    private static int lastVendorID;
    private static int lastEmployeeID;
    private static int lastSuppliesID;
    private static int lastWeddingsID;


    public fetchTableData() {
        //l = new Login();

    }
    public ObservableList<Employee> fetchEmployeeData() throws IOException {
        ObservableList<Employee> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Employee;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {
                int cID = results.getInt("employeeID");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String street = results.getString("street");
                String city = results.getString("city");
                Employee c = new Employee(cID, firstName, lastName, street, city);
                setLastEmployeeID(cID);
                data.add(c);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ObservableList<Vendors> fetchVendorData() throws IOException {
        ObservableList<Vendors> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Vendors;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {
                int vID = results.getInt("vendorID");
                String vendorName = results.getString("vendorName");
                Vendors v = new Vendors(vID, vendorName);
                setLastVendorID(vID);
                data.add(v);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }


    public ObservableList<Weddings> fetchWeddingsData() throws IOException {
        ObservableList<Weddings> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Weddings;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {
                int wID = results.getInt("weddingID");
                String weddingName = results.getString("weddingName");
                Date weddingDate = results.getDate("weddingDate");
                Double weddingPrice = results.getDouble("weddingPrice");
                Double amountPaid = results.getDouble("amountPaid");
                Double xtraCost = results.getDouble("extraCosts");
                Weddings w = new Weddings(wID,weddingName,weddingDate,weddingPrice,amountPaid,xtraCost);
                setLastWeddingsID(wID);
                data.add(w);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ObservableList<Supplies> fetchSuppliesData() throws IOException {
        ObservableList<Supplies> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Supplies;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {
                int sID = results.getInt("suppliesID");
                String suppliesName = results.getString("suppliesName");
                Supplies s = new Supplies(sID,suppliesName);
                setLastSuppliesID(sID);
                data.add(s);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ObservableList<Price> fetchPriceData() throws IOException {
        ObservableList<Price> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Price;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {
                int vID = results.getInt("vendorID");
                int sID = results.getInt("suppliesID");
                Double price = results.getDouble("Price");
                Price p = new Price(vID,sID,price);

                data.add(p);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public  void setLastVendorID(int fetchLast) {fetchTableData.lastVendorID = fetchLast;}

    public  int getLastVendorID() {
        return lastVendorID;
    }

    public  void setLastEmployeeID(int fetchLast) {fetchTableData.lastEmployeeID = fetchLast;}

    public  int getLastEmployeeID() {
        return lastEmployeeID;
    }

    public  void setLastWeddingsID(int fetchLast) {fetchTableData.lastWeddingsID = fetchLast;}

    public  int getLastWeddingsID() {
        return lastWeddingsID;
    }

    public  void setLastSuppliesID(int fetchLast) {fetchTableData.lastSuppliesID = fetchLast;}

    public  int getLastSuppliesID() {
        return lastSuppliesID;
    }










}
