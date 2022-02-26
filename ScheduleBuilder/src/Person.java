import java.util.ArrayList;

public class Person {
    
    private Schedule[] schedules = new Schedule[4];
    private int size = 0;
    private String name;

    public Person (String name) {
        this.name = name;
    }
    
    public void addEvent(int week, String day, String eventName, double startTime, double endTime) {
    	Schedule schedule = schedules[week];
    	schedule.addEvent(day, eventName, startTime, endTime);
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
