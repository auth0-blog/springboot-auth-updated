package com.sample.springbootauth.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
  @Id
  @JsonIgnore
  private long id;
  private String description;

  protected Task() {
  }

  public Task(String description) {
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}