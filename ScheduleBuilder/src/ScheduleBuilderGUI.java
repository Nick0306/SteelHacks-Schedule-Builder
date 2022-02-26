import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
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
	private JLabel lblTitle, lblCOLON, lblCOLON1;
	private JTextField txtEventName, txtSHour, txtSMin, txtEHour, txtEMin;
	private JButton btnAddUser, btnCompare, btnAddEvent, btnShowSchedule;
	private JComboBox cboUser, cboDays, cboWeek;
	private JTextArea txtareaOutput;
	private JScrollPane sp;
	
	public ScheduleBuilderGUI(){
		
		
		frmWindow = new JFrame("ScheduleBuilder");
		frmWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmWindow.getContentPane().setLayout(null);
		
		lblTitle = new JLabel("SCHEDULE BUILDER");
		lblTitle.setFont(new Font("LEMON MILK", Font.BOLD | Font.ITALIC, 20));
		lblTitle.setBounds(19, 25, 220, 33);
		
		cboDays = new JComboBox(new String[] {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"});
		cboDays.setBounds(446, 248, 148, 33);
		
		cboWeek = new JComboBox(new String[] {"Week One", "Week Two", "Week Three", "Week Four"});
		cboWeek.setBounds(475, 154, 119, 33);
		
		
		cboUser = new JComboBox();
		cboUser.setBounds(485, 20, 109, 26);
		
		
		
		txtEventName = new JTextField();
		txtEventName.setHorizontalAlignment(SwingConstants.CENTER);
		txtEventName.setForeground(Color.LIGHT_GRAY);
		txtEventName.setText("Event Name");
		txtEventName.setBounds(19, 88, 130, 26);
		txtEventName.setColumns(10);
		
		txtSHour = new JTextField();
		txtSHour.setForeground(Color.LIGHT_GRAY);
		txtSHour.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtSHour.setHorizontalAlignment(SwingConstants.CENTER);
		txtSHour.setText("HOUR");
		txtSHour.setBounds(19, 126, 46, 26);
		txtSHour.setColumns(10);
		
		txtSMin = new JTextField();
		txtSMin.setForeground(Color.LIGHT_GRAY);
		txtSMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtSMin.setText("MIN");
		txtSMin.setColumns(10);
		txtSMin.setBounds(88, 126, 46, 26);
		
		
		txtEHour = new JTextField();
		txtEHour.setText("HOUR");
		txtEHour.setHorizontalAlignment(SwingConstants.CENTER);
		txtEHour.setForeground(Color.LIGHT_GRAY);
		txtEHour.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtEHour.setColumns(10);
		txtEHour.setBounds(19, 157, 46, 26);
		
		
		txtEMin = new JTextField();
		txtEMin.setText("MIN");
		txtEMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtEMin.setForeground(Color.LIGHT_GRAY);
		txtEMin.setColumns(10);
		txtEMin.setBounds(88, 157, 46, 26);
		
		
		txtareaOutput = new JTextArea();
		txtareaOutput.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		
		
		sp = new JScrollPane(txtareaOutput);
		sp.setBounds(19, 235, 301, 180);
		
		
		frmWindow.setBounds(100,100,600,500);
		
		lblCOLON = new JLabel(":");
		lblCOLON.setHorizontalAlignment(SwingConstants.CENTER);
		lblCOLON.setBounds(66, 126, 20, 26);
		
		
		lblCOLON1 = new JLabel(":");
		lblCOLON1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCOLON1.setBounds(66, 155, 20, 26);
		
		
		btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				
				String name = JOptionPane.showInputDialog("Name of New User");
				if(name == null) {
					return;
				}
				users.add(new Person(name));
				txtareaOutput.append("\n~~~~~~~~~~~~~~~~~~~~~\n" + name + " was successfully added to the User List!");
				
				//Person[] people = (Person[])users.toArray();
				//cboUser = new JComboBox(people);
				cboUser.addItem(name);
				for(Person use : users) {
					System.out.println(use.getName());
				}
				
				
			}
		});
		
		
		
		
		btnCompare = new JButton("Compare");
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
		
		
		
		btnAddEvent = new JButton("Add Event");
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
				System.out.println(day);
				String userName = (String) cboUser.getSelectedItem();
				System.out.println(userName);
				
				Person user = null;
				
				for(Person per : users) {
					if(per.getName().equals(userName)) {
						user = per;
						break;
					}
				}
				System.out.println(user.getName());
				
				boolean successful = user.addEvent(week, day, eventName, sTime, eTime);
				if(successful) {
					txtareaOutput.append("\n~~~~~~~~~~~~~~~~~~~~~~~\n" + eventName + " was successfully added for" + user.getName() + "!");
				}
				
				
			}
		});
		
		btnShowSchedule = new JButton("Show Schedule");
		
		
		btnShowSchedule.setBounds(359, 437, 117, 29);
		btnAddUser.setBounds(6, 437, 117, 29);
		btnCompare.setBounds(477, 437, 117, 29);
		btnAddEvent.setBounds(19, 190, 91, 33);
		
		
		
		frmWindow.getContentPane().add(lblTitle);
		frmWindow.getContentPane().add(cboDays);
		frmWindow.getContentPane().add(cboWeek);
		frmWindow.getContentPane().add(cboUser);
		frmWindow.getContentPane().add(txtEventName);
		frmWindow.getContentPane().add(txtSHour);
		frmWindow.getContentPane().add(txtSMin);
		frmWindow.getContentPane().add(txtEHour);
		frmWindow.getContentPane().add(txtEMin);
		frmWindow.getContentPane().add(sp);
		frmWindow.getContentPane().add(lblCOLON);
		frmWindow.getContentPane().add(lblCOLON1);
		frmWindow.getContentPane().add(btnAddUser);
		frmWindow.getContentPane().add(btnCompare);
		frmWindow.getContentPane().add(btnAddEvent);
		frmWindow.getContentPane().add(btnShowSchedule);
		
		
		
		
		frmWindow.setVisible(true);
	}
	public static void main(String[] args) {
		ScheduleBuilderGUI window = new ScheduleBuilderGUI();
	}
}
