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
 * ����id�޸���Ʒ����Ϣ
 */

public class ProdUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//ͨ����������������
		//��ȡ�޸ĺ����Ʒ��Ϣ
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		double price = Double.parseDouble(request.getParameter("price"));
		int pnum= Integer.parseInt(request.getParameter("pnum"));
		String description = request.getParameter("description");
		//����id���޸�ָ����Ʒ��Ϣ
		updateProdById(id,name,category,price,pnum,description);
		//��ʾ�û���Ʒ�޸ĳɹ�
		PrintWriter out = response.getWriter();
		out.write("<h1>");
		out.write("��Ʒ�޸ĳɹ���3�����ת����Ʒ�б�ҳ��");
		out.write("</h1>");
		//��ʱˢ�£���ת���б�ҳ��
		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/ProdListServlet");
	}

	/**
	 * �޸���Ʒ��Ϣ
	 * @param id
	 * @param name
	 * @param category   ����
	 * @param price
	 * @param pnum
	 * @param description  ����
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
			System.out.println("�޸�"+rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�޸�ʧ��");
			throw new RuntimeException("��Ʒ�޸�ʧ��");
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
