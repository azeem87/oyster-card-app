package com.oyster;

import java.util.List;
import java.util.function.BiFunction;

public class TripCharger {

	private double max_fare_charge = 3.20;
	private double bus_fare_charge = 1.80;

	public void beginTubeJourney(final Station fromStation, OysterCard oysterCard) throws InSufficientFundException {
		oysterCard.setStartStation(fromStation);
		oysterCard.amountToDebit(max_fare_charge);
	}

	public void commuteByBus(final OysterCard oysterCard) throws InSufficientFundException {
		oysterCard.amountToDebit(bus_fare_charge);
	}

	public void exitTubeJourney(final Station toStation, final OysterCard oysterCard) throws InSufficientFundException {
		oysterCard.setEndStation(toStation);
		oysterCard.amountToCredit(max_fare_charge);

		Integer numberOfZonesVisited = calculateNumberOfZonesTravelled(oysterCard);
		boolean hasZoneOneVisited = hasZoneOneTravelled(oysterCard);

		Double zoneChargeToDebit = Math.min(
				ZoneChargePredicates.getTravelledChargeBetweenZones(numberOfZonesVisited, hasZoneOneVisited), max_fare_charge);

		oysterCard.amountToDebit(zoneChargeToDebit);
	}

	private Integer calculateNumberOfZonesTravelled(final OysterCard oysterCard) {
		Integer minZonesTravelled = Integer.MAX_VALUE;
		final List<Integer> fromStationZones = oysterCard.getStartStation().getZones();
		final List<Integer> toStationZones = oysterCard.getEndStation().getZones();

		for (Integer fromZone : fromStationZones) {
			for (Integer toZone : toStationZones) {
				int zonesVisited = Math.abs(toZone - fromZone) + 1;
				minZonesTravelled = Math.min(minZonesTravelled,zonesVisited);
			}
		}
		return minZonesTravelled;
	}

	private boolean hasZoneOneTravelled(final OysterCard oysterCard) {
		List<Integer> fromStationZones = oysterCard.getStartStation().getZones();
		List<Integer> toStationZones = oysterCard.getEndStation().getZones();

		if (fromStationZones.size() == 1 && fromStationZones.contains(1)) {
			return true;
		}

		if (toStationZones.size() == 1 && toStationZones.contains(1)) {
			return true;
		}

		return false;
	}

}

class ZoneChargerComputeService {

	static double computeAnyWhereZone1(Integer numberOfZonesVisited, Boolean hasZoneOneVisited) {
		if (numberOfZonesVisited == 1 && hasZoneOneVisited) {
			//System.out.println("Only Zone1");
			return 2.50;
		}
		return -1;
	}

	static double computeAny1ZoneOutSideZone1(Integer numberOfZonesVisited, Boolean hasZoneOneVisited) {
		if (numberOfZonesVisited == 1 && !hasZoneOneVisited) {
			//System.out.println("Any1 Zone Outside Zone1");
			return 2.0;
		}
		return -1;
	}

	static double computeAnyTwoZonesIncludingZone1(Integer numberOfZonesVisited, Boolean hasZoneOneVisited) {
		if (numberOfZonesVisited == 2 && hasZoneOneVisited) {
			//System.out.println("Any2 Zone Including Zone1");
			return 3.0;
		}
		return -1;
	}

	static double computeAnyTwoZonesExcludingZone1(Integer numberOfZonesVisited, Boolean hasZoneOneVisited) {
		if (numberOfZonesVisited == 2 && !hasZoneOneVisited) {
			//System.out.println("Any2 Zone Excluding Zone1");
			return 2.25;
		}
		return -1;
	}

	static double computeMoreThanTwoZones(Integer numberOfZonesVisited, Boolean hasZoneOneVisited) {
		if (numberOfZonesVisited >= 3) {
			//System.out.println("More than 2 zones");
			return 3.20;
		}
		return -1;
	}
}

enum ZoneChargePredicates {
	ANY_WHERE_ZONE_1(ZoneChargerComputeService::computeAnyWhereZone1),
	ANY_1_ZONE_OUTSIDE_ZONE_1(ZoneChargerComputeService::computeAny1ZoneOutSideZone1),
	ANY_TWO_ZONES_INCLUDING_ZONE_1(ZoneChargerComputeService::computeAnyTwoZonesIncludingZone1),
	ANY_TWO_ZONES_EXCLUDING_ZONE_1(ZoneChargerComputeService::computeAnyTwoZonesExcludingZone1),
	MORE_THAN_TWO_ZONES_EXCLUDING_ZONE_1(ZoneChargerComputeService::computeMoreThanTwoZones);

	public final BiFunction<Integer, Boolean, Double> zoneAlgo;

	ZoneChargePredicates(BiFunction<Integer, Boolean, Double> zoneAlgo) {
		this.zoneAlgo = zoneAlgo;
	}

	public static Double getTravelledChargeBetweenZones(Integer numberOfZonesTravelled, Boolean hasZoneOneVisited) {
		for (ZoneChargePredicates v : values()) {

			Double chargeToDebit = v.zoneAlgo.apply(numberOfZonesTravelled, hasZoneOneVisited);

			if (chargeToDebit != -1) {
				return chargeToDebit;
			}
		}
		return 0.0;
	}
}