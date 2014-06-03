package com.service;

import java.util.ArrayList;

import com.google.gson.Gson;

public class JsonCreator {

	public String getJSON(ArrayList<Summary> summary) {
		// TODO Auto-generated method stub
		
		Gson gson = new Gson();
		String json = gson.toJson(summary);
		
		return json;
	}
	


}