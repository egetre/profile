package com.tedu.jt.web.backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.jt.utils.JDBCUtils;
/**
 * ������Ʒ��id����ѯ��Ʒ����Ϣ
 */

public class ProdInfoSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		//��ȡ��Ʒ��id
		int id = Integer.parseInt(request.getParameter("id"));
		//����id��ѯ��Ʒ��Ϣ��product��
		Product prod = findProdById(id);
		//����Ʒ����浽����
		request.setAttribute("prod", prod);
		//ת��������Ʒ��Ϣ����prod_upd.jsp;�л���
		request.getRequestDispatcher("/backend/prod_upd.jsp").forward(request, response);
	}

	
	private Product findProdById(int id) {
		Connection  conn= null;
		PreparedStatement ps = null;
		ResultSet  rs = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from product where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Product prod = null;
			if (rs.next()) {
				prod = new Product(
						id, 
						rs.getString("name"),
						rs.getString("category"),
						rs.getDouble("price"), 
						rs.getInt("pnum"), 
						rs.getString("description"));
			}
			return prod;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ѯʧ��");
			throw new RuntimeException("��Ʒ��ѯʧ��");
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
