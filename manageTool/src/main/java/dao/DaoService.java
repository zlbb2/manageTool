package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.jcabi.jdbc.JdbcSession;
import com.jolbox.bonecp.BoneCPDataSource;

public class DaoService {
	public BoneCPDataSource src = null;	
	public JdbcSession jdbcSession = null;
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet resultSet = null;
	public PreparedStatement preparedStatement = null;
	String sql = "";
	
	public static void main(String[] args) {
		DaoService daoService = new DaoService();
		daoService.connect();
//		daoService.createToolDatabases();
		daoService.createTable();
//		Tool tool2 = new Tool();
//		System.out.println(daoService.insertOneTool(tool2));
//		ArrayList<String> list = daoService.showAllTool();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		daoService.updateIsBorrowed(1, true);
//		daoService.updateIsBorrowed(2, true);
//		daoService.updateIsBorrowed(3, true);
//		daoService.updateIsBorrowed(4, true);
//		daoService.deleteTool(1);
//		daoService.deleteTool(2);
//		daoService.deleteTool(3);
//		daoService.deleteTool(4);
		
		System.out.println("total : " + daoService.getTotal());
		
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
		System.out.println(sql);
		
	}

	// 5. insert data into table
	public String insertOneTool(Tool tool) {
		PreparedStatement prest = null;
		String sql = "insert into tool_one values(?,?,?,?,?,?)";
		try {
			conn = src.getConnection();
			prest = conn.prepareStatement(sql);
			prest.setInt(1, 0);
			prest.setString(2, tool.t_name);
			prest.setString(3, tool.t_type);
			prest.setString(4, tool.t_product);
			prest.setBoolean(5, tool.t_isBorrowed);
			prest.setBoolean(6, tool.t_isSelected);
			prest.executeUpdate();
			System.out.println("insert a tool ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (prest != null) {
			try {
				prest.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return tool.toString();
	}
	
	// 6. select the tool
	public ArrayList<String> showAllTool() {
		ArrayList<String> allTool = new ArrayList<String>();
		try {
			resultSet = stmt.executeQuery("select * from tool_one");
			while(resultSet.next()){
				String id = resultSet.getString(Tool.T_ID);
				String name = resultSet.getString(Tool.T_NAME);
				String type = resultSet.getString(Tool.T_TYPE);
				String product = resultSet.getString(Tool.T_PRODUCT);
				System.out.println("id:" + id
						+ " name: " + name
						+ " type: "+ type 
						+ " product : " + product);
				allTool.add(id + "," + name + "," + type + "," + product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTool;
	}
	
	// 7. update the is_borrowed
	public boolean updateIsBorrowed(int id,boolean isBorrowed) {
		String sql = "update tool_one set t_isBorrowed=? where t_id =?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setBoolean(1, isBorrowed);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isBorrowed;
	}
	
	// 8. update the is_selected
	public boolean updateIsSelected(int id,boolean isSelected) {
		String sql = "update tool_one set t_isSelected=? where t_id =?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setBoolean(1, isSelected);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSelected;
	}
	
	// 9. delete the tool accord id
	public void deleteTool(int t_id) {
		sql = "delete from tool_one where t_id =?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, t_id);
			preparedStatement.executeUpdate();
			System.out.println("delete from tool_one where t_id = " + t_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 10. get the total of tool
	public int getTotal() {
		sql = "select * from tool_one";
		int row = 0 ;
		try {
			resultSet = stmt.executeQuery(sql);
			resultSet.last();
			row = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
