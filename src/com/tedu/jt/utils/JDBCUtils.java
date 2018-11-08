package com.tedu.jt.utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * JDBC工具类
 */
public class JDBCUtils {
	/**
	 * 2.创建c3p0连接池实例
	 */
	private static ComboPooledDataSource pool 
					= new ComboPooledDataSource();

	/**
	 * 1.私有化构造函数
	 */
	private JDBCUtils() {}

	/**
	 * 3.提供getConn方法, 用于从连接池中获取一个连接对象
	 * @return Connection 连接对象
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
	 * 4.提供close方法, 用于释放资源
	 * @param conn 连接对象
	 * @param ps 传输器对象
	 * @param rs 结果集对象
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
