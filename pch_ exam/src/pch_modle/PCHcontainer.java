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

import bean.Container;
import bean.Service;

public class PCHcontainer {
	Connection conn = null;
	PCHrelation pcHrelation = new PCHrelation();
	public PCHcontainer() throws NamingException, SQLException{
		conn=JDBCUtils.getConnection();
		System.out.println(111);
	}
	//查询所有服务器以及他们的服务
	public ArrayList<Container> queryAllContainers()throws SQLException{
		ArrayList<Container> list = new ArrayList<>();
		String sql = "select * from pchcontainer";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
	        ResultSet thingSet = pstm.executeQuery();
	        while (thingSet.next()) {
	        	Container container = new Container();
	        	int this_t_id = thingSet.getInt("t_id");
	        	container.setT_id(this_t_id);
	        	container.setT_name(thingSet.getString("t_name"));
	        	ArrayList<Service> sList = new ArrayList<>();
	        	sList = pcHrelation.queryServiceByTid(this_t_id);
	        	container.setSonServices(sList);
	        	list.add(container);
	        }
//	        System.out.println(list.size());
//	        System.out.println(list.get(0).getSonServices().size());
	        return list;
	}
	
}
