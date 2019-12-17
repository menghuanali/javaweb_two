package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Container implements Serializable{
	private static final long serialVersionUID = 1L;
	private int t_id;
	private String t_name;
	private ArrayList<Service> sonServices = new ArrayList<>();
	public Container() {
		
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public ArrayList<Service> getSonServices() {
		return sonServices;
	}
	public void setSonServices(ArrayList<Service> sonServices) {
		this.sonServices = sonServices;
	}
	
}
