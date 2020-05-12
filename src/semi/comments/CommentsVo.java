package semi.comments;

public class CommentsVo {
	private int comments_num;
	private int contents_num;
	private int users_num;
	private String comments_content;
	private int comments_ref;
	private int comments_lev;
	private int comments_step;
	public CommentsVo(int comments_num, int contents_num, int users_num, String commnets_content, int comments_ref,
			int comments_lev, int comments_step) {
		super();
		this.comments_num = comments_num;
		this.contents_num = contents_num;
		this.users_num = users_num;
		this.comments_content = commnets_content;
		this.comments_ref = comments_ref;
		this.comments_lev = comments_lev;
		this.comments_step = comments_step;
	}
	public int getComments_num() {
		return comments_num;
	}
	public void setComments_num(int comments_num) {
		this.comments_num = comments_num;
	}
	public int getContents_num() {
		return contents_num;
	}
	public void setContents_num(int contents_num) {
		this.contents_num = contents_num;
	}
	public int getUsers_num() {
		return users_num;
	}
	public void setUsers_num(int users_num) {
		this.users_num = users_num;
	}
	public String getComments_content() {
		return comments_content;
	}
	public void setCommnets_content(String commnets_content) {
		this.comments_content = commnets_content;
	}
	public int getComments_ref() {
		return comments_ref;
	}
	public void setComments_ref(int comments_ref) {
		this.comments_ref = comments_ref;
	}
	public int getComments_lev() {
		return comments_lev;
	}
	public void setComments_lev(int comments_lev) {
		this.comments_lev = comments_lev;
	}
	public int getComments_step() {
		return comments_step;
	}
	public void setComments_step(int comments_step) {
		this.comments_step = comments_step;
	}
}
	