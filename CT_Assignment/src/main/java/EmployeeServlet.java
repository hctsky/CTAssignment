
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/employeedata";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO EmployeeData"
			+ " (name, password, email, mobile, id, position, salary) VALUES " + " (?, ?, ?);";
	private static final String SELECT_EMPLOYEE_BY_ID = "select name,password,email, mobile, id, position, salary from EmployeeData where name =?";
	private static final String SELECT_ALL_EMPLOYEES = "select * from EmployeeData ";
	private static final String DELETE_EMPLOYEES_SQL = "delete from EmployeeData where name = ?;";
	private static final String UPDATE_EMPLOYEES_SQL = "update EmployeeData set name = ?,password= ?, email =?,mobile =?, id =?, position =?, salary =?  where name = ?;";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();
		try {
			switch (action) {
			case "/EmployeeServlet/delete":
				deleteEmployee(request, response);
				break;
			case "/EmployeeServlet/edit":
				showEmployeeEditForm(request, response);
				break;
			case "/EmployeeServlet/update":
				updateEmployee(request, response);
				break;
			case "/EmployeeServlet/dashboard":
				listEmployees(request, response);
				break;
			case "/EmployeeServlet/insert":
				addNewEmployee(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Step 5: listUsers function to connect to the database and retrieve all users
	// records
	private void listEmployees(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = getConnection(); // Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) { // Step 5.2:
			// Execute the
			// query or
			// update query
			ResultSet rs = preparedStatement.executeQuery(); // Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				String id = rs.getString("id");
				String position = rs.getString("position");
				Integer salary = rs.getInt("salary");
				employees.add(new Employee(name, password, email, mobile, id, position, salary));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5.4: Set the users list into the listUsers attribute to be pass to the
			// userManagement.jsp
		request.setAttribute("listEmployees", employees);
		request.getRequestDispatcher("/EmployeeManagement.jsp").forward(request, response);
	}

	private void showEmployeeEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String name = request.getParameter("name");
		Employee existingEmployee = new Employee("", "", "", "", "", "", 0); // Step 1: Establishing a Connection
		try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
			preparedStatement.setString(1, name); // Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery(); // Step 4: Process the ResultSet object
			while (rs.next()) {
				name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				String id = rs.getString("id");
				String position = rs.getString("position");
				Integer salary = rs.getInt("salary");
				existingEmployee = new Employee(name, password, email, mobile, id, position, salary);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("employee", existingEmployee);
		request.getRequestDispatcher("/EmployeeEdit.jsp").forward(request, response);
	}

	// method to update the employee table base on the form data
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		// Step 1: Retrieve value from the request
		String oriName = request.getParameter("oriName");
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String id = request.getParameter("id");
		String position = request.getParameter("position");
		String salary = request.getParameter("salary");

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEES_SQL);) {
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, mobile);
			statement.setString(5, id);
			statement.setString(6, position);
			statement.setString(7, salary);
			statement.setString(8, oriName);
			int i = statement.executeUpdate();
		} // Step 3: redirect back to UserServlet (note: remember to change the url to
			// your project name)
		response.sendRedirect("http://localhost:8095/CT_Assignment_2/EmployeeServlet/dashboard");
	}

	// method to delete user
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException { // Step 1: Retrieve value from the request
		String name = request.getParameter("name");

		// Step 2: Attempt connection with database and execute delete employee SQL
		// query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEES_SQL);) {
			statement.setString(1, name);
			int i = statement.executeUpdate();
		} // Step 3: redirect back to EmployeeServlet dashboard (note: remember to change
			// the
			// url to your project name)
		response.sendRedirect("http://localhost:8095/CT_Assignment_2/EmployeeServlet/dashboard");
	}

	// method to trigger RegisterServlet
	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher rd = null;
		rd = getServletContext().getRequestDispatcher("/EmployeeRegisterServlet");
		rd.include(request, response);
		response.sendRedirect("http://localhost:8095/CT_Assignment_2/EmployeeServlet/dashboard");
	}

}
