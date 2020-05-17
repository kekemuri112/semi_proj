package semi.notice;

public class NoticeVo {
	private int notice_num;
	private int cafe_num;
	private String notice_name;
	private int notice_lev;
	private int notice_ref;
	private int notice_step;
	private int notice_grade;
	
	public NoticeVo() {}

	public NoticeVo(int notice_num, int cafe_num, String notice_name, int notice_lev, int notice_ref, int notice_step,
			int notice_grade) {
		this.notice_num = notice_num;
		this.cafe_num = cafe_num;
		this.notice_name = notice_name;
		this.notice_lev = notice_lev;
		this.notice_ref = notice_ref;
		this.notice_step = notice_step;
		this.notice_grade = notice_grade;
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public int getCafe_num() {
		return cafe_num;
	}

	public void setCafe_num(int cafe_num) {
		this.cafe_num = cafe_num;
	}

	public String getNotice_name() {
		return notice_name;
	}

	public void setNotice_name(String notice_name) {
		this.notice_name = notice_name;
	}

	public int getNotice_lev() {
		return notice_lev;
	}

	public void setNotice_lev(int notice_lev) {
		this.notice_lev = notice_lev;
	}

	public int getNotice_ref() {
		return notice_ref;
	}

	public void setNotice_ref(int notice_ref) {
		this.notice_ref = notice_ref;
	}

	public int getNotice_step() {
		return notice_step;
	}

	public void setNotice_step(int notice_step) {
		this.notice_step = notice_step;
	}

	public int getNotice_grade() {
		return notice_grade;
	}

	public void setNotice_grade(int notice_grade) {
		this.notice_grade = notice_grade;
	}
	
	
}
