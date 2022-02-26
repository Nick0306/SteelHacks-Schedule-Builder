import java.util.ArrayList;

public class Day {
    
    private String day;
    private ArrayList<Event> events;

    public Day (String day) {
        this.day = day;
        events = new ArrayList<Event>();
    }

    public Day (ArrayList<Event> events, String day) {
        this.events = events;
        this.day = day;
    }

    public void addEvent (double start, double end, String title) {
        events.add(new Event(start, end, title));
    }

    public ArrayList<Event> getEvents () {
        return events;
    }

    
}
