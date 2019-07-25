package main.vo;

public class WriteVo {

	int board_no;
	String board_title;
	String board_text;
	String board_user;
	String board_time;
	int board_view;
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_text() {
		return board_text;
	}
	public void setBoard_text(String board_text) {
		this.board_text = board_text;
	}
	public String getBoard_user() {
		return board_user;
	}
	public void setBoard_user(String board_user) {
		this.board_user = board_user;
	}
	public String getBoard_time() {
		return board_time;
	}
	public void setBoard_time(String board_time) {
		this.board_time = board_time;
	}
	public int getBoard_view() {
		return board_view;
	}
	public void setBoard_view(int board_view) {
		this.board_view = board_view;
	}
	
	@Override
	public String toString() {
		return "WriteVo [board_no=" + board_no + ", board_title=" + board_title + ", board_text=" + board_text
				+ ", board_user=" + board_user + ", board_time=" + board_time + ", board_view=" + board_view + "]";
	}
	
}
