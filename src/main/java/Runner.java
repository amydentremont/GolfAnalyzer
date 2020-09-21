import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Event;
import models.Golfer;
import readers.EventsReader;
import readers.GolfersReader;
import readers.OddsAdder;

public class Runner {

  public static void main(String[] args) throws FileNotFoundException {
    List<Golfer> golfers = GolfersReader.getGolfers();
    System.out.println(String.format("Read in %d golfers", golfers.size()));

    List<Event> events = EventsReader.getEvents();
    System.out.println(String.format("Read in %d events", events.size()));

    OddsAdder.addOdds(golfers, events);
    System.out.println("Added odds for each event to golfers");

    Map<Golfer, Double> averageOddsByGolfer = new HashMap<>();
    for (Golfer golfer : golfers) {
      double averageOdds = golfer.getOddsByEvent().values().stream()
          .mapToDouble(a -> a)
          .average().orElse(0);
      averageOddsByGolfer.put(golfer, averageOdds);
    }
    System.out.println("Calculated simple average odds per player");
  }
}
