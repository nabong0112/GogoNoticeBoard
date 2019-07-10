package main.vo;

public class MemberVO {
//테이블 속성 정의
	private String user_id;
	private String user_pw;
	private String user_name;
	
	//자동으로 getter와 setter를 추가함.(오른쪽 클릭->source->generate getters and setters클릭
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	//자동으로 toString메소드를 오버라이딩 함. 이유는 객체에 저장된 필드 값들을 출력해서 보기쉽게 하기위해
	 @Override
	public String toString() {
		return "MemberVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + "]";
	}
}
