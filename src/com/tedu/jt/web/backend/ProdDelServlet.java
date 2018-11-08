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
 * ɾ��ָ���ĵ�
 */
public class ProdDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		//��ȡ��Ʒ��id
		int id = Integer.parseInt(request.getParameter("id"));
		//����idɾ����Ʒ��Ϣ
		deleteProdById(id);
		//��ʾ�û���Ʒɾ���ɹ���
		PrintWriter out = response.getWriter();
		out.write("<h1 style='color:green;margin:15px 15px'>");
		out.write("��Ʒɾ���ɹ�,3��󽫻�������Ʒ���� ҳ��");
		out.write("</h1>");
		//��ʱˢ��
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
			 System.out.println("ɾ���ɹ�: " + rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ɾ��ʧ��");
			throw new RuntimeException("��Ʒɾ��ʧ��");
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		
		doGet(request, response);
	}

}
