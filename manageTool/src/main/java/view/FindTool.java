package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.util.TestUtils;

import dao.ViewService;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class FindTool extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2354671653799046279L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField txtA;
	private JTextField txtD;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	public JFrame jFrame = null;

	/**
	 * Launch the application.
	 */
	ViewService viewService = null;
	ArrayList<JTextField> textFieldArray = null;
	JTextArea txtrA = null;
	JScrollBar jScrollBar = null;
	
	
	public static void main(String[] args) {
		FindTool frame = new FindTool();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public FindTool() {
		// load service
		viewService = new ViewService();
		jFrame = this;
		jFrame.setResizable(false);
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
				Login.frame.setVisible(true);
				// every parameter initial
				closeInitial();
			}
			
			public void windowClosed(WindowEvent e) {
				
			}
			
			public void windowActivated(WindowEvent e) {
				
			}
		});

		setTitle("\u67E5\u627E\u501F\u7528");
		setBounds(100, 100, 872, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u641C \u7D22\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 70, 31);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(105, 12, 233, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u641C  \u7D22");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		button.setBounds(367, 11, 93, 27);
		contentPane.add(button);
		
		JLabel label = new JLabel("1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 52, 44, 31);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(54, 52, 424, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_0 = new JButton("\u501F");
		button_0.setBounds(488, 53, 54, 31);
		contentPane.add(button_0);

		JLabel label_8 = new JLabel("\u9875 \u7801\uFF1A");
		label_8.setBounds(67, 405, 54, 15);
		contentPane.add(label_8);
		
		textField_9 = new JTextField();
		textField_9.setBounds(105, 402, 66, 21);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JButton button_8 = new JButton("\u4E0A\u4E00\u9875");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastPage();
			}
		});
		button_8.setBounds(220, 401, 93, 23);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("\u4E0B\u4E00\u9875");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPage();
			}
		});
		button_9.setBounds(367, 401, 93, 23);
		contentPane.add(button_9);
		
		JButton button_10 = new JButton("");
		button_10.setBounds(552, 0, 15, 473);
		contentPane.add(button_10);
		
		JLabel label_9 = new JLabel("\u501F\u7528\u5DE5\u5177\u5217\u8868");
		label_9.setFont(new Font("宋体", Font.PLAIN, 14));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(577, 19, 269, 15);
		contentPane.add(label_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(577, 50, 269, 237);
		contentPane.add(scrollPane);
		
		txtrA = new JTextArea();
		txtrA.setFont(new Font("宋体", Font.PLAIN, 16));
		txtrA.setEditable(false);
		scrollPane.setViewportView(txtrA);
		jScrollBar=scrollPane.getVerticalScrollBar();
		
		
		JLabel label_10 = new JLabel("\u501F\u7528\u65F6\u95F4\uFF1A");
		label_10.setBounds(577, 297, 70, 21);
		contentPane.add(label_10);
		
		textField_10 = new JTextField();
		textField_10.setText("2019-02-12");
		textField_10.setBounds(668, 297, 111, 21);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		textField_10.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
			    if (clickTimes == 2) {
			    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println(df.format(new Date()));
					textField_10.setText(df.format(new Date()));
			    }				
			}
		});
		
		JLabel label_11 = new JLabel("\u65F6\u95F4\u683C\u5F0F\uFF1A2019-01-01");
		label_11.setForeground(Color.RED);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(577, 326, 202, 15);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("\u9886\u7528\u4EBA\uFF1A");
		label_12.setBounds(577, 354, 54, 15);
		contentPane.add(label_12);
		
		txtA = new JTextField();
		txtA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
			    if (clickTimes == 2) {
			    	txtA.setText(ViewService.ADMIN);
			    }
			}
		});
		txtA.setText("a");
		txtA.setBounds(668, 351, 111, 21);
		contentPane.add(txtA);
		txtA.setColumns(10);
		
		JLabel label_13 = new JLabel("\u53D1\u653E\u4EBA\uFF1A");
		label_13.setBounds(577, 382, 54, 15);
		contentPane.add(label_13);
		
		txtD = new JTextField();
		txtD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickTimes = e.getClickCount();
			    if (clickTimes == 2) {
			    	txtD.setText(ViewService.BORROWER);
			    }
			}
		});
		txtD.setText("d");
		txtD.setBounds(668, 379, 111, 21);
		contentPane.add(txtD);
		txtD.setColumns(10);
		
		JButton button_11 = new JButton("\u786E\u5B9A");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmBorrow();
			}
		});
		button_11.setBounds(588, 430, 93, 23);
		contentPane.add(button_11);
		
		JButton button_12 = new JButton("\u91CD\u7F6E");
		button_12.setBounds(711, 430, 93, 23);
		contentPane.add(button_12);
		button_12.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				clearSelect();	
			}
		});
		
		JButton button_1 = new JButton("\u501F");
		button_1.setBounds(488, 94, 54, 31);
		contentPane.add(button_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(54, 93, 424, 31);
		contentPane.add(textField_2);
		
		JLabel label_1 = new JLabel("2");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(10, 93, 44, 31);
		contentPane.add(label_1);
		
		JButton button_2 = new JButton("\u501F");
		button_2.setBounds(488, 136, 54, 31);
		contentPane.add(button_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(54, 135, 424, 31);
		contentPane.add(textField_3);
		
		JLabel label_2 = new JLabel("3");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(10, 135, 44, 31);
		contentPane.add(label_2);
		
		JButton button_3 = new JButton("\u501F");
		button_3.setBounds(488, 178, 54, 31);
		contentPane.add(button_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(54, 177, 424, 31);
		contentPane.add(textField_4);
		
		JLabel label_3 = new JLabel("4");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(10, 177, 44, 31);
		contentPane.add(label_3);
		
		JButton button_4 = new JButton("\u501F");
		button_4.setBounds(488, 220, 54, 31);
		contentPane.add(button_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(54, 219, 424, 31);
		contentPane.add(textField_5);
		
		JLabel label_4 = new JLabel("5");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(10, 219, 44, 31);
		contentPane.add(label_4);
		
		JButton button_5 = new JButton("\u501F");
		button_5.setBounds(488, 262, 54, 31);
		contentPane.add(button_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(54, 261, 424, 31);
		contentPane.add(textField_6);
		
		JLabel label_5 = new JLabel("6");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("宋体", Font.PLAIN, 16));
		label_5.setBounds(10, 261, 44, 31);
		contentPane.add(label_5);
		
		JButton button_6 = new JButton("\u501F");
		button_6.setBounds(488, 304, 54, 31);
		contentPane.add(button_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(54, 303, 424, 31);
		contentPane.add(textField_7);
		
		JLabel label_6 = new JLabel("7");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("宋体", Font.PLAIN, 16));
		label_6.setBounds(10, 303, 44, 31);
		contentPane.add(label_6);
		
		JButton button_7 = new JButton("\u501F");
		button_7.setBounds(488, 346, 54, 31);
		contentPane.add(button_7);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(54, 345, 424, 31);
		contentPane.add(textField_8);
		
		JLabel label_7 = new JLabel("8");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("宋体", Font.PLAIN, 16));
		label_7.setBounds(10, 345, 44, 31);
		contentPane.add(label_7);
		
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
		
		// display the tool info
		viewService.displayTool(textFieldArray);
		
		// display the page number
		viewService.displayPageNumber(textField_9,1);
		
		JLabel lblNewLabel_1 = new JLabel("红色代表工具已经借出");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_1.setBounds(54, 438, 424, 35);
		contentPane.add(lblNewLabel_1);
		
		// add the borrow button action
		button_0.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(0);
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(1);
			}
		});

		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(2);
			}
		});
		
		button_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(3);
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(4);
			}
		});
		
		button_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(5);
			}
		});
		
		button_6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(6);
			}
		});
		
		button_7.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				borrowedSelect(7);
			}
		});

	}		
	
		// search tool
		protected void search() {
			System.out.println("search .............. 1 ");
			viewService.search(textField,jFrame,textFieldArray,textField_9);
		}

		// press clear
		protected void clearSelect() {
			viewService.clearSelect(txtrA,textField_10,txtA,txtD,jFrame, textFieldArray);
		}

		// press confirm
		protected void confirmBorrow() {
			viewService.confirmBorrow(txtrA,textField_10,txtA,txtD,jFrame,textFieldArray);
		}

		// press borrow
		public void borrowedSelect(int i) {
			viewService.borrowedSelect(i,textFieldArray,jFrame,txtrA,jScrollBar);
		}

		// last page action
		public void lastPage() {
			viewService.lastPage(textFieldArray,textField_9,jFrame);
		}
		
		// next page action
		public void nextPage() {
			viewService.nextPage(textFieldArray,textField_9,jFrame);
		}
		
		// close initial
		public void closeInitial() {
			viewService.nowPage = 1;
			viewService.closeInitial();
		}
}
