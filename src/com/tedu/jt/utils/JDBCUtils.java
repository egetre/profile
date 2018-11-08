package com.tedu.jt.utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * JDBC������
 */
public class JDBCUtils {
	/**
	 * 2.����c3p0���ӳ�ʵ��
	 */
	private static ComboPooledDataSource pool 
					= new ComboPooledDataSource();

	/**
	 * 1.˽�л����캯��
	 */
	private JDBCUtils() {}

	/**
	 * 3.�ṩgetConn����, ���ڴ����ӳ��л�ȡһ�����Ӷ���
	 * @return Connection ���Ӷ���
	 * @throws Exception
	 */
	public static Connection getConn() throws Exception {
		try {
			return pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 4.�ṩclose����, �����ͷ���Դ
	 * @param conn ���Ӷ���
	 * @param ps ����������
	 * @param rs ���������
	 */
	public static void close(Connection conn, Statement stat,
			ResultSet rs) {
		if(rs != null ){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(stat != null ){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat = null;
			}
		}
		if(conn != null ){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
}
