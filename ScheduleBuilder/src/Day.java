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

    public void addEvent (double start, double end) {

    }

    // Event Inner Class
    private class Event {

        private TimeFrame time;
        private String title;

        private Event (TimeFrame time, String title) {
            this.time = time;
            this.title = title;
        }
    }
}
