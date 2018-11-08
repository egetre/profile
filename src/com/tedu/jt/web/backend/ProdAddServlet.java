package com.tedu.jt.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ranges.RangeException;

import com.tedu.jt.utils.JDBCUtils;
/**
 * 商品添加请求
 */

public class ProdAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		//处理请求参数乱码(post)
		//request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		//获取将要添加的商品信息（请求参数）
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		double price = Double.parseDouble(request.getParameter("price"));
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		String description = request.getParameter("description");
		
		//将商品信息调调到数据库中保存
		addProd(name,category,price,pnum,description);
		//提示用户商品添加成功3秒后跳转到商品的列表页面
		PrintWriter out = response.getWriter();
		out.write("<h1 style='color:green;margin:5px 15px'>");
		out.write("商品添加成功，3秒后跳转到商品的列表面...");
		out.write("</h1>");
		//定时刷新，3秒后跳转到商品列表页面
		//添加完后，显示所有商品信息，先访问prodListServlet查询,再到ProdServlet转发到商品管理，进行显示
		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/ProdListServlet");
	}
/**
 * 将商品信息保存到数据库中
 * @param name
 * @param category
 * @param price
 * @param pnum
 * @param description
 */
	private void addProd(String name, String category, double price, int pnum, String description) {
		Connection  conn =null;
		PreparedStatement ps = null;
		try {
			//获取数据库连接
			conn = JDBCUtils.getConn();
			//声明SQL骨架
			String sql = "insert into product values(null,?,?,?,?,?)";
			//获取传输器
			ps = conn.prepareStatement(sql);
			//设置SQL参数
			ps.setString(1, name);
			ps.setString(2, category);
			ps.setDouble(3, price);
			ps.setInt(4, pnum);
			ps.setString(5, description);
			//执行SQL
			int rows = ps.executeUpdate();
			System.out.println("rows" + rows);
		} catch (Exception e) {
			e.printStackTrace();//打印异常信息
			System.out.println("商品添加失败！！");
			throw new RuntimeException("商品添加失败！！");
		}finally{
			//释放资源
			JDBCUtils.close(conn, ps, null);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		doGet(request, response);
	}

}
