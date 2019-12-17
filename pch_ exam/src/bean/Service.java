package bean;

import java.io.Serializable;

public class Service implements Serializable{

	private static final long serialVersionUID = 1L;
	private int s_id;
	private String s_name;
	private int s_choice;
	public Service() {
		
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getS_choice() {
		return s_choice;
	}
	public void setS_choice(int s_choice) {
		this.s_choice = s_choice;
	}
	

}
