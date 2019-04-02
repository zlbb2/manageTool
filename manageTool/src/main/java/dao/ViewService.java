package dao;

import java.util.ArrayList;

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
	
	public ViewService () {
		daoService = new DaoService();
		daoService.connect();
		allTool = daoService.showAllTool();
		totalPage = allTool.size() / 8 + 1;
		totalTool = allTool.size();
		System.out.println("load view service , tool amount : " + allTool.size());
	}

	// 1. display tool initial time
	public void displayTool(ArrayList<JTextField> textFieldArray) {
		if (allTool.size() > 8) {
			for (int j = 0; j < 8; j++) {
				textFieldArray.get(j).setText(allTool.get(j));
			}
		}else {
			for (int i = 0; i < allTool.size(); i++) {
				textFieldArray.get(i).setText(allTool.get(i));
			}
		}
	}

	
	// 2. display page number
	public void displayPageNumber(JTextField textField_9,int nowPage) {
		totalTool = allTool.size();
		totalPage = totalTool / 8 + 1;
		textField_9.setText(nowPage + " / " + totalPage);
	}

	
	// 3. last page
	public void lastPage(ArrayList<JTextField> textFieldArray, JTextField textField_9, JFrame jFrame) {
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
						}else {
							textFieldArray.get(i).setText(allTool.get((nowPage -1) * 8 + i - 1));
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
						}else {
							textFieldArray.get(i).setText(allTool.get((nowPage -1) * 8 + i - 1));
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
		
		if (toolInfo.equals("\\")) {
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
