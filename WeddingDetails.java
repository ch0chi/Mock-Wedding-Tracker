package dbgui;

import javafx.beans.property.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

/**
 * Created by Michael's Account on 12/9/2015.
 */
public class WeddingDetails {


    private SimpleStringProperty suppName = new SimpleStringProperty();
    private SimpleObjectProperty<Date> wedDate = new SimpleObjectProperty<>();
    private SimpleStringProperty wedName = new SimpleStringProperty("");
    private SimpleDoubleProperty priceVendor = new SimpleDoubleProperty();
    //private SimpleDoubleProperty amountPaid = new SimpleDoubleProperty();
    private SimpleStringProperty vendName = new SimpleStringProperty();
    private SimpleIntegerProperty itemCount = new SimpleIntegerProperty();
    private SimpleIntegerProperty total = new SimpleIntegerProperty();
    private SimpleDoubleProperty wedPrice = new SimpleDoubleProperty();







    public StringProperty vendNameProperty(){return vendName;}
    public String getVendName(){return vendNameProperty().get();}
    public void setVendName(String name){vendNameProperty().set(name);}

    public StringProperty suppNameProperty(){return suppName;}
    public String getSuppName(){return suppNameProperty().get();}
    public void setSuppName(String name){suppNameProperty().set(name);}

    public StringProperty wedNameProperty(){return wedName;}
    public String getWedName(){return wedNameProperty().get();}
    public void setWedName(String name){wedNameProperty().set(name);}

    public ObjectProperty wedDateProperty(){return wedDate;}
    public Date getWedDate(){return wedDate.get();}
    public void setWedDate(Date value){wedDate.set(value);}

    public IntegerProperty itemCountProperty(){return itemCount;}
    public int getitemCount(){return itemCountProperty().get();}
    public void setitemCount(int item){itemCountProperty().set(item);}

    public IntegerProperty totalProperty() {return total;}
    public int getTotal(){return totalProperty().get();}
    public void setTotal(int total){totalProperty().set(total);}

    public DoubleProperty wedPriceProperty(){return wedPrice;}
    public Double getWedPrice(){return priceVendorProperty().get();}
    public void setWedPrice(Double price){wedPriceProperty().set(price);}

    public DoubleProperty priceVendorProperty(){return priceVendor;}
    public Double getPriceVendor(){return priceVendorProperty().get();}
    public void setPriceVendor(Double price){priceVendorProperty().set(price);}
/*
    public DoubleProperty amountPaidProperty(){return amountPaid;}
    public Double getWeddingAmountPaid(){return amountPaidProperty().get();}
    public void setWeddingAmountPaid(Double paid){amountPaidProperty().set(paid);}

    public DoubleProperty extraCostsProperty(){return extraCosts;}
    public Double getExtraCosts(){return extraCostsProperty().get();}
    public void setExtraCosts(Double xtraCosts){extraCostsProperty().set(xtraCosts);}
    */





    public WeddingDetails(){}

    public WeddingDetails(String wedName, String supplyName,Double price, String vendName,int iCount,int total,Date date,Double wedPrice){
        setWedName(wedName);
        setWedDate(date);
        setSuppName(supplyName);
        setPriceVendor(price);
        setVendName(vendName);
        setitemCount(iCount);
        setTotal(total);
        setWedPrice(wedPrice);




    }
}
