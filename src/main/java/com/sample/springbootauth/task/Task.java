package com.sample.springbootauth.task;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "tasks")
public class Task {

  @Id
  private String id = UUID.randomUUID().toString();
  private String description;

  protected Task() {
  }

  public Task(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}