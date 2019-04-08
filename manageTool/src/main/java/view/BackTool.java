package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import dao.DaoService;
import dao.ViewService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BackTool extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	
	// display borrow proof
	public ArrayList<JTextField> textFieldArray = null;
	// borrow press
	public ArrayList<JButton> buttonArray = null;
	// view service
	public ViewService viewService = null;
	public JFrame jFrame = null;
	// listener
	ButtonPress buttonPress = null;
	// display the borrow tool
	JTextArea textArea = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackTool frame = new BackTool();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BackTool() {
		jFrame = this;
		// close
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
				
			}
			
			public void windowIconified(WindowEvent e) {
				
			}
			
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			public void windowClosing(WindowEvent e) {
				// TODO clean
				Login.frame.setVisible(true);
				
			}
			
			public void windowClosed(WindowEvent e) {
				
			}
			
			public void windowActivated(WindowEvent e) {
				
				
			}
		});
		this.setResizable(false);
		setTitle("\u5DE5\u5177\u5F52\u8FD8");
		setBounds(100, 100, 735, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u67E5 \u627E\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(10, 10, 67, 25);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(87, 12, 163, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u67E5 \u627E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrowSearch();
			}
		});
		button.setBounds(271, 11, 93, 23);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("1");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 61, 38, 25);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(58, 63, 306, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("\u8FD8");
		button_1.setBounds(381, 62, 54, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u8FD8");
		button_2.setBounds(381, 97, 54, 23);
		contentPane.add(button_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(58, 98, 306, 23);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("2");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(10, 96, 38, 25);
		contentPane.add(label_2);
		
		JButton button_3 = new JButton("\u8FD8");
		button_3.setBounds(381, 131, 54, 23);
		contentPane.add(button_3);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(58, 132, 306, 23);
		contentPane.add(textField_3);
		
		JLabel label_3 = new JLabel("3");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(10, 130, 38, 25);
		contentPane.add(label_3);
		
		JButton button_4 = new JButton("\u8FD8");
		button_4.setBounds(381, 168, 54, 23);
		contentPane.add(button_4);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(58, 169, 306, 23);
		contentPane.add(textField_4);
		
		JLabel label_4 = new JLabel("4");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(10, 167, 38, 25);
		contentPane.add(label_4);
		
		JButton button_5 = new JButton("\u8FD8");
		button_5.setBounds(381, 207, 54, 23);
		contentPane.add(button_5);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(58, 208, 306, 23);
		contentPane.add(textField_5);
		
		JLabel label_5 = new JLabel("5");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(10, 206, 38, 25);
		contentPane.add(label_5);
		
		JButton button_6 = new JButton("\u8FD8");
		button_6.setBounds(381, 247, 54, 23);
		contentPane.add(button_6);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(58, 248, 306, 23);
		contentPane.add(textField_6);
		
		JLabel label_6 = new JLabel("6");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(10, 246, 38, 25);
		contentPane.add(label_6);
		
		JButton button_7 = new JButton("\u8FD8");
		button_7.setBounds(381, 286, 54, 23);
		contentPane.add(button_7);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(58, 287, 306, 23);
		contentPane.add(textField_7);
		
		JLabel label_7 = new JLabel("7");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		label_7.setBounds(10, 285, 38, 25);
		contentPane.add(label_7);
		
		JButton button_8 = new JButton("\u8FD8");
		button_8.setBounds(381, 328, 54, 23);
		contentPane.add(button_8);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(58, 329, 306, 23);
		contentPane.add(textField_8);
		
		JLabel label_8 = new JLabel("8");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(10, 327, 38, 25);
		contentPane.add(label_8);
		
		JButton button_9 = new JButton("");
		button_9.setBounds(439, 0, 16, 434);
		contentPane.add(button_9);
		
		JLabel label_9 = new JLabel("\u9875\u7801\uFF1A");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(10, 377, 54, 15);
		contentPane.add(label_9);
		
		textField_9 = new JTextField();
		textField_9.setBounds(58, 374, 66, 21);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JButton button_10 = new JButton("\u4E0A\u4E00\u9875");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastPage();
			}
		});
		button_10.setBounds(157, 373, 93, 23);
		contentPane.add(button_10);
		
		JButton button_11 = new JButton("\u4E0B\u4E00\u9875");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPage();
			}
		});
		button_11.setBounds(271, 373, 93, 23);
		contentPane.add(button_11);
		
		JLabel label_10 = new JLabel("\u5F52\u8FD8\u5DE5\u5177\u8BE6\u7EC6\u4FE1\u606F");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(465, 10, 244, 15);
		contentPane.add(label_10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(465, 33, 244, 197);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel label_11 = new JLabel("\u5F52\u8FD8\u65F6\u95F4\uFF1A");
		label_11.setBounds(465, 251, 73, 15);
		contentPane.add(label_11);
		
		textField_10 = new JTextField();
		textField_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
			    if (clickTimes == 2) {
			    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println(df.format(new Date()));
					textField_10.setText(df.format(new Date()));
			    }
			}
		});
		textField_10.setBounds(542, 248, 167, 21);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel label_12 = new JLabel("\u65F6\u95F4\u683C\u5F0F\uFF1A2019-01-01");
		label_12.setForeground(Color.RED);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(465, 276, 244, 15);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("\u5F52\u8FD8\u4EBA\uFF1A");
		label_13.setBounds(465, 304, 54, 15);
		contentPane.add(label_13);
		
		textField_11 = new JTextField();
		textField_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
			    if (clickTimes == 2) {
			    	// back people
			    	textField_11.setText(ViewService.BORROWER);
			    }
			}
		});
		textField_11.setBounds(542, 301, 167, 21);
		contentPane.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel label_14 = new JLabel("\u63A5\u6536\u4EBA\uFF1A");
		label_14.setBounds(465, 332, 54, 15);
		contentPane.add(label_14);
		
		textField_12 = new JTextField();
		textField_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
			    if (clickTimes == 2) {
			    	textField_12.setText(ViewService.ADMIN);
			    }
			}
		});
		textField_12.setBounds(542, 329, 167, 21);
		contentPane.add(textField_12);
		textField_12.setColumns(10);
		
		JButton button_12 = new JButton("\u786E\u8BA4");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
		button_12.setBounds(475, 373, 93, 23);
		contentPane.add(button_12);
		
		JButton button_13 = new JButton("\u91CD\u7F6E");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetInput();
			}
		});
		button_13.setBounds(594, 373, 93, 23);
		contentPane.add(button_13);
		
		// load the component
		textFieldArray = new ArrayList<JTextField>();
		textFieldArray.add(textField_1);
		textFieldArray.add(textField_2);
		textFieldArray.add(textField_3);
		textFieldArray.add(textField_4);
		textFieldArray.add(textField_5);
		textFieldArray.add(textField_6);
		textFieldArray.add(textField_7);
		textFieldArray.add(textField_8);
		
		buttonArray = new ArrayList<JButton>();
		buttonArray.add(button_1);
		buttonArray.add(button_2);
		buttonArray.add(button_3);
		buttonArray.add(button_4);
		buttonArray.add(button_5);
		buttonArray.add(button_6);
		buttonArray.add(button_7);
		buttonArray.add(button_8);

		
		// initial display
		viewService = new ViewService();
		viewService.displayBorrowProof(textFieldArray,textField_9);
		
		JLabel label_15 = new JLabel("绿色表示已经归还");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setForeground(Color.GREEN);
		label_15.setFont(new Font("黑体", Font.PLAIN, 16));
		label_15.setBounds(131, 409, 233, 15);
		contentPane.add(label_15);
		
				
		// load the listener
		
		for (int i = 0; i < buttonArray.size(); i++) {
			buttonPress = new ButtonPress(i,textFieldArray,viewService,jFrame,textArea);
			buttonArray.get(i).addActionListener(buttonPress);
		}
		
		// color tips
		for (int i = 0; i < textFieldArray.size(); i++) {
			viewService.colorBorrowProoTips(textFieldArray.get(i));
		}
		
		
	}
	
	
	// borrow button listener
	class ButtonPress implements ActionListener{

		int i = 0;
		ArrayList<JTextField> textFields = null;
		ViewService viewService = null;
		JFrame jFrame = null;
		public ButtonPress(int i,ArrayList<JTextField> textFields,ViewService viewService,JFrame jFrame,JTextArea textArea) {
			this.i = i;
			this.textFields = textFields;
			this.viewService = viewService;
			this.jFrame = jFrame;
		}
		public void actionPerformed(ActionEvent e) {
			textArea.setText("");
			if (textFields.get(i).getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(jFrame, "选择栏为空");
			}else {
				String s = textFields.get(i).getText();
				// 1. get the id
				String[] sp = s.split(",");
				String sid = null;
				int id = 0;
				if (sp != null) {
					for (int i = 0; i < sp.length; i++) {
						System.out.println(sp[i]);
					}
					sid = sp[0];
					id = Integer.parseInt(sid);
				}
				if (id != 0) {
					// 1-2 if the borrow proof had been back, then tips
					boolean isBack = viewService.getBorrowProofIsBackStateById(id);
					if (isBack) {
						JOptionPane.showMessageDialog(jFrame, "此借用记录工具已经归还");
					}
					
					// 2. get the borrow proof info of the id
					String borrowToolAndPeopleInfo ="";
					borrowToolAndPeopleInfo= viewService.getBorrowToolAndPeopleInfoById(id);
					textArea.append(borrowToolAndPeopleInfo);
					
					// 2-2 if the tool has back show more info
					if (isBack) {
						String moreInfo = viewService.getBorrowProofMoreInfoWhenIsBack(id);
						textArea.append(moreInfo);
					}
				}
				
			}
		}
		
	}
	
	// 5. reset the input
	protected void resetInput() {
		textArea.setText("");
		textField_10.setText("");
		textField_11.setText("");
		textField_12.setText("");
	}

	// 4. confirm to give back the tool
	protected void confirm() {
		int id = 0;
		// 1. judge the text area content
		if (textArea.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(jFrame, "请选择需要归还的工具借用记录");
		}else {
			// 2. get the text area info
			// 2.1 borrow proof id
			String borrowInfo = textArea.getText();
			String[] line = borrowInfo.split("\n");
			if (line[0] != null) {
				String borrowId = line[0];
				id = Integer.parseInt(borrowId);
			}
			if (viewService.getBorrowProofIsBackStateById(id)) {
				JOptionPane.showMessageDialog(jFrame, "此工具记录已经被归还");
				return;
			}
			// 3. the user input check
			if (textField_10.getText().isEmpty()) {
				JOptionPane.showMessageDialog(jFrame, "请将信息补全");
			}
			else if (textField_11.getText().isEmpty()) {
				JOptionPane.showMessageDialog(jFrame, "请将信息补全");
			}
			else if (textField_12.getText().isEmpty()) {
				JOptionPane.showMessageDialog(jFrame, "请将信息补全");
			}else {
				// 4. confirm the input
				int result = JOptionPane.showConfirmDialog(jFrame, "确定以上信息无误");
				if (result == JOptionPane.YES_OPTION) {
					// 5. back the tool set tool borrow false
					JOptionPane.showMessageDialog(jFrame, "归还成功");
					
					// TODO color tips,databases set
					// 5-1 tool one table state set false borrow proof
					viewService.setBorrowProofBackById(id);
					// 5-2 borrow proof info set
					String backTime = textField_10.getText().trim();
					String backGivePeople = textField_11.getText().trim();
					String backGetPeople = textField_12.getText().trim();
					viewService.setBorrowProofBackInfo(backTime,backGivePeople,backGetPeople,id);
					// 5-3 color tips
					for (int i = 0; i < textFieldArray.size(); i++) {
						viewService.colorBorrowProoTips(textFieldArray.get(i));
					}
					
					// 5-4 reset
					textArea.setText("");
					textField_10.setText("");
					textField_11.setText("");
					textField_12.setText("");
					
				}
			}
		}
		
	}

	// 3. next page
	protected void nextPage() {
		viewService.borrowProofNextPage(textFieldArray, textField_9, jFrame);
	}

	// 2. last page
	protected void lastPage() {
		viewService.borrowProofLastPage(textFieldArray, textField_9, jFrame);
	}

	// 1. search the borrow proof
	protected void borrowSearch() {
		// TODO Auto-generated method stub
		
	}
}
