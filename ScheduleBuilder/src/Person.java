import java.util.ArrayList;

public class Person {
    
    private Schedule[] schedules = new Schedule[4];
    private int size = 0;
    private String name;

    public Person (String name) {
        this.name = name;
    }
    
    public boolean addEvent(int week, String day, String eventName, double startTime, double endTime) {
    	Schedule schedule = schedules[week];
    	boolean added = schedule.addEvent(day, eventName, startTime, endTime);
    	if(added) {
    		return true;
    	}
    	return false;
    }
    
    
    /*
    public void addSchedule (Schedule sch) {
        schedules[size] = sch;
        size++;
    }
    */

    public Schedule getSchedule (int i) {
        return schedules[i];
    }
    
    public String getName() {
    	return name;
    }
}
