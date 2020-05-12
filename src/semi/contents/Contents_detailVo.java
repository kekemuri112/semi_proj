package semi.contents;

import java.sql.Date;

public class Contents_detailVo {
	private String contents_title;
	private String users_id;
	private String post;
	private Date contents_regDate;
	private Date contents_modifyDate;
	
	public Contents_detailVo() {}
	

	public Contents_detailVo(String contents_title, String users_id, String post, Date contents_regDate,
			Date contents_modifyDate) {
		super();
		this.contents_title = contents_title;
		this.users_id = users_id;
		this.post = post;
		this.contents_regDate = contents_regDate;
		this.contents_modifyDate = contents_modifyDate;
	}


	public String getContents_title() {
		return contents_title;
	}
	public String getUsers_id() {
		return users_id;
	}
	public String getPost() {
		return post;
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
	public void setPost(String post) {
		this.post = post;
	}
	public void setContents_regDate(Date contents_regDate) {
		this.contents_regDate = contents_regDate;
	}
	public void setContents_modifyDate(Date contents_modifyDate) {
		this.contents_modifyDate = contents_modifyDate;
	}
	
	
}
