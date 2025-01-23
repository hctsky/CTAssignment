
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * Servlet implementation class EmployeeRegisterServlet
 */
@WebServlet("/EmployeeRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("http://localhost:8095/CT_Assignment_2/EmployeeServlet/dashboard");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter(); 
		
		//Step 2: retrieve the four parameters from the request from the web form
		String nm = request.getParameter("userName");
		String pw = request.getParameter("password");
		String em = request.getParameter("email");
		String mb = request.getParameter("mobile");
		String id = request.getParameter("id");
		String po = request.getParameter("position");
		String sl = request.getParameter("salary");
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/employeedata", "root", "password");
		
			//Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
			PreparedStatement ps = con.prepareStatement("insert into EMPLOYEEDATA values(?,?,?,?,?,?,?)");
			
			//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
			ps.setString(1, nm);
			ps.setString(2, pw);
			ps.setString(3, em);
			ps.setString(4, mb); 
			ps.setString(5, id);
			ps.setString(6, po);
			ps.setString(7, sl);
			
			//Step 6: perform the query on the database using the prepared statement
			int i = ps.executeUpdate();
		
			//response.sendRedirect("http://localhost:8095/CT_Assignment_2/EmployeeServlet/dashboard");
			//Step 7: check if the query had been successfully execute, return “You are successfully registered” via the response,
			/*if (i > 0){
				PrintWriter writer = response.getWriter();
				writer.println("<h1>" + "You have successfully registered an Employee account!" + "</h1>");
				writer.close();
				
			}*/
			
			
			
		} //Step 8: catch and print out any exception
		catch (Exception exception) {
			
			System.out.println(exception);
			out.close();
		}
		
		doGet(request, response);
	}
}
