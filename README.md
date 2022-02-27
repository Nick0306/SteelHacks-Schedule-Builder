# SteelHacks
## Schedule Builder README
Please read before running in order to best use our project.

## Project Motivation
As college students, we are constantly busy with attending class, studying, and attending 
office hours; it is difficult for us to coordinate activities with friends during free
time. Planning to meet with others can become cumbersome when you factor in everyone's limited
and staggered free time. Whether the event is a quick lunch date or time consuming like visiting
a museum together, the only way to guarantee hanging out with others is to plan for it ahead of 
time. That's where our project comes in. Our version of a schedule builder allows multiple people 
to input their schedules for the week and collectively determine when they have free time in common.

## Project Description
Our schedule builder enables a user to enter events for many people into the user-friendly user
interface. The application prompts the user for the person's name, the week of the month, the day
of the week, the event title, the event start time, and the event end time. Alternatively the user
can choose to upload their calender as a .ics file from any online calendar, and the events will be
converted into objects in our program. Once the user is done submitting all of the events, they will 
click the compare button. The compare button is what runs our algorithm and determines the 
availabilities across all of the schedules selected, where it is then displayed back to the user. 
Our project also has the ability  to display the schedule of the selected person to the user, so they
can use it as a weekly schedule too.

To create our project, we used two different programs to best implement our ideas. First we made 
multiple java classes to hold attributes for the event, the day of the week, and the schedule. 
These classes not only hold the attributes, but they also hold the all of the logic for inputting
events, and comparing schedules. We also used WindowBuilder to create Java GUI application to 
allow the user to interact with our program. The GUI prompts the user with all of the proper 
fields to fully utilize our program and come up with the result that they are looking for

The first challenge was being able to write an algorithm that allows us to compare events on two 
schedule , and returns the common availabilities across the schedules. Although it was difficult,
we were able to overcome this obstacle, and even implement the functionality two compare an
arbitrary number of schedules and return the common availabilities to the user. 

One of the main challenges that we faced when writing our program was finding a way to import the
.ics files and interpret it in the same way we would if every even was entered manually. We were
able to solve this by parsing the calendar into each event that only held the start time, the 
end time, and the title of the event. This allowed us to compare each event from the calendar
using our algorithm so the free times and schedule could be displayed to the user.

A major challenge that we came across was in relation to interpreting recurring events
from the .ics. Recurring events are slightly different way than the non-recurring events, and
require a completely different approach for parsing the file. We were unable to find a solution
to this problem due to our time limit for the Hackathon, but we inted to implement that 
functionality in the future.

We also intended to add the ability for schedule builder to display a person's schedule in 
calendar view. We were unable to implemet this feature due to time constraints, however, we
plan to add it in the future.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` 
and update the related settings there.

## Running Our Project

Part1: Compiling

The user should be able to compile and run our program from the command line.
In order to run the project, you will have to run this special command for each of the four .jar
libraries that were implemented in our project. The command prompt follows this structure:

javac -cp "/Path/to/project/SteelHacks/Reference Libraries/biweekly-0.6.6.jar:/Path/to/project/SteelHacks/Reference Libraries/jackson-core-2.12.1.jar://Path/to/project/SteelHacks/Reference Libraries/joda-time-2.10.13.jar:/Path/to/project/SteelHacks/Reference Libraries/vinnie-2.0.2.jar:" ScheduleBuilderGUI.java

By Entering this command into the command line, the user will reference the libraries that are 
required in order to take in the .ics files and integrate them into the Schedule Builder. Without 
these files the program will still compile and run, however the user will not be able to import
.ics from their online calendars into the program.

Part 2: Running

To run our project you will need access to src, where all of the class files are stored,
including the "ScheduleBuilderGUI.java". The user will compile and run "ScheduleBuilderGUI.java" 
in terminal, and then and they can use the program freely from there.  Once all events and 
calendars are imported, either manually or via the import button (See Part 3), the user can 
click the "Show Schedule" button which will display all events for the selected person, separated
by day. The user's other option is the aforementioned compare button which will return the common
free times between all events.

Part 3: Importing .ics files

Our program has the functionality to read an exported .ics file and run import it into the 
Schedule Builder while it is running. Any .ics will work in the ScheduleBuilder such as 
Apple iCal, Google Calendar, and Mozilla Lightning, as long as the schedule imported does 
not contain any recurring events. (See Project Description P4 for details). In order to 
properly import a .ics file, the user will need to copy and paste the relative path of the
.ics and paste it into the the field  when it prompts the user to do so. To see if the 
.ics schedule was imported correctly, the user should click the "Show Schedule" button.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here]
(https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Credits
Contributors:

Nick Usme || github: Nick0306 || Discord: NICKelodeon#8368

Toby Beidler-Shenk || github: tbeidlershenk || Discord: tobeee#2848

Jeff Zheng || github: jeffzheng13 || Discord: 21zhengj#6553

Steve Johns || github: srj42 || Discord: Svets#4987

Visit License.md for licensing information.
