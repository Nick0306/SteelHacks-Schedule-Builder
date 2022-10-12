import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.joda.time.*;
import org.joda.time.Instant;
import org.joda.time.LocalDate;

import biweekly.*;
import biweekly.component.VEvent;
import biweekly.property.DateEnd;
import biweekly.property.DateStart;
import biweekly.util.Duration;
import biweekly.util.ICalDate;
import biweekly.util.Period;
import biweekly.util.Recurrence;
import biweekly.util.com.google.ical.compat.javautil.DateIterator;

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
            //reads all calendars and stores in list
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
        weekStart = weekStart.minusHours(weekStart.getHourOfDay() - 1);
        weekEnd = now.plusDays(7 - iDayNow);
        weekEnd = weekEnd.plusHours(24 - weekEnd.getHourOfDay() - 1);
        System.out.println(weekEnd);
        System.out.println(weekStart);
    }

    public ICalendar daysInterested() {
        ICalendar newICal = new ICalendar();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        for(VEvent event: this.ical.getEvents()) {
            //System.out.println(event.getSummary().getValue());
            
            DateTime dtStart = new DateTime((Date) event.getDateStart().getValue());
            DateTime dtEnd = new DateTime((Date) event.getDateEnd().getValue());

            Duration dur = Duration.builder().hours(dtEnd.getHourOfDay() - dtStart.getHourOfDay()).minutes(dtEnd.getMinuteOfHour() - dtStart.getMinuteOfHour()).build();


            //this gets rid of all events before, need to take into account recurring events
            if(!dtStart.isBefore(this.weekStart) && !dtStart.isAfter(this.weekEnd)) {
                newICal.addEvent(event);
                //System.out.println(event.getSummary().getValue());
            } else {
                //recurring events have recurrenceRule
                //TODO: Set event time, currently initalizes to 0:00
                if(event.getRecurrenceRule() != null) {
                    //Get an iterator that iterates through list of dates
                    DateIterator iterator = event.getRecurrenceRule().getDateIterator(event.getDateStart().getValue(), TimeZone.getDefault());
                    //skip to date concerned with
                    iterator.advanceTo(this.weekStart.toDate());
                    //iterator isn't empty
                    while(iterator.hasNext()) {
                        //get date
                        Date startDate = iterator.next();
                        //append time to Date
                        startDate.setHours(event.getDateStart().getValue().getHours());
                        startDate.setMinutes(event.getDateStart().getValue().getMinutes());
            
                        //stop loop if iterating date is out of week interested in
                        if(startDate.compareTo(weekEnd.toDate()) > 0) {
                            break;
                        }
                        
                        Date endDate = dur.add(startDate);
                        //makes a deep copy of event
                        VEvent recurEvent = new VEvent(event.copy());
                        //System.out.println("Should have date and time: " + startDate);
                        //System.out.println("What time is End Date: " + dtEnd);
                        recurEvent.setDateStart(startDate);
                        recurEvent.setDateEnd(endDate);
                        //have to set make a variable and set it or else will give ambiguity error
                        Recurrence x = null;
                        recurEvent.setRecurrenceRule(x);
                        newICal.addEvent(recurEvent);
                    }
                     
                }
                
            }
        
            //System.out.println((Date) event.getDateStart().getValue());
        }

        return newICal;

    }

    public Schedule setEventsToDays(ICalendar newICal) {
        Schedule schedule = new Schedule("Person 1");
        int count = 0;
        for(VEvent event : newICal.getEvents()) {
            //System.out.println(event.getUid());
            DateStart eventStart = event.getDateStart();
            DateEnd eventEnd = event.getDateEnd();

            if(eventStart != null) {
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                
                DateTime start = new DateTime((Date) event.getDateStart().getValue());
                int startHours = start.getHourOfDay();
                double startMins = start.getMinuteOfHour();
                DateTime end = new DateTime((Date) event.getDateEnd().getValue());
                int endHours = end.getHourOfDay();
                double endMins = end.getMinuteOfHour();
                double doubleStart = startHours + (startMins / 60.0);
                double doubleEnd = endHours + (endMins / 60.0);
                int iDayNow = start.getDayOfWeek();
                String dayString = "";
                if(iDayNow == 1) {
                    dayString = "Monday";
                } else if (iDayNow == 2) {
                    dayString = "Tuesday";
                } else if (iDayNow == 3) {
                    dayString = "Wednesday";
                } else if (iDayNow == 4) {
                    dayString = "Thursday";
                } else if (iDayNow == 5) {
                    dayString = "Friday";
                } else if (iDayNow == 6) {
                    dayString = "Saturday";
                } else if (iDayNow == 7) {
                    dayString = "Sunday";
                }
                
                schedule.addEvent(dayString, event.getSummary().getValue(), doubleStart, doubleEnd, true);
                
                //DateTime dt = new DateTime(df);
                //schedule.getDay(count).addEvent()
            }
            
        }

        return schedule;
    }

    public static Schedule importICS(String filePath, int week) {
        DateTime now = DateTime.now(); 
        now = now.plusDays(week * 7); //offset to which week of schedule
        readICal cal = new readICal(filePath);
        cal.setWeek(now);
        Schedule P1 = cal.setEventsToDays(cal.daysInterested());
        return P1;
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