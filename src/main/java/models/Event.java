package models;

import java.util.Optional;

public class Event {

  private final String name;
  private final Optional<Integer> year;
  private final Optional<Integer> strengthOfField;

  public Event(String name, Optional<Integer> year, Optional<Integer> strengthOfField) {
    this.name = name;
    this.year = year;
    this.strengthOfField = strengthOfField;
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
