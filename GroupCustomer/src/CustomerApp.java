import java.sql.SQLException;
import java.util.Scanner;


public class CustomerApp {
	public static void main(String[] args) throws SQLException
	{
		String response = "",address = "",new_state = "",new_city = "";
		int new_response = 0,new_zip = 0;
		
		Scanner sc = new Scanner(System.in);
		Customer cust = new Customer();
		Location loc = new Location();
		
		do{
		
		System.out.println("Please insert the customer Last Name: ");
		response = sc.next();
		
		cust.setLast_name(response);
		if(cust.lastNameSearch()){
		
		System.out.println("Customer Number: "+cust.getCust_id());
		System.out.println(cust.getTitle()+" "+cust.getFull_name());
		System.out.println(cust.getAddress());
		System.out.println(cust.getNewLocation()+", "+cust.getLocationState()+" "+cust.getZip());
		System.out.println(cust.getEmail());
		System.out.println(cust.getLocationCompany());
		}
		else{
		System.out.println("Customer not found");	
		}
		
		System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address.");
		new_response = sc.nextInt();
	
		
		
	}while(new_response == 1);
	
	System.out.println("Enter the new Street Address: ");
	address = sc.next();
	address += sc.nextLine();
	System.out.println("Enter the new State: (IN)");
	new_state = sc.next();
	sc.nextLine();
	System.out.println("Enter the new City: ");
	new_city = sc.next();
	sc.nextLine();
	
	System.out.println("Enter the new ZipCode: ");
	new_zip = sc.nextInt();
	
	cust.setAddress(address);
	cust.setNewLocationCity(new_city);
	cust.setNewLocationState(new_state);
	cust.setZip(new_zip);
	cust.updateStreetAddress();
	

}
}