package models;

import java.time.ZonedDateTime;

/**
 * The World Ranking Points for each player are accumulated over a
 * two year “rolling” period with the points awarded for each tournament
 * maintained for a 13-week period to place additional
 * emphasis on recent performances.
 */
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
