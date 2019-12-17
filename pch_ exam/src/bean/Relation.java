package bean;

import java.io.Serializable;

public class Relation implements Serializable{
	private static final long serialVersionUID = 1L;
	private int l_id;
	private int s_id;
	private int t_id;
	public Relation() {
		
	}
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	
}
