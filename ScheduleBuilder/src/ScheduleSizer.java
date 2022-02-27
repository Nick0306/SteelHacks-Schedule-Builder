public class ScheduleSizer {
    
    public int day; // value 0 - 6
    public int frameHeight;
    public int frameWidth;
    public int frameX;
    public int frameY;
    public int widthOffset;
    public int heightOffset;
    public double interval;
    public ArrayList<Schedule> schedules;

    public ScheduleSizer (int day, int frameHeight, int frameWidth, int frameX, int frameY, ArrayList<Schedule> schedules) {
        this.day = day;
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.frameX = frameX;
        this.frameY = frameY;
        this.schedules = schedules;
        interval = calcInterval();
    }

    // testing constructor


    public int calcX (int currSchedule, int width) {
        int spaces = currSchedule * widthOffset;
        int eventSpace = (currSchedule - 1) * width;
        int total = frameX + spaces + eventSpace;
        return total;
    }

    public int calcY (Schedule currSchedule, int eventsPrinted) {
        int spaces = heightOffset;
        int eventSpace = 0;
        for (int i = 0; i < eventsPrinted; i++) {
            Event e = currSchedule.getDay(i).getEvents().get(i);
            double start = e.getStart();
            double end = e.getEnd();
            eventSpace += end - start;
        }
        return frameY + spaces + eventSpace;
    }

    public int calcWidth () {
        int offsets = schedules.size() + 1;
        double totalWidth = frameWidth - offsets * widthOffset;
        return (int)(totalWidth / schedules.size());
    }

    public int calcHeight (double subInterval) {
        double pixels = (subInterval / interval) * frameHeight;
        return (int) pixels;

    }

    private int calcInterval () {
        Day[] days = new Day[schedules.size()];
        for (int i = 0; i < schedules.size(day); i++)
            days[i] = schedules.get(i).getDay();
        ArrayList<Event> full = new ArrayList<Event>();
        for (int i = 0; i < days.length; i++) {
            ArrayList<Event> events = days[i].getEvents();
            for (int j = 0; j < events.size(); j++) full.add(events.get(j));
        }
        for (int i = 0; i < full.size()-1; i++) {
            int idx = i;
            for (int j = i+1; j < full.size(); j++) 
                if (full.get(j).getStart() < full.get(idx).getStart())
                    idx = j;
            Event temp = full.get(idx);
            full.set(idx, full.get(i));
            full.set(i, temp);
        }
        double min = full.get(0).getStart();
        double max = full.get(0).getEnd();
        for (Event e: full) {
            if (e.getEnd() > max) 
                max = e.getEnd();
        }
        return (int)(max - min + heightOffset * 2);
    }
}
