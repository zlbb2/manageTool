package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Preparation;
import com.jolbox.bonecp.BoneCPDataSource;

public class DaoService {
	public BoneCPDataSource src = null;	
	public JdbcSession jdbcSession = null;
	public Connection conn = null;
	public Statement stmt = null;
	
	public static void main(String[] args) {
		DaoService daoService = new DaoService();
		daoService.connect();
//		daoService.createToolDatabases();
		daoService.createTable();
		
		
		
		daoService.close();
	}

	// 1. connect
	public void connect() {
		src = new BoneCPDataSource();
        src.setDriverClass("com.mysql.cj.jdbc.Driver");
        src.setJdbcUrl("jdbc:mysql://localhost:3306/tool? characterEncoding=utf8 & useSSL=false & serverTimezone=UTC & rewriteBatchedStatements=true");
        src.setUser("root");
        src.setPassword("root");
        jdbcSession = new JdbcSession(src);
        try {
            conn = src.getConnection();
            stmt = conn.createStatement();
            System.out.println("connect ... ");
        }catch (Exception e){
            e.printStackTrace();
        }
        
        // test
        
	}

	// 2. close
	public void close() {
      if (stmt != null) {
			try {
				stmt.close();
				System.out.println("close the database ... ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
      if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// 3. create the tool databases
	public void createToolDatabases() {
		try {
//			PreparedStatement pstmt = conn.prepareStatement("show databases;",Statement.RETURN_GENERATED_KEYS);
			jdbcSession.sql("CREATE DATABASE IF NOT EXISTS tool DEFAULT CHARSET utf8")
				.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. create the table
	public void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS `tool_one`"
				+ "(" + 
				"   `t_id` INT UNSIGNED AUTO_INCREMENT," + 
				"   `t_name` VARCHAR(127)," + 
				"   `t_type` VARCHAR(127)," + 
				"   `t_product` VARCHAR(127)," +
				"   `t_isBorrowed` boolean," + 
				"   `t_isSelected` boolean," + 
				"   PRIMARY KEY ( `t_id` )" + 
				")DEFAULT CHARSET=utf8 COLLATE `utf8_general_ci`";
		try {
			stmt = conn.createStatement();
			boolean reset = stmt.execute(sql);
			if (reset) {
				System.out.println("create the tool_one succeed ...");
			}else {
				System.out.println("create table fail ...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (stmt != null) {
			try {
				stmt.close();
				System.out.println("statement is closed ... ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	
	
}
