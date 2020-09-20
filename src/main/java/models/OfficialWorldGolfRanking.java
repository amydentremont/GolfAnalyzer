package models;

import java.time.ZonedDateTime;

public class OfficialWorldGolfRanking {

  private ZonedDateTime date;
  private int ranking;

  public OfficialWorldGolfRanking(ZonedDateTime date, int ranking) {
    this.date = date;
    this.ranking = ranking;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public int getRanking() {
    return ranking;
  }
}
