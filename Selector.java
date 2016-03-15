package dbgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import java.lang.Math.*;
import java.lang.Integer;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Selector {

    private ResultSet results;

    private fetchTableData ftd;
    private Weddings w;
    private PriceCompare prc;
    private int vendorID;
    private int itemCost;
    private int totalItemCost;
    private Random rn;

    public Selector(){
        ftd = new fetchTableData();
        w = new Weddings();
        prc = new PriceCompare();
        vendorID=0;
        itemCost=0;
        totalItemCost=0;

    }
    @FXML
    private TableView<PriceCompare> priceCompare;

    @FXML
    private TableColumn<PriceCompare, String> vendName;

    @FXML
    private TableColumn<PriceCompare, String> itemName;

    @FXML
    private TableColumn<PriceCompare, String> cost;
    @FXML
    private Label displayComparison;
    @FXML
    private Label compareInstructions;
    @FXML
    private Label getVendorName;
    @FXML
    private Label dollarSign;

    @FXML
    private TableView<UnpaidWeddings> selectUnpaidWedding;

    @FXML
    private TableColumn<UnpaidWeddings, String> weddingName;

    @FXML
    private TableColumn<UnpaidWeddings, String> weddingPrice;

    @FXML
    private TableColumn<UnpaidWeddings, String> totalPaid;
    @FXML
    private TableView<InProgressWeddings> unfinishedWeddings;

    @FXML
    void getTableRefresh() throws IOException {
        selectUnpaidWedding.setItems(unPaidWeddings());

    }
    @FXML
    void refreshPriceCompare()throws IOException{
        priceCompare.setItems(fetchPriceCompare());
    }
    @FXML void getInProgressWeddings() throws IOException {
        fetchTableData ftd = new fetchTableData();
        ftd.fetchWeddingsData();
        unfinishedWeddings.setItems(notDoneWeddings());

    }

    public Date fetchCurrentDate() throws IOException {
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ftd.fetchWeddingsData();

        return currentDate;

    }


    public ObservableList<PriceCompare> fetchPriceCompare()throws IOException{
        ObservableList<PriceCompare> data = FXCollections.observableArrayList();
        String query = "SELECT v.vendorname, s.SUPPLIESNAME, p.price FROM vendors v, supplies s, price p where v.vendorid = p.vendorid and s.suppliesid = p.suppliesid;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {
                String vendorName = results.getString("VENDORNAME");
                String suppliesName = results.getString("SUPPLIESNAME");
                Double price = results.getDouble("Price");


                PriceCompare pc = new PriceCompare(vendorName, suppliesName, price);

                data.add(pc);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @FXML public void disableInstructions(MouseEvent event)throws IOException{




        if(event.getSource()==priceCompare.getSelectionModel().getTableView()){
            compareInstructions.setVisible(false);
            dollarSign.setVisible(true);
        }

    }
    //listens for the currently selected cell and displays cheapest item in the box to the right.
    public void getSelectedItemName(MouseEvent event) throws IOException,SQLException{
            //use further down in code displayComparison.setText(priceString);
        prc=priceCompare.getSelectionModel().getSelectedItem();
        String stringOld = prc.getSuppliesNames();
            //need to call the supply name then compare currently selected supply with an item with the same name. Then, ge
        if(event.getSource()==priceCompare.getSelectionModel().getTableView()){
            compareInstructions.setVisible(false);

        }



        PreparedStatement stmt;
        String updateQuery = "SELECT price.price, vendors.vendorname FROM Price JOIN vendors ON price.vendorID=vendors.vendorID WHERE suppliesID IN(SELECT suppliesID FROM Vendors) AND suppliesID IN (SELECT suppliesID FROM supplies WHERE suppliesName='"+stringOld+"' AND Price != ?)";
        try {

            stmt = user.getConn().prepareStatement(updateQuery);


            System.out.println(stringOld);
            Double priceOld = prc.getVendorsPrice();
            //stmt.setString(1,stringOld);
            stmt.setDouble(1,priceOld);
            results = stmt.executeQuery();

            while(results.next()){
                String vendorN = results.getString("vendorname");
                Double priceNew = results.getDouble("price");
                if(priceOld>priceNew){
                    String getString = Double.toString(priceNew);
                    displayComparison.setText(getString);
                }else if(priceOld<priceNew){
                    String strPriceOld = Double.toString(priceOld);
                    displayComparison.setText(strPriceOld);
                }

                getVendorName.setText(vendorN);
            }
            stmt.close();
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error!");
        }










    }




    public ObservableList<UnpaidWeddings> unPaidWeddings() throws IOException {
        System.out.println(fetchCurrentDate());
        ObservableList<UnpaidWeddings> data = FXCollections.observableArrayList();
        String query = "SELECT WeddingName,WeddingPrice,AmountPaid FROM Weddings WHERE amountpaid < weddingprice;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {


                String weddingName = results.getString("weddingName");
                Double weddingPrice = results.getDouble("weddingPrice");
                Double amountPaid = results.getDouble("amountPaid");
                UnpaidWeddings w = new UnpaidWeddings(weddingName,weddingPrice,amountPaid);

                data.add(w);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ObservableList<InProgressWeddings> notDoneWeddings() throws IOException {
        ObservableList<InProgressWeddings> data = FXCollections.observableArrayList();
        String query = "SELECT WeddingName,WeddingDate FROM Weddings WHERE CURDATE()<WeddingDate;";
        try {
            Statement stat = user.getConn().createStatement();
            results = stat.executeQuery(query);
            while (results.next()) {
                    String weddingName = results.getString("weddingName");
                    Date weddingDate = results.getDate("weddingDate");
                    InProgressWeddings inProg = new InProgressWeddings(weddingName,weddingDate);
                    data.add(inProg);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public float profitAndLoss(String weddingName){
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Couldn't load the driver class.");
            System.exit(1);
        }

        PreparedStatement stmt;
        float profit = 0;
        String updateQuery = "select i.amountSold as aSold, p.price as price from price p, ";
        updateQuery += "itemsSold i where i.weddingid in (select weddingid from weddings where weddingName = ?)";
        try {
            stmt = user.getConn().prepareStatement(updateQuery);
            stmt.setString(1, weddingName);
            ResultSet rSet = stmt.executeQuery();
            float totalPricePaid = 0;
            while(rSet.next()){
                totalPricePaid += rSet.getInt("aSold") * rSet.getInt("price");
            }
            updateQuery = "select price from weddings where weddingName = ?";
            stmt = user.getConn().prepareStatement(updateQuery);
            stmt.setString(1, weddingName);
            rSet = stmt.executeQuery();
            float weddingPrice = 0;
            while(rSet.next()){
                weddingPrice = rSet.getFloat("price");
            }
            profit = weddingPrice - totalPricePaid;
            System.out.println(profit);
            rSet.close();
            stmt.close();
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return profit;
    }

    public void getProfitAndLoss()throws IOException,SQLException{
        //SELECT weddingID,suppliesID,count(*)/3 AS Iterations FROM weddingsupplies GROUP BY weddingID,suppliesID
        //HAVING count(*)>1  AND  weddingid IN(select weddingID FROM weddingsupplies
                //WHERE weddingid=2)

        //displays the suppliesID,weddingID,vendorID,and how many of them are need
        /*
        SELECT weddingID,suppliesID,vendorID,price, count(*)/3 AS Iterations FROM weddingsupplies JOIN price
ON  weddingsupplies.suppliesID=price.suppliesID GROUP BY weddingID,suppliesID,vendorID
HAVING count(*)>1  AND  weddingid IN(select weddingID FROM weddingsupplies
WHERE weddingid=2)

*******final query!!!!*******
* SELECT  weddingName,weddingDate,weddingID,suppliesname,vendorID,price, count(*)/3 AS Iterations ,(price*count(*)/3) as total FROM weddingsupplies JOIN price
ON  weddingsupplies.suppliesID=price.suppliesID JOIN weddings ON weddingsupplies.weddingid=weddings.weddingID JOIN supplies ON weddingSupplies.suppliesID=supplies.suppliesID
GROUP BY weddingName,weddingDate,weddingID,suppliesID,suppliesname,vendorID,price
HAVING count(*)>1  AND  weddingid IN(select weddingID FROM weddingsupplies
WHERE weddingid=0) AND suppliesID IN(select suppliesID FROM weddingsupplies WHERE suppliesID=16)
         */



    }

    public void randomVendor(int suppliesID){
        if(Math.random()<0.5 && suppliesID==0){
            setRandomVendor(5);
        }else{
            setRandomVendor(8);
        }
        if(Math.random()<0.5 && suppliesID==1){
            setRandomVendor(1);
        }else{
            setRandomVendor(10);
        }
        if(Math.random()<0.5 && suppliesID==2){
            setRandomVendor(3);
        }else{
            setRandomVendor(4);
        }
        if(Math.random()<0.5 && suppliesID==3){
            setRandomVendor(2);
        }else{
            setRandomVendor(3);
        }
        if(Math.random()<0.5 && suppliesID==4){
            setRandomVendor(11);
        }else{
            setRandomVendor(2);
        }
        if(Math.random()<0.5 && suppliesID==5){
            setRandomVendor(2);
        }else{
            setRandomVendor(11);
        }
        if(Math.random()<0.5 && suppliesID==6){
            setRandomVendor(5);
        }else{
            setRandomVendor(8);
        }

    }
    public void setRandomVendor(int vendID){
        this.vendorID=vendID;
    }

    public int getRandomVendor(){
        return vendorID;
    }



}