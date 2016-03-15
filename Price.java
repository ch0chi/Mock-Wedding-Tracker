package dbgui;

import javafx.beans.property.*;

/**
 * Created by Michael's Account on 12/3/2015.
 *
 * The Price.class serves as a medium between Login.java and viewDatabase.fxml.
 * Price.java acts as the backbone for the price tableview.
 */
public class Price {

    private Vendors v = new Vendors();
    private Supplies s=new Supplies();

    private SimpleIntegerProperty vendorsSuppliesID = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty suppliesSoldID = new SimpleIntegerProperty();
    private SimpleDoubleProperty priceVendorsSupplies = new SimpleDoubleProperty();

    public IntegerProperty vendorsSuppliesIDProperty(){return vendorsSuppliesID;}
    public int getVendorsSuppliesID(){return vendorsSuppliesIDProperty().get();}
    public void setVendorsSuppliesID(int vendID){vendorsSuppliesIDProperty().set(vendID);}

    public IntegerProperty suppliesSoldProperty(){return suppliesSoldID;}
    public int getSuppliesSoldID(){return suppliesSoldProperty().get();}
    public void setSuppliesSoldID(int supplyID){suppliesSoldProperty().set(supplyID);}

    public DoubleProperty priceVendorsSuppliesProperty(){return priceVendorsSupplies;}
    public Double getVendorsPrice(){return priceVendorsSuppliesProperty().get();}
    public void setVendorsPrice(Double price){priceVendorsSuppliesProperty().set(price);}




    public Price(){}

    public Price(int vendSuppliesID, int supplySold, Double p){
        setVendorsSuppliesID(vendSuppliesID);
        setSuppliesSoldID(supplySold);
        setVendorsPrice(p);

    }
}
