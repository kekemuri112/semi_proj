package semi.comments;

public class UserVo {
	private int user_num;
	private String user_id;
	public UserVo(int user_num, String user_id) {
		super();
		this.user_num = user_num;
		this.user_id = user_id;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
