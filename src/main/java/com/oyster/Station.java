package com.oyster;

import java.util.List;

public class Station {
	
	private String name;
	private final List<Integer> zones;
	
	public Station(String name,List<Integer> zones) {
		this.name = name;
		this.zones = zones;
	}
	
	public final List<Integer> getZones() {
		return zones;
	}
	
	public String getName() {
		return this.name;
	}

}
