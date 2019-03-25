package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcabi.jdbc.JdbcSession;
import com.jolbox.bonecp.BoneCPDataSource;

public class DaoService {
	public BoneCPDataSource src = null;	
	public JdbcSession jdbcSession = null;
	public Connection conn = null;
	public Statement stmt = null;
	
	public static void main(String[] args) {
		DaoService daoService = new DaoService();
		daoService.connect();
		
	}

	public void connect() {
		src = new BoneCPDataSource();
		src = new BoneCPDataSource();
        src.setDriverClass("com.mysql.cj.jdbc.Driver");
        src.setJdbcUrl("jdbc:mysql://localhost:3306/world?characterEncoding=utf8");
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
        if (stmt != null) {
			try {
				stmt.close();
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
	
	
}
