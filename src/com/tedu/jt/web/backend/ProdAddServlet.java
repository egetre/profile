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
 * ��Ʒ�������
 */

public class ProdAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		//���������������(post)
		//request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		//��ȡ��Ҫ��ӵ���Ʒ��Ϣ�����������
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		double price = Double.parseDouble(request.getParameter("price"));
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		String description = request.getParameter("description");
		
		//����Ʒ��Ϣ���������ݿ��б���
		addProd(name,category,price,pnum,description);
		//��ʾ�û���Ʒ��ӳɹ�3�����ת����Ʒ���б�ҳ��
		PrintWriter out = response.getWriter();
		out.write("<h1 style='color:green;margin:5px 15px'>");
		out.write("��Ʒ��ӳɹ���3�����ת����Ʒ���б���...");
		out.write("</h1>");
		//��ʱˢ�£�3�����ת����Ʒ�б�ҳ��
		//��������ʾ������Ʒ��Ϣ���ȷ���prodListServlet��ѯ,�ٵ�ProdServletת������Ʒ����������ʾ
		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/ProdListServlet");
	}
/**
 * ����Ʒ��Ϣ���浽���ݿ���
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
			//��ȡ���ݿ�����
			conn = JDBCUtils.getConn();
			//����SQL�Ǽ�
			String sql = "insert into product values(null,?,?,?,?,?)";
			//��ȡ������
			ps = conn.prepareStatement(sql);
			//����SQL����
			ps.setString(1, name);
			ps.setString(2, category);
			ps.setDouble(3, price);
			ps.setInt(4, pnum);
			ps.setString(5, description);
			//ִ��SQL
			int rows = ps.executeUpdate();
			System.out.println("rows" + rows);
		} catch (Exception e) {
			e.printStackTrace();//��ӡ�쳣��Ϣ
			System.out.println("��Ʒ���ʧ�ܣ���");
			throw new RuntimeException("��Ʒ���ʧ�ܣ���");
		}finally{
			//�ͷ���Դ
			JDBCUtils.close(conn, ps, null);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		doGet(request, response);
	}

}
