package com.example.training.rest.simpleclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class TodoData {
	
	private int userId;
	private int id;
	private String title;
	private boolean completed;
	
}
