package dbgui;

import javafx.beans.property.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
/**
 * Created by Michael's Account on 12/2/2015.
 * This class is called via fxml to associate the Property Value Factory with the table columns associated id
 *
 * weddingIDProperty gets and stores the wedding id's from the sql database
 * weddingNameProperty gets and stores the wedding names from the sql database
 * weddingPriceProperty gets and stores the wedding price from the sql database
 */
public class Weddings {



    private SimpleIntegerProperty weddingID = new SimpleIntegerProperty(0);
    private SimpleObjectProperty<Date> weddingDate = new SimpleObjectProperty<>();
    private SimpleStringProperty weddingName = new SimpleStringProperty("");
    private SimpleDoubleProperty weddingPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty amountPaid = new SimpleDoubleProperty();
    private SimpleDoubleProperty extraCosts = new SimpleDoubleProperty();


    public IntegerProperty weddingIDProperty(){return weddingID;}
    public int getWeddingID(){return weddingIDProperty().get();}
    public void setWeddingID(int ID){weddingIDProperty().set(ID);}

    public StringProperty weddingNameProperty(){return weddingName;}
    public String getWeddingName(){return weddingNameProperty().get();}
    public void setWeddingName(String name){weddingNameProperty().set(name);}

    public ObjectProperty weddingDateProperty(){return weddingDate;}
    public Date getWeddingDate(){return weddingDate.get();}
    public void setWeddingDate(Date value){weddingDate.set(value);}

    public DoubleProperty weddingPriceProperty(){return weddingPrice;}
    public Double getWeddingPrice(){return weddingPriceProperty().get();}
    public void setWeddingPrice(Double price){weddingPriceProperty().set(price);}

    public DoubleProperty amountPaidProperty(){return amountPaid;}
    public Double getWeddingAmountPaid(){return amountPaidProperty().get();}
    public void setWeddingAmountPaid(Double paid){amountPaidProperty().set(paid);}

    public DoubleProperty extraCostsProperty(){return extraCosts;}
    public Double getExtraCosts(){return extraCostsProperty().get();}
    public void setExtraCosts(Double xtraCosts){extraCostsProperty().set(xtraCosts);}






    public Weddings(){}

    public Weddings(int Id, String name,Date date, Double wedPrice, Double amtPaid,Double xtraCosts){
        setWeddingID(Id);
        setWeddingName(name);
        setWeddingDate(date);
        setWeddingPrice(wedPrice);
        setWeddingAmountPaid(amtPaid);
        setExtraCosts(xtraCosts);


    }
}
