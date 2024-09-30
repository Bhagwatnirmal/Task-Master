package com.ToDo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String details;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", details=" + details + "]";
	}
	public Task(int id, String details) {
		super();
		this.id = id;
		this.details = details;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
