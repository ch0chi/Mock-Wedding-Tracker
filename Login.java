package dbgui;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Created by Michael's Account on 11/14/2015.
 */
public class Login implements Initializable {

    //need to add a seperate class(like the employee class)

    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Label result;
    @FXML
    private Button populateList;

    @FXML private Button addCustomer;

    @FXML private TableView<Vendors> vendorsTable = new TableView();
    @FXML private TableColumn<Vendors,String> vendorName = new TableColumn();

    @FXML private TableColumn<Vendors, String> vendorID = new TableColumn();

    @FXML private TableView<Weddings> weddingTable = new TableView();

    @FXML private TableColumn<Weddings, String> weddingID;

    @FXML private TableColumn<Weddings, String> weddingName;

    @FXML private TableColumn<Weddings,String> weddingPrice;

    @FXML private TableColumn<Weddings,String> amountPaid;

    @FXML private TableColumn<Weddings,String> extraCosts;

    @FXML private TableView<Supplies> suppliesTable= new TableView();

    @FXML private TableColumn<Supplies, String> suppliesID;

    @FXML private TableColumn<Supplies, String> suppliesName;

    @FXML private TableView<Price> priceTable = new TableView();

    @FXML private TableColumn<Price, String> vendorIDPrice;

    @FXML private TableColumn<Price, String> suppliesIDPrice;

    @FXML private TableColumn<Price, String> price;

    @FXML private TableView<Employee> EmployeeTable = new TableView();

    @FXML private TableColumn<Employee, String> employeeID;

    @FXML private TableColumn<Employee, String> firstName;

    @FXML private TableColumn<Employee, String> lastName;

    @FXML private TableColumn<Employee, String> street;
    @FXML
    private TableView<WeddingDetails> weddingDetailTable;

    @FXML
    private TableColumn<WeddingDetails, String> weddingDetailSupplies;

    @FXML
    private TableColumn<WeddingDetails, String> amount;

    @FXML
    private TableColumn<WeddingDetails, String> vendNameDetail;
    @FXML
    private TableColumn<WeddingDetails,String> priceDetail;
    @FXML
    private TableColumn<WeddingDetails,String> totalDetail;


    @FXML
    private Button refreshWeddingDetail;

    @FXML
    private Label wName;



    @FXML
    private Label weddingCost;

    @FXML
    private Label paidFull;

    @FXML
    private Label notPaid;
    @FXML
    private Label wedDate;


    @FXML private TextField newFirstName;
    @FXML private TextField newLastName;
    @FXML private TextField newStreet;
    @FXML private TextField newCity;
    @FXML private Label alreadyInDatabase;
    @FXML private Tab vendorsTab;
    @FXML private Tab weddingTab;
    @FXML private Tab suppliesTab;
    @FXML private Tab priceTab;
    @FXML private Tab employeeTab;
    @FXML private TextField searchWeddings;
    @FXML private Button btnSearch;


    @FXML
    private String newCell;
    private EditDatabase edit;



    private Connection conn = null;
    private Connection connect = null;
    private ArrayList<String> ids;
    private ArrayList<String> fName;
    private ArrayList<String> lName;
    private ArrayList<String> cCity;
    private ArrayList<String> cStreet;
    private String usern;
    private String pass;
    private boolean loginAuth = false;
    private user user1 = new user();
    private user passUser;
    private static int lastID;
    private ResultSet results;
    private boolean checkIfExecutedProperly=false;
    private fetchTableData ftd;
    private Vendors v;
    private Supplies s;
    private Weddings w;
    private Employee e;
    private static String searchQuery = "";








    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Login() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        ftd = new fetchTableData();

    }

    public boolean setLogon() throws IOException, SQLException {
        usern = username.getText();
        user.setUserName(usern);
        pass = password.getText();
        user.setPassword(pass);
        try {
            String dbName = "db/database";
            String jdbcUrl = "jdbc:hsqldb:file:" + dbName;
            System.out.println(jdbcUrl);
            user.setJdbcUrl(jdbcUrl);
            conn = DriverManager.getConnection(user.getJdbcUrl(), user.getUserName(),
                    user.getPassword());
            dbgui.user.setConn(conn);
            System.out.println(conn);
            System.out.println("Connected to database as: " + usern);
            loginAuth = true;
            setAuth(true);

        } catch (SQLException ex) {
            System.err.println("Couldn't connect to database");
            username.clear();
            password.clear();
        }
        return loginAuth;
    }

    public void setAuth(boolean flag){
        this.loginAuth=flag;
    }
    public boolean getAuth(){
        return loginAuth;
    }




    public void newVendorView() throws IOException{
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("addVendor.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add New Vendor");
        stage.setScene(new Scene(viewDB));
        stage.show();
    }
    @FXML
    void displayNewVendorView(ActionEvent event) throws SQLException, IOException{
        newVendorView();
    }


    public void newSupplyView() throws IOException{
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("addSupplies.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add New Supply");
        stage.setScene(new Scene(viewDB));
        stage.show();
    }
    @FXML void displayNewSupplyView(ActionEvent event)throws SQLException,IOException{
        //need to add this to button
        newSupplyView();
    }


    public void newWeddingView() throws IOException{
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("newWedding.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add New Wedding");
        stage.setScene(new Scene(viewDB));
        stage.show();
    }
    @FXML public void displayNewWeddingView(ActionEvent event)throws SQLException,IOException{
        newWeddingView();
    }


    public void newEmployeeView() throws IOException {
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("addNewEmployee.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add New User");
        stage.setScene(new Scene(viewDB));
        stage.show();
    }

    @FXML
    void displayEmployeeView(ActionEvent event) throws SQLException, IOException {
        //add to fxml menu item
        newEmployeeView();
    }




    public void viewDatabase() throws IOException {
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("viewDatabase.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Database View");
        stage.setScene(new Scene(viewDB));
        stage.show();
        vendorsTable.setEditable(true);

    }

    public void viewUnpaidWeddings() throws IOException{
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("unpaidWedding.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Unpaid Weddings");
        stage.setScene(new Scene(viewDB));
        stage.show();
    }

    public void viewUnfinishedWeddings() throws IOException{
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("notFinishedWeddings.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Unfinished Weddings");
        stage.setScene(new Scene(viewDB));
        stage.show();
        ftd.fetchWeddingsData();
    }
    public void viewPriceCompare()throws IOException{
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("priceCompare.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Price Comparison");
        stage.setScene(new Scene(viewDB));
        stage.show();
    }

    public void viewWeddingDetails()throws IOException{
        Parent viewDB;
        viewDB = FXMLLoader.load(getClass().getResource("weddingDetails.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Wedding Details");
        stage.setScene(new Scene(viewDB));
        stage.show();
    }


    @FXML
    void buttonLogon() throws IOException, SQLException {
        if (setLogon()) {
            viewDatabase();

        } else {
            result.setText("Wrong username/password!");
        }
    }

    @FXML
    void getTableRefresh() throws SQLException, IOException {
        EmployeeTable.setItems(ftd.fetchEmployeeData());
        vendorsTable.setItems(ftd.fetchVendorData());
        weddingTable.setItems(ftd.fetchWeddingsData());
        suppliesTable.setItems(ftd.fetchSuppliesData());
        priceTable.setItems(ftd.fetchPriceData());
        editCell();

    }

    //called in the menu

    //called in the menu


    public Tab getCurrentTab() throws IOException, SQLException {
        if(vendorsTab.isSelected()){
            return vendorsTab;
        }else if(weddingTab.isSelected()){
            return weddingTab;
        }else if(suppliesTab.isSelected()){
            return suppliesTab;
        }else if(employeeTab.isSelected()){
            return employeeTab;
        }
        return vendorsTab;
    }

    public ObservableList<WeddingDetails> fetchWeddingDetails() throws IOException, SQLException {

        ObservableList<WeddingDetails> data = FXCollections.observableArrayList();


        String query = "SELECT  weddingName,weddingDate,weddingDate,suppliesname,vendorname,price, count(*) AS Iterations ,(price*count(*)) as ItemCostTotal,weddingprice,amountpaid FROM weddingsupplies JOIN price\n" +
                "ON  weddingsupplies.suppliesID=price.suppliesID JOIN weddings ON weddingsupplies.weddingid=weddings.weddingID JOIN supplies ON weddingSupplies.suppliesID=supplies.suppliesID JOIN vendors\n" +
                "ON price.vendorID=vendors.vendorID \n" +
                "GROUP BY weddingName,weddingDate,weddingID,suppliesID,suppliesname,vendorname,price,weddingprice,amountpaid,weddingDate\n" +
                "HAVING count(*)>0  AND weddingname IN(select weddingname FROM weddings WHERE weddingname LIKE '%"+getSelectedWeddingName()+"%')";
        PreparedStatement stmt;
        try {

            stmt = user.getConn().prepareStatement(query);
            //stmt.setString(1,getSelectedWeddingName());
            results = stmt.executeQuery();
            while(results.next()){
                String wedName=results.getString("weddingName");
                String supplyName = results.getString("suppliesName");
                String vendName = results.getString("vendorName");
                Date weddDate = results.getDate("weddingDate");
                Double weddPrice = results.getDouble("weddingprice");
                Double amtPaid = results.getDouble("amountpaid");
                Double prc = results.getDouble("price");
                int iterations = results.getInt("Iterations");
                int total = results.getInt("ItemCostTotal");
                System.out.println(prc);

                //convert variables to string for display
                String strWedPrice = Double.toString(weddPrice);
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                String strDate = df.format(weddDate);




                System.out.println(getSelectedWeddingName());




                WeddingDetails wd = new WeddingDetails(wedName,supplyName,prc,vendName,iterations,total,weddDate,weddPrice);
                wName.setText(wd.getWedName());
                weddingCost.setText(strWedPrice);
                wedDate.setText(strDate);


                //check if wedding cost is >= amount paid.
                if(amtPaid>weddPrice){
                    paidFull.setVisible(true);
                    notPaid.setVisible(false);
                }else if(weddPrice>amtPaid){
                    paidFull.setVisible(false);
                    notPaid.setVisible(true);

                }


                System.out.println(wd.getWedName());
                System.out.println(wd.getWedName());
                data.add(wd);



            }
            stmt.close();
            results.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return data;

    }


    @FXML public void searchWeddings(KeyEvent event) throws IOException, SQLException {



        if (event.getCode() == KeyCode.ENTER && getSelectedWeddingName() != null) {

            //getSelectedWeddingName();
            System.out.println(getSelectedWeddingName());
            //fetchWeddingDetails();
            weddingDetailTable.setItems(fetchWeddingDetails());



        }

    }
    @FXML public void searchWeddingsOnClick(MouseEvent event)throws IOException,SQLException{
        if(event.getSource()==btnSearch && getSelectedWeddingName()!=null){
            System.out.println(getSelectedWeddingName());
        }
    }


    public String getSelectedWeddingName() throws IOException,SQLException{
            searchQuery=searchWeddings.getText();
            return searchQuery;
    }




    @FXML
    void buttonDelete() throws IOException, SQLException {

        if(getCurrentTab()==vendorsTab){
            deleteVendorFromDatabase();
            getTableRefresh();
        }else if(getCurrentTab()==weddingTab){
            deleteWeddingFromDatabase();
            getTableRefresh();
        }else if(getCurrentTab()==suppliesTab){
            deleteSuppliesFromDatabase();
            getTableRefresh();
        }else if(getCurrentTab()==employeeTab){
            deleteEmployeeFromDatabase();
            getTableRefresh();
        }





    }
    public void deleteEmployeeFromDatabase() throws IOException, SQLException {

        e = EmployeeTable.getSelectionModel().getSelectedItem();

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM Employee WHERE EmployeeID=?;";
            stmt = user.getConn().prepareStatement(delete);
            stmt.setInt(1, e.getEmployeeId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteVendorFromDatabase() throws IOException, SQLException{
        v= vendorsTable.getSelectionModel().getSelectedItem();

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM Vendors WHERE VendorID=?;";
            stmt = user.getConn().prepareStatement(delete);
            System.out.println("lol");
            stmt.setInt(1, v.getID());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteSuppliesFromDatabase() throws IOException, SQLException{
        s = suppliesTable.getSelectionModel().getSelectedItem();


        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM Supplies WHERE SuppliesID=?;";
            stmt = user.getConn().prepareStatement(delete);
            stmt.setInt(1, s.getID());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWeddingFromDatabase() throws IOException,SQLException{
        w = weddingTable.getSelectionModel().getSelectedItem();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM Weddings WHERE WeddingID=?;";
            stmt = user.getConn().prepareStatement(delete);
            stmt.setInt(1, w.getWeddingID());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Edit cells

    public int setSelectRowID() throws IOException, SQLException {
        //get current selected cell
        if(getCurrentTab()==vendorsTab){
            v=vendorsTable.getSelectionModel().getSelectedItem();
            return v.getID();
        }else if(getCurrentTab()==weddingTab){
            w=weddingTable.getSelectionModel().getSelectedItem();
            return w.getWeddingID();
        }else if(getCurrentTab()==suppliesTab){
            s=suppliesTable.getSelectionModel().getSelectedItem();
            return s.getID();
        }else if(getCurrentTab()==employeeTab){
            e=EmployeeTable.getSelectionModel().getSelectedItem();
            return e.getEmployeeId();
        }
        return 6;
    }
    public void getSelectRowID(MouseEvent click) throws IOException, SQLException {
        setSelectRowID();

    }

    public String getNewCell() {

        return newCell;
    }
    public void setNewCell(String newCell){
        this.newCell=newCell;
    }

    public void editCell() throws IOException, SQLException {

        //calls the sql methods during to edit
        editVendorName();

    }

    //*******Edit Vendors********

    public void editVendorName() throws IOException, SQLException {
        this.vendorsTable.setEditable(true);
        Callback cellFactory = p -> new EditingCell();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }
        try
        {
            String update = "UPDATE Vendors SET VendorName=? WHERE VendorID=?;";
            final PreparedStatement stmt = user.getConn().prepareStatement(update);

            this.vendorName.setCellFactory(cellFactory);
            this.vendorName.setOnEditCommit(t -> {
                ((Vendors)t.getTableView().getItems().get(t
                        .getTablePosition().getRow()))
                        .setVendorName((String)t
                                .getNewValue());
                Login.this.setNewCell((String)t.getNewValue());
                try {
                    stmt.setString(1, Login.this.getNewCell());
                    stmt.setInt(2, Login.this.setSelectRowID());
                    stmt.executeUpdate();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                t.consume();
            });
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }

    }


    class EditingCell
            extends TableCell<Vendors, String>
    {
        private TextField textField;

        public EditingCell() {}

        public void startEdit()
        {
            super.startEdit();
            if (this.textField == null) {
                createTextField();
            }
            setGraphic(this.textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.textField.selectAll();
        }

        public void cancelEdit()
        {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        public void updateItem(String item, boolean empty)
        {
            super.updateItem(item, empty);
            if (empty)
            {
                setText(null);
                setGraphic(null);
            }
            else if (isEditing())
            {
                if (this.textField != null) {
                    this.textField.setText(getString());
                }
                setGraphic(this.textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
            else
            {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }

        private void createTextField()
        {
            this.textField = new TextField(getString());
            this.textField.setMinWidth(getWidth() - getGraphicTextGap() * 2.0D);
            this.textField.setOnKeyPressed(t -> {
                if (t.getCode() == KeyCode.ENTER) {
                    EditingCell.this.commitEdit(EditingCell.this.textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    EditingCell.this.cancelEdit();
                }
            });
        }

        private String getString()
        {
            return getItem() == null ? "" : ((String)getItem()).toString();
        }
    }



















}
