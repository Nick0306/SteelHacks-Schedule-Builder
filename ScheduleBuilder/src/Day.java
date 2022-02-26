import java.util.ArrayList;

public class Day {
    
    private String day;
    private int dayOfMonth;
    private String month;
    private int year;
    private ArrayList<Event> events;

    public Day (String day, int dayOfMonth, String month, int year) {
        this.day = day;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        events = new ArrayList<Events>();
    }

    private class Event {

        private TimeFrame time;
        private String title;

        private Event (TimeFrame time, String title) {
            this.time = time;
            this.title = title;
        }
    }
}
