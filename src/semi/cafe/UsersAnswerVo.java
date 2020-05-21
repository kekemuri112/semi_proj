package semi.cafe;

public class UsersAnswerVo {
	private int users_cafe_num;
	private int cafereg_num;
	private String cafereg_question;
	private String answer_contents;
	
	public UsersAnswerVo() {}
	public UsersAnswerVo(int users_cafe_num, int cafereg_num, String cafereg_question, String answer_contents) {
		super();
		this.users_cafe_num = users_cafe_num;
		this.cafereg_num = cafereg_num;
		this.cafereg_question = cafereg_question;
		this.answer_contents = answer_contents;
	}
	public int getUsers_cafe_num() {
		return users_cafe_num;
	}
	public int getCafereg_num() {
		return cafereg_num;
	}
	public String getCafereg_question() {
		return cafereg_question;
	}
	public String getAnswer_contents() {
		return answer_contents;
	}
	public void setUsers_cafe_num(int users_cafe_num) {
		this.users_cafe_num = users_cafe_num;
	}
	public void setCafereg_num(int cafereg_num) {
		this.cafereg_num = cafereg_num;
	}
	public void setCafereg_question(String cafereg_question) {
		this.cafereg_question = cafereg_question;
	}
	public void setAnswer_contents(String answer_contents) {
		this.answer_contents = answer_contents;
	}
	
	
}
