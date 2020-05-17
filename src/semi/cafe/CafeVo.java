package semi.cafe;

public class CafeVo {
	private int cafe_num;
	private String cafe_name;
	private String cafe_desc;
	private String cafe_intent;
	private String cafe_admin;
	private String cafe_approved;
	private String cafe_image;
	
	public CafeVo() {}

	public CafeVo(int cafe_num, String cafe_name, String cafe_desc, String cafe_intent, String cafe_admin,
			String cafe_approved, String cafe_image) {
		super();
		this.cafe_num = cafe_num;
		this.cafe_name = cafe_name;
		this.cafe_desc = cafe_desc;
		this.cafe_intent = cafe_intent;
		this.cafe_admin = cafe_admin;
		this.cafe_approved = cafe_approved;
		this.cafe_image = cafe_image;
	}

	public int getCafe_num() {
		return cafe_num;
	}

	public void setCafe_num(int cafe_num) {
		this.cafe_num = cafe_num;
	}

	public String getCafe_name() {
		return cafe_name;
	}

	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}

	public String getCafe_desc() {
		return cafe_desc;
	}

	public void setCafe_desc(String cafe_desc) {
		this.cafe_desc = cafe_desc;
	}

	public String getCafe_intent() {
		return cafe_intent;
	}

	public void setCafe_intent(String cafe_intent) {
		this.cafe_intent = cafe_intent;
	}

	public String getCafe_admin() {
		return cafe_admin;
	}

	public void setCafe_admin(String cafe_admin) {
		this.cafe_admin = cafe_admin;
	}

	public String getCafe_approved() {
		return cafe_approved;
	}

	public void setCafe_approved(String cafe_approved) {
		this.cafe_approved = cafe_approved;
	}

	public String getCafe_image() {
		return cafe_image;
	}

	public void setCafe_image(String cafe_image) {
		this.cafe_image = cafe_image;
	}

}
