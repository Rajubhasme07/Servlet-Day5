

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("/stockupdate")
public class Stockupdate extends HttpServlet {
	
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Customer Data");
		out.println("</title>");
		out.println("<style>");
		out.println(".result{background:White;"
				+ "margin-top:250px;"
				+ "padding-top: 50px;"
				+ "padding-right: 60px;"
				+ "padding-bottom: 50px;"
				+ "padding-left:80px;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body background='img/displayimg.png'>");
	
		String cname=request.getParameter("cname");
		String cemail=request.getParameter("cemail");
		String cpro=request.getParameter("cpro");
		String pdate=request.getParameter("pdate");
		String url="jdbc:mysql://localhost:3306/customer",uname="root",pass="abc123";
		//creating Config method for 
		 ServletConfig sc=getServletConfig();
		 String lshead=sc.getInitParameter("lshead");
		
		
		
		
		//Creating Context Method for Driver
	    ServletContext sct=getServletContext();
		String Driver=sct.getInitParameter("Driver");
		try {
			
			Class.forName(Driver);
//			out.print("Driver registered");
			
		
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			
			
			Connection con=DriverManager.getConnection(url,uname,pass);
			PreparedStatement ps=con.prepareStatement("INSERT INTO custdetails VALUES(?,?,?,?)");
			ps.setString(1, cname);
			ps.setString(2, cemail);
			ps.setString(3, cpro);
			ps.setString(4, pdate);
//			out.print("Statement Created");
			int a=ps.executeUpdate();
			if(a>0){
				out.print("<div class='result'>");
				out.println("<b ><center><b><h1>CUSTOMER DETAILS</h1></b></center>");
				out.println("<b ><center>Customer Name:</b>"+cname+"</center>");
				out.println("<b><center>Customer Email:</b>"+ cemail+"</center>");
				out.println("<b><center>Product Purchase:</b>"+cpro+"</center>");
				out.println("<b><center>Purchase Date:</b>"+pdate+"</center>");
				out.println("</br><center><b>"+lshead+"</center>");
				out.print("<div>");
			}
			else{
				out.print("Data not inserted");
			}

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</body>");
		out.println("<html>");
		
			}
	
	}

