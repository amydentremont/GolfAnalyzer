import java.io.FileNotFoundException;
import java.util.List;

import models.Event;
import models.Golfer;
import readers.EventsReader;
import readers.GolfersReader;
import readers.OddsAdder;

public class Executor {

  public static void main(String[] args) throws FileNotFoundException {
    List<Golfer> golfers = GolfersReader.getGolfers();
    System.out.println(String.format("Read in %d golfers", golfers.size()));

    List<Event> events = EventsReader.getEvents();
    System.out.println(String.format("Read in %d events", events.size()));

    OddsAdder.addOdds(golfers, events);
    System.out.println("Added odds for each event to golfers");
  }
}
