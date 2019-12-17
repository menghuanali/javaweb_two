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
 * Servlet implementation class PCHservlet
 */
//这里选的2.5 就不加@WebServlet("/PCHservlet")
public class PCHservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PCHservlet() {
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
		String nameString = request.getParameter("s_name");
		System.out.println(nameString);
		Service newService = new Service();
		newService.setS_name(nameString);
		PCHservice pcHservice;
		try {
			pcHservice = new PCHservice();
			int r = pcHservice.insertService(newService);
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
//				ArrayList<Service> resultList = new ArrayList<>();
//				resultList = pcHservice.queryService();
//				// 将list集合变成json格式
//				Gson gson = new Gson();
//				String str = gson.toJson(resultList);
//				System.out.println(str);
//				response.getWriter().write(str);
				response.getWriter().write("你插入的服务已存在");
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
