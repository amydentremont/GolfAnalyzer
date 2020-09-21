package models;

import java.util.Optional;

public class Event {

  private final String name;
  private final Optional<Integer> year;

  /**
   * The Strength of Field for each tours event is determined
   * by using the World Rating and Home Tour Rating.
   *
   * The World Rating is based on the number of Top-200 World Ranked players
   * competing in the event and a value is allocated to the position within the Top 200.
   *
   * The Home Tour Rating is based on the number of Top-30 Ranked players using
   * each tours end of year final ranking with a value allocated to the position
   * within the Top 30 Ranked players.
   * Exception - Web.com Tour Home Tour Rating is based on the number of Top-30
   *             Ranked players prior to the Hybrid Events.
   *
   * "Strength of Field: Whom did the player compete against and
   * how strong were the records of the their competitors (25 percent).
   */
  private final Optional<Integer> strengthOfField;
  private final Optional<Course> course;

  public Event(String name, Optional<Integer> year, Optional<Integer> strengthOfField) {
    this.name = name;
    this.year = year;
    this.strengthOfField = strengthOfField;
    this.course = Optional.empty();
  }

  public String getName() {
    return name;
  }

  public Optional<Integer> getYear() {
    return year;
  }

  public Optional<Integer> getStrengthOfField() {
    return strengthOfField;
  }
}
