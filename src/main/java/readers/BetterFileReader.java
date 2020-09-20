package readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BetterFileReader {

  public static List<String> getLines(String fileName) throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(
        new FileReader(fileName)
    );

    return reader.lines()
        .map(BetterFileReader::sanitizeInput)
        .collect(Collectors.toList());
  }

  public static Optional<String> readOptionalString(String string) {
    if (string.equals(" ") || string.equals("  ")) {
      return Optional.empty();
    } else {
      return Optional.of(string);
    }
  }

  public static Optional<Integer> readOptionalInt(String string) {
    return readOptionalString(string).map(str -> Integer.valueOf(str));
  }

  public static Optional<Double> readOptionalDouble(String string) {
    return readOptionalString(string).map(str -> Double.valueOf(str));
  }

  /**
   * If entries in CSV are blank they are like ",," and splitter would skip them,
   * meaning "odds1,,,odds4,odds5".split(",") would come out as ["odds1","odds4","odds5"].
   *
   * We need to capture the blanks since the order of something like odds corresponds with the
   * order of the events.
   * So, here we add a space in between any instance of ",," to make it ", ,",
   * since "odds1, , ,odds4,odds5".split(",") comes out as ["odds1"," "," ","odds4","odds5"].
   *
   * Then, we use the "readOptional" methods to count " " as Optional.empty()
   */
  private static String sanitizeInput(String line) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < line.length() - 1; i++) {
      stringBuilder.append(line.charAt(i));
      if (line.charAt(i) == ',' && line.charAt(i + 1) == ',') {
        stringBuilder.append(" ");
      }
    }
    if (line.charAt(line.length() - 1) == ',') {
      stringBuilder.append(" ");
    }
    return stringBuilder.toString();
  }
}
