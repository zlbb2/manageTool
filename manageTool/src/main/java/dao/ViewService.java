package dao;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewService {
	DaoService daoService = null;
	public int totalTool = 1;
	public int totalPage = 1;
	public int nowPage = 1;
	// all tools
	ArrayList<String> allTool = new ArrayList<String>();
	// borrowing car
	public String borrowingCar = "";
	// default administrator
	public static final String ADMIN = "管理员";
	// default borrowed
	public static final String BORROWER = "工具领用人";
	// all borrow proof
	ArrayList<String> allBorrowProof = new ArrayList<String>();
	// borrow proof total page
	public int borrowProofTotalPage = 1;
	public int borrowProofNowPage = 1;
	
	public ViewService () {
		daoService = new DaoService();
		daoService.connect();
		// tool one
		allTool = daoService.showAllTool();
		totalPage = allTool.size() / 8 + 1;
		totalTool = allTool.size();
		// borrow proof
		allBorrowProof = daoService.getAllBorrowProof();
		borrowProofTotalPage = allBorrowProof.size() / 8 + 1;
		
		System.out.println("load view service , tool amount : " + allTool.size());
	}

	// 1. display tool initial time
	public void displayTool(ArrayList<JTextField> textFieldArray) {
		
		// 1. clean the display
		for (int i = 0; i < textFieldArray.size(); i++) {
			textFieldArray.get(i).setText("");
			textFieldArray.get(i).setBackground(Color.WHITE);
		}
		if (allTool.size() > 8) {
			for (int j = 0; j < 8; j++) {
				textFieldArray.get(j).setText(allTool.get(j));
				colorTips(textFieldArray.get(j));
			}
		}else {
			for (int i = 0; i < allTool.size(); i++) {
				textFieldArray.get(i).setText(allTool.get(i));
				colorTips(textFieldArray.get(i));
			}
		}
	}

	// 1-2. display borrow proof initial time
	public void displayBorrowProof(ArrayList<JTextField> textFieldArray) {
		// 1. clean the display
				for (int i = 0; i < textFieldArray.size(); i++) {
					textFieldArray.get(i).setText("");
					textFieldArray.get(i).setBackground(Color.WHITE);
				}
				if (allBorrowProof.size() > 8) {
					for (int j = 0; j < 8; j++) {
						textFieldArray.get(j).setText(allBorrowProof.get(j));
						textFieldArray.get(j).setBackground(Color.WHITE);
					}
				}else {
					for (int i = 0; i < allBorrowProof.size(); i++) {
						textFieldArray.get(i).setText(allBorrowProof.get(i));
						textFieldArray.get(i).setBackground(Color.WHITE);
					}
				}
	}

	// 2. display page number
	public void displayPageNumber(JTextField textField_9,int nowPage) {
		totalTool = allTool.size();
		totalPage = totalTool / 8 + 1;
		textField_9.setText(nowPage + " / " + totalPage);
	}

	// 2-2. borrow proof display page number
	public void borrowProofDisplayPageNumber(JTextField pageNumber,int nowPage) {
		borrowProofTotalPage = allBorrowProof.size() / 8 + 1;
		pageNumber.setText(nowPage + " / " + borrowProofTotalPage);
	}
	
	// 3. last page
	public void lastPage(ArrayList<JTextField> textFieldArray, JTextField textField_9, JFrame jFrame) {
		// TODO color tips
		clearColor(textFieldArray);
		if (nowPage <=1 ) {
			// now is the last page
			JOptionPane.showMessageDialog(jFrame, "这是第一页");
			displayTool(textFieldArray);
		}else {
			nowPage --;
			if (nowPage <= 1) {
				nowPage = 1;
			}
			displayPageNumber(textField_9, nowPage);
			// accord the page number display the data
			for (int i = 0; i < 8; i++) {
				if (((nowPage - 1) * 8 + i) < allTool.size() + 1) {
					if (nowPage <= 1) {
						displayTool(textFieldArray);
					}else {
						if (nowPage == 2) {
							textFieldArray.get(i).setText(allTool.get(8 + i));
							colorTips(textFieldArray.get(i));
						}else {
							textFieldArray.get(i).setText(allTool.get((nowPage -1) * 8 + i - 1));
							colorTips(textFieldArray.get(i));
						}
					}
				}else {
					textFieldArray.get(i).setText("\\");
				}
			}
		}
		System.out.println("now page: " + nowPage);
	}
	
	// 4. next page
	public void nextPage(ArrayList<JTextField> textFieldArray, JTextField textField_9, JFrame jFrame) {
		// TODO color tips
		clearColor(textFieldArray);
		if (nowPage >= totalPage) {
			JOptionPane.showMessageDialog(jFrame, "这是最后一页");
		}else {
			nowPage ++;
			if (nowPage >= totalPage) {
				nowPage = totalPage;
			}
			displayPageNumber(textField_9, nowPage);
			// accord the page number display the data
			for (int i = 0; i < 8; i++) {
				if (((nowPage - 1) * 8 + i) < allTool.size() + 1) {
					if (nowPage <= 1) {
						displayTool(textFieldArray);
					}else {
						if (nowPage == 2) {
							textFieldArray.get(i).setText(allTool.get(8 + i));
							colorTips(textFieldArray.get(i));
						}else {
							textFieldArray.get(i).setText(allTool.get((nowPage -1) * 8 + i - 1));
							colorTips(textFieldArray.get(i));
						}
					}
				}else {
					textFieldArray.get(i).setText("\\");
				}
			}
		}
		System.out.println("now page: " + nowPage);
	}

	// 5. close initial
	public void closeInitial() {
		daoService.initialSelected();
	}

	// 6. borrow tool press
	public void borrowedSelect(int i,ArrayList<JTextField> textFieldArray,
			JFrame jFrame,JTextArea jta,JScrollBar jScrollBar) {
		// 1. check the position whether text field is empty
		String toolInfo = textFieldArray.get(i).getText();
		
		if (toolInfo.equals("\\") || toolInfo.trim().isEmpty()) {
			System.out.println("the select tool is empty ... ");
			JOptionPane.showMessageDialog(jFrame, "你选择的工具栏为空");
		}else {
			System.out.println("the select tool info : " + toolInfo);
			// 1.2 judge if the tool has been selected
			// 1.2.1 get the id
			String[] sid = toolInfo.split(",");
			for (int j = 0; j < sid.length; j++) {
				if (sid != null) {
					System.out.println(sid[j]);
				}
			}
			int id = Integer.parseInt(sid[0]);
			System.out.println("the selected tool id : " + id);
			if (id != -1) {
				// 1.2.2 get the tool info of is_selected
				if(daoService.isSelectedById(id)) {
					// 1.2.3 if the tool has been selected
					JOptionPane.showMessageDialog(jFrame, "此工具已经添加到了借用清单");
				}else {
					if (daoService.isBorrowedById(id)) {
						JOptionPane.showMessageDialog(jFrame, "此工具已经借出还未归还");
					}else {
							// 1.2.4 the tool has not selected
							// 2. add the tool to the borrowing car
							borrowingCar += toolInfo;
							jta.append(toolInfo + "\n");
							// 3. scroll the bar to bottom
							jScrollBar.setValue(jScrollBar.getMaximum());
							// 4. set the tool info of is_seleted is true
							daoService.setToolIsSelected(id,true);
					}
				}
			}
			
			

			
		}
		
		
		
	}

	// 7. confirm borrow 
	public void confirmBorrow(JTextArea toolList, JTextField time, JTextField borrowPeople,
			JTextField givePeople, JFrame jFrame,ArrayList<JTextField> displayTextArray) {
		// 1. check whether the info is complete
		boolean complete = true;
		if (time.getText().isEmpty()) {
			JOptionPane.showMessageDialog(jFrame, "日期未填写");
			complete = false;
		}
		if (borrowPeople.getText().isEmpty()) {
			JOptionPane.showMessageDialog(jFrame, "领用人未填写");
			complete = false;
		}
		if (givePeople.getText().isEmpty()) {
			JOptionPane.showMessageDialog(jFrame, "借用人未填写");
			complete = false;
		}
		if (toolList.getText().isEmpty()) {
			JOptionPane.showMessageDialog(jFrame, "未借用工具");
			complete = false;
		}			
		String timeString = time.getText();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.parse(timeString);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(jFrame, "请按照格式填写时间");
			complete = false;
		}
		
		if (complete) {
			// 2. complete the info produce borrow proof
			String borrowPeopleString = borrowPeople.getText().trim();
			String givePeopleString = givePeople.getText().trim();
			System.out.println(timeString);
			System.out.println(borrowPeopleString);
			System.out.println(givePeopleString);
			// 3. confirm again
			// 3.1 get the borrow tool amount
			int borrowToolAmount = daoService.getSelectedToolAmount();
			int confirm = JOptionPane.showConfirmDialog(jFrame, 
					"发放人： " + givePeopleString + "\n" 
					+ "领用人： " + borrowPeopleString + "\n"
					+ "借用时间： " + timeString + "\n"
					+ "借用工具数量： " + borrowToolAmount);
			
			if (confirm == JOptionPane.YES_OPTION) {
				// 4. produce the record
				System.out.println(confirm + "  ....  ");
				// create the table
				daoService.createBorrowProofTable();
				// 5. record the borrow proof into database
				// 5.1 get the selected tool id list
				ArrayList<Integer> list = daoService.getSelectedToolIdList();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list);
				}
				// 5.2 save info
				daoService.setBorrowProof(timeString,borrowPeopleString,givePeopleString,list);
				// 6. clear the selected tool mark
				daoService.setAllToolSelected(false);
				// 7. clear the display
				toolList.setText("");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(df.format(new Date()));
				time.setText(df.format(new Date()));
				borrowPeople.setText("");
				givePeople.setText("");
				// 8. set the tool of list is borrowed
				daoService.setToolOfListBorrowed(list,true);
				// 9. give the user a message of succeed
				int r = JOptionPane.showConfirmDialog(jFrame, "工具借用成功，已生成借用记录，是否跳转查看");
				if (r == JOptionPane.YES_OPTION) {
					// 11. give the user color tip of what tool is borrowed
					for (int i = 0; i < 8; i++) {
						colorTips(displayTextArray.get(i));
					}
					// 10. jump to borrow proof frame
					// TODO
					
				}else {
					// 11. give the user color tip of what tool is borrowed
					for (int i = 0; i < 8; i++) {
						colorTips(displayTextArray.get(i));
					}
					
				}
				
			}
		}
		
	}
	
	// 8. color tips
	public void colorTips(JTextField jtField) {
		jtField.setBackground(Color.WHITE);
		if (!jtField.getText().trim().isEmpty()) {
			// 1. get the content
			String con = jtField.getText();
			System.out.println("content : " + con );
			// 2. get tool id
			String[] ids = con.split(",");
			System.out.println("ids 0 is ..... : " + ids[0]);
			int toolId = Integer.parseInt(ids[0]);
			// 3. check whether borrowed
			boolean f = daoService.isBorrowedById(toolId);
			// 4. change color
			if (f) {
				jtField.setBackground(Color.RED);
			}else {
				jtField.setBackground(Color.WHITE);
			}
		}else {
			jtField.setBackground(Color.WHITE);
		}
		
	}

	// 9. clear the select
	public void clearSelect(JTextArea toolList, JTextField time, JTextField borrowPeople,
		JTextField givePeople, JFrame jFrame,ArrayList<JTextField> displayTextArray) {
		toolList.setText("");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(new Date()));
		time.setText(df.format(new Date()));
		borrowPeople.setText("");
		givePeople.setText("");
		daoService.setAllToolSelected(false);
		
	}

	// 10. search tool
	public void search(JTextField searchTool, JFrame jFrame, ArrayList<JTextField> displayArray,
			JTextField page) {
		// 1. get search info
		String toolName = searchTool.getText().trim();
		if (toolName.isEmpty()) {
			JOptionPane.showMessageDialog(jFrame, "请输入搜索信息");
			allTool = daoService.showAllTool();
			updatePage(allTool,page);
			displayTool(displayArray);
		}else {
			// 2. search in the databases
			System.out.println("search .............. 2");
			ArrayList<String> searchResult = daoService.searchToolByInfo(toolName);
			for (int i = 0; i < searchResult.size(); i++) {
				System.out.println("search .............. ");
				System.out.println(searchResult.get(i));
			}
			// 3. get tool info list
			ArrayList<String> toolList = daoService.getToolListByIdList(searchResult);
			// 4. give the result to display
			allTool.clear();
			allTool = toolList;
			System.out.println("tool list size ................... : " + toolList.size());
			updatePage(allTool,page);
			displayTool(displayArray);
		}
	}

	// 11. update page
	private void updatePage(ArrayList<String> allTool2,JTextField page) {
		totalPage = allTool2.size() / 8 + 1;
		nowPage = 1;
		page.setText(nowPage + " / " + totalPage);
		System.out.println("total page ............................ : " + totalPage);
	}
	
	// 12. clear color
	public void clearColor(ArrayList<JTextField> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBackground(Color.WHITE);
		}
	}
	
	// 13. display borrow proof initial time
	public void displayBorrowProof(ArrayList<JTextField> display,JTextField pageDisplay) {
		// 1. clean display
		for (int i = 0; i < display.size(); i++) {
			display.get(i).setText("");
			display.get(i).setBackground(Color.WHITE);
		}
		if (allBorrowProof.size() > 8) {
			for (int j = 0; j < 8; j++) {
				display.get(j).setText(allBorrowProof.get(j));
				display.get(j).setBackground(Color.WHITE);
			}
		}else {
			for (int i = 0; i < allBorrowProof.size(); i++) {
				display.get(i).setText(allBorrowProof.get(i));
				display.get(i).setBackground(Color.WHITE);
			}
		}
		// 2. page display
		pageDisplay.setText(borrowProofNowPage + " / " + borrowProofTotalPage);
		// color tips
		for (int i = 0; i < display.size(); i++) {
			colorBorrowProoTips(display.get(i));
		}
	}
	
	// 14. borrow proof last page
	public void borrowProofLastPage(ArrayList<JTextField> display, JTextField pageDisplay, JFrame jFrame) {
		// clear
		clearColor(display);
		if (borrowProofNowPage <= 1) {
			JOptionPane.showMessageDialog(jFrame, "这是第一页了");
			displayBorrowProof(display, pageDisplay);
		}else {
			borrowProofNowPage --;
			if (borrowProofNowPage <= 1) {
				borrowProofNowPage = 1;
			}
			borrowProofDisplayPageNumber(pageDisplay, borrowProofNowPage);
			// accord the page number display borrow proof 
			displayContent(borrowProofNowPage, allBorrowProof, display);
			// color tips
			for (int i = 0; i < display.size(); i++) {
				colorBorrowProoTips(display.get(i));
			}
			
		}
	}
	
	// 15. accord the page number display the data
	public void displayContent(int nowPage,ArrayList<String> content,ArrayList<JTextField> displays) {
		for (int i = 0; i < 8; i++) {
			if (((nowPage - 1) * 8 + i) < content.size() + 1) {
				if (nowPage <= 1) {
					displayBorrowProof(displays);
				}else {
					if (nowPage == 2) {
						if (8 + i < content.size()) {
							displays.get(i).setText(content.get(8 + i));
							
						}

					}else {
						displays.get(i).setText(content.get((nowPage -1) * 8 + i - 1));
						
					}
				}
			}else {
				displays.get(i).setText("\\");
			}
		}
	}
	
	// 16. borrow proof next page
	public void borrowProofNextPage(ArrayList<JTextField> display, JTextField pageDisplay, JFrame jFrame) {
		// clear
		clearColor(display);
		if (borrowProofNowPage >= borrowProofTotalPage) {
			JOptionPane.showMessageDialog(jFrame, "这是最后一页");
			displayBorrowProof(display, pageDisplay);
		}else {
			borrowProofNowPage ++;
			if (borrowProofNowPage >= borrowProofTotalPage) {
				borrowProofNowPage = borrowProofTotalPage;
			}
			borrowProofDisplayPageNumber(pageDisplay, borrowProofNowPage);
			// accord the page number display borrow proof 
			displayContent(borrowProofNowPage, allBorrowProof, display);
			// color tips
			for (int i = 0; i < display.size(); i++) {
				colorBorrowProoTips(display.get(i));
			}
		}
	}

	// 17. get borrow info by id
	public String getBorrowToolAndPeopleInfoById(int id) {
		String r = "";
		r += id + "\n";
		ArrayList<Integer> ids = daoService.getToolListByBorrowProofId(id);
		ArrayList<String> toolNameList = daoService.getToolNameListByIdList(ids);
		for (int i = 0; i < ids.size(); i++) {
			r += ids.get(i) + "," + toolNameList.get(i) + "\n";
		}
		String givePeople = daoService.getBorrowToolGivePeopleById(id);
		String borrowPeople = daoService.getBorrowToolBorrowPeopleById(id);
		r += "领用人：" + borrowPeople + "\n";
		r += "发放人：" + givePeople + "\n";
		String borrowTime = daoService.getBorrowToolBorrowTimeById(id);
		r += "借用时间：" + borrowTime + "\n";
		return r;
	}

	// 18. accord the borrow proof id back the tool , set is_borrowed false
	public void setBorrowProofBackById(int id) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids = daoService.getToolListByBorrowProofId(id);
		for (int i = 0; i < ids.size(); i++) {
			// t_isBorrowed : false
			daoService.setBorrowProofBackByToolId(ids.get(i), false);	
		}
	}

	// 19. back tool set the info
	public void setBorrowProofBackInfo(String backTime, String backGivePeople, String backGetPeople,int id) {
		daoService.setBorrowProofBackInfo(backTime,backGivePeople,backGetPeople,id);
	} 
	
	// 20. color tips
	public void colorBorrowProoTips(JTextField jtField) {
		jtField.setBackground(Color.WHITE);
		if (!jtField.getText().trim().isEmpty() && !jtField.getText().trim().equals("\\")) {
			// 1. get the content
			String con = jtField.getText();
			System.out.println("content : " + con );
			// 2. get tool id
			String[] ids = con.split(",");
			int borrowProofId = Integer.parseInt(ids[0]);
			// 3. check whether borrowed
//			boolean f = daoService.isBorrowedById(toolId);
			boolean f = daoService.isBorrowProofBack(borrowProofId);
			// 4. change color
			if (f) {
				jtField.setBackground(Color.GREEN);
			}else {
				jtField.setBackground(Color.WHITE);
			}
		}else {
			jtField.setBackground(Color.WHITE);
		}
		
	}

	// 21. get the is back state of borrow proof by id
	public boolean getBorrowProofIsBackStateById(int id) {
		boolean r = daoService.getBorrowProofIsBackStateById(id);
		
		return r;
	}

	// 22. get more info when borrow proof is back
	public String getBorrowProofMoreInfoWhenIsBack(int id) {
		return daoService.getBorrowProofMoreInfoWhenIsBack(id);
	}
	
	
	
	
	
	
	
	
	
}
