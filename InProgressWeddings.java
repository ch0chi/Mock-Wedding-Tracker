package dbgui;

import javafx.beans.property.*;

import java.sql.Date;

/**
 * Created by Michael's Account on 12/7/2015.
 */
public class InProgressWeddings {


    private SimpleObjectProperty<Date> weddingFinishDate = new SimpleObjectProperty<>();
    private SimpleStringProperty weddingName = new SimpleStringProperty("");





    public StringProperty weddingNameProperty(){return weddingName;}
    public String getWeddingName(){return weddingNameProperty().get();}
    public void setWeddingName(String name){weddingNameProperty().set(name);}

    public ObjectProperty weddingFinishProperty(){return weddingFinishDate;}
    public Date getWeddingFinishDate(){return weddingFinishDate.get();}
    public void setWeddingFinishDate(Date value){weddingFinishDate.set(value);}







    public InProgressWeddings(){}

    public InProgressWeddings(String name,Date date){
        setWeddingName(name);
        setWeddingFinishDate(date);
    }
}
