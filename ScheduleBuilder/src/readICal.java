import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import biweekly.*;
import biweekly.component.VEvent;

public class readICal {
    LocalDateTime now = LocalDateTime.now();


    public readICal(String fileName) {
        try {
            ICalendar ical = Biweekly.parse(new FileReader(fileName)).first();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) throws FileNotFoundException, IOException {

    //     // parse the first iCalendar object from the data stream
    //     //ICalendar ical = Biweekly.parse(new FileReader("ScheduleBuilder/src/Spring Term 2021-2022 (1).ics")).first();

    //     // or parse all objects from the data stream
    //     List<ICalendar> icals = Biweekly.parse(new FileReader("ScheduleBuilder/src/Spring Term 2021-2022 (1).ics")).all();

    //     VEvent event = icals.get(0).getEvents().get(1);
    //     String summary = event.getSummary().getValue();
    //     System.out.print(event);
    // }

}