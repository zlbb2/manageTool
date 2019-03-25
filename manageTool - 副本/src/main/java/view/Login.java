package view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class Login {

	public static JFrame frame = new JFrame();
	private JTextField textField;
	private JTextField textField_1;
	String logName = "";
	String passWord = "";
	// flag that log success
	boolean logSuccess = false;
	public FindTool findTool = null;
	public BackTool backTool = null;
	public ShowChart showChart = null;
	public static Login window = null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		window = new Login();
		Login.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Login() {
		initialize();
		findTool = new FindTool();
		findTool.setVisible(false);
		backTool = new BackTool();
		backTool.setVisible(false);
		showChart = new ShowChart();
		showChart.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5DE5\u5177\u7BA1\u7406");
		frame.setBounds(100, 100, 669, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(228, 91, 289, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u767B\u5F55\u540D\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(127, 89, 104, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 16));
		label.setBounds(127, 183, 104, 38);
		frame.getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(228, 185, 289, 38);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("\u767B \u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// click the login then get the name and password then check
				logName = textField.getText();
				passWord = textField_1.getText();
				if (logName.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "登录名不能为空", "注意", JOptionPane.WARNING_MESSAGE);
				}else {
					// show the succeed
					JOptionPane.showMessageDialog(frame, "登录成功");
					// link the databases
					logSuccess = true;
				}
				
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(140, 273, 116, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u91CD \u7F6E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clear
				textField.setText("");
				textField_1.setText("");
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(383, 273, 116, 38);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u501F\u7528\u5DE5\u5177");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// borrow tool 
				if (logSuccess) {
					frame.setVisible(false);
					findTool.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(frame, "没有登录","注意",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		button_1.setBounds(90, 366, 97, 55);
		frame.getContentPane().add(button_1);
		
		JLabel label_1 = new JLabel("\u5DE5\u5177\u7BA1\u7406");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("仿宋", Font.BOLD, 16));
		label_1.setBounds(10, 10, 633, 38);
		frame.getContentPane().add(label_1);
		
		JButton button_2 = new JButton("\u5F52\u8FD8\u5DE5\u5177");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// back tool
				if(logSuccess) {
					// show the frame
					frame.setVisible(false);
					backTool.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(frame, "没有登录","注意",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 14));
		button_2.setBounds(277, 366, 97, 55);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u67E5\u770B\u7EDF\u8BA1");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logSuccess) {
					// show the chart
					frame.setVisible(false);
					showChart.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(frame,"没有登录","注意",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 14));
		button_3.setBounds(464, 366, 97, 55);
		frame.getContentPane().add(button_3);
		
		JLabel timeLabel = new JLabel("");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setBounds(10, 459, 633, 15);
		frame.getContentPane().add(timeLabel);
		// time
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		timeLabel.setText(calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH) );
		
	}
}
