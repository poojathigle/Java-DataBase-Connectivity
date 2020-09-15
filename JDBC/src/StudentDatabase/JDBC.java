package StudentDatabase;

import java.sql.*;
import java.io.*;
public class JDBC {

	public static void main(String[] args)
	{
	   Connection conn=null;
	   Statement stmt=null;
	   String query;
	   String user="c23";
	   String pass="c23";
	   String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	   String DB_URL="jdbc:mysql://172.16.1.68:3306/c23";
	   int ans=0;
	   String name,aop,ln,br;
	   double mb,lamt,ac;
	   int bal;
	   PreparedStatement p;
	 try
	 {
		 Class.forName(JDBC_DRIVER);
		 conn=DriverManager.getConnection(DB_URL,user,pass);
		 stmt=conn.createStatement();
		 do
		 {
		 System.out.println("1.INSERT\n 2.UPDATE\n 3.DELETE\n 4.DISPLAY\n");
		 System.out.println("Enter your choice");
		 BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
		 int a = Integer.parseInt(b.readLine());
		 switch(a)
		 {
		 case 1:
			 System.out.println("Enter number of entries");
			 int num = Integer.parseInt(b.readLine());
			 
			 for(int i = 0 ; i < num ; i++)
			 {
			 
			 System.out.println("INSERT");
			 System.out.print("Enter the customer name: ");
			 name = b.readLine();
			 
			 System.out.print("Enter mobile number: ");
			 mb = Double.parseDouble(b.readLine());
			 
			 System.out.print("Enter account number: ");
			 ac = Double.parseDouble(b.readLine());
			 
			 System.out.print("Enter the branch name: ");
			 br = b.readLine();
			 
			 System.out.print("Enter balance in account: ");
			 bal = Integer.parseInt(b.readLine());
			 
			 System.out.print("Enter the Account opening date: ");
			 aop = b.readLine();
			 
			 System.out.print("Enter the loan number: ");
			 ln =  b.readLine();
			 
			 System.out.print("Enter the loan amount: ");
			 lamt = Double.parseDouble(b.readLine());
			 
			 query = "insert into Account(cust_name,mobile_number,acc_no,branch,balance,acc_op_date,loan_no,loan_amt)"+
			 "values(?,?,?,?,?,?,?,?)";
			 p = conn.prepareStatement(query);
			 p.setString(1,name);
			 p.setDouble(2, mb);
			 p.setDouble(3, ac);
			 p.setString(4, br);
			 p.setDouble(5,bal);
			 p.setString(6, aop);
			 p.setString(7, ln);
			 p.setDouble(8, lamt);
			
	         p.execute();
			 }
			 
			 break;
		 case 2:
			 System.out.println("updating");
			 System.out.print("Enter the acc no to be updated: ");
			 ac = Double.parseDouble(b.readLine());
			 System.out.println("press 1.to update balance\n 2.to update loan amount\n 3.to update mobile number");
			 int ch = Integer.parseInt(b.readLine());
			 if(ch == 1)
			 {
				 System.out.print("Enter new balance: ");
				 bal = Integer.parseInt(b.readLine());
				 query = "update Account set balance=? where acc_no=?";
				 p = conn.prepareStatement(query);
				 p.setInt(1,bal);//1 2 3 4 indicates no of questyion marks in query
				 p.setDouble(2, ac);
				 
				 p.execute();
			 }
			 
			 if(ch == 2)
			 {
				 System.out.println("Enter new loan amount");
				 lamt = Double.parseDouble(b.readLine());
				 query = "update Account set loan_amt=? where acc_no=?";
				 p = conn.prepareStatement(query);
				 p.setDouble(1,lamt);//1 2 3 4 indicates no of question marks in query
				 p.setDouble(2, ac);
				 p.execute();
				 
			 }
			 
			 if(ch == 3)
			 {
				 System.out.print("Enter new mobile number: ");
				 mb = Double.parseDouble(b.readLine());
				 query = "update Account set mobile_number=? where acc_no=?";
				 p = conn.prepareStatement(query);
				 p.setDouble(1,mb);//1 2 3 4 indicates no of question marks in query
				 p.setDouble(2, ac);
				 p.execute();
				 
			 }
			 break;
		 case 3:
			 System.out.println("deletion");
			 System.out.print("Enter account number: ");
			 ac = Integer.parseInt(b.readLine());
			 query = "delete from Account where acc_no=?";
			 p = conn.prepareStatement(query);
			 p.setDouble(1,ac);
			 p.execute();
			 break;
		 case 4:
			 System.out.println("DISPLAY");
			 
			 query = "select * from Account";
			 stmt.execute(query);
			 System.out.println("Succesful");
			 ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
		     {
			  name = rs.getString("cust_name");//quotes madhla is column name in table
			  mb = rs.getDouble("mobile_number");
			  ac = rs.getDouble("acc_no");
			  br = rs.getString("branch");
			  bal = rs.getInt("balance");
			  aop = rs.getString("acc_op_date");
			  ln = rs.getString("loan_no");
			  lamt = rs.getDouble("loan_amt");
			  
			  String output=("%s %f %f %s %d %s %s %f");
			  System.out.println(String.format(output,name,mb,ac,br,bal,aop,ln,lamt));
			 
		 }
			 break;
		 
		 }
		 System.out.print("Presss 1 to continue...");
		 ans = Integer.parseInt(b.readLine());
		 }while(ans == 1);	
		 conn.close();
	}
	 catch(Exception e)
	 {
		e.printStackTrace();
	 }
	
	}
}

	
