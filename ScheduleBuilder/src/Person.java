import java.util.ArrayList;

public class Person {
    
    private ArrayList<Schedule> schedules;
    private String name;

    public Person (String name) {
        this.name = name;
    }

    public void addSchedule (Schedule sch) {
        schedules.add(sch);
    }
}
