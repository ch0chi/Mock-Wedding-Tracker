package dbgui;

import javafx.beans.property.*;

/**
 * Created by Michael's Account on 12/6/2015.
 */
public class UnpaidWeddings {
    private SimpleIntegerProperty weddingID = new SimpleIntegerProperty(0);
    private SimpleStringProperty weddingName = new SimpleStringProperty("");
    private SimpleDoubleProperty weddingPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty amountPaid = new SimpleDoubleProperty();


    public IntegerProperty weddingIDProperty(){return weddingID;}
    public int getWeddingID(){return weddingIDProperty().get();}
    public void setWeddingID(int ID){weddingIDProperty().set(ID);}

    public StringProperty weddingNameProperty(){return weddingName;}
    public String getWeddingName(){return weddingNameProperty().get();}
    public void setWeddingName(String name){weddingNameProperty().set(name);}

    public DoubleProperty weddingPriceProperty(){return weddingPrice;}
    public Double getWeddingPrice(){return weddingPriceProperty().get();}
    public void setWeddingPrice(Double price){weddingPriceProperty().set(price);}

    public DoubleProperty amountPaidProperty(){return amountPaid;}
    public Double getWeddingAmountPaid(){return amountPaidProperty().get();}
    public void setWeddingAmountPaid(Double paid){amountPaidProperty().set(paid);}






    public UnpaidWeddings(){}

    public UnpaidWeddings(String name, Double wedPrice, Double amtPaid){

        setWeddingName(name);
        setWeddingPrice(wedPrice);
        setWeddingAmountPaid(amtPaid);


    }
}
