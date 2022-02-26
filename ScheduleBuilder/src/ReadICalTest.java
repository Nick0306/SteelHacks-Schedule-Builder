import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import biweekly.*;
import biweekly.component.VEvent;

public class ReadICalTest{

    public static void main (String [] args) throws FileNotFoundException, IOException{

        ICalendar ical = Biweekly.parse(new FileReader("ScheduleBuilder/src/Spring Term 2021-2022 (1).ics")).first();
//        LocalDateTime now = LocalDatetime.now();

        VEvent event = icals.get(0).getEvents().get(1);
        String summary = event.getSummary().getValue();
        System.out.print(event);
    //    System.out.println(now);
    }
}
