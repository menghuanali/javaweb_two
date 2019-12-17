package pch_modle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.Service;

public class PCHservice {
	Connection conn = null;
	public PCHservice() throws NamingException, SQLException{
//		Context initContext = new InitialContext();
//		DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/PCHtion");
		conn=JDBCUtils.getConnection();
//		conn = ds.getConnection();
//		System.out.println(333);
	}
	public ArrayList<Service> queryService() throws SQLException{
		 
		ArrayList<Service> list = new ArrayList<>();
		 PreparedStatement statement = conn.prepareStatement("SELECT * FROM pchservlet");
	        ResultSet thingSet = statement.executeQuery();
	        while (thingSet.next()) {
	        	Service bean = new Service();
	            bean.setS_id(thingSet.getInt("s_id"));
	            bean.setS_name(thingSet.getString("s_name"));
	            bean.setS_choice(thingSet.getInt("s_choice"));
	            list.add(bean);
	        }
	        return list;
	}
	public int insertService(Service ms){
		if(isExist(ms)==true) {
			return 0;
		}
		String sql = "insert into pchservlet(s_id,s_name,s_choice) values(?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, 0);
			pstm.setString(2, ms.getS_name());
			pstm.setInt(3,0);
			pstm.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return 0;
	}
	public boolean isExist(Service ms) {
		String sql = "select * from pchservlet where s_name = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, ms.getS_name());
			ResultSet rst = pstm.executeQuery();
			if(rst.next()==false){
//				System.out.println("不存在");
			   return false;
			}else{
//				System.out.println("存在");
			   return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public int deleteService(int s_id) {
		String sql = "delete from pchservlet where s_id = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, s_id);
			int r = pstm.executeUpdate();
			if(r>=0)
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public Service selectBySid(int s_id) {
		Service thisService = new Service();
		String sql = "select * from pchservlet where s_id = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, s_id);
			ResultSet thingSet = pstm.executeQuery();
			 while (thingSet.next()) {	
				 thisService.setS_id(thingSet.getInt("s_id"));
				 thisService.setS_name(thingSet.getString("s_name"));
				 thisService.setS_choice(thingSet.getInt("s_choice"));
		        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thisService;
	}
	public void updateChickedBySid(int s_id) throws SQLException{
		String sql = "update pchservlet set s_choice = 1 where s_id = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, s_id);
			pstm.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateChickedBySidforzero(int s_id) throws SQLException{
		String sql = "update pchservlet set s_choice = 0 where s_id = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, s_id);
			pstm.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
