package readers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Event;

public class EventsReader {

  public static List<Event> getEvents() throws FileNotFoundException {
    List<String> lines = BetterFileReader.getLines("src/main/resources/events.csv");

    List<Optional<Integer>> strengthOfFieldNumbers = Arrays.stream(lines.get(0).split(","))
        .map(sof -> BetterFileReader.readOptionalInt(sof))
        .collect(Collectors.toList());

    List<Event> events = new ArrayList<>();
    String[] eventsAsStrings = lines.get(1).split(",");
    for (int i = 0; i < eventsAsStrings.length; i++) {
      String eventString = eventsAsStrings[i];
      String[] parsed = eventString.split(" 2");
      String eventName = parsed[0];
      Optional<Integer> eventYear;
      if (parsed.length == 2) {
        eventYear = Optional.of(Integer.valueOf("2" + parsed[1]));
      } else {
        eventYear = Optional.empty();
      }
      Optional<Integer> strengthOfFieldNumber;
      if (strengthOfFieldNumbers.size() > i) {
        strengthOfFieldNumber = strengthOfFieldNumbers.get(i);
      } else {
        strengthOfFieldNumber = Optional.empty();
      }

      events.add(new Event(eventName, eventYear, strengthOfFieldNumber));
    }
    return events;
  }
}
