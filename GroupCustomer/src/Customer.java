import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class Customer {
	private String title;
	private String last_name;
	private String first_name;
	private int cust_id;
	private String address;
	private String email;
	private String full_name;
	private Location newLocation = new Location();
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	private int zip;
	
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getNewLocation() {
		return newLocation.getCity();
		
	}
	public String getLocationState(){
		return newLocation.getState();
	}
	public String getLocationCompany(){
		return newLocation.getCompany();
	}
	public void setNewLocationCity(String city) {
		this.newLocation.setCity(city);
	}
	public void setNewLocationState(String state) {
		this.newLocation.setState(state);
	}
	
	
	public boolean lastNameSearch() throws SQLException{
		String url = "jdbc:oracle:thin:system/password@localhost"; 
	      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql ="select * from customer where lastname = '" + this.last_name + "'";

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
		
        if(result.next()){
        this.first_name = result.getString("firstname");
        this.last_name = result.getString("lastname");
        this.address = result.getString("streetaddress");
        this.cust_id = result.getInt("customer_id");
        this.email = result.getString("emailaddress");
        this.full_name = result.getString("fullname");
        this.title = result.getString("title");
        this.zip = result.getInt("zipcode");
       
        this.newLocation.setCityStateId(result.getInt("city_state_id"));
        this.newLocation.setCompanyid(result.getInt("company_id"));
        newLocation.locationSearch();
        return true;
        }
        else
        	return false;
        
       
	}
	public void updateStreetAddress() throws SQLException{
		
		String url = "jdbc:oracle:thin:system/password@localhost"; 
	      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql ="update customer set streetaddress = '"+this.address +"' where lastname = '"+this.last_name+"'";
        String sql_2 = "update customer set zipcode = "+this.zip+"where lastname = '"+this.last_name+"'";
        

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
        preStatement = conn.prepareStatement(sql_2);
        
        result = preStatement.executeQuery();
        
        int new_state_id = this.newLocation.newCityState();
        
        String sql_3 = "update customer set city_state_id = "+new_state_id+ "where lastname = '"+this.last_name+"'";
        preStatement = conn.prepareStatement(sql_3);
        
        result = preStatement.executeQuery();
        
	}
	
		
	}
	


