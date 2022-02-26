import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.util.Calendar;
import java.util.List;

import org.joda.time.*;
import org.joda.time.Instant;

import biweekly.*;
import biweekly.component.VEvent;
import biweekly.util.Period;

public class readICal {
    DateTime now = DateTime.now();
    org.joda.time.DateTime weekStart;
    org.joda.time.DateTime weekEnd;
    ICalendar ical;


    public readICal(String fileName) {
        try {
            ical = Biweekly.parse(new FileReader(fileName)).first();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public readICal(String[] fileNames) {
        try {
            List<ICalendar> icals = Biweekly.parse(new FileReader(fileNames[0])).all();
            for(int i = 1; i < fileNames.length; i++) {
                 icals.add(Biweekly.parse(new FileReader(fileNames[i])).first());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWeek(DateTime now) {
        int iDayNow = now.getDayOfWeek();
        weekStart = now.minusDays((iDayNow - 1));
        weekEnd = now.plusDays(7 - iDayNow);
    }

    public ICalendar daysInterested() {
        ICalendar newICal = new ICalendar();
        for(VEvent event: this.ical.getEvents()) {
            if(!((ReadableInstant) event.getDateStart()).isBefore(this.weekStart) && !((ReadableInstant) event.getDateStart()).isAfter(this.weekEnd)) {
                newICal.addEvent(event);
            }
        }

        return newICal;

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