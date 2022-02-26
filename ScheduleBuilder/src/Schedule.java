public class Schedule {
    
    //Array of Days of the Week
    //Sunday is 0 and Saturday is 6
    private Day[] schedOfWeek = new Day[7];
    private String title;

    public Schedule (String title) {
        schedOfWeek[0] = new Day("Sunday");
        schedOfWeek[1] = new Day("Monday");
        schedOfWeek[2] = new Day("Tuesday");
        schedOfWeek[3] = new Day("Wednesday");
        schedOfWeek[4] = new Day("Thursday");
        schedOfWeek[5] = new Day("Friday");
        schedOfWeek[6] = new Day("Saturday");
        this.title = title;
    }


}
