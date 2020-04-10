package com.example.springbootdemos.async;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoData {
	
	private int userId;
	private int id;
	private String title;
	private boolean completed;
	
}
