package dbgui;

import com.sun.javafx.beans.IDProperty;
import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Michael's Account on 12/2/2015.
 */
public class Vendors {
    private SimpleIntegerProperty vendorID = new SimpleIntegerProperty(0);
    private SimpleStringProperty vendorName = new SimpleStringProperty("");

    public IntegerProperty vendorIDProperty(){return vendorID;}
    public int getID(){
        return vendorIDProperty().get();
    }
    public void setID(int ID){vendorIDProperty().set(ID);}

    public StringProperty vendorNameProperty(){return vendorName;}
    public String getVendorName(){return vendorNameProperty().get();}
    public void setVendorName(String vendor){vendorNameProperty().set(vendor);}


    public Vendors(){}

    public Vendors(int Id, String vendor){
        setID(Id);
        setVendorName(vendor);
    }
}
