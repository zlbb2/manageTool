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
//		daoService.createTable();
		Tool tool2 = new Tool();
		System.out.println(daoService.insertOneTool(tool2));
		System.out.println(daoService.insertOneTool(tool2));
		System.out.println(daoService.insertOneTool(tool2));
		System.out.println(daoService.insertOneTool(tool2));
		ArrayList<String> list = daoService.showAllTool();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
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
				e.printStackTrace();
			}
		}
      if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
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
	
	// 6. show all tools
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
	
	// 11. all selected tool set false
	public void initialSelected() {
		sql = "update tool_one set t_isSelected=false where t_id>0";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {

		}
	}

	// 12. get the boolean of is selected
	public boolean isSelectedById(int id) {
		sql = "select t_isSelected from tool_one where t_id = ?";
		boolean r = false;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("the tool is select : " + resultSet.getBoolean("t_isSelected"));
				r = resultSet.getBoolean("t_isSelected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}

	// 13. set the tool is selected
	public void setToolIsSelected(int id,boolean b) {
		updateIsSelected(id, b);
	}

	// 14. get selected tool amount
	public int getSelectedToolAmount() {
		sql = "select * from tool_one where t_isSelected=true";
		int amount = 0;
		try {
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				amount ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return amount;
	}
	
	// 15. create the borrow proof table
	public void createBorrowProofTable() {
		sql = "create table if not exists `borrow_proof` ("
				+ "`t_id` int unsigned auto_increment,"
				+ "`t_time` varchar(127),"
				+ "`t_givePeople` varchar(127),"
				+ "`t_borrowPeople` varchar(127),"
				+ "`t_toolList` varchar(1024),"
				+ "`t_isBack` boolean,"
				+ "`t_backTime` varchar(127),"
				+ "`t_backGivePeople` varchar(127),"
				+ "`t_backRecivePeople` varchar(127),"
				+ "`t_note` varchar(1024),"
				+ "primary key(`t_id`))DEFAULT CHARSET=utf8 COLLATE `utf8_general_ci`";
		
		try {
			stmt = conn.createStatement();
			boolean reset = stmt.execute(sql);
			if (reset) {
				System.out.println("create the borrow proof succeed ...");
			}else {
				System.out.println("create table fail ...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(sql);
		
	}

	// 16. get the selected tool id 
	public ArrayList<Integer> getSelectedToolIdList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		sql = "select t_id from tool_one where t_isSelected=true";
		try {
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				list.add(resultSet.getInt("t_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 17. save one borrow proof
	/*
	 *1 t_id
	 *2 t_time
	 *3 t_givePeople
	 *4 t_borrowPeople
	 *5 t_toolList
	 *6 t_isBack boolean
	 *7 t_backTime
	 *8 t_backGivePeople
	 *9 t_backRecivePeople
	 *10 t_note
	 */
	public void setBorrowProof(String timeString, 
			String borrowPeopleString, 
			String givePeopleString,
			ArrayList<Integer> list) {
		PreparedStatement prest = null;
		String sql = "insert into borrow_proof values(?,?,?,?,?"
												+ ",?,?,?,?,?)";
		
		String toolList = "";
		for (int i = 0; i < list.size(); i++) {
			toolList += list.get(i) + ",";
		}
		try {
			conn = src.getConnection();
			prest = conn.prepareStatement(sql);
			prest.setInt(1, 0);
			prest.setString(2, timeString);
			prest.setString(3, givePeopleString);
			prest.setString(4, borrowPeopleString);
			prest.setString(5, toolList);
			prest.setBoolean(6, false);
			prest.setString(7, null);
			prest.setString(8, null);
			prest.setString(9, null);
			prest.setString(10, null);
			
			prest.executeUpdate();
			System.out.println("insert a borrow proof ...");
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
		
	}

	// 18. all tool selected is b
	public void setAllToolSelected(boolean b) {
		String sql = "update tool_one set t_isSelected=? where t_id>0";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setBoolean(1, b);
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 19. set the tool of list is borrowed is b
	public void setToolOfListBorrowed(ArrayList<Integer> list, boolean b) {
		for (int i = 0; i < list.size(); i++) {
			updateIsBorrowed(list.get(i), b);
		}
	}

	// 20. check whether the tool is borrowed
	public boolean isBorrowedById(int id) {
		sql = "select t_isBorrowed from tool_one where t_id = ?";
		boolean r = false;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("the tool is borrowed : " + resultSet.getBoolean("t_isBorrowed"));
				r = resultSet.getBoolean("t_isBorrowed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}

	// 21. search tool by user input
	public ArrayList<String> searchToolByInfo(String toolName) {
		sql = "select * from `tool_one` where concat(ifnull(`t_name`,''),ifnull(`t_type`,''),ifnull(`t_product`,'')) like ?"; 
		ArrayList<String> r = new ArrayList<String>();
		try {
			preparedStatement = conn.prepareStatement(sql);
			// 设定参数
			preparedStatement.setString(1, "%" + toolName + "%" );        
			// 获取查询的结果集      
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				r.add(resultSet.getString("t_id"));
			}
			System.out.println("search .............. 3");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// 21. accord the id list get the tool list
	public ArrayList<String> getToolListByIdList(ArrayList<String> searchResult) {
		ArrayList<String> toolList = new ArrayList<String>();
		sql = "select * from tool_one where t_id = ?";
		String s = "";
		for (int i = 0; i < searchResult.size(); i++) {
			try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, searchResult.get(i));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				s = "" + resultSet.getString("t_id") + ",";
				s += resultSet.getString("t_type") + ",";
				s += resultSet.getString("t_product");
				System.out.println("search result ...... ");
				System.out.println(s);
			}
				toolList.add(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return toolList;
	}


	// 22. get all the borrow proof to display
	public ArrayList<String> getAllBorrowProof() {
		sql = "select * from borrow_proof";
		ArrayList<String> allBorrowProof = new ArrayList<String>();
		try {
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String id = resultSet.getString(BorrowProof.T_ID);
				String time = resultSet.getString(BorrowProof.T_TIME);
				String borrowPeople = resultSet.getString(BorrowProof.T_BORROW_PEOPLE);
				String givePeople = resultSet.getString(BorrowProof.T_GIVE_PEOPLE);
				allBorrowProof.add(id + "," + time + "," + borrowPeople + "," + givePeople);
			}
		} catch (Exception e) {

		}
		
		return allBorrowProof;
	}


	// 23. get tool id list by borrow proof id
	public ArrayList<Integer> getToolListByBorrowProofId(int id) {
		sql = "select t_toolList from borrow_proof where t_id=?";
		ArrayList<Integer> idList = new ArrayList<Integer>();
		String r = "";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				r = resultSet.getString("t_toolList");
//				System.out.println("t - tool list dao service ................ ");
//				System.out.println(r);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if (r.isEmpty()) {
			System.out.println("无工具");
		}else {
			String[] sp = r.split(",");
			for (int i = 0; i < sp.length; i++) {
				idList.add(Integer.parseInt(sp[i]));
			}
		}
		
		
		
		return idList;
	}


	// 24. accord the tool id list get the tool name list
	public ArrayList<String> getToolNameListByIdList(ArrayList<Integer> ids) {
		ArrayList<String> names = new ArrayList<String>();
		sql = "select t_name from tool_one where t_id=?";
		for (int i = 0; i < ids.size(); i++) {
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, ids.get(i));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					names.add(resultSet.getString("t_name"));
					System.out.println("t name tool name list by id list ...................... :  " + resultSet.getString("t_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return names;
	}


	// 25. get borrow proof give people when borrow tool by id
	public String getBorrowToolGivePeopleById(int id) {
		sql = "select t_givePeople from borrow_proof where t_id=?";
		String r = "";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				r = resultSet.getString("t_givePeople");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	// 26. get borrow proof borrow people when borrow tool by id
	public String getBorrowToolBorrowPeopleById(int id) {
		sql = "select t_borrowPeople from borrow_proof where t_id=?";
		String r = "";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				r = resultSet.getString("t_borrowPeople");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}


	// 27. get borrow time by id borrow proof
	public String getBorrowToolBorrowTimeById(int id) {
		sql = "select t_time from borrow_proof where t_id=?";
		String r = "";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				r = resultSet.getString("t_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}



	// 28. set borrow proof back by id
	public void setBorrowProofBackByToolId(int id,boolean state) {
		sql = "update tool_one set t_isBorrowed=? where t_id=?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setBoolean(1, state);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	// 29. set back tool info in the borrow proof table
	public void setBorrowProofBackInfo(String backTime, String backGivePeople, String backGetPeople, int id) {
		sql = "update borrow_proof set t_isBack=?,t_backTime=?,t_backGivePeople=?,t_backRecivePeople=? where t_id=?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, backTime);
			preparedStatement.setString(3, backGivePeople);
			preparedStatement.setString(4, backGetPeople);
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	// 30. get the state of borrow proof is back 
	public boolean isBorrowProofBack(int borrowProofId) {
		sql = "select t_isBack from borrow_proof where t_id=?";
		boolean r = false;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, borrowProofId);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {
				r = resultSet.getBoolean("t_isBack");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}



	// 31. get borrow proof is back state
	public boolean getBorrowProofIsBackStateById(int id) {
		boolean r = false;
		sql = "select t_isBack from borrow_proof where t_id=?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				r = resultSet.getBoolean("t_isBack");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}



	// 32. get the borrow proof time people info
	public String getBorrowProofMoreInfoWhenIsBack(int id) {
		String r = "";
		sql = "select * from borrow_proof where t_id=?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				r += "归还时间： " + resultSet.getString("t_backTime") + "\n";
				r += "归还人： " + resultSet.getString("t_backGivePeople") + "\n";
				r += "接收人： " + resultSet.getString("t_backRecivePeople") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
