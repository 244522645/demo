package test.com.ybt.base;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ybt.dao.base.BaseDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class TestCase extends junit.framework.TestCase {

	/**
	 * 
	 * <p>
	 * 根据sql语句查询结果集,查询当前连接的结果及
	 * </p>
	 * <p>
	 * 初编时间 2015年6月12日 下午4:16:08
	 * </p>
	 * 
	 * @param sql
	 * @return 查询到的结果集,数据结构为列表内内嵌哈希表。
	 * @throws SQLException
	 */
	public List<Hashtable<String, Object>> queryBySql(final StringBuffer sql) throws SQLException {
		return queryBySql(sql.toString());
	}

	/**
	 * 
	 * <p>
	 * 根据sql语句查询结果集,查询当前连接的结果及
	 * </p>
	 * <p>
	 * 初编时间 2015年6月12日 下午4:16:22
	 * </p>
	 * 
	 * @param sql
	 * @param values
	 * @return 查询到的结果集,数据结构为列表内内嵌哈希表。
	 * @throws SQLException
	 */
	public List<Hashtable<String, Object>> queryBySql(final String sql, Object[] values) throws SQLException {

		if (null == sql)
			return null;
		if (sql.concat(" ").split("\\?").length != (values.length + 1))
			throw new SQLException("错误参数数目不正确。");
		PreparedStatement statement = null;
		Connection conn = DBA.getConn();
		ResultSet rs = null;
		List<Hashtable<String, Object>> list = new ArrayList<Hashtable<String, Object>>();
		try {
			statement = conn.prepareStatement(sql);
			for (int ii = 0; ii < values.length; ii++) {
				Object value = values[ii];
				if (null == value) {
					statement.setObject(ii + 1, null);
				} else if (value instanceof Double) {
					statement.setDouble(ii + 1, (Double) value);
				} else if (value instanceof java.math.BigDecimal) {
					statement.setBigDecimal(ii + 1, new BigDecimal(value.toString()));
				} else if (value instanceof Float) {
					statement.setDouble(ii + 1, Float.valueOf(value.toString()));
				} else if (value instanceof Long) {
					statement.setLong(ii + 1, Long.valueOf(value.toString()));
				} else if (value instanceof Number || value instanceof Integer) {
					statement.setBigDecimal(ii + 1, new BigDecimal(value.toString()));
				} else if (value instanceof java.sql.Date) {
					statement.setTimestamp(ii + 1, new java.sql.Timestamp(((java.sql.Date) value).getTime()));
				} else if (value instanceof java.util.Date) {
					statement.setTimestamp(ii + 1, new java.sql.Timestamp(((java.util.Date) value).getTime()));
				} else {// String
					statement.setString(ii + 1, value.toString());
				}
			}
			rs = statement.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next()) {
				Hashtable<String, Object> ht = new Hashtable<String, Object>();
				for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
					Object obj = rs.getObject(i);
					if (obj != null)
						ht.put(rsmd.getColumnName(i), obj);
				}
				list.add(ht);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != statement)
					statement.close();
			} catch (SQLException e) {
				rs = null;
				statement = null;
			}
			DBA.closeConn(conn);
		}
		return list;
	}

	/**
	 * 
	 * <p>
	 * 根据sql语句查询结果集,查询当前连接的结果及
	 * </p>
	 * <p>
	 * 初编时间 2015年6月12日 下午4:16:46
	 * </p>
	 * 
	 * @param sql
	 * @return 查询到的结果集,数据结构为列表内内嵌哈希表。
	 * @throws SQLException
	 */
	public List<Hashtable<String, Object>> queryBySql(final String sql) throws SQLException {

		if (null == sql)
			return null;
		Statement statement = null;
		Connection conn = DBA.getConn();
		ResultSet rs = null;
		List<Hashtable<String, Object>> list = new ArrayList<Hashtable<String, Object>>();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next()) {
				Hashtable<String, Object> ht = new Hashtable<String, Object>();
				for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
					Object obj = rs.getObject(i);
					if (obj != null)
						ht.put(rsmd.getColumnName(i), obj);
				}
				list.add(ht);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != statement)
					statement.close();
			} catch (SQLException e) {
				rs = null;
				statement = null;
			}
			DBA.closeConn(conn);
		}
		return list;
	}

}

class DBA {
	private static final String dbconf = "/ghsdk.dbcp.properties";
	private static DataSource dataSource;
	static {
		Properties pro = new Properties();
		try {
			InputStream is = BaseDao.class.getResourceAsStream(dbconf);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			pro.load(br);
			dataSource = BasicDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 数据库操作者
	 * </p>
	 */
	private DBA() {
	}

	/**
	 * 
	 * <p>
	 * 获取链接，用完后记得关闭
	 * </p>
	 * <p>
	 * 初编时间 2015年6月12日 下午4:17:59
	 * </p>
	 * 
	 * @return dbcp连接池中获取一个有效连接
	 */
	public static final Connection getConn() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 
	 * <p>
	 * 关闭连接
	 * </p>
	 * <p>
	 * 初编时间 2015年6月12日 下午4:18:14
	 * </p>
	 * 
	 * @param conn
	 */
	public static void closeConn(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
