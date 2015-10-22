import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class Location {
	private String company;
	private String state;
	private String city;
	private int cityStateId;
	private int companyid;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCityStateId() {
		return cityStateId;
	}
	public void setCityStateId(int cityStateId) {
		this.cityStateId = cityStateId;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	
	public void locationSearch() throws SQLException{
		String url = "jdbc:oracle:thin:system/password@localhost"; 
	      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql ="select cm.company,cs.city,cs.state from company cm,customer c,"+
        "city_state cs where c.company_id = cm.company_id AND cs.city_state_id = "
        		+ "c.city_state_id AND cm.company_id = "+this.companyid+" AND cs.city_state_id = "+this.cityStateId;

//        String sql = "select * from company,city_state where company_id = "+this.companyid+" AND city_state_id = "+this.cityStateId;
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
		
        if(result.next()){
        	
        	this.city = result.getString("city");
        	this.state = result.getString("state");
        	this.company = result.getString("company");
        	
        	
        }
        else
        	System.out.println("Error! Invalid Entry!");
        
        
	}
	public int newCityState() throws SQLException{
		String url = "jdbc:oracle:thin:system/password@localhost"; 
	      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql ="select * from city_state where city = '"+this.city+"' AND state = '"+this.state+"'";

//        String sql = "select * from company,city_state where company_id = "+this.companyid+" AND city_state_id = "+this.cityStateId;
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
        if(result.next()){
        	return result.getInt("city_state_id");
        }
        else{
        	String sql_2 = "insert into city_state (city, state,city_state_id)values('"+this.city+"','"+this.state+"',rownum)";
        
        
        preStatement = conn.prepareStatement(sql_2);
        
        result = preStatement.executeQuery();
        String sql_3 ="select * from city_state where city = '"+this.city+"' AND state = '"+this.state+"'";

//      String sql = "select * from company,city_state where company_id = "+this.companyid+" AND city_state_id = "+this.cityStateId;
      //creating PreparedStatement object to execute query
      preStatement = conn.prepareStatement(sql_3);
  
      result = preStatement.executeQuery();
      
      	return result.getInt("city_state_id");
	}
        
	}

}
