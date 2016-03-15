package dbgui;

import javafx.beans.property.*;

/**
 * Created by Michael's Account on 11/15/2015.
 */

public  class Employee {

    private  SimpleIntegerProperty employeeID = new SimpleIntegerProperty(0);
    private  SimpleStringProperty firstName = new SimpleStringProperty("");
    private  SimpleStringProperty lastName = new SimpleStringProperty("");
    private  SimpleStringProperty street = new SimpleStringProperty("");
    private  SimpleStringProperty city = new SimpleStringProperty("");




    public  IntegerProperty employeeIDProperty(){
        return employeeID;
    }
    public  int getEmployeeId() {
        return employeeIDProperty().get();
    }

    public  void setID(int ID){
        employeeIDProperty().set(ID);
    }


    public StringProperty firstNameProperty(){
        return firstName;
    }

    public String getFirstName() {
        return firstNameProperty().get();
    }

    public void setFirstName(String fn){
        firstNameProperty().set(fn);
    }

    public StringProperty lastNameProperty(){
        return lastName;
    }


    public String getLastName() {
        return lastNameProperty().get();
    }

    public void setLastName(String ln){
        lastNameProperty().set(ln);
    }

    public StringProperty streetProperty(){
        return street;
    }

    public String getStreet() {
        return streetProperty().get();
    }

    public void setStreet(String street){
        streetProperty().set(street);
    }
    public StringProperty cityProperty(){
        return city;
    }

    public String getCity() {
        return cityProperty().get();
    }

    public void setCity(String city){
        cityProperty().set(city);
    }

    public Employee(){}


    public Employee(int Id, String fN, String lN, String street, String city){
        setID(Id);
        setFirstName(fN);
        setLastName(lN);
        setStreet(street);
        setCity(city);

        /*
        setID(Id);
        setFirstName(fn);
        setLastName(ln);
        setStreet(street);
        setCity(city);
        */
    }
}