import java.util.ArrayList;

public class Person {
    
    private Schedule[] schedules;
    private int size = 0;
    private String name;

    public Person (String name) {
        this.name = name;
    }

    public void addSchedule (Schedule sch) {
        schedules[size] = sch;
        size++;
    }

    public Schedule getSchedule (int i) {
        return schedules[i];
    }
}
