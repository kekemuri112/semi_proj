package semi.questions;

public class CaferegVo {
	private int cafereg_num;
	private int cafe_num;
	private String cafereg_question;
	
	public CaferegVo() {}	
	public CaferegVo(int cafereg_num, int cafe_num, String cafereg_question) {
		this.cafereg_num = cafereg_num;
		this.cafe_num = cafe_num;
		this.cafereg_question = cafereg_question;
	}
	public int getCafereg_num() {
		return cafereg_num;
	}
	public void setCafereg_num(int cafereg_num) {
		this.cafereg_num = cafereg_num;
	}
	public int getCafe_num() {
		return cafe_num;
	}
	public void setCafe_num(int cafe_num) {
		this.cafe_num = cafe_num;
	}
	public String getCafereg_question() {
		return cafereg_question;
	}
	public void setCafereg_question(String cafereg_question) {
		this.cafereg_question = cafereg_question;
	}
	
}
