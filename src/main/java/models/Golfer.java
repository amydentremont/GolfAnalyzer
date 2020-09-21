package models;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Golfer {

  private String name;
  private Optional<ZonedDateTime> birthday;
  private Optional<String> college;
  private Optional<String> state;
  private Optional<String> residence;
  private Optional<String> notes;

  private List<OfficialWorldGolfRanking> officialWorldGolfRankings;
  private Map<Event, Double> oddsByEvent;

  public Golfer(String name,
                Optional<ZonedDateTime> birthday,
                Optional<String> college,
                Optional<String> state,
                Optional<String> residence,
                Optional<String> notes) {
    this.name = name;
    this.birthday = birthday;
    this.college = college;
    this.state = state;
    this.residence = residence;
    this.notes = notes;
    this.officialWorldGolfRankings = new ArrayList<>();
    this.oddsByEvent = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public Optional<Long> getAge() {
    if (birthday.isPresent()) {
      long birthdayInEpochMilliseconds = birthday.get().toEpochSecond() * 1000;
      long ageInEpochMilliseconds = System.currentTimeMillis() - birthdayInEpochMilliseconds;
      return Optional.of(
          Duration.ofMillis(ageInEpochMilliseconds).toDays() / 365
      );
    } else {
      return Optional.empty();
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Golfer - name: " + name);
    getAge().ifPresent(age -> builder.append(", age: " + age));
    college.ifPresent(college -> builder.append(", college: " + college));
    state.ifPresent(state -> builder.append(", state: " + state));
    residence.ifPresent(residence -> builder.append(", residence: " + residence));
    notes.ifPresent(notes -> builder.append(", note: " + notes));
    return builder.toString();
  }

  public void addOfficialWorldGolfRanking(OfficialWorldGolfRanking ranking) {
    officialWorldGolfRankings.add(ranking);
  }

  public void addOdds(Event event, double odds) {
    oddsByEvent.put(event, odds);
  }

  public Map<Event, Double> getOddsByEvent() {
    return oddsByEvent;
  }
}
