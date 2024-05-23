package jdbc_customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchExecution {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Customer customer=new Customer();
		
		customer.setId(1);
		customer.setName("Shubh");
		customer.setPhone(9834109745l);
		customer.setAddress("Solapur");
		
		
		Customer customer2= new Customer();
		customer2.setId(2);
		customer2.setName("bhavik");
		customer2.setPhone(9834369745l);
		customer2.setAddress("Ahilyanagar");
		
		Customer customer3= new Customer();  
		customer3.setId(3);
		customer3.setName("pratham");
		customer3.setPhone(8234568942l);
		customer3.setAddress("pune");
		
		List <Customer>list=new ArrayList<Customer>();
		list.add(customer);
		list.add(customer2);
		list.add(customer3);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb","root","root");
		
		String query= "insert into customer (id,name,phone,address) values(?,?,?,?)";
		PreparedStatement preparedStatement= connection.prepareStatement(query);
		
		for (Customer customer4 :list) {
			preparedStatement.setInt(1, customer4.getId());
			preparedStatement.setString(2, customer4.getName() );
			preparedStatement.setLong(3,customer4.getPhone());
			preparedStatement.setString(4, customer4.getAddress());
			
			preparedStatement.addBatch();
			
		}
		
		preparedStatement.executeBatch();
		System.out.println("Batch Executed");
	
		connection.close();
	}

}
