package pch_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Container;
import bean.Relation;
import bean.Service;
import pch_modle.PCHcontainer;
import pch_modle.PCHrelation;
import pch_modle.PCHservice;


/**
 * Servlet implementation class PCHrelationInsert
 */
public class PCHrelationInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PCHrelationInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String jsonString = request.getParameter("relation_array");
//		System.out.println(jsonString);
		Gson gson = new Gson(); 
		Integer[][] jsonArr = gson.fromJson(jsonString, Integer[][].class);//转换
//		System.out.println(jsonArr.length);
		PCHrelation pcHrelation;
		PCHservice pcHservice;
		PCHcontainer pcHcontainer;
		try {
			pcHrelation = new PCHrelation();
			pcHservice = new PCHservice();
			pcHcontainer = new PCHcontainer();
			for(int i=0;i<jsonArr.length;i++) {
				if(jsonArr[i]==null) {
				}else {
					for(int j=0;j<jsonArr[i].length;j++) {
						System.out.println(i+" "+jsonArr[i][j]);//t_id s_id
						Relation a_relation = new Relation();
						a_relation.setS_id(jsonArr[i][j]);
						a_relation.setT_id(i);
						if(pcHrelation.isExistRelation(a_relation)==true) {
							response.getWriter().write("你所添加的关系中有重复!：服务 "+jsonArr[i][j]+" 和服务器 "+i);
							return;
						}
					}
				}
			}
			System.out.println("检查完毕");
			for(int i=0;i<jsonArr.length;i++) {
				if(jsonArr[i]==null) {
				}else {
					for(int j=0;j<jsonArr[i].length;j++) {
						System.out.println(i+" "+jsonArr[i][j]);//t_id s_id
						Relation a_relation = new Relation();
						a_relation.setS_id(jsonArr[i][j]);
						a_relation.setT_id(i);
						if(pcHrelation.insertRelation(a_relation)==1) {
							pcHservice.updateChickedBySid(jsonArr[i][j]);
						}else {
							response.getWriter().write("插入错误");
						}
					}
				}
			}
			ArrayList<Service> resultList_s = new ArrayList<>();
			resultList_s = pcHservice.queryService();
			// 将list集合变成jsonArray 但是这个方法直接卡住 我太难了
//			JsonArray array_s = gson.toJsonTree(resultList_s, new TypeToken<List<Service>>() {}.getType()).getAsJsonArray();
			String str_s = gson.toJson(resultList_s);//将list集合变成json字符串
			System.out.println(str_s);
			
			ArrayList<Container> resultList_t = new ArrayList<>();
			resultList_t = pcHcontainer.queryAllContainers();
//			JsonArray array_t = gson.toJsonTree(resultList_t, new TypeToken<List<Container>>() {}.getType()).getAsJsonArray();
			String str_t = gson.toJson(resultList_t);//将list集合变成json字符串
			System.out.println(str_t);
			
//			JsonObject outData = new JsonObject();
//			outData.add("service", array_s);
//			outData.add("container", array_t);
//			System.out.println(gson.toJson(outData));
			System.out.println("{ \"service\" :"+str_s+","+"\"container\":"+str_t+"}");
			response.getWriter().write("{ \"service\" :"+str_s+","+"\"container\":"+str_t+"}");
//			response.getWriter().write("成功");
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
