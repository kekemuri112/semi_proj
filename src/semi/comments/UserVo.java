package semi.comments;

public class UserVo {
	private int users_num;
	private String users_id;
	
	public UserVo() {};
	public UserVo(int users_num, String users_id) {
		super();
		this.users_num = users_num;
		this.users_id = users_id;
	}
	public int getUsers_num() {
		return users_num;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_num(int users_num) {
		this.users_num = users_num;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
}
	