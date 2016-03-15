package dbgui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Michael's Account on 12/3/2015.
 * This class functions as a polymorphism medium for viewDatabase.fxml and Login.
 * The class name "Supplies" is used to get tableview properties from viewDatabase.fxml
 */
public class Supplies {

    private SimpleIntegerProperty suppliesID = new SimpleIntegerProperty(0);
    private SimpleStringProperty suppliesName = new SimpleStringProperty("");

    public IntegerProperty suppliesIDProperty(){return suppliesID;}
    public int getID(){return suppliesIDProperty().get();}
    public void setID(int ID){suppliesIDProperty().set(ID);}

    public StringProperty suppliesNameProperty(){return suppliesName;}
    public String getSuppliesName(){return suppliesNameProperty().get();}
    public void setSuppliesName(String name){suppliesNameProperty().set(name);}


    public Supplies(){}

    public Supplies(int Id, String supplies){
        setID(Id);
        setSuppliesName(supplies);
    }
}
