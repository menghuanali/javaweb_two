<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pch_modle.*" %>
<%@ page import="bean.*" %>
<%@ page import="pch_servlet.*" %>
<%@ page import="java.util.ArrayList"%>
<%
	PCHservice s_model = new PCHservice();
	ArrayList<Service> s_lists = new ArrayList<>();
	s_lists = s_model.queryService();
	
	PCHcontainer t_model = new PCHcontainer();
	ArrayList<Container> t_lists = new ArrayList<>();
	t_lists = t_model.queryAllContainers();
	
 %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PCH服务管理网站</title>
    <link rel="stylesheet" href="./css/pch.css">
    <link rel="stylesheet" href="./css/jquery-ui.css">
    <!-- 先引入 -->
    <script src="./js/jquery-3.4.0.js"></script>
    <!-- 后引入 -->
    <script src="./js/jquery-ui.js"></script>
   
</head>
<body>
    <div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                服务管理网站
            </div>
            <div id="search" class="v-center">
                <input type="text">
            </div>
            <div id="double-link" class="v-center">

                <a href="#" id="btn-register" class="v-link">注册</a>
                <span class="v-link">|</span>
                <a href="#" id="btn-login" class="v-link">登陆</a>

            </div>
        </div>
    </div>

    <div id="content" class="clearfix">
        <div id="content-left">
            <div id="add-service">
                <input type="text" name="service-name" id="add-name">
                <button type="submit" id="add-submit">添加服务</button>
            </div>
            <div id="service-lists">
	           <% for(Service temp : s_lists){ %>
		           <div class="a-service">
		                   <div class="a-service-name" onclick="click_chick(this)">
		                   <%= temp.getS_name() %>
		                   <span style="display: none;" class="delete-it"><%= temp.getS_id() %></span>
		                   </div>
		                   <% if (temp.getS_choice() == 1 ) { %>
		                   <button class="a-service-btnno"></button>
						<% } else { %>
		  					  <button class="a-service-btn" onclick="delete_service(this)">删除<span style="display: none;" class="delete-it"><%= temp.getS_id() %></span></button>
					<% } %>
		           </div>
	           <%} %>
            </div>
        </div>
        <!-- 左边未选中提示 -->
        <div id="left-error-dialog">
            你还没有选中任何服务用来添加
        </div>
    	<!-- 右边未选中提示 -->
        <div id="right-insert-error-dialog">
            你还没有选中任何服务器用来添加
        </div>
        <!-- 右边未选中提示 -->
        <div id="right-error-dialog">
            你还没有选中任何服务用来卸载
        </div>
        <!-- 重复添加一个服务 -->
        <div id="repeat-error-dialog">
            请检查你选择的服务是否重复添加
        </div>
        <div id="content-center">
            <button id="add-btn">添加〉</button>
            <button id="delete-btn">〈卸载</button>
        </div>
        <div id="content-right">
         <% for(Container temp : t_lists){ %>
            <div class="a-the-service">
                <input type="checkbox" name="one-service" class="the-oneservice" value="<%= temp.getT_id()%>"><%= temp.getT_name() %>
                <% for(int i=-1;i<temp.getSonServices().size();i++) {%>
                <% if(i==-1){ %>
                <div class="one-service-name">
                    原始服务1
                </div>
                <div class="one-service-name">
                    原始服务2
                </div>
                <%}else{ %>
                	<div class="one-service-name" onclick="click_chick(this)">
                   <%= temp.getSonServices().get(i).getS_name() %>
                   <span style="display: none;" class="delete-it"><%= temp.getSonServices().get(i).getS_id() %></span>
                   <span style="display: none;" class="delete-it2"><%= temp.getT_id() %></span>
                	</div>
                <%} %>
                <%} %>
            </div>
            <%} %>
        </div>
    </div>


    <div id="footer">
        <div id="footer-content">
            <div id="updata">
                ©1997-2019
            </div>
            <div id="copyright">
                公司版权所有
            </div>
            <div id="footer-links">
                <ul class="">
                    <li class="footer-space">|</li>
                    <li><a href="#">公司简介</a></li>
                    <li class="footer-space">|</li>
                    <li><a href="#">联系方法</a></li>
                    <li class="footer-space">|</li>
                    <li><a href="#">招聘信息</a></li>
                    <li class="footer-space">|</li>
                    <li><a href="#">客户服务</a></li>
                    <li class="footer-space">|</li>
                    <li><a href="#">隐私政策</a></li>
                    <li class="footer-space">|</li>
                    <li><a href="#">广告服务</a></li>
                    <li class="footer-space">|</li>
                    <li><a href="#">网站地图</a></li>
                    <li class="footer-space">|</li>
                    <li><a href="#">不良信息举报</a></li>
                </ul>
            </div>
        </div>
    </div>
     <script src="./js/pch.js"></script>
</body>
</html>