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
 * 根据id修改商品的信息
 */

public class ProdUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//通过过滤器处理乱码
		//获取修改后的商品信息
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		double price = Double.parseDouble(request.getParameter("price"));
		int pnum= Integer.parseInt(request.getParameter("pnum"));
		String description = request.getParameter("description");
		//根据id来修改指定商品信息
		updateProdById(id,name,category,price,pnum,description);
		//提示用户商品修改成功
		PrintWriter out = response.getWriter();
		out.write("<h1>");
		out.write("商品修改成功，3秒后跳转到商品列表页面");
		out.write("</h1>");
		//当时刷新，跳转到列表页面
		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/ProdListServlet");
	}

	/**
	 * 修改商品信息
	 * @param id
	 * @param name
	 * @param category   分类
	 * @param price
	 * @param pnum
	 * @param description  描述
	 */
	private void updateProdById(int id, String name, String category, double price, int pnum, String description) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "update product set name=?,category=?,price=?,pnum=?,description=? where id=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setString(2, category);
			ps.setDouble(3, price);
			ps.setInt(4, pnum);
			ps.setString(5, description);
			ps.setInt(6, id);
			int rows = ps.executeUpdate();
			System.out.println("修改"+rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改失败");
			throw new RuntimeException("商品修改失败");
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
