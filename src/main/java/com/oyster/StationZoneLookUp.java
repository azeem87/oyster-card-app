package com.oyster;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StationZoneLookUp {

	public static String HOLBRON = "Holborn";
	public static String ALDGATE = "Aldgate";
	public static String EARLS_COURT = "Earl’s Court";
	public static String HAMMERSMITH = "Hammersmith";
	public static String ARSENAL = "Arsenal";
	public static String WIMBLEDON = "Wimbledon";

	private static Map<String, Station> stationZoneMap;
	static {
		stationZoneMap = new HashMap<>();
		stationZoneMap.put(HOLBRON, new Station(HOLBRON, Arrays.asList(1)));
		stationZoneMap.put(ALDGATE, new Station(ALDGATE, Arrays.asList(1)));
		stationZoneMap.put(EARLS_COURT, new Station(EARLS_COURT, Arrays.asList(1, 2)));
		stationZoneMap.put(HAMMERSMITH, new Station(HAMMERSMITH, Arrays.asList(2)));
		stationZoneMap.put(ARSENAL, new Station(ARSENAL, Arrays.asList(2)));
		stationZoneMap.put(WIMBLEDON, new Station(WIMBLEDON, Arrays.asList(3)));
	}

	public static Station getStationByName(String stationName) {
		return stationZoneMap.get(stationName);
	}
}
