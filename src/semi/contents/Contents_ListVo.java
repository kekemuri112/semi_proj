package semi.contents;

import java.sql.Date;

public class Contents_ListVo {
	private int index;
	private String contents_title;
	private String users_id;
	private Date contents_regDate;
	private Date contents_modifyDate;
	
	public Contents_ListVo() {}
	
	
	public Contents_ListVo(int index, String contents_title, String users_id, Date contents_regDate,
			Date contents_modifyDate) {
		super();
		this.index = index;
		this.contents_title = contents_title;
		this.users_id = users_id;
		this.contents_regDate = contents_regDate;
		this.contents_modifyDate = contents_modifyDate;
	}


	public int getIndex() {
		return index;
	}

	public String getContents_title() {
		return contents_title;
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

	public void setIndex(int index) {
		this.index = index;
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
