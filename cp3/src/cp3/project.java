package cp3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class project {
	
public void run() {
		
		//Basic Structure
		System.out.println("Welcome to Our Car Sales Application");
		
		
		char input = 'Q';
		
		do {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Please Choose A Selection!!");
			System.out.println("Enter V To View All Cars");
			System.out.println("Enter G To Get All Cars By Types Example Sedan, Coupe, SUV");
			System.out.println("Enter C To Get All Color Quanties of Cars");
			System.out.println("Enter S To Search For Car By Name With Quantity Of With Car");
			System.out.println("Enter A To Add A Car");
			System.out.println("Enter Q To Quit!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			//Get Input From User
			Scanner kb = new Scanner(System.in);
			input = kb.next().charAt(0); //Get input from user and give the first character
			
			if(input == 'V') {
				System.out.println("View All Cars");
				GetProducts();
				
			}else if (input == 'G') {
				System.out.println("View Car Types");
				GetProductByType();
			}
			else if (input == 'C') {
				System.out.println("View Car Color Quanities");
				GetProductByColorQuanities();
			}else if (input == 'S') {
				System.out.println("Search For A Car By Name With Quantity Of With Car");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
				
				System.out.println("Enter The Name Of The Car To Search");
				String pName = kb.next();
				if(pName.length() > 2) {
					GetProductByName(pName);
				}else {
					System.out.println("Please Enter Atleast 3 Charatcers For Car name");
				}	
			}
			else if(input == 'A') {
				//Call to Add A Car
				System.out.println("Add A Car");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Enter Car Name: ");
				String pName = kb.next();
				System.out.println("Enter Car Quantity: ");
				int qty = kb.nextInt();
				AddProducts(pName , qty);
				
			}
			else if (input != 'Q'){
				System.out.println("INVALID INPUT !?! Please Try Again!!");
			}

			
		}while (input != 'Q');
		
		System.out.println("THANK YOU PLEASE COME AGAIN!!!!");
			
		}
        
        public void GetProducts() {
        	try {
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myStore","root","Deshawn34");
				PreparedStatement ps = connect.prepareStatement("Select * from products");
				ResultSet rs = ps.executeQuery();
				
				
				PrintHeader();
				while(rs.next()) {
					System.out.println(rs.getString("productID") + " " + rs.getString("name"));
					
				}
				PrintFooter();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
       

		public void GetProductByType() {
        	try {
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myStore","root","Deshawn34");
				PreparedStatement ps = connect.prepareStatement("Select * from types");
				ResultSet rs = ps.executeQuery();
				
				PrintHeader();
				while(rs.next()) {
					System.out.println(rs.getString("typesID") + " " + rs.getString("name")+ " " + rs.getInt("qty"));
					
				}
				PrintFooter();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        

		}
		public void  GetProductByColorQuanities() {
			try {
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myStore","root","Deshawn34");
				PreparedStatement ps = connect.prepareStatement("Select * from colors");
				ResultSet rs = ps.executeQuery();
				
				PrintHeader();
				while(rs.next()) {
					System.out.println(rs.getString("colorsID") + " " + rs.getString("name")+ " " + rs.getInt("qty"));
					
				}
				PrintFooter();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void GetProductByName(String name) {
			try {
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myStore","root","Deshawn34");
				PreparedStatement ps = connect.prepareStatement("Select * from products where name = '" + name +"'");
				ResultSet rs = ps.executeQuery();
				
				PrintHeader();
				while(rs.next()) {
					System.out.println(rs.getString("name") + " " + rs.getInt("qty"));
					
				}
				PrintFooter();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void AddProducts(String name, int qty) {
			try {
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myStore","root","Deshawn34");
				PreparedStatement ps = connect.prepareStatement("insert into products(name , qty) values(? , ?)");
				ps.setString(1, name);
				ps.setInt(2, qty);
				ps.executeUpdate();
				System.out.println("This Car Has Been Added To The Database: " + name);
				
				GetProducts();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void PrintHeader() {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Your Results Are:");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
			
		}
		
		public void PrintFooter() {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
		}
       
       	
} 

	


	
	


