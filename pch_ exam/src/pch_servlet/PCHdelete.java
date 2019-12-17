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

import bean.Service;
import pch_modle.PCHservice;

/**
 * Servlet implementation class PCHdelete
 */
public class PCHdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PCHdelete() {
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
		String idString = request.getParameter("s_id");
		int s_id = Integer.parseInt(idString);
		System.out.println("删除的id "+s_id);
		PCHservice pcHservice;
		try {
			pcHservice = new PCHservice();
			int r = pcHservice.deleteService(s_id);
			System.out.println("返回结果"+r);
			if(r==1) {
				ArrayList<Service> resultList = new ArrayList<>();
				resultList = pcHservice.queryService();
				// 将list集合变成json格式
				Gson gson = new Gson();
				String str = gson.toJson(resultList);
				System.out.println(str);
				response.getWriter().write(str);

			}else {
				response.getWriter().write("删除失败");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
