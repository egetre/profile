package com.tedu.jt.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.jt.utils.JDBCUtils;

/**
 * 删除指定文档
 */
public class ProdDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		//获取商品的id
		int id = Integer.parseInt(request.getParameter("id"));
		//根据id删除商品信息
		deleteProdById(id);
		//提示用户商品删除成功，
		PrintWriter out = response.getWriter();
		out.write("<h1 style='color:green;margin:15px 15px'>");
		out.write("商品删除成功,3秒后将会跳到商品管理 页面");
		out.write("</h1>");
		//定时刷新
		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/ProdListServlet");
	}
	
	private void deleteProdById(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn= JDBCUtils.getConn();
			String sql = "delete  from product where id=?";
			 ps = conn.prepareStatement(sql);
			 ps.setInt(1, id);
			 int rows = ps.executeUpdate();
			 System.out.println("删除成功: " + rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("删除失败");
			throw new RuntimeException("商品删除失败");
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		
		doGet(request, response);
	}

}
