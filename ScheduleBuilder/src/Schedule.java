import java.util.ArrayList;

public class Schedule {
    
    //Array of Days of the Week
    //Sunday is 0 and Saturday is 6
    private static String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private Day[] schedOfWeek = new Day[7];
    private String title;

    public Schedule (String title) {
        for (int i = 0; i < week.length; i++)
            schedOfWeek[i] = week[i];
        this.title = title;
    }

    public Schedule () {
        this.title = "Overlayed Schedule";
    }

    public Day getDay (int i) {
        return schedOfWeek[i];
    }
    
    public Day[] getSchedule() {
    	return schedOfWeek;
    }

    public static Schedule compareDays (ArrayList<Schedule> schedules) {
        Schedule overlayed = new Schedule();
		for (int k = 0; k < 7; k++) {
            Day[] days = new Day[schedules.size()];
            for (int i = 0; i < schedules.size(); i++)
                days[i] = schedules.get(i).getDay(i);
            ArrayList<Event> full = new ArrayList<Event>();
            for (int i = 0; i < days.length; i++) {
                ArrayList<Event> events = days[i].getEvents();
                for (int j = 0; j < events.size(); j++) full.add(events.get(j));
            }
            for (int i = 0; i < full.size()-1; i++) {
                int idx = i;
                for (int j = i+1; j < n; j++)
                    if (full.get(j).start < full.get(idx).start)
                        idx = j;
                Event temp = full.get(idx);
                full.set(idx, full.get(i));
                full.set(i, full.get(temp));
            }
            for (int i = 0; i < full.size()-1; i++) {
                double end = full.get(i).getEnd();
                double nextStart = full.get(i+1).getStart();
                double nextEnd = full.get(i+1).getEnd();
                if (end > nextStart) {
                    if (end < nextEnd) {
                        end = nextEnd;
                        full.remove(i+1);
                    }
                }
            }
            overlayed.add(new Day(full, week[k]));
        }
        return overlayed;
	}
}
