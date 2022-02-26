import java.util.ArrayList;

public class Day {
    
    private String day;
    private ArrayList<Event> events;

    public Day (String day, int dayOfMonth, String month, int year) {
        this.day = day;
        events = new ArrayList<Events>();
    }

    public Day (String day) {
        this.day = day;
    }

    public void addEvent (double start, double end, String title) {
        events.add(new Event(start, end, title));
    }

    // Event Inner Class
    private class Event {

        private double start;
        private double end;
        private String title;

        private Event (double start, double end, String title) {
            this.start = start;
            this.end = end;
            this.title = title;
        }
    }
}
