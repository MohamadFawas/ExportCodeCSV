package com.defect.tracker.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Entity
@Getter
@Setter
public class ProjectStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String color;
  public ProjectStatus(Long id, String name, String color) {
    super();
    this.id = id;
    this.name = name;
    this.color = color;
  }
  public ProjectStatus() {
    super();
  }
public ProjectStatus(String name, String color) {
	super();
	this.name = name;
	this.color = color;
}
  
  
  
  
  
  
  
  
}
