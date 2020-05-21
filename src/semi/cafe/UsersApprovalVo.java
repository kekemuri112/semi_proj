package semi.cafe;

public class UsersApprovalVo {
	private int users_num;
	private String users_id;
	private String users_pwd;
	private String users_name;
	private String users_email;
	private String users_birth;
	private String users_phone;
	private int users_cafe_num;
	private int cafe_num;
	private int users_cafe_point;
	private String users_cafe_approved;
	
	public UsersApprovalVo() {}
	public UsersApprovalVo(int users_num, String users_id, String users_pwd, String users_name, String users_email,
			String users_birth, String users_phone, int users_cafe_num, int cafe_num, int users_cafe_point,
			String users_cafe_approved) {
		super();
		this.users_num = users_num;
		this.users_id = users_id;
		this.users_pwd = users_pwd;
		this.users_name = users_name;
		this.users_email = users_email;
		this.users_birth = users_birth;
		this.users_phone = users_phone;
		this.users_cafe_num = users_cafe_num;
		this.cafe_num = cafe_num;
		this.users_cafe_point = users_cafe_point;
		this.users_cafe_approved = users_cafe_approved;
	}
	public int getUsers_num() {
		return users_num;
	}
	public String getUsers_id() {
		return users_id;
	}
	public String getUsers_pwd() {
		return users_pwd;
	}
	public String getUsers_name() {
		return users_name;
	}
	public String getUsers_email() {
		return users_email;
	}
	public String getUsers_birth() {
		return users_birth;
	}
	public String getUsers_phone() {
		return users_phone;
	}
	public int getUsers_cafe_num() {
		return users_cafe_num;
	}
	public int getCafe_num() {
		return cafe_num;
	}
	public int getUsers_cafe_point() {
		return users_cafe_point;
	}
	public String getUsers_cafe_approved() {
		return users_cafe_approved;
	}
	public void setUsers_num(int users_num) {
		this.users_num = users_num;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public void setUsers_pwd(String users_pwd) {
		this.users_pwd = users_pwd;
	}
	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}
	public void setUsers_email(String users_email) {
		this.users_email = users_email;
	}
	public void setUsers_birth(String users_birth) {
		this.users_birth = users_birth;
	}
	public void setUsers_phone(String users_phone) {
		this.users_phone = users_phone;
	}
	public void setUsers_cafe_num(int users_cafe_num) {
		this.users_cafe_num = users_cafe_num;
	}
	public void setCafe_num(int cafe_num) {
		this.cafe_num = cafe_num;
	}
	public void setUsers_cafe_point(int users_cafe_point) {
		this.users_cafe_point = users_cafe_point;
	}
	public void setUsers_cafe_approved(String users_cafe_approved) {
		this.users_cafe_approved = users_cafe_approved;
	}
	
}
