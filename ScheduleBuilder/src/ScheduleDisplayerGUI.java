import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.util.ArrayList;

public class ScheduleDisplayerGUI{
	ArrayList<JLabel> components = new ArrayList<JLabel>();
	ArrayList<Person> users = new ArrayList<Person>();
	public JFrame frame;
	
	public ScheduleDisplayerGUI(ArrayList<Person> users, int week, int day) {
		frame = new JFrame("Schedule Displayer");
		frame.setBounds(100,100,620,520);
		frame.getContentPane().setLayout(null);
		
		
		
		this.users = users;
		
		ArrayList<Day> days = new ArrayList<Day>();
		for(int i = 0; i < users.size(); i++){
			Schedule schedule = users.get(i).getSchedule(week);
			for(Event events : schedule.getDay(day).getEvents()) {
				components.add(new JLabel());
			}
		}
		
		
		for(int i = 0; i < components.size(); i ++) {
			JLabel x = components.get(i);
			x.setText(i+"");
			x.setBounds(50*i, 50*i, 50, 50);
			frame.getContentPane().add(x);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
	}
	
	public void setUsers(ArrayList<Person> users) {
		this.users = users;
	}
}
