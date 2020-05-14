package semi.contents;

import java.sql.Date;

public class ContentsVo {
	private int contents_num;
	private int notice_num;
	private int users_num;
	private String contents_title;
	private String contents_post;
	private Date contents_regdate;
	private Date contents_modifydate;
	
	public ContentsVo() {}
	
	public ContentsVo(int contents_num, int notice_num, int users_num, String contents_title, String contents_post,
			Date contents_regdate, Date contents_modifydate) {
		super();
		this.contents_num = contents_num;
		this.notice_num = notice_num;
		this.users_num = users_num;
		this.contents_title = contents_title;
		this.contents_post = contents_post;
		this.contents_regdate = contents_regdate;
		this.contents_modifydate = contents_modifydate;
	}
	public int getContents_num() {
		return contents_num;
	}
	public int getNotice_num() {
		return notice_num;
	}
	public int getUsers_num() {
		return users_num;
	}
	public String getContents_title() {
		return contents_title;
	}
	public String getContents_post() {
		return contents_post;
	}
	public Date getContents_regdate() {
		return contents_regdate;
	}
	public Date getContents_modifydate() {
		return contents_modifydate;
	}
	public void setContents_num(int contents_num) {
		this.contents_num = contents_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public void setUsers_num(int users_num) {
		this.users_num = users_num;
	}
	public void setContents_title(String contents_title) {
		this.contents_title = contents_title;
	}
	public void setContents_post(String contents_post) {
		this.contents_post = contents_post;
	}
	public void setContents_regdate(Date contents_regdate) {
		this.contents_regdate = contents_regdate;
	}
	public void setContents_modifydate(Date contents_modifydate) {
		this.contents_modifydate = contents_modifydate;
	}
	
}
