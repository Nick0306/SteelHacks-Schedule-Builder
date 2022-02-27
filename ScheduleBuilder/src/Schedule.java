import java.util.ArrayList;

public class Schedule {
    
    //Array of Days of the Week
    //Sunday is 0 and Saturday is 6
    public static String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private Day[] schedOfWeek = new Day[7];
    private int size = 0;
    private String title;

    public Schedule (String title) {
        for (int i = 0; i < week.length; i++)
            schedOfWeek[i] = new Day(week[i]);
        size = 7;
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

    public void add (Day day) {
        schedOfWeek[size] = day;
        size++;
    }

    public String toString() {
        String s = "";
        for (Day d: schedOfWeek) {
            s += "\n" + d.toString();
        }
        return s;
    }
    
    public boolean addEvent(String day, String eventName, double startTime, double endTime, boolean override) {
    	Day dayOfWeek = null;
    	for(Day days : schedOfWeek) {
    		if(days.getDay().equals(day)) {
    			dayOfWeek = days;
    			break;
    		}
    	}
    	
    	boolean added = dayOfWeek.addEvent(eventName, startTime, endTime, override);
    	if(added) {
    		return true;
    	}
    	return false;
    }

    public static Schedule compareDays (ArrayList<Schedule> schedules) {
        Schedule overlayed = new Schedule("Overlayed Schedule");
        boolean[] nums = new boolean[7];
        int count = 0;
        for (Schedule s: schedules) {
            nums[count] = false;
            for (Day d: s.schedOfWeek) {
                if (d.getSize() > 0) nums[count] = true;
                count++;
            }
            count = 0;
        }
		for (int k = 0; k < 7; k++) {
            if (nums[k]) {
                Day[] days = new Day[schedules.size()];
                for (int i = 0; i < schedules.size(); i++)
                    days[i] = schedules.get(i).getDay(k);
                ArrayList<Event> full = new ArrayList<Event>();
                for (int i = 0; i < days.length; i++) {
                    ArrayList<Event> events = days[i].getEvents();
                    //System.out.println(days.length);
                    for (int j = 0; j < events.size(); j++) full.add(events.get(j));
                }
                //Schedule.print(full);
                for (int i = 0; i < full.size()-1; i++) {
                    int idx = i;
                    for (int j = i+1; j < full.size(); j++) 
                        if (full.get(j).getStart() < full.get(idx).getStart())
                            idx = j;
                    Event temp = full.get(idx);
                    full.set(idx, full.get(i));
                    full.set(i, temp);
                    //Schedule.print(full);
                }
                Schedule.print(full);
                for (int i = 0; i < full.size()-1; i++) {
                    double end = full.get(i).getEnd();
                    double nextStart = full.get(i+1).getStart();
                    double nextEnd = full.get(i+1).getEnd();
                    if (end > nextStart) {
                        // next event fully enclosed (so we remove it)
                        if (end > nextEnd) {
                            full.remove(i+1);
                        // next event not fully enclosed (so we update the first event)
                        } else {
                            full.get(i).setEnd(nextEnd);
                            full.remove(i+1);
                        }
                        i--;
                    }
                    //Schedule.print(full);
                }
                //Schedule.print(full);

                overlayed.getDay(k).setEvents(full); 
            }
        }
        double start = 0;
        double end = 24;
        Schedule free = new Schedule("Free times");
        //System.out.println(overlayed.toString());

        for (int i = 0; i < free.getSchedule().length; i++) {
            ArrayList<Event> eventsOnDay = overlayed.getSchedule()[i].getEvents();
            double[] times = new double[2 * eventsOnDay.size()];
            int pos = 0;
            if (times.length > 0) {
                for (Event e: eventsOnDay) {
                    times[pos] = e.getStart();
                    times[pos+1] = e.getEnd();
                    pos+=2;
                }
                free.getDay(i).addEvent("", start, times[0], true);
                for (int j = 1; j < times.length-1; j+=2) {
                    if (times[j] != times[j+1])
                        free.getDay(i).addEvent("", times[j], times[j+1], true);
                }
                free.getDay(i).addEvent("", times[times.length-1], end, true);
            }
        }

        return free;
	}

    public static void print (ArrayList<Event> full) {
        for (Event e: full) {
            System.out.print(e.toString() + " ");
        }
        System.out.println();
    }
}
