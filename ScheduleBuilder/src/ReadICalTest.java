import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import biweekly.*;
import biweekly.component.VEvent;

public class ReadICalTest{

    public static void main (String [] args) throws FileNotFoundException, IOException{

        DateTime now = DateTime.now();
        //readICal nCal = new readICal("ScheduleBuilder/src/Spring Term 2021-2022 (1).ics");
        readICal nCal = new readICal("ScheduleBuilder/src/stevenrj2@gmail.com.ics");
        readICal mCal = new readICal("ScheduleBuilder/src/srj42@pitt.edu.ics");

        nCal.setWeek(now);
        mCal.setWeek(now);
        //System.out.println(nCal.weekEnd);
        //System.out.println(nCal.weekStart);
        //System.out.println();
        //ICalendar ical = nCal.daysInterested();
        Schedule P1 = nCal.setEventsToDays(nCal.daysInterested());
        Schedule P2 = mCal.setEventsToDays(mCal.daysInterested());
        ArrayList<Schedule> P3 = new ArrayList<Schedule>();
        P3.add(P1);
        P3.add(P2);
        Schedule P4 = Schedule.compareDays(P3);
        P4.toString();
        //P1.schedOfWeek[0]

        
    }
}
