package dbgui;

import javafx.beans.property.*;

/**
 * Created by Michael's Account on 12/6/2015.
 *
 * Shows cheapest price
 */
public class Compare {

    private Vendors v = new Vendors();
    private Supplies s=new Supplies();

    private SimpleStringProperty vendorsName= new SimpleStringProperty("");
    private SimpleStringProperty suppliesName = new SimpleStringProperty();
    private SimpleDoubleProperty priceOriginal = new SimpleDoubleProperty();
    private SimpleDoubleProperty cheapest = new SimpleDoubleProperty();

    public StringProperty vendorsNameProperty(){return vendorsName;}
    public String getVendorsNames(){return vendorsNameProperty().get();}
    public void setVendorsNames(String vendID){vendorsNameProperty().set(vendID);}

    public StringProperty suppliesNameProperty(){return suppliesName;}
    public String getSuppliesName(){return suppliesNameProperty().get();}
    public void setSuppliesName(String supplyName){suppliesNameProperty().set(supplyName);}

    public DoubleProperty priceOriginalProperty(){return priceOriginal;}
    public Double getOriginalPrice(){return priceOriginalProperty().get();}
    public void setOriginalPrice(Double price){priceOriginalProperty().set(price);}

    public DoubleProperty priceCheapestProperty(){return cheapest;}
    public Double getPriceCheapestProperty(){return priceCheapestProperty().get();}
    public void setCheapestPrice(Double cheapestP){priceCheapestProperty().set(cheapestP);}




    public Compare(){}

    public Compare(String vendNames, String supplyNames, Double originalPrice, Double cheapest){
        setVendorsNames(vendNames);
        setSuppliesName(supplyNames);
        setOriginalPrice(originalPrice);
        setCheapestPrice(cheapest);


    }
}
