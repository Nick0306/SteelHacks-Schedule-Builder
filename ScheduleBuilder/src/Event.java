public class Event {

    private double start;
    private double end;
    private String title;

    public Event (double start, double end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }

    public double getStart () {
        return start;
    }

    public double getEnd () {
        return end;
    }

    public String getTitle () {
        return title;
    }

    public String toString () {
        return title + " " + start + " " + end;
    }
}
