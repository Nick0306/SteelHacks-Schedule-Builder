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
import java.util.ArrayList;
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
import javax.swing.JTextArea;

public class ScheduleBuilderGUI{
	
	private ArrayList<Person> users = new ArrayList<Person>();
	
	public JFrame frmWindow;
	private JTextField txtEventName;
	private JTextField txtSHour;
	private JTextField txtSMin;
	private JTextField txtEHour;
	private JTextField txtEMin;
	private JComboBox cboUser;
	
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
		JComboBox cboWeek = new JComboBox(new String[] {"Week One", "Week Two", "Week Three", "Week Four"});
		cboWeek.setBounds(352, 155, 119, 33);
		frmWindow.getContentPane().add(cboWeek);
		
	
		
		if(users.size() > 0) {
			cboUser = new JComboBox(users.toArray());
		}else {
			cboUser = new JComboBox();
		}
		cboUser.setBounds(364, 21, 109, 26);
		frmWindow.getContentPane().add(cboUser);
		
		txtEventName = new JTextField();
		txtEventName.setHorizontalAlignment(SwingConstants.CENTER);
		txtEventName.setForeground(Color.LIGHT_GRAY);
		txtEventName.setText("Event Name");
		txtEventName.setBounds(19, 88, 130, 26);
		frmWindow.getContentPane().add(txtEventName);
		txtEventName.setColumns(10);
		
		txtSHour = new JTextField();
		txtSHour.setForeground(Color.LIGHT_GRAY);
		txtSHour.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtSHour.setHorizontalAlignment(SwingConstants.CENTER);
		txtSHour.setText("HOUR");
		txtSHour.setBounds(19, 126, 46, 26);
		frmWindow.getContentPane().add(txtSHour);
		txtSHour.setColumns(10);
		
		txtSMin = new JTextField();
		txtSMin.setForeground(Color.LIGHT_GRAY);
		txtSMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtSMin.setText("MIN");
		txtSMin.setColumns(10);
		txtSMin.setBounds(88, 126, 46, 26);
		frmWindow.getContentPane().add(txtSMin);
		
		txtEHour = new JTextField();
		txtEHour.setText("HOUR");
		txtEHour.setHorizontalAlignment(SwingConstants.CENTER);
		txtEHour.setForeground(Color.LIGHT_GRAY);
		txtEHour.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtEHour.setColumns(10);
		txtEHour.setBounds(19, 157, 46, 26);
		frmWindow.getContentPane().add(txtEHour);
		
		txtEMin = new JTextField();
		txtEMin.setText("MIN");
		txtEMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtEMin.setForeground(Color.LIGHT_GRAY);
		txtEMin.setColumns(10);
		txtEMin.setBounds(88, 157, 46, 26);
		frmWindow.getContentPane().add(txtEMin);
		
		
		
		JLabel lblCOLON = new JLabel(":");
		lblCOLON.setHorizontalAlignment(SwingConstants.CENTER);
		lblCOLON.setBounds(66, 126, 20, 26);
		frmWindow.getContentPane().add(lblCOLON);
		
		JLabel lblCOLON1 = new JLabel(":");
		lblCOLON1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCOLON1.setBounds(66, 155, 20, 26);
		frmWindow.getContentPane().add(lblCOLON1);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				
				String name = JOptionPane.showInputDialog("Name of New User");
				users.add(new Person(name));
				//Person[] people = (Person[])users.toArray();
				//cboUser = new JComboBox(people);
				cboUser.addItem(name);
				for(Person use : users) {
					System.out.println(use.getName());
				}
				
				
			}
		});
		btnAddUser.setBounds(6, 337, 117, 29);
		frmWindow.getContentPane().add(btnAddUser);
		
		JTextArea txtareaOutput = new JTextArea();
		txtareaOutput.setBounds(19, 235, 267, 95);
		frmWindow.getContentPane().add(txtareaOutput);
		frmWindow.setBounds(100,100,500,400);
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Schedule> sArr = new ArrayList<Schedule>();
				int index = 0;
				if(cboWeek.getSelectedItem().equals("Week One")) {
					index = 0;
				}else if(cboWeek.getSelectedItem().equals("Week Two")) {
					index = 1;
				}else if(cboWeek.getSelectedItem().equals("Week Three")) {
					index = 2;
				}else if(cboWeek.getSelectedItem().equals("Week Four")) {
					index = 3;
				}
				for(int i = 0; i < users.size(); i++) {
					sArr.set(i, users.get(i).getSchedule(index));
				}
				Schedule schedule = Schedule.compareDays(sArr);
				
				for(Day day : schedule.getSchedule()) {
					ArrayList<Event> eArr = day.getEvents();
					
					txtareaOutput.append("Day: " + day.getDay() + "\n~~~~~~~~~~~" + "\n");
					for(int i = 0; i < eArr.size(); i++) {
						txtareaOutput.append("Free between: ");
						int sHour = (int) eArr.get(i).getStart();
						int sMin = (int)Math.ceil((eArr.get(i).getStart() - ((int) eArr.get(i).getStart())) * 60);
						txtareaOutput.append(sHour + ":" + sMin);
						int eHour = (int) eArr.get(i).getEnd();
						int eMin = (int)Math.ceil((eArr.get(i).getEnd() - ((int) eArr.get(i).getEnd())) * 60);
						txtareaOutput.append(" - " + eHour + ":" + eMin);
					}
				}
				
			}
		});
		btnCompare.setBounds(377, 337, 117, 29);
		frmWindow.getContentPane().add(btnCompare);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventName = txtEventName.getText();
				double sTime = Double.parseDouble(txtSHour.getText()) + (Double.parseDouble(txtSMin.getText())/60);
				double eTime = Double.parseDouble(txtEHour.getText()) + (Double.parseDouble(txtEMin.getText())/60);
				
				int week = 0;
				if(cboWeek.getSelectedItem().equals("Week One")) {
					week = 0;
				}else if(cboWeek.getSelectedItem().equals("Week Two")) {
					week = 1;
				}else if(cboWeek.getSelectedItem().equals("Week Three")) {
					week = 2;
				}else if(cboWeek.getSelectedItem().equals("Week Four")) {
					week = 3;
				}
				
				String day = (String) cboDays.getSelectedItem();
				String userName = (String) cboUser.getSelectedItem();
				
				Person user = null;
				
				for(Person per : users) {
					if(per.getName().equals(userName)) {
						user = per;
						break;
					}
				}
				
				user.addEvent(week, day, eventName, sTime, eTime);
				
				
			}
		});
		btnAddEvent.setBounds(19, 190, 91, 33);
		frmWindow.getContentPane().add(btnAddEvent);
		
		
		frmWindow.setVisible(true);
	}
	public static void main(String[] args) {
		ScheduleBuilderGUI window = new ScheduleBuilderGUI();
	}
}
