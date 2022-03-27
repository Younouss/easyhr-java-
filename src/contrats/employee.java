/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

/**
 *
 * @author h
 */
public class employee {
    private String registration_number;
    private String cnps;
    private String surname;
    private String name;
    private String description;
    private String entry_date;
    private int length;
    private String end_date;
    private String observation;
    private String date_created;
    private String user_date_created;
    private String date_modified;
    private String user_date_modified;
    private String contract;
    private int days;
    public employee(String registration_number,String surname, String name, String end_date, String contract){
        this.registration_number = registration_number;
        this.surname = surname;
        this.name = name;
        this.end_date = end_date;  
        this.contract = contract;
    }
    public employee(String registration_number, String cnps,String surname, String name, String end_date, String contract){
        this.registration_number = registration_number;
        this.cnps = cnps;
        this.surname = surname;
        this.name = name;
        this.end_date = end_date;    
    }
    public employee(String registration_number, String cnps,String surname, String name, String entry_date,String end_date,int days){
        this.registration_number = registration_number;
        this.cnps = cnps;
        this.surname = surname;
        this.name = name;
        this.entry_date = entry_date;
        this.end_date = end_date;   
        this.days = days;
    }
    public employee(String registration_number, String cnps, String surname, String name, String description, String entry_date, String end_date, int length, String contract, String observation,String date_created, String user_date_created, String date_modified, String user_date_modified)
    {
        this.registration_number = registration_number;
        this.cnps = cnps;
        this.surname = surname;
        this.name = name;
        this.description = description;
        this.entry_date = entry_date;
        this.end_date = end_date;
        this.length= length;
        this.contract = contract;
        this.observation = observation;
        this.date_created = date_created;
        this.user_date_created = user_date_created;
        this.date_modified = date_modified;
        this.user_date_modified = user_date_modified;
    }
    
     public String getRegistration_number()
    {
        return registration_number;
    }
    public String getCnps()
    {
        return cnps;
    }
    
    public String getSurname()
    {
        return surname;
    }
    
    public String getName()
    {
        return name;
    }
    public String getEntry_date()
    {
        return entry_date;
    } 
    public String getEnd_date()
    {
        return end_date;
    } 
    public String getDescription()
    {
        return description;
    }
    
    public int getLength()
    {
        return length;
    }
    public String getContract()
    {
        return contract;
    }
   public String getObservation()
    {
        return observation;
    } 
    public String getDate_created()
    {
        return date_created;
    }  
    public String getUser_date_created()
    {
        return user_date_created;
    }
    
    public String getDate_modified()
    {
        return date_modified;
    }
   public String getUser_date_modified()
    {
        return user_date_modified;
    } 
   public int getDays(){
       return days;
   }
}
