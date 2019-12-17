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

import bean.Relation;
import bean.Service;

public class PCHrelation {
	Connection conn = null;
	PCHservice pHservice = new PCHservice();
	public PCHrelation() throws NamingException, SQLException{
		conn=JDBCUtils.getConnection();
		System.out.println(222);
	}
	//插入一个关系
	public int insertRelation(Relation ms) throws SQLException{
		String sql = "insert into pchrelation(l_id,s_id,t_id) values(?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, 0);
			pstm.setInt(2, ms.getS_id());
			pstm.setInt(3,ms.getT_id());
			pstm.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	//删除一个关系
	public int deleteRelation(Relation ms) throws SQLException{
		String sql = "delete from pchrelation where s_id = ? and t_id = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ms.getS_id());
			pstm.setInt(2, ms.getT_id());
			pstm.execute();
			if(isExistRelationBySid(ms.getS_id())==false) {
				pHservice.updateChickedBySidforzero(ms.getS_id());
			}
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	//是否存在一个关系
	public boolean isExistRelation(Relation ms) throws SQLException{
		String sql = "select * from pchrelation where s_id = ? and t_id = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ms.getS_id());
			pstm.setInt(2, ms.getT_id());
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
	//是否存在一个关系与s_id关联
	public boolean isExistRelationBySid(int s_id) throws SQLException{
		String sql = "select * from pchrelation where s_id = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, s_id);
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
	//根据t_id查找某个服务器的所有服务
	public ArrayList<Service> queryServiceByTid(int t_id) throws SQLException{
		ArrayList<Service> list = new ArrayList<>();
		String sql = "select * from pchrelation where t_id = ?";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, t_id);
	        ResultSet thingSet = pstm.executeQuery();
	        while (thingSet.next()) {
	        	int this_s_id = thingSet.getInt("s_id");
	        	Service bean = new Service();
	        	bean = pHservice.selectBySid(this_s_id);
	            list.add(bean);
	        }
	        return list;
	} 
}
