
public class Employee {
	
	// declare attributes
	protected String name;
	protected String password;
	protected String email;
	protected String mobile;
	protected String id;
	protected String position;
	protected Integer salary;

	public Employee(String name, String password, String email, String mobile, String id, String position,
			Integer salary) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.id = id;
		this.position = position;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

}
