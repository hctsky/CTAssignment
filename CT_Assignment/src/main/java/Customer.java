
public class Customer {
	
	//declare attributes
	protected String name;
	protected String password;
	protected String email;
	protected String mobile;
	protected String id;
	protected Integer rewardpoints;
	
	public Customer(String name, String password, String email, String mobile, String id, Integer rewardpoints) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.id = id;
		this.rewardpoints = rewardpoints;
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
	public Integer getRewardpoints() {
		return rewardpoints;
	}
	public void setRewardpoints(Integer rewardpoints) {
		this.rewardpoints = rewardpoints;
	}
}
