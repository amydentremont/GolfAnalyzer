package readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Golfer;
import models.OfficialWorldGolfRanking;

public class GolfersReader {

  public static List<Golfer> getGolfers() throws FileNotFoundException {
    List<String> lines = BetterFileReader.getLines("src/main/resources/golfers.csv");

    List<ZonedDateTime> owgrDates = Arrays.stream(lines.get(0).split(","))
        .map(dateString -> toZonedDateTime(dateString, "\\.", "20"))
        .collect(Collectors.toList());

    List<String> golferLines = lines.subList(1, lines.size());
    return golferLines.stream()
        .map(line -> toGolfer(line, owgrDates))
        .collect(Collectors.toList());
  }

  private static Golfer toGolfer(String line, List<ZonedDateTime> owgrDates) {
    String[] entries = line.split(",");

    String name = entries[0];
    Optional<String> birthdayString = BetterFileReader.readOptionalString(entries[1]);
    Optional<ZonedDateTime> birthday = birthdayString.map(string -> toZonedDateTime(string, "/", "19"));
    Optional<String> notes = BetterFileReader.readOptionalString(entries[10]);
    Optional<String> college = BetterFileReader.readOptionalString(entries[11]);
    Optional<String> state = BetterFileReader.readOptionalString(entries[12]);
    Optional<String> residence;
    if (entries.length == 14) {
      residence = BetterFileReader.readOptionalString(entries[13]);
    } else {
      residence = Optional.empty();
    }

    Golfer golfer = new Golfer(
          name,
          birthday,
          college,
          state,
          residence,
          notes
      );

    for (int i = 0; i < 8; i++) {
      String ranking = entries[i + 2];
      if (ranking.equals(" ")) {
        continue;
      }
      golfer.addOfficialWorldGolfRanking(
          new OfficialWorldGolfRanking(owgrDates.get(i), Integer.valueOf(ranking))
      );
    }
    return golfer;
  }

  private static ZonedDateTime toZonedDateTime(String dateAsString, String regex, String century) {
    String[] parsed = dateAsString.split(regex);
    int month = Integer.valueOf(parsed[0]);
    int day = Integer.valueOf(parsed[1]);
    int year = Integer.valueOf(century + parsed[2]);
    return ZonedDateTime.of(
        year,
        month,
        day,
        0,
        0,
        0,
        0,
        ZoneId.systemDefault()
    );
  }
}
