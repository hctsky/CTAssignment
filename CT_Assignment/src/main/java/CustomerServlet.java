
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
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/customerdata";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_CUSTOMER_SQL = "INSERT INTO CustomerData"
			+ " (name, password, email, mobile, id, rewardpoints) VALUES " + " (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_CUSTOMER_BY_ID = "select name,password,email,mobile, id, rewardpoints from CustomerData where name =?";
	private static final String SELECT_ALL_CUSTOMERS = "select * from CustomerData ";
	private static final String DELETE_CUSTOMERS_SQL = "delete from CustomerData where name = ?;";
	private static final String UPDATE_CUSTOMERS_SQL = "update CustomerData set name = ?,password= ?, email =?,mobile =?, id=?, rewardpoints=? where name = ?;";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
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

		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/CustomerServlet/delete":
				deleteCustomer(request, response);
				break;
			case "/CustomerServlet/edit":
				showCustomerEditForm(request, response);
				break;
			case "/CustomerServlet/update":
				updateCustomer(request, response);
				break;
			case "/CustomerServlet/dashboard":
				listCustomers(request, response);
				break;
			case "/CustomerServlet/insert":
				addNewCustomer(request, response);
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

	// Step 5: listUsers function to connect to the database and retrieve all
	// customers
	// records
	private void listCustomers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Customer> customers = new ArrayList<>();
		try (Connection connection = getConnection(); // Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) { // Step 5.2:
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
				Integer rewardpoints = rs.getInt("rewardpoints");
				customers.add(new Customer(name, password, email, mobile, id, rewardpoints));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5.4: Set the users list into the listUsers attribute to be pass to the
			// userManagement.jsp
		request.setAttribute("listCustomers", customers);
		request.getRequestDispatcher("/CustomerManagement.jsp").forward(request, response);
	}

	// method to get parameter, query database for existing customer data and
	// redirect
	// to customer edit page
	private void showCustomerEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String name = request.getParameter("name");
		Customer existingCustomer = new Customer("", "", "", "", "", 0); // Step 1: Establishing a Connection
		try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
			preparedStatement.setString(1, name); // Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery(); // Step 4: Process the ResultSet object
			while (rs.next()) {
				name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				String id = rs.getString("id");
				Integer rewardpoints = rs.getInt("rewardpoints");
				existingCustomer = new Customer(name, password, email, mobile, id, rewardpoints);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("customer", existingCustomer);
		request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
	}

	// method to update the customer table base on the form data
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String oriName = request.getParameter("oriName");
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String id = request.getParameter("id");
		String rewardpoints = request.getParameter("rewardpoints");

		// Step 2: Attempt connection with database and execute update customer SQL
		// query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);) {
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, mobile);
			statement.setString(5, id);
			statement.setString(6, rewardpoints);
			statement.setString(7, oriName);
			int i = statement.executeUpdate();
		} // Step 3: redirect back to UserServlet (note: remember to change the url to
			// your project name)
		response.sendRedirect("http://localhost:8095/CT_Assignment_2/CustomerServlet/dashboard");
	}

	// method to delete customer
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name"); // Step 2: Attempt connection with database and execute delete user
													// SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMERS_SQL);) {
			statement.setString(1, name);
			int i = statement.executeUpdate();
		} // Step 3: redirect back to UserServlet dashboard (note: remember to change the
			// url to your project name)
		response.sendRedirect("http://localhost:8095/CT_Assignment_2/CustomerServlet/dashboard");
	}

	// method to trigger RegisterServlet
	private void addNewCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher rd = null;
		rd = getServletContext().getRequestDispatcher("/CustomerRegisterServlet");
		rd.include(request, response);
		response.sendRedirect("http://localhost:8095/CT_Assignment_2/CustomerServlet/dashboard");
	}

}
