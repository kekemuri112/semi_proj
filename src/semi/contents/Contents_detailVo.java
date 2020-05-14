package semi.contents;

import java.sql.Date;

public class Contents_detailVo {
	private String contents_title;
	private String users_id;
	private String contents_post;
	private Date contents_regDate;
	private Date contents_modifyDate;
	private int contents_num;
	private int users_num;
	
	
	public Contents_detailVo() {}
	

	public Contents_detailVo(String contents_title, String users_id, String contents_post, Date contents_regDate,
			Date contents_modifyDate,int contents_num,int users_num) {
		super();
		this.contents_title = contents_title;
		this.users_id = users_id;
		this.contents_post = contents_post;
		this.contents_regDate = contents_regDate;
		this.contents_modifyDate = contents_modifyDate;
		this.contents_num=contents_num;
		this.users_num=users_num;

	}
	
	public String getContents_post() {
		return contents_post;
	}


	public void setContents_post(String contents_post) {
		this.contents_post = contents_post;
	}


	public int getUsers_num() {
		return users_num;
	}


	public void setUsers_num(int users_num) {
		this.users_num = users_num;
	}


	public String getContents_title() {
		return contents_title;
	}
	public int getContents_num() {
		return contents_num;
	}


	public void setContents_num(int contents_num) {
		this.contents_num = contents_num;
	}


	public String getUsers_id() {
		return users_id;
	}
	
	
	public Date getContents_regDate() {
		return contents_regDate;
	}
	public Date getContents_modifyDate() {
		return contents_modifyDate;
	}
	public void setContents_title(String contents_title) {
		this.contents_title = contents_title;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}

	public void setContents_regDate(Date contents_regDate) {
		this.contents_regDate = contents_regDate;
	}
	public void setContents_modifyDate(Date contents_modifyDate) {
		this.contents_modifyDate = contents_modifyDate;
	}
	
	
}
