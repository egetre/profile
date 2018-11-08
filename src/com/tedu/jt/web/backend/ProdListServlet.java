package com.tedu.jt.web.backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.jt.utils.JDBCUtils;
/**
 * 查询所有商品信息
 */

public class ProdListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		//查询出所有商品信息
		List<Product> list = findProdList();
		
		System.out.println("list: "+list);
		//将所有商品存到request域中，转发到JSP显示
		request.setAttribute("list", list);
		request.getRequestDispatcher("/backend/prod_list.jsp").forward(request, response);
	}
	/**
	 * 查询所有的商品信息，以list集合返回
	 * @param id 
	 * @return List<Product>
	 */
	private List<Product> findProdList() {
		Connection conn= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn=JDBCUtils.getConn();
			String sql = "select * from product";
			ps = conn.prepareStatement(sql);
			List<Product> list = new ArrayList<>();
			Product prod = null;
			rs = ps.executeQuery();
			while(rs.next()){
				//遍历的记录封装到prod中
				prod = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getInt("pnum"), rs.getString("description"));
				//将prod存入到list中
				list.add(prod);
			}
			/*rs = ps.executeQuery();
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getDouble("price"));
				p.setPnum(rs.getInt("pnum"));
				p.setDescription(rs.getString("description"));
				list.add(p);
			}*/
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("商品查询失败");
			throw new RuntimeException("商品查询失败");
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		//return null;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		doGet(request, response);
	}

}
