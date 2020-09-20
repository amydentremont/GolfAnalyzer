package readers;

import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import models.Event;
import models.Golfer;

public class OddsAdder {

  public static List<Golfer> addOdds(List<Golfer> golfers, List<Event> events) throws FileNotFoundException {
    List<String> lines = BetterFileReader.getLines("src/main/resources/odds.csv");

    Map<String, Golfer> golfersByName = golfers.stream()
        .collect(Collectors.toMap(
            Golfer::getName,
            Function.identity()
        ));

    for (String line : lines) {
      String[] parsed = line.split(",");
      String name = parsed[0];
      Golfer golfer = golfersByName.get(name);
      if (golfer == null) {

      }

      for (int i = 0; i < events.size(); i++) {
        Optional<Double> odds;
        if (parsed.length > (i + 1)) {
          odds = BetterFileReader.readOptionalDouble(parsed[i + 1]);
        } else {
          odds = Optional.empty();
        }

        if (odds.isPresent()) {
          golfer.addOdds(events.get(i), odds.get());
        }
      }
    }
    return golfers;
  }
}
