import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScheduleBuilderGUI{
	
	public JFrame frmWindow;
	private JTextField txtEventName;
	private JTextField txtHour;
	private JTextField txtMin;
	
	public ScheduleBuilderGUI(){
		frmWindow = new JFrame("ScheduleBuilder");
		frmWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmWindow.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("SCHEDULE BUILDER");
		lblTitle.setFont(new Font("LEMON MILK", Font.BOLD | Font.ITALIC, 20));
		lblTitle.setBounds(19, 25, 220, 33);
		frmWindow.getContentPane().add(lblTitle);
		
		@SuppressWarnings("unchecked")
		JComboBox cboDays = new JComboBox(new String[] {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"});
		//cboDays.setToolTipText("Pick A Day");
		cboDays.setBounds(323, 248, 148, 33);
		frmWindow.getContentPane().add(cboDays);
		
		@SuppressWarnings("unchecked")
		JComboBox comboBox = new JComboBox(new String[] {"Week One", "Week Two", "Week Three", "Week Four"});
		comboBox.setBounds(352, 155, 119, 33);
		frmWindow.getContentPane().add(comboBox);
		
		txtEventName = new JTextField();
		txtEventName.setHorizontalAlignment(SwingConstants.CENTER);
		txtEventName.setForeground(Color.LIGHT_GRAY);
		txtEventName.setText("Event Name");
		txtEventName.setBounds(19, 172, 130, 26);
		frmWindow.getContentPane().add(txtEventName);
		txtEventName.setColumns(10);
		
		txtHour = new JTextField();
		txtHour.setForeground(Color.LIGHT_GRAY);
		txtHour.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtHour.setHorizontalAlignment(SwingConstants.CENTER);
		txtHour.setText("HOUR");
		txtHour.setBounds(18, 210, 46, 26);
		frmWindow.getContentPane().add(txtHour);
		txtHour.setColumns(10);
		
		txtMin = new JTextField();
		txtMin.setForeground(Color.LIGHT_GRAY);
		txtMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtMin.setText("MIN");
		txtMin.setColumns(10);
		txtMin.setBounds(87, 209, 46, 26);
		frmWindow.getContentPane().add(txtMin);
		
		JLabel lblCOLON = new JLabel(":");
		lblCOLON.setHorizontalAlignment(SwingConstants.CENTER);
		lblCOLON.setBounds(66, 209, 20, 26);
		frmWindow.getContentPane().add(lblCOLON);
		
		JComboBox cboUser = new JComboBox();
		cboUser.setBounds(364, 21, 109, 26);
		frmWindow.getContentPane().add(cboUser);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddUser.setBounds(6, 337, 117, 29);
		frmWindow.getContentPane().add(btnAddUser);
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setBounds(377, 337, 117, 29);
		frmWindow.getContentPane().add(btnCompare);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.setBounds(18, 248, 91, 33);
		
		
		frmWindow.getContentPane().add(btnAddEvent);
		frmWindow.setBounds(100,100,500,400);
		
		
		
		frmWindow.setVisible(true);
	}
	public static void main(String[] args) {
		ScheduleBuilderGUI window = new ScheduleBuilderGUI();
	}
}
