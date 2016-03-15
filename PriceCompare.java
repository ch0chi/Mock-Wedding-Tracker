package dbgui;

import javafx.beans.property.*;

/**
 * Created by Michael's Account on 12/7/2015.
 */
public class PriceCompare {
    private SimpleStringProperty vendorsNames = new SimpleStringProperty();
    private SimpleStringProperty suppliesNames = new SimpleStringProperty();
    private SimpleDoubleProperty priceVendors = new SimpleDoubleProperty();


    public StringProperty vendorsNamesProperty(){return vendorsNames;}
    public String getVendorsNames(){return vendorsNamesProperty().get();}
    public void setVendorsNames(String vendName){vendorsNamesProperty().set(vendName);}

    public StringProperty suppliesNamesProperty(){return suppliesNames;}
    public String getSuppliesNames(){return suppliesNamesProperty().get();}
    public void setSuppliesNames(String supplyName){suppliesNamesProperty().set(supplyName);}

    public DoubleProperty priceVendorsProperty(){return priceVendors;}
    public Double getVendorsPrice(){return priceVendorsProperty().get();}
    public void setVendorsPrice(Double price){priceVendorsProperty().set(price);}




    public PriceCompare(){}

    public PriceCompare(String vName,String sName, Double p){
        setVendorsNames(vName);
        setSuppliesNames(sName);
        setVendorsPrice(p);

    }
}
