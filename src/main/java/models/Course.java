package models;

import java.util.Optional;

public class Course {

  private String name;
  private String location;
  private Optional<GrassType> grassType;
  private Optional<String> designer;
  private Optional<Integer> yards;
  private Optional<Integer> par;
}
